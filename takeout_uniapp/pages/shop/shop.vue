<template>
	<view>
		<Business :shopInfo="shopInfo"></Business>
		<Tabs></Tabs>
		<Ordering :class="[hideing === 0 ? 'actineclass' : 'errorclass']" :goodList="goodList" :shopInfo="shopInfo"></Ordering>
		<Message :class="[hideing === 1 ? 'actineclass' : 'errorclass']"></Message>
	</view>
</template>

<script>
	import Business from './components/business.vue'
	import Tabs from './components/tabs.vue'
	import Ordering from './components/ordering.vue'
	import Message from './components/message.vue'
	
	import {listing} from '../../api/api.js'
	import {GetGood} from '../../api/request.js'
	export default{
		components:{
			Business,
			Tabs,
			Ordering,
			Message,
		},
		data(){
			return{
				hideing:0,
				goodList:[],
				shopInfo:{}
			}
		},
		methods:{
			fatherMethod(index){
				this.hideing=index
			},
			takeGood(){
				listing('http://localhost:3000/good/listGood?s_id=1')
				.then((res)=>{
					let Res=res[1].data
					if(Res.code==='200'){
						this.goodList=Res.data
						console.log(this.goodList)
					}
				}),
				listing('http://localhost:3000/shop/listById?s_id=1')
				.then((res)=>{
					let Res=res[1].data
					if(Res.code==='200'){
						this.shopInfo=Res.data
						console.log(this.shopInfo)
					}
				})
			}
		},
		onLoad(){
			this.takeGood()
		}
	}
</script>

<style scoped>
	.actineclass{
		display: block;
	}
	.errorclass{
		display: none;
	}
</style>
