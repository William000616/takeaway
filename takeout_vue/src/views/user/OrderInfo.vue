<template>
    <div class="goods">
        <van-nav-bar title="订单详情" left-text="返回" left-arrow @click-left="onClickLeft" />
        <van-cell-group class="goods-cell-group">

            <van-cell>
                <van-col span="16">收货人：{{ addressMessage.name }}</van-col>
                <van-col span="16">联系方式：{{ addressMessage.tel }}</van-col>
                <van-col span="21" style=";width:280px">收货地址：{{
                    addressMessage.address }}</van-col>
            </van-cell>

        </van-cell-group>

        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express" style="font-weight: bold">
                <van-col span="20">{{ orderInfo.shop_name }}</van-col>
            </van-cell>
        </van-cell-group>

        <van-card v-for="(good, index) in orderDetail.goods" :key="index" :num="good.count" :price="good.price"
            :title="good.good_name" :thumb="good.good_pic" />

        <van-cell-group class="goods-cell-group">
            <van-cell class="goods-express" style="font-weight: bold">
                <van-col span="20">商品金额</van-col>
                <van-col style="color: red">￥{{ orderInfo.good_total_price }}</van-col>
            </van-cell>
        </van-cell-group>

        <van-cell-group>
            <van-cell class="goods-cell-group" style="font-weight: bold">
                <van-col span="20">配送费</van-col>
                <van-col style="color: red">￥{{ orderInfo.delivery_price }}</van-col>
            </van-cell>
        </van-cell-group>


        <van-cell-group>
            <van-cell class="goods-cell-group" style="font-weight: bold">
                <van-col span="20">合计</van-col>
                <van-col style="color: red">￥{{ orderInfo.total_price }}</van-col>
            </van-cell>
        </van-cell-group>

        <van-cell-group>
            <van-cell class="goods-express" style="font-weight: bold" v-if="orderDetail.order_Number !== null">
                <van-col span="20" style="width:90px">订单号</van-col>
                <van-col style="color: red;width: 140px;margin-left: 60px;">{{ orderDetail.order_Number }}</van-col>
            </van-cell>
            <van-cell class="goods-express" style="font-weight: bold">
                <van-col span="20" style="width:80px">下单时间</van-col>
                <van-col style="color: red;width: 140px;margin-left: 70px;">{{ orderDetail.create_time }}</van-col>
            </van-cell>
            <van-cell class="goods-express" style="font-weight: bold" v-if="orderDetail.order_time !== null">
                <van-col span="20" style="width:80px">接单时间</van-col>
                <van-col style="color: red;width: 140px;margin-left: 70px;">{{ orderDetail.order_time }}</van-col>
            </van-cell>
            <van-cell class="goods-express" style="font-weight: bold" v-if="orderDetail.arrive_time !== null">
                <van-col span="20" style="width:90px">送达时间</van-col>
                <van-col style="color: red;width: 140px;margin-left: 60px;">{{ orderDetail.arrive_time }}</van-col>
            </van-cell>


            <van-cell class="goods-express" style="font-weight: bold">
                <van-col span="20" style="width:80px">订单状态</van-col>
                <van-col style="color: red;width: 140px;margin-left: 70px;">{{ payStatChange(orderInfo.order_Stat)
                }}</van-col>
            </van-cell>
        </van-cell-group>
        <div style="height:50px;display:block;"></div>
    </div>
</template>

<script>
import { getOrderInfo, GetAddressById } from "../../api/api.js";
export default {
    name: "OrderInfo",
    created() {
        this.orderDetail = this.$route.params.orderInfo;

        getOrderInfo(this.orderDetail).then(resp => {
            this.orderDetail = { ...this.orderDetail, goods: resp.data.list }
            console.log(this.orderDetail);
        })

        GetAddressById(this.orderDetail).then(resp => {
            if (resp.code === '200') {

                this.addressMessage = resp.data[0]
                console.log(this.addressMessage);
            }

        })
        this.totalPrice = this.$route.params.orderInfo.good_total_price;
        this.orderNumber = this.orderDetail.order_Number;
        this.orderStat = this.orderDetail.order_Stat;
        this.orderInfo = this.orderDetail;

        console.log(this.orderDetail)
    },
    data() {
        return {
            orderDetail: "",
            goods: null,
            totalPrice: null,
            orderNumber: null,
            addressMessage: null,
            orderStat: null,
            deliveryPrice: null,
            orderInfo: null,
            deliveryMan: {
                phone: ''
            },
        }
    },
    methods: {
        payStatChange(stat) {
            if (stat === 0) {
                return '未支付';
            } else if (stat === 1) {
                return '已支付,等待商家接单';
            } else if (stat === 2) {
                return '商家已接单,正在烹饪';
            } else if (stat === 3) {
                return '订单配送中,等待送达';
            } else if (stat === 4) {
                return '订单已送达,未评价';
            } else if (stat === 5) {
                return '订单已完成';
            }
        },
        onClickLeft() {
            this.$router.push("/order");
        }
    }
}
</script>

<style scoped></style>
