import Vue from 'vue'
import Router from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'LAYOUT',
        meta: {title: '布局'},
        component: () => import('@/views/layout/Index'),
        children: [
            {
                path: '/index',
                name: 'INDEX',
                meta: {title: '首页', isLogin: true},
                component: () => import('@/views/home/Index')
            },
            {
                path: '/job',
                name: 'JOB',
                meta: {title: '定时器', isLogin: true},
                component: () => import('@/views/job/Index')
            },
            {
                path: '/email',
                name: 'EMAIL',
                meta: {title: '邮件', isLogin: true},
                component: () => import('@/views/email/Index')
            }
        ]
    },
    {
        path: '/login',
        name: 'AUTH_LOGIN',
        meta: {title: '登录', isLogin: false},
        component: () => import('@/views/auth/Index')
    },
]

Vue.use(Router)
export default new Router({
    routes
})