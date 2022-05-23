import Vue from 'vue'
import App from './App.vue'

//引入VueRouter
import VueRouter from 'vue-router'
//引入路由器
import router from './router'
// import './theme/theme/index.css';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

//关闭Vue的生产提示
Vue.config.productionTip = false

//应用ElementUI
Vue.use(ElementUI);
Vue.use(VueRouter);
//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	router:router,
	beforeCreate() {
			Vue.prototype.$bus = this //安装全局事件总线
		},
})
