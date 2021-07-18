<template>
    <div class="topBox">
        <div class="title">
            <h2>GH-BLOG</h2>
        </div>
        <div class="tool">

        </div>
        <div class="headPortrait">
            <el-avatar :size="40" icon="el-icon-user-solid" @error="errorHandler">
                <img src="../../../static/image/logo.png"/>
            </el-avatar>
        </div>
    </div>
</template>

<script>
export default {
    name: "Header",
    data() {
        return {
        }
    },
    methods: {
        errorHandler() {
            return true
        }
    }
}
</script>

<style scoped>
.topBox .title h2 {
    float: left;
    color: white;
    margin: 0 0 0 50px;
}

.topBox .headPortrait {
    float: right;
    margin-right: 50px;
    margin-top: 10px;
}
</style>