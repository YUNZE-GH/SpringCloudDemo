<template>
    <div class="topBox" >
        <el-row :style="{height: height}">
            <el-col :span="16" :style="{lineHeight: height}">
                <div class="logo">
                    <img alt="Vue logo" src="../../static/image/logo.png">
                </div>
            </el-col>
            <el-col :span="8">
                <div style="margin-top: 35%">
                    <Login ref="login" />
                </div>
            </el-col>
        </el-row>
        <el-row class="header">
            <el-col :span="24">
                <div>
                    <a href="https://beian.miit.gov.cn/#/Integrated/index">鄂ICP备19029486号</a>
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
}

.header a {
    text-decoration: none;
}

.logo {
    text-align: center;
}
</style>