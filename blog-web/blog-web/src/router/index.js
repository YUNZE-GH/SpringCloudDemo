import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import WebWeChat from '@/components/WebWeChat'
import Demo from '@/components/Demo'

Vue.use(Router)

export default new Router({
    routes: [{
        path: '/',
        name: 'Login',
        component: Login
        /* ,
        children: [
            {
                path: '/userinfo',
                meta: {title}
            }
        ] */
    }, {
        path: '/WebWeChat',
        name: 'WebWeChat',
        component: WebWeChat
    }, {
        path: '/Demo',
        name: 'Demo',
        component: Demo
    }]
})