import Vue from 'vue'
import Router from 'vue-router'

import COMPONENT_AUTH_LOGIN from "@/views/auth/Index";
import COMPONENT_HOME_INDEX from "@/views/home/Index"

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'AUTH_LOGIN',
            component: COMPONENT_AUTH_LOGIN
        },
        {
            path: '/index',
            name: 'HOME_INDEX',
            component: COMPONENT_HOME_INDEX
        },
    ]
})