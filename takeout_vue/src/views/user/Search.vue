<template>
    <div>
        <van-nav-bar title="搜索页面" left-text="返回" left-arrow @click-left="onClickLeft" />
        <van-search v-model="value" placeholder="请输入搜索关键词" show-action>
            <template #action>
                <div @click="search">搜索</div>
            </template>
        </van-search>
        <el-card class="box-card" v-show="cardShow" style="background-color: #f7f8fa">
            <div slot="header" class="clearfix">
                <span>{{ title }}</span>
            </div>
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

        </el-card>
        <div style="height:60px;display:block;background-color: white"></div>
    </div>
</template>

<script>
import axios from "axios";
import { GetAllShop } from "../../api/api.js";
export default {
    name: "Search",
    data() {
        return {
            totalPrice: null,
            show: true,
            value: '',
            cardShow: true,
            title: '',
            shops: [

            ],
        }
    },
    methods: {
        onClickLeft() {
            this.$router.push({
                path: '/home_user',
            });

        },

        search() {
            const params = {
                shop_name: this.value
            }
            GetAllShop(params).then(resp => {
                console.log(resp.data);
                this.shops = resp.data.list;
            })
        },

        /*进入店铺界面*/
        toShop(shop) {
            console.log("进入店铺界面" + shop.sid);
            this.$router.push({
                name: 'shop',
                params: {
                    shop: shop
                }
            })
        },


    }
}
</script>

<style scoped></style>
