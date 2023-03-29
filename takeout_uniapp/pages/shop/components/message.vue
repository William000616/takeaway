<template>
	<view class="message-view">
		<!-- <block v-for="(item,index) in leaveworld" :key="index"> -->
			<view class="message-dist">
			<view class="users-message">
				<view class="cont-name">
					<image src="" mode="widthFix"></image>
					<text>马化腾</text>
				</view>
				<view class="message-time">
					<text>2020-03-03</text>
				</view>
			</view>
			<view class="cont-name-text">
				<text>味道很好，配送很快！</text>
			</view>
			</view>
		<!-- </block> -->
	</view>
</template>

<script>
</script>

<style scoped>
	.message-view{
		margin: 0 20upx 10upx 20upx;
		
	}
	.activetext{
		color: #4CD964;
		background: #ffddoo !important;
		border: 1px solid #ffdd00 !important;
		
	}
	.users-message{
		display: flex;
		justify-content: space-between;
		height: 50upx;
		align-items: center;
		margin-bottom: 15upx;
	}
	.message-time{
		font-size: 25upx;
		color: #A8A8A8;
	}
	.cont-name image{
		width: 50upx !important;
		height: 50upx !important;
		border-radius: 50%;
		margin-right: 9upx;
		
	}
	.cont-name text{
		font-size: 30upx;
		color: #A8A8A8;
	}
	.cont-name{
		display: flex;
		align-items: center;
		height: 50upx;
		
	}
	.cont-name-text text{
		font-size: 27upx;
		color: #333333;
		line-height: 1.5;
		display: -webkit-box;
		-webkit-box-orient:vertical;
		-webkit-line-clamp:2;
		overflow: hidden;
	}
	.message-dist{
		margin-top: 30upx;
		color: #cccccc;
		text-align: center;
		padding-top: 40upx;
	}
</style>
