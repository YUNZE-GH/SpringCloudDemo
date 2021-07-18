<template>
    <div>
        <el-menu
            default-active="2"
            :style="{height:height, lineHeight: height}"
            :unique-opened="true"
            router
            @open="handleOpen"
            @close="handleClose">
<!--            <el-submenu index="/job">
                <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>导航一</span>
                </template>
                <el-menu-item-group>
                    <template slot="title">分组一</template>
                    <el-menu-item index="/job">选项1选项1选项1选项1选项1选...</el-menu-item>
                    <el-menu-item index="/email">选项2</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="分组2">
                    <el-menu-item index="/job">选项3</el-menu-item>
                </el-menu-item-group>
                <el-submenu index="/job">
                    <template slot="title">选项4</template>
                    <el-menu-item index="/email">选项1</el-menu-item>
                </el-submenu>
            </el-submenu>-->
            <el-menu-item :index="item.path" v-for="(item,index) in routes[0].children" :key="index">
                <i class="el-icon-menu"></i>
                <span slot="title">{{ item.meta.title }}</span>
            </el-menu-item>
            <router-link to="/email">
                <el-menu-item index="/email">
                    <i class="el-icon-menu"></i>
                    <span slot="title">测试-邮箱</span>
                </el-menu-item>
            </router-link>



            <el-submenu index="1-4">
                <template slot="title">
                    <i class="el-icon-menu"></i>
                    <span>定时器</span>
                </template>
                <router-link to="/job">
                    <el-menu-item index="1-4-1">测试-定时器</el-menu-item>
                </router-link>
            </el-submenu>

            <router-view></router-view>

            <el-submenu index="1-3">
                <template slot="title">
                    <i class="el-icon-menu"></i>
                    <span>导航一</span>
                </template>
            </el-submenu>
<!--            <el-menu-item index="/job" disabled>
                <i class="el-icon-document"></i>
                <span slot="title">导航三</span>
            </el-menu-item>-->
        </el-menu>
    </div>
</template>

<script>
export default {
    name: "Menu",
    props: ['height'],
    data() {
        return {
            routes: null
        }
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }
    },
    created() {
        this.routes = this.$router.options.routes;
    }
}
</script>

<style scoped>
.el-menu {
    margin-left: -200px;
}

.el-menu .el-menu-item {
    text-align: left;
    margin-left: 220px;
}
</style>