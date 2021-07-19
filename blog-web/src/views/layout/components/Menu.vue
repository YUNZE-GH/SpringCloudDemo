<template>
    <div>
        <el-menu
            :default-active="routes[0].children[0].path"
            :style="{height: height, lineHeight: height}"
            :unique-opened="true"
            router
            @open="handleOpen"
            @close="handleClose">

            <div v-for="(item,index) in routes[0].children" :key="index">
                <el-menu-item :index="item.path" v-if="item.meta.isShow !== false">
                    <i class="el-icon-menu"></i>
                    <span slot="title">{{ item.meta.title }}</span>
                </el-menu-item>
            </div>

        </el-menu>
    </div>
</template>

<script>
export default {
    name: "Menu",
    data() {
        return {
            height: this.$common.commonHeight(),
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