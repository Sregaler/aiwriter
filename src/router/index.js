
import VueRouter from 'vue-router'

import MoBan from "../components/MoBan.vue"
import QuickWriter from "../components/QuickWriter.vue"
import CheckPublish from "../components/CheckPublish.vue"
import AiWrite from "../components/AiWrite.vue"
import AiCheck from "../components/AiCheck.vue"
import TestToVideo from "../components/TestToVideo.vue"
import AiIndex from "../components/AiIndex.vue"
import Manager from "../components/Manager.vue"
import Tool from "../components/Tool.vue"
import SuCaiKu from "../components/SuCaiKu.vue"
import HotFind from "../components/HotFind.vue"
import ThingDetail from "../components/ThingDetail.vue"
//创建并暴露一个路由器
const rounter = new VueRouter({
	routes:[
		{
			name:'aiindex',
			path:'/',
			component:AiIndex,
		},
		{
			name:'moban',
			path:'/moban',
			// path:'/',
			component:MoBan,
		},
		{
			name:"quickwriter",
			path:'/quickwriter',
			component:QuickWriter,
			props($route){
				return {
					qwtext:$route.params.qwtext,
					modal:$route.params.modal,
				}
			}
		},
		{
			name:"checkpublish",
			path:'/checkpublish',
			component:CheckPublish,
			props($route){
				return {
					qwtext:$route.params.qwtext,
					qtitle:$route.params.qtitle,
					innerdata:$route.params.innerdata,
				}
			}
		},
		{
			name:"aiwrite",
			path:'/aiwrite',
			component:AiWrite,
			props($route){
				return {
					qwtext:$route.params.qwtext,
					qtitle:$route.params.qtitle,
					innerdata:$route.params.innerdata,
					textobj:$route.params.textobj,
				}
			}
		},
		{
			name:"aicheck",
			path:'/aicheck',
			component:AiCheck,
			props($route){
				return {
					qwtext:$route.params.qwtext,
					qtitle:$route.params.qtitle,
					textobj:$route.params.textobj,
				}
			}
		},
		{
			name:"testtovideo",
			path:'/testtovideo',
			component:TestToVideo,
		},
		{
			name:"tool",
			path:'/tool',
			component:Tool,
		},
		{
			name:"sucaiku",
			path:'/sucaiku',
			component:SuCaiKu,
		},
		{
			name:"manager",
			path:'/manager',
			component:Manager,
		},
		{
			name:"hotfind",
			path:'/hotfind',
			component:HotFind,
		},
		{
			name:"thingdetial",
			path:'/thingdetial',
			component:ThingDetail,
		},
	]
})

rounter.beforeEach((to,from,next)=>{
	next()
})
export default rounter
