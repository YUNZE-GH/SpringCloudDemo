<template>
    <div class="weChat">
        <el-tag>{{ username }}</el-tag>
        <router-link :to="{ name: 'Login' }">
            <el-button type="primary" @click="closeWebSocket" size="mini">退出聊天室</el-button>
        </router-link>
        <el-divider></el-divider>
        <div>
            {{ message }}
        </div>
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="在线用户">
                <el-select v-model="form.username" placeholder="请选择聊天用户">
                    <el-option v-for="item in options" :key="item.username" :label="item.username"
                        :value="item.username">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="内容">
                <el-input v-model="form.content"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="send">发送</el-button>
    </div>
</template>

<script>
    export default {
        name: "WebWeChat",
        data() {
            return {
                options: [{
                    username: "--所有--"
                }],
                form: {
                    content: null,
                    username: null, // 信息发送对像
                },
                username: "", // 登录用户
                webSocket: null,
                commWebSocket: null,
                message: "",
            };
        },
        mounted() {
            let that = this;
            this.username = this.$route.params.username != null ? this.$route.params.username : "登录用户";
            if ("WebSocket" in window) {
                this.webSocket = new WebSocket(
                    "ws://localhost:9020/websocket/" + this.username
                );
                // 连通之后的回调事件
                this.webSocket.onopen = function () {
                    // webSocket.send( document.getElementById('username').value+"已经上线了");
                    console.log("已经连通了websocket");
                    that.setMessageInnerHTML("已经连通了websocket");
                };

                // 接收后台服务端的消息
                this.webSocket.onmessage = function (evt) {
                    var received_msg = evt.data;
                    console.log("数据已接收:" + received_msg);
                    var obj = JSON.parse(received_msg);
                    console.log("可以解析成json:" + obj.messageType);
                    // 1代表上线 2代表下线 3代表在线名单 4代表普通消息
                    if (obj.messageType == 1) {
                        // 把名称放入到selection当中供选择
                        var onlineName = obj.username;
                        var option = {
                            username: onlineName
                        };
                        that.options.push(option);
                        that.setMessageInnerHTML(onlineName + "上线了");
                    } else if (obj.messageType == 2) {
                        that.options = [{
                            username: "--所有--"
                        }];
                        var onlineName = obj.onlineUsers;
                        var offlineName = obj.username;
                        for (var i = 0; i < onlineName.length; i++) {
                            if (!(onlineName[i] == that.form.username)) {
                                var option = {
                                    username: onlineName[i],
                                };
                                that.options.push(option);
                            }
                        }
                        that.setMessageInnerHTML(offlineName + "下线了");
                    } else if (obj.messageType == 3) {
                        var onlineName = obj.onlineUsers;
                        for (var i = 0; i < onlineName.length; i++) {
                            if (!(onlineName[i] == that.username)) {
                                var option = {
                                    username: onlineName[i],
                                };
                                that.options.push(option);
                            }
                        }
                        console.log("获取了在线的名单" + onlineName.toString());
                    } else {
                        that.setMessageInnerHTML(
                            obj.fromusername +
                            "对" +
                            obj.tousername +
                            "说：" +
                            obj.textMessage
                        );
                    }
                };
            }
        },
        methods: {
            // 关闭websocket连接
            closeWebSocket: function () {
                this.$message.success({
                    message: "退出聊天室成功！",
                    center: true,
                });
                // 直接关闭websocket的连接
                this.webSocket.close();
            },
            send: function () {
                this.$message.success({
                    message: this.form.content,
                    center: true,
                });
                this.message += this.form.content;

                var selectText = this.form.username;
                if (selectText == "--所有--") {
                    selectText = "All";
                } else {
                    this.setMessageInnerHTML(
                        this.form.username +
                        "对" +
                        selectText +
                        "说：" +
                        this.form.content
                    );
                }
                var message = {
                    message: this.form.content, // 发送消息内容
                    username: this.username,    // 信息发送人
                    to: selectText,             // 信息接收人
                };
                this.webSocket.send(JSON.stringify(message));
                this.form.content = "";
            },
            setMessageInnerHTML: function (innerHTML) {
                this.message += innerHTML + "、";
            }
        },
    };

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
