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
            <!-- <van-card  v-for="(good,index) in goods" :key="index"
                       :num="good.num"
                       :price="good.price"
                       :desc="good.desc"
                       :title="good.title"
                       :thumb="good.thumb"
            >
            </van-card> -->
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

            <!-- <ul>
                <li v-for="(shop, index) in shops" :key="index">
                    <div style="border-radius: 10px;background-color: white" @click="toShop(shop)">
                        <div style="display:inline-block;float: left">
                            <img :src="shop.logo_src" width="80" height="80" alt="">
                        </div>
                        <div style="">
                            <h3 style="width: 195px;margin-bottom: 5px;padding-left: 80px;text-align: left">
                                {{ shop.shop_name }}</h3>
                            <div style="width: 195px;padding-left: 80px;text-align: left">
                                <span style="font-size: 15px;">月售{{ shop.total_sales }}</span><br>
                            </div>
                            <div style="width: 195px;font-size: 15px;padding-left: 80px;text-align: left">
                                <span style="">起送￥{{ shop.delivery_cost }}</span>
                                <span style="margin: 5px">配送￥{{ shop.min_cost }}</span>
                                <span style="margin: 5px" v-if="shop.delivery_time === null">40分钟</span>
                                <span style="margin: 5px" v-if="shop.delivery_time !== null">{{ shop.delivery_time
                                }}分钟</span>
                                <br>
                            </div>
                        </div>
                    </div>

                </li>
            </ul> -->
        </el-card>
        <!--填充-->
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
                // {
                //     shopName: "蜜雪冰城",
                //     desc: "冰淇淋与茶",
                //     totalSales: 2000,
                //     minCost: "15",
                //     deliveryCost: "3",
                //     deliveryTime: "40",
                //     logoSrc: "upload/蜜雪冰城logo_20210905175221.png"
                // },

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
            console.log(this.value);
            // this.axios.get("http://localhost:3000/shop/list", {
            const params = {
                shop_name: this.value
            }
            GetAllShop(params).then(resp => {
                console.log(resp.data);
                this.shops = resp.data.data.list;
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
