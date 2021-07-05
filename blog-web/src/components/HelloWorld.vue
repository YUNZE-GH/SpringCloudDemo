<template>
    <div class="hello">
        <h1>{{ msg }}</h1>

        <el-button @click="btnClick">请求</el-button>
        {{ resultResponse }}
    </div>
</template>

<script>
export default {
    name: 'HelloWorld',
    props: {
        msg: String,
    },
    data() {
        return {
            params: {
                "userAccount": "admin",
                "userPassword": "123456",
                "tokenValidPeriod": 240
            },
            resultResponse: {}
        }
    },
    methods: {
        btnClick() {
            let url = "/api/auth/login";
            this.$http.post(url, this.params).then(response => {
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
