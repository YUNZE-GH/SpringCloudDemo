<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <el-input v-model="url"></el-input>
        <el-button @click="btnClick1">请求1</el-button>
        <el-button @click="btnClick2">请求2</el-button>
        <el-button @click="btnClick3">请求3</el-button>
        {{ resultResponse }}
    </div>
</template>

<script>

import {AUTH_LOGIN} from '@/apis';

export default {
    name: 'HelloWorld',
    props: {
        msg: String,
    },
    data() {
        return {
            url: null,
            params: {
                "userAccount": "admin",
                "userPassword": "123456",
                "tokenValidPeriod": 240
            },
            resultResponse: {}
        }
    },
    methods: {
        btnClick1() {
            let url = "/sys-api/auth/login";
            this.$http.post(url, this.params).then(response => {
                this.resultResponse = response;
            })
        },
        btnClick2() {
            this.$http.post(AUTH_LOGIN, this.params).then(response => {
                this.resultResponse = response;
            })
        },
        btnClick3() {
            this.$http.post(this.url, this.params).then(response => {
                this.resultResponse = response;
            })
        }
    },
    mounted() {
        console.log(process.env)
    }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
