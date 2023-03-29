<template>
	<view class="ordering-fls">
		<view class="ordering-left">
			<block v-for="(item,index) in orderclass" :key="index">
				<text :class="{activeord:index == numing}" @click="ordermeClic(index,item)">{{item}}</text>
			</block>
		</view>
		<view class="ordering-right">			
			<text class="ordering-right-title">{{ordertitle}}</text>
			<view>
				<!-- <block v-for="(item,index) in classifarr" :key="index"> -->
					<view class="content-view">
						<view class="content-img">
							<image src="https://i0.hdslb.com/bfs/vc/5885cacaffb53a78be1e66bee0a1ee4d253709d4.png@1c.webp" mode="aspectFill"></image>
						</view>
						<view class="content-title">
							<text class="conteng-take">123</text>
							<view class="conteng-starting">
								<text>销量0</text>
							</view>
							<view class="conteng-price">
								<view class="conteng-shi">￥1</view>
								<view class="ordering-price">
									<image @click="reDue(index,item._id,item.objdis.Discount,item.amount,item.obidis.image,item.objdis.input)" src="../../../static/image/减.png" mode="widthFix"></image>
									<text class="amounting">1</text>
									<image @click="plus(index,item._id,item.objdis.Discount,item.amount,item.objdis.image,item)" src="../../../static/image/加.png" mode="widthFix"></image>
								</view>
							</view>
						</view>
					</view>
				<!-- </block> -->
			</view>
		</view>
		<view class="Shopping">
			<view class="Shopping-dis">
				<view class="Shopping-left">
					<view class="Cost-mony">
						<text class="Total-price">￥123</text>
						<text class="Delivery">另需配送费123元</text>
					</view>
				</view>
				<view class="Shopping-right" :class="{shoppactive:isActive}" @click="flat && settLement">
					<text v-if="!isActive">1元起送</text>
					<text v-if="isActive">1223</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				orderclass:['盖浇饭','小吃'],
				numing:0,
				isActive:false
			}
		},
		methods:{
			ordermeClic(index){
				this.numing=index
			}
		}
	}
</script>

<style scoped>
	.ordering-fls{
		display: flex;
		justify-content: space-between;
	}
	.ordering-left{
		width: 190upx;
		background: #fafafa;
		overflow-y: auto;
		height: 100%;
	}
	.ordering-left text{
		color: #a8a8a8;
		display: block;
		font-size: 28upx;
		height: 80upx;
		line-height: 80upx;
		text-align: center;
	}
	.activeord{
		background: #FFFFFF !important;
		font-weight: bold !important;
	}
	.ordering-right{
		width: 100%;
		padding: 9upx 9upx 130upx 9upx;
		overflow: hidden;
		overflow-y: auto;
	}
	.ordering-right-title{
		font-size: 28upx;
		height: 60upx;
		line-height: 60upx;
	}
	.content-view{
		display: flex;
		justify-content: space-between;
		height: 200upx !important;
		overflow: hidden;
		margin: 30upx 0;
		color: #898989;
	}
	.content-img{
		width: 180rpx !important;
		height: 200rpx !important;
		overflow: hidden;
	}
	.content-img image{
		height: 100%;
		width: 100%;
		border-radius: 10upx;
		overflow: hidden;
	}
	.content-title{
		padding-left: 10upx;
		font-size: 24upx;
	}
	.conteng-take{
		color: #333333;
		height: 50upx;
		font-size: 33upx;
		font-weight: bold;
		line-height: 50upx;
		display: -webkit-box;
		-webkit-box-orient:vertical;
		-webkit-line-clamp:1;
		overflow: hidden;
	}
	.conteng-monthly{
		display: flex;
	}
	.content-monthly text{
		background: #F1F1F1;
		margin-right: 9upx;
		padding: 4upx 13upx;
		border-radius: 7upx;
		display: block;
	}
	.conteng-starting{
		display: flex;
		align-items: center;
		height: 50upx;
		line-height: 50upx;
	}
	.conteng-price image{
		width: 60upx;
		height: 60upx;
	}
	.conteng-price{
		display: flex;
		align-items: center;
	}
	.ordering-price{
		display: flex;
		align-items: center;
		justify-content: space-around;
		width: 100%;
	}
	.amounting{
		width: 50upx;
		text-align: center;
	}
	.conteng-shi{
		color: #fb4e44;
		font-size: 33upx;
		width: 180upx;
		overflow: hidden;
	}
	.Shopping{
		position: fixed;
		left: 0;
		right: 0;
		bottom: 0;
		width: 100%;
	}
	.Shopping-left{
		height: 130upx;
		background: #000000; flex-grow: 2;
		border-top-left-radius: 50upx;
		border-bottom-left-radius: 50upx;
	}
	.Shopping-right{
		height: 130upx;
		line-height: 130upx;
		font-size: 30upx;
		color: #7f7f7f;
		text-align: center;
		background: #000000;
		flex-grow: 1;
		border-top-right-radius: 50upx;
		border-bottom-right-radius: 50upx;
	}
	.Shopping-dis{
		display: flex;
		height: 130upx;
		margin: 0 20upx;
	}
	.Delivery{
		color: #999999;
		font-size: 28upx;
		height: 65upx;
	}
	.Shopping-left text{
		display: block;
	}
	.Total-price{
		font-size: 40upx;
		color: #FFFFFF;
		height: 65upx;
		line-height: 65upx;
	}
	.Cost-mony{
		padding-left: 170upx;
		color: #4CD964;
		height: 130upx;
	}
	.shoppactive{
		background: #ffd300 !important;
		color: #000000 !important;
	}
</style>
