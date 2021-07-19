<template>
    <div>
        <el-container>
            <el-header class="el-header-1">
                <Header/>
            </el-header>

            <el-container>
                <el-aside>
                    <Menu />
                </el-aside>

                <el-container>
                    <el-header height="40px" style="line-height: 40px">
                        <el-breadcrumb separator-class="el-icon-arrow-right" style="line-height: 40px">
                            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                            <el-breadcrumb-item>活动管理</el-breadcrumb-item>
                            <el-breadcrumb-item>活动列表</el-breadcrumb-item>
                            <el-breadcrumb-item>活动详情</el-breadcrumb-item>
                        </el-breadcrumb>
                    </el-header>

                    <el-main>
                        <div>
                            <span style="font-size: 26px; height: 40px;line-height: 40px; color: #909399;">
                                <i class="el-icon-s-fold"></i> <span style="color: #409EFF; font-weight: bold;">首页</span>
                            </span>
                        </div>
                        <router-view></router-view>
                    </el-main>
                </el-container>
            </el-container>
        </el-container>
        <el-footer height="20px">
            <div>
                <a href="https://beian.miit.gov.cn/#/Integrated/index" target="_blank" style="text-decoration: none">鄂ICP备19029486号</a>
            </div>
        </el-footer>
    </div>
</template>

<script>

import Header from "./components/Header";
import Menu from "./components/Menu"

export default {
    name: "Index",
    components: {
        Header,
        Menu,
    },
    data() {
        return {
        }
    },
    methods: {},
    mounted() {
        this.$common.test();
        console.log(this.$route)
        console.log(this.$router.options.routes);
    }
}
</script>

<style scoped>
.el-header-1 {
    background-color: #409EFF;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-footer {
    background-color: #409EFF;
    color: #333;
    text-align: center;
    line-height: 20px;
    font-size: 12px;
}

.el-footer a {
    color: white;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    width: 250px;
}


.el-main {
    margin: 0 20px 20px 20px;
    padding: 0px;
    background-color: #E9EEF3;
    color: #333;
}

body > .el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}
</style>