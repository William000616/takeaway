<template>
	<view>
		<block v-for="(item,index) in shopList" :key="index">
			<view class="content-view" @click="takeOut(item.openid)">
				<view class="content-img">
					<image :src="item.logo_src" mode="aspectFill"></image>
				</view>
				<view class="content-title">
					<text class="content-take">{{item.shop_name}}</text>
					<view class="content-monthly">
						<text>总销量：{{item.total_sales}}</text>
						<text>{{item.delivery_time}}分钟</text>
					</view>
					<view class="content-starting">
						<text>起送￥{{item.min_cost}}</text>
						<text>配送￥{{item.delivery_cost}}</text>
					</view>
				</view>
			</view>
		</block>
	</view>
</template>

<script>
	export default{
		props:{
			shopList:Array
		},
		data(){
			return{
				
			}
		}
	}
</script>

<style scoped>
	@import '../../../common/takeout.css'
</style>
