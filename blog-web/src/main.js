import Vue from 'vue'
import App from './App.vue'
import router from './router/Index'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";
import './static/css/style.css';

Vue.config.productionTip = false

Vue.prototype.$http = axios
Vue.use(ElementUI);
new Vue({
    el: '#app',
    router,
    render: h => h(App),
}).$mount('#app')
