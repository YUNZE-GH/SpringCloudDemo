<template>
    <div class="signBox" v-loading="loading">
        <el-form ref="form" :model="form" height="100%">
            <h2>用户登录</h2>
            <el-input prefix-icon="el-icon-user" v-model="form.userAccount" placeholder="账号/手机号"
                      class="signData"></el-input>
            <el-input prefix-icon="el-icon-lock" v-model="form.userPassword" placeholder="输入密码" type="password"
                      show-password class="signData"></el-input>
            <el-button type="primary" style="width: 100%;font-weight: bold;font-size: 18px" class="signData"
                       @click="onSubmit">登&nbsp;&nbsp;&nbsp;录
            </el-button>
        </el-form>
    </div>
</template>

<script>

import {AUTH_LOGIN} from '@/apis'

export default {
    name: "Login",
    data() {
        return {
            loading: false,
            form: {
                userAccount: null,
                userPassword: null,
                tokenValidPeriod: process.env.VUE_APP_TOKEN_VALIDITY
            }
        }
    },
    methods: {
        onSubmit() {
            this.openLoading();
            this.$http.post(AUTH_LOGIN, this.form).then(response => {
                this.closeLoading();
                if (response.data.code === 0) {
                    // 将数据临时存放到本地
                    sessionStorage.setItem("user", JSON.stringify(response.data.data));

                    // 跳转页面
                    this.$router.push({
                        name: "INDEX",
                        params: {
                            user: response.data.data
                        },
                    });
                } else {
                    this.$message.error(response.data.message);
                }
            })
        },
        openLoading() {
            this.loading = true;
            setTimeout(() => {
                this.$message.warning("请求超时，请稍后再试！")
                this.loading = false;
            }, 5000);
        },
        closeLoading() {
            setTimeout(() => {
                this.loading = false;
            }, 300);
        }
    }
}
</script>

<style scoped>
.signBox {
    width: 350px;
    height: 300px;
    background-color: white;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    border-radius: 6px;
    padding: 20px;
    text-align: center;
}

.signData {
    margin: 10px 0;
}
</style>