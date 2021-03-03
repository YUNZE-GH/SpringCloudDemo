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
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="登录用户">
                <el-select v-model="form.username" placeholder="请选择用户">
                    <el-option v-for="item in options" :key="item.username" :label="item.username"
                        :value="item.username">
                    </el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <!-- <router-link :to="{ name: 'WebWeChat' }"> -->
        <el-button type="primary" @click="singnIn">登录</el-button>
        <!-- </router-link> -->
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                info: null,
                options: [{
                    username: "张三"
                }, {
                    username: "李四"
                }, {
                    username: "王五"
                }],
                form: {
                    username: null,
                },
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
            singnIn: function () {
                this.$router.push({
                    name: "WebWeChat",
                    params: {
                        username: this.form.username
                    },
                });
            },
        },
    };

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
