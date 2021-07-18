import Vue from 'vue'
import App from './App.vue'
import router from './router/Index'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import './static/css/style.css';

import axios from "axios";
Vue.prototype.$http = axios

import common from "@/static/js/common/common";
Vue.prototype.$common = common

Vue.config.productionTip = false
Vue.use(ElementUI);
new Vue({
    el: '#app',
    router,
    render: h => h(App),
}).$mount('#app')
