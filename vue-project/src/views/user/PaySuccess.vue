<template>
    <div>
        <van-image width="100" height="100" src="/pic/paysuccess.png" />
        <van-cell-group>
            <!--<van-cell>
                <van-col span="21" style="width: 140px">商品总金额</van-col>
                <van-col style="width: 50px;margin-left: 80px">￥{{goodTotalPrice}}</van-col>
            </van-cell>
            <van-cell>
                <van-col span="21" style="width: 140px">配送费</van-col>
                <van-col style="width: 50px;margin-left: 80px">￥{{deliveryPrice}}</van-col>
            </van-cell>-->
            <van-cell>
                <van-col span="21" style="width: 140px">支付金额</van-col>
                <van-col style="width: 50px;margin-left: 80px">￥{{ orderInfo.total_price }}</van-col>
            </van-cell>
        </van-cell-group>
        <van-cell-group>
            <van-cell>
                <van-col span="21" style="width: 60px">订单号</van-col>
                <van-col style="width: 200px;margin-left: 25px">{{ orderInfo.order_Number }}</van-col>
            </van-cell>
        </van-cell-group>
        <van-cell-group>
            <van-cell>
                <van-col span="21" style="width: 60px">订单状态</van-col>
                <van-col style="width: 140px;margin-left: 60px" v-if="orderInfo.order_Stat === 1">已支付，等待商家接单</van-col>
                <van-col style="width: 140px;margin-left: 60px" v-if="orderInfo.order_Stat === 2">商家已接单</van-col>
                <van-col style="width: 140px;margin-left: 60px" v-if="orderInfo.order_Stat === 3">骑手已接单，等待送达</van-col>
                <van-col style="width: 140px;margin-left: 60px" v-if="orderInfo.order_Stat === 4">已送达</van-col>
            </van-cell>
            <van-cell>
                <van-col span="21" style="width: 60px">创建时间</van-col>
                <van-col style="width: 140px;margin-left: 80px">{{ orderInfo.create_time }}</van-col>
            </van-cell>
            <!--<van-cell>
                <van-col span="21" style="width: 140px">配送方式</van-col>
                <van-col style="width: 80px;margin-left: 60px">旋风骑士</van-col>
            </van-cell>-->
        </van-cell-group>
        <van-button type="primary" size="large" @click="showOrderInfo">查看订单详情</van-button>
        <!--遮挡板-->
        <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100">

        </div>
    </div>
</template>

<script>
import OrderInfo from "./OrderInfo";
import Toast from "vant/lib/toast";

export default {
    /*orderNumber:"202108040080",
    total_price:this.total_price,
    goods:this.selectedGoods,
    addressMessage:this.addressMessage,
    order_Stat:1*/
    name: "PaySuccess",
    created() {
        Toast("支付成功");
        this.orderInfo.o_id = this.$route.params.o_id;
        this.orderInfo.total_price = this.$route.params.total_price;
        this.orderInfo.good_total_price = this.$route.params.good_total_price;
        this.orderInfo.delivery_price = this.$route.params.delivery_price;
        this.orderInfo.order_Number = this.$route.params.order_Number;
        this.orderInfo.a_id = this.$route.params.a_id;
        // this.orderInfo.addressMessage = this.$route.params.addressMessage;
        // this.orderInfo.shopName = this.$route.params.shopName;
        this.orderInfo.order_Stat = this.$route.params.order_Stat;
        this.orderInfo.create_time = this.$route.params.create_time;
    },
    data() {
        return {
            /*goodTotalPrice:null,
            deliveryPrice:null,
            total_price:null,
            orderNumber:null,
            goods:null,
            addressMessage: null,
            order_Stat:null,
            create_time:null,*/
            orderInfo: {
                good_total_price: null,
                delivery_price: null,
                total_price: null,
                order_Number: null,
                goods: null,
                addressMessage: null,
                shopName: null,
                order_Stat: null,
                create_time: null,
                /*deliveryManId*/
                o_id: '',
                a_id: '',
            }
        }
    },
    methods: {
        showOrderInfo() {
            //Toast("查看订单详情");
            this.$router.push({
                name: 'orderInfo_user',
                params: {
                    orderInfo: this.orderInfo
                }
            })
        }
    }

}
</script>

<style scoped></style>
