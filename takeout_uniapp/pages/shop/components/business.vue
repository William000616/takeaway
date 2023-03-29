<template>
	<view>
		<view class="business-view">
			<view class="business-take">
				<view class="business-img">
					<image :src="busidata.logo" mode="aspectFill"></image>
				</view>
				<view class="business-right">
					<text class="business-title">KFC</text>
					<view class="business-time">
						<text>配送约20分钟</text>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		props:{
			busidata:Object
		}
	}
</script>

<style scoped>
	text{display: block;}
	.business-view{
		height: 130upx;
		background:linear-gradient(to top, #ffe566 10%,#ffd300 100%);
		border-bottom-left-radius: 10upx;
		border-bottom-right-radius: 10upx;
	}
	.business-img image{
		width: 100%;
		height: 100%;
		border-radius: 10uxp;
		
	}
	.business-img{
		width: 150upx !important;
		height: 120upx !important;
		padding-right: 20upx;
	}
	.business-time{
		display: flex;
		align-items: center;
		font-size: 27upx;
		height: 40upx;
		line-height: 40upx;
	}
	.business-take{
		display: flex;
		height: 130upx;
		padding: 0 10upx;
	}
	.business-right{width: 100%;}
	.business-title{
		font-size: 37upx;
		margin-bottom: 19upx;
		display: block;
		display: -webkit-box;
		-webkit-box-orient:vertical;
		-webkit-line-clamp:1;
		overflow: hidden;
	}
	/* .Notice{
		font-size: 27upx;
		padding: 20upx 10upx 0 10upx;
		display: block;
		display: -webkit-box;
		-webkit-box-orient:vertical;
		-webkit-line-clamp:1;
		overflow: hidden;
	} */
	
</style>
