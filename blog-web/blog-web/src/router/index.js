import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import WebWeChat from '@/components/WebWeChat'

Vue.use(Router)

export default new Router({
    routes: [{
        path: '/',
        name: 'Login',
        component: Login
    }, {
        path: '/WebWeChat',
        name: 'WebWeChat',
        component: WebWeChat
    }]
})