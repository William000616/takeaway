<template>
    <div>
        <span>
            <h4>我的订单</h4>
        </span>
        <!--<van-card  v-for="(good,index) in goods" :key="index"
                   :num="good.count"
                   :price="good.price"
                   :desc="good.desc"
                   :title="good.title"
                   :thumb="good.thumb"
        >
            <template #tags>
                <van-tag plain type="danger">标签</van-tag>
            </template>
            <template #tags>
                <van-tag plain type="danger">标签</van-tag>
            </template>
            <template #footer>
                <van-button round hairline style="width: 80px" type="small" @click="deleteOrder(good.num)">删除订单</van-button>
            </template>
        </van-card>-->
        <van-card v-for="(order, index) in orders" :key="index" @click="toOrderInfo(order)">
            <template #title>
                <div style="width: 200px">
                    <h3 class="line-limit-length" style="margin: 5px;width: 90px;display: inline-block;text-align: left;">
                        {{ order.shop_name }}</h3>
                    <span style="margin: 5px;float: right;width: 75px" v-if="order.order_Stat === 1">
                        等待商家接单
                    </span>
                    <span style="margin: 5px;float: right" v-if="order.order_Stat === 2">
                        商家备货中
                    </span>
                    <span style="margin: 5px;float: right" v-if="order.order_Stat === 3">
                        配送中
                    </span>
                    <span style="margin: 5px;float: right" v-if="order.order_Stat === 4">
                        已送达
                    </span>

                </div>
            </template>
            <template #num>
                <span style="font-size: 15px;color: crimson">￥{{ order.total_price }}</span>
            </template>
            <template #thumb>
                <img :src="order.logo_src" style="width: 90px;height: 80px" alt="" />
            </template>
            <template #desc>
                <div style="text-align: left;font-size: 15px;margin-left: 10px">
                    <span>{{ order.create_time }}</span>
                </div>

            </template>
            <!--<template #tags>
                <van-tag plain type="danger">标签</van-tag>
                <van-tag plain type="danger">标签</van-tag>
            </template>-->
            <template #footer>
                <van-button round hairline style="width: 80px" type="small">订单详情</van-button>
            </template>
        </van-card>
        <div v-if="orders.length === 0">
            <van-empty description="您还没有订单噢~~现在去下单吧！！" />
        </div>
        <!--填充-->
        <div style="height:50px;display:block;"></div>
    </div>
</template>

<script>
import Toast from "vant/lib/toast";
import { GetOrder } from "../../api/api.js";
export default {
    name: "Order",
    created() {
        const params = {
            u_id: localStorage.getItem("u_id")
        }
        GetOrder(params).then(resp => {
            console.log(resp.data);
            this.orders = resp.data.list;
        })
    },
    data() {
        return {
            uid: '',
            orders: [
                {
                    /*
                    goods: null,
                    totalPrice:null,
                    orderNumber:null,
                    addressMessage:null,
                    orderStat:null,
                    deliveryPrice:null,
                  */
                    /*oid:'',
                    sid:'',
                    logoSrc:'/pic/kfcLogo.png',
                    shopName:'华莱士·全鸡汉堡',
                    orderStat:1,
                    totalPrice:'50',
                    createTime:'2021.8.9',
                    goods:'',
                    orderNumber:'',
                    deliveryPrice:''*/
                },
                /*{
                    num: 2,
                    price:"2.00",
                    desc:"描述信息",
                    title:"商品标题",
                    thumb:"https://img01.yzcdn.cn/vant/ipad.jpeg"
                },
                {
                    num: 3,
                    price:"2.00",
                    desc:"描述信息",
                    title:"商品标题",
                    thumb:"https://img01.yzcdn.cn/vant/ipad.jpeg"
                },
                {
                    num: 4,
                    price:"2.00",
                    desc:"描述信息",
                    title:"商品标题",
                    thumb:"https://img01.yzcdn.cn/vant/ipad.jpeg"
                },
                {
                    num: 5,
                    price:"2.00",
                    desc:"描述信息",
                    title:"商品标题",
                    thumb:"https://img01.yzcdn.cn/vant/ipad.jpeg"
                }*/
            ],
        }
    },
    methods: {
        deleteOrder(id) {
            Toast("删除订单" + id);
            console.log(id)
        },

        toOrderInfo(order) {
            console.log(order);
            this.$router.push({
                name: "orderInfo_user",
                params: {
                    orderInfo: order
                }
            })

            /* orderInfo: {
                 goodTotalPrice:null,
                     deliveryPrice:null,
                     totalPrice:null,
                     orderNumber:null,
                     goods:null,
                     addressMessage: null,
                     orderStat:null,
                     createTime:null,
                     /!*deliveryManId*!/
                     dMId:null
             }*/
        }
    }
}
</script>

<style scoped>
.line-limit-length {

    overflow: hidden;

    text-overflow: ellipsis;

    white-space: nowrap;

}
</style>
