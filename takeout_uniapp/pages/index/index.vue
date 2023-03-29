<template>
	<view class="content-takeout">
		<Takeout :shopList="shopList"></Takeout>
	</view>
</template>

<script>
	import Takeout from './components/takeout.vue';
	import {listing} from '../../api/api.js'
	import {GetShop} from '../../api/request.js'
	export default {
		components:{
			Takeout
		},
		data() {
			return {
				title: 'Hello',
				shopList:[],
			}
		},
		onLoad() {

		},
		methods: {
			shop(){
				listing(GetShop)
				.then((res)=>{
					let Res=res[1].data
					if(Res.code==='200'){
						this.shopList=Res.data.list
						console.log(this.shopList)
					}
				})
			}

		},
		mounted(){
			this.shop()
		}
	}
</script>

<style scoped>
.content-takeout{
	margin: 0 15upx;
}
</style>
