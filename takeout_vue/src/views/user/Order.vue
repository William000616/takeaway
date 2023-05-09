<template>
    <div>
        <span>
            <h4>我的订单</h4>
        </span>
        <van-card v-for="(order, index) in orders" :key="index">
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
                    <span style="margin: 5px;float: right" v-if="order.order_Stat === 5">
                        已完成
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
            <template #footer>
                <van-button round hairline style="width: 80px" type="small" @click="toOrderInfo(order)">订单详情</van-button>
                <van-button round hairline style="width: 80px" type="small" v-if="order.order_Stat === 4"
                    @click="showPopup(order)">去评价</van-button>
                <van-button round hairline style="width: 80px" type="small" v-if="order.order_Stat === 5"
                    @click="showPopup1(order)">查看评价</van-button>
            </template>

        </van-card>
        <div v-if="orders.length === 0">
            <van-empty description="您还没有订单噢~~现在去下单吧！！" />
        </div>
        <div style="height:50px;display:block;"></div>
        <van-popup v-model="show" position="bottom" :style="{ height: '30%' }">
            <van-field :style="{ height: '30%' }" v-model="commentMessage" rows="2" autosize label="写评价" type="textarea"
                placeholder="请输入评价内容" />
            <van-button round hairline style="width: 80px" type="small" @click="addComment">发布评价</van-button>
        </van-popup>
        <van-popup v-model="show1" position="bottom" :style="{ height: '30%' }">
            <van-field :style="{ height: '30%' }" v-model="addedComment.addedComment" rows="2" autosize label="我的评价"
                type="textarea" readonly />
            <van-field :style="{ height: '30%' }" v-model="addedComment.date" rows="2" autosize label="评价时间" readonly />
        </van-popup>
    </div>
</template>

<script>
import Toast from "vant/lib/toast";
import { GetOrder, AddComment, changeOrderState, GetCommentById } from "../../api/api.js";
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
            ],
            show: false,
            show1: false,
            commentMessage: '',

            comment: {
                o_id: '',
                u_id: '',
                s_id: '',
                sm_date: '',
                message: '',
            },
            addedComment: {
                addedComment: '123',
                date: '12'
            }
        }
    },
    methods: {
        deleteOrder(id) {
            Toast("删除订单" + id);
            console.log(id)
        },
        showPopup(order) {

            this.comment = {
                o_id: order.o_id,
                u_id: order.u_id,
                s_id: order.s_id,
            }
            this.show = true;
        },
        showPopup1(order) {
            this.show1 = true;
            GetCommentById(order).then(res => {
                console.log(res);
                if (res.code === '200') {
                    this.addedComment.date = res.data[0].cm_date
                    this.addedComment.addedComment = res.data[0].message
                }
            })
        },
        addComment() {
            this.comment.message = this.commentMessage;
            console.log(this.comment)
            AddComment(this.comment).then(res => {
                console.log(res);
                if (res.code === '200') {
                    const data = {
                        o_id: this.comment.o_id,
                        order_Stat: 5
                    }
                    changeOrderState(data).then(resp => {
                        if (resp.code === '200') {
                            const params = {
                                u_id: localStorage.getItem("u_id")
                            }
                            GetOrder(params).then(resp => {
                                console.log(resp.data);
                                this.orders = resp.data.list;
                            })
                            Toast("评价成功！")
                        } else {
                            Toast("评价失败！")
                        }
                    })
                } else {
                    Toast("评价失败！")
                }
            })
            this.show = false;
            this.commentMessage = '';
        },
        toOrderInfo(order) {
            console.log(order);
            this.$router.push({
                name: "orderInfo_user",
                params: {
                    orderInfo: order
                }
            })
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
