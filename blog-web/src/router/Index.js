import Vue from 'vue'
import Router from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'LAYOUT',
        meta: {title: '布局'},
        redirect: '/login',
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
                path: '/test2',
                name: 'test2',
                meta: {title: '测试菜单2', isLogin: true, isShow: false},
                component: () => import('@/views/home/Index')
            },
            {
                path: '/email',
                name: 'EMAIL',
                meta: {title: '邮件', isLogin: true},
                component: () => import('@/views/email/Index')
            },
            {
                path: '/test',
                name: 'test',
                meta: {title: '测试菜单', isLogin: true, isShow: true},
                component: () => import('@/components/HelloWorld')
            },
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