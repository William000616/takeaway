<template>
  <div id="app">
    <span v-show="showSearch">
      <van-search placeholder="请输入搜索关键词" v-on:click="search" />
    </span>
    <div>
      <swipe></swipe>
    </div>
    <van-tag color="#ffe1e1" size="large" text-color="#ad0000"><van-icon name="fire-o" />hot</van-tag>
    <h4 style="display: inline-block;font-size: 20px">所有店铺</h4>
    <van-card v-for="(shop, index) in shops" :key="index" @click="toShop(shop)">
      <template #title>
        <span>
          <h3 style="margin: 5px">{{ shop.shop_name }}</h3>
        </span>
      </template>
      <template #num>
        <span>销量{{ shop.total_sales }}</span>
      </template>
      <template #thumb>
        <img :src="shop.logo_src" style="width: 100px;height: 100px" alt="" />
      </template>
      <template #desc>
        <div>
          <span>￥{{ shop.min_cost }}起送</span>&nbsp;
          <span v-if="shop.deliveryCost !== 0">配送费{{ shop.delivery_cost }}￥</span>
          <span v-if="shop.deliveryCost === 0">免配送费</span>
        </div>
      </template>
    </van-card>
    <div style="height:50px;display:block;"></div>

  </div>
</template>

<script>
import Swipe from "../../views/user/Swipe.vue";
import { GetAllShop } from "../../api/api.js";
export default {
  components: {
    Swipe
  },
  methods: {
    /*跳转到搜索页面*/
    search() {
      this.showSearch = false;
      this.$router.replace({
        path: '/search',
      });
    },

    /*跳转到商铺页面*/
    toShop(shop) {
      this.$router.push({
        name: "shop",
        params: {
          shop: shop
        }
      });
    },

  },

  created() {
    GetAllShop().then(res => {
      console.log(res.data);
      this.shops = res.data.list;
    })
  },

  data() {
    return {
      value: '',
      shops: [
      ],
      homeShow: true,
      showSearch: true,
      show: true,
      active: 'home'
    };
  }
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
