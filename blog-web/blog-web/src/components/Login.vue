<template>
    <div class="hello">
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
