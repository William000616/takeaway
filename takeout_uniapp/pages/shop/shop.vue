<template>
	<view>
		<Business></Business>
		<Tabs></Tabs>
		<Ordering></Ordering>
		<Message></Message>
		<Information></Information>
	</view>
</template>

<script>
	import Business from './components/business.vue'
	import Tabs from './components/tabs.vue'
	import Ordering from './components/ordering.vue'
	import Message from './components/message.vue'
	import Information from './components/information.vue'
	export default{
		components:{
			Business,
			Tabs,
			Ordering,
			Message,
			Information
		}
	}
</script>

<style>
</style>
