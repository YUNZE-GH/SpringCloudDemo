<template>
    <div class="hello">
        <el-button type="primary" @click="btnClick">主要按钮</el-button>
        <br />
        {{ info }}
        <br />
        <el-button type="primary" @click="btnClick2">主要按钮2</el-button>
        <br />
        <br />
        <el-divider></el-divider>
        <el-button type="primary" @click="btnClick3">创建连接</el-button>
        <br />
    </div>
</template>

<script>
    export default {
        data() {
            return {
                info: null,
                username: "赵六", // 登录用户
                webSocket: null
            };
        },
        methods: {
            btnClick: function () {
                let url1 = "https://api.coindesk.com/v1/bpi/currentprice.json";
                let url2 = "/api/test";
                this.$axios.get(url2).then((response) => {
                    this.info = response;
                    this.$message.success({
                        message: "查询成功！",
                        center: true
                    });
                });
            },
            btnClick2: function () {
                this.$common.test();
            },
            btnClick3: function () {
                let that = this;
                if ("WebSocket" in window) {
                    this.webSocket = new WebSocket(
                        "ws://localhost:9010/websocket/" + this.username
                    );
                    // 连通之后的回调事件
                    this.webSocket.onopen = function () {
                        console.log("已经连通了websocket");
                    };

                    // 接收后台服务端的消息
                    this.webSocket.onmessage = function (evt) {
                        var received_msg = evt.data;
                        console.log("数据已接收:" + received_msg);
                    };
                }
            }
        },
    };

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
