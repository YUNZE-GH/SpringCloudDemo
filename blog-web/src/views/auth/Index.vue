<template>
    <div class="topBox">
        <el-row class="main" :style="{height: height}">
            <el-col :span="16" :style="{lineHeight: height}">
                <div class="logo">
                    <!--                    <img alt="Vue logo" src="../../static/image/bg-2.jpg">-->
                    GH BLOG CLOUD
                </div>
            </el-col>
            <el-col :span="8">
                <div style="margin-top: 35%">
                    <Login ref="login"/>
                </div>
            </el-col>
        </el-row>
        <el-row class="header">
            <el-col :span="24">
                <div>
                    <a href="https://beian.miit.gov.cn/#/Integrated/index" target="_blank">鄂ICP备19029486号-2</a>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import Login from "@/views/auth/components/Login";

export default {
    name: "Index",
    components: {
        Login
    },
    data() {
        return {
            height: 'calc(100vh - 40px)',
        }
    }
}
</script>

<style scoped>
::v-deep .topBox {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    height: 100vh;
}

.header {
    line-height: 40px;
    height: 40px;
    background-color: #DCDFE6;
    text-align: center;
    /*position: fixed;*/
    top: calc(100vh - 40px);
}

.header a {
    text-decoration: none;
}

.logo {
    text-align: center;
    font-size: 80px;
    font-weight: bold;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    color: white;
    text-shadow: 0 0 5px #C0C4CC, 0 0 10px #C0C4CC, 0 0 15px #C0C4CC, 0 0 40px #C0C4CC, 0 0 70px #C0C4CC;
    /*text-shadow: 1px 1px #C0C4CC,2px 2px #C0C4CC,3px 3px #C0C4CC,4px 4px #C0C4CC,5px 5px #C0C4CC,6px 6px #C0C4CC;*/
}

.main {
    background: url("../../static/image/bg-2.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
}
</style>