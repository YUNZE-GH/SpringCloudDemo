<template>
    <div class="hello">
        <el-button type="primary" @click="btnClick">主要按钮</el-button>
        <br>
        {{info}}
        <br>
        <el-button type="primary" @click="btnClick2">主要按钮2</el-button>
        <br>
        <br>
        <el-button type="primary" @click="createWebSocket">创建连接</el-button>
        <el-button type="primary" @click="closeWebSocket">关闭连接</el-button>
        <br>
        <el-divider></el-divider>
        <div>
            {{message}}
        </div>
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="用户">
                <el-select v-model="form.username" placeholder="请选择用户">
                    <el-option v-for="item in options" :key="item.username" :label="item.username" :value="item.username"></el-option>
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
        name: "HelloWorld",
        data() {
            return {
                msg: "Welcome to Your Vue.js App",
                info: null,
                options: [{ username: "张三" }, { username: "李四" }],
                form: {
                    content: null,
                    username: null
                },
                webSocket: null,
                commWebSocket: null,
                message: ""
            };
        },
        mounted: function() {

        },
        methods: {
            btnClick: function() {
                let url1 = 'https://api.coindesk.com/v1/bpi/currentprice.json';
                let url2 = '/api/test';
                this.$axios.get(url2).then(response => {
                    this.info = response;
                    this.$message.success({ message: "查询成功！", center: true });
                })
            },
            btnClick2: function() {
                this.$common.test();
            },
            createWebSocket: function() {
                if ("WebSocket" in window) {
                    webSocket = new WebSocket("/websocket/websocket/" + this.form.username);
                    // 连通之后的回调事件
                    webSocket.onopen = function() {
                        //webSocket.send( document.getElementById('username').value+"已经上线了");
                        console.log("已经连通了websocket");
                        setMessageInnerHTML("已经连通了websocket");
                    };

                    // 接收后台服务端的消息
                    webSocket.onmessage = function(evt) {
                        var received_msg = evt.data;
                        console.log("数据已接收:" + received_msg);
                        var obj = JSON.parse(received_msg);
                        console.log("可以解析成json:" + obj.messageType);
                        // 1代表上线 2代表下线 3代表在线名单 4代表普通消息
                        if (obj.messageType == 1) {
                            // 把名称放入到selection当中供选择
                            var onlineName = obj.username;
                            var option = { username: onlineName };
                            this.options.push(option)
                            setMessageInnerHTML(onlineName + "上线了");
                        } else if (obj.messageType == 2) {
                            $("#onLineUser").empty();
                            var onlineName = obj.onlineUsers;
                            var offlineName = obj.username;
                            var option = { username: "--所有--" };
                            for (var i = 0; i < onlineName.length; i++) {
                                if (!(onlineName[i] == this.form.username)) {
                                    option = { username: onlineName[i] };

                                }
                            }
                            this.options.push(option);
                            setMessageInnerHTML(offlineName + "下线了");
                        } else if (obj.messageType == 3) {
                            var onlineName = obj.onlineUsers;
                            var option = null;
                            for (var i = 0; i < onlineName.length; i++) {
                                if (!(onlineName[i] == document.getElementById('username').value)) {
                                    option = { username: onlineName[i] };
                                }
                            }
                            this.options.push(option);
                            console.log("获取了在线的名单" + onlineName.toString());
                        } else {
                            setMessageInnerHTML(obj.fromusername + "对" + obj.tousername + "说：" + obj.textMessage);
                        }
                    }
                }
            },
            // 关闭websocket连接
            closeWebSocket: function() {
                // 直接关闭websocket的连接
                webSocket.close();
            },
            send: function() {
                this.$message.success({ message: this.form.content, center: true });
                this.message += this.form.content;

                var selectText = this.form.username;
                if (selectText == "--所有--") {
                    selectText = "All";
                } else {
                    setMessageInnerHTML(this.form.username + "对" + selectText + "说：" + $("#text").val());
                }
                var message = {
                    "message": document.getElementById('text').value,
                    "username": document.getElementById('username').value,
                    "to": selectText
                };
                webSocket.send(JSON.stringify(message));
                this.form.content = "";
            },
            setMessageInnerHTML: function(innerHTML) {
                this.message += this.message + "</br>";
            }
        }
    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>