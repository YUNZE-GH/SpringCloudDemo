<template>
    <div class="hello">
        <el-button type="primary" @click="btnClick">主要按钮</el-button>
        <br>
        {{info}}
        <br>
        <el-button type="primary" @click="btnClick2">主要按钮2</el-button>
        <br>
        <el-divider></el-divider>
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="用户">
                <el-select v-model="form.username" placeholder="请选择用户">
                    <el-option label="张三" value="张三"></el-option>
                    <el-option label="李四" value="李四"></el-option>
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
                form: {
                    content: null,
                    username: null
                }
            };
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
            send: function() {
                this.$message.success({ message: this.form.content, center: true });
            }
        }
    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>