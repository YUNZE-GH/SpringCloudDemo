<template>
    <div class="hello">
        <el-button type="primary" @click="btnClick">主要按钮</el-button>
        <br>
        {{info}}
        <br>
        <el-button type="primary" @click="btnClick2">主要按钮2</el-button>
    </div>
</template>

<script>
    export default {
        name: "HelloWorld",
        data() {
            return {
                msg: "Welcome to Your Vue.js App",
                info: null
            };
        },
        methods: {
            btnClick: function() {
                let url1 = 'https://api.coindesk.com/v1/bpi/currentprice.json';
                let url2 = '/api/test';
                this.$http.get(url2).then(response => {
                    this.info = response;
                    this.$message.success({ message: "查询成功！", center: true });
                })
            },
            btnClick2: function() {
                this.$common.test();
            }
        }
    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>