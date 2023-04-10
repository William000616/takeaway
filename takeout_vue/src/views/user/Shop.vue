<template>
    <div>
        <div style="" v-if="showShopInfo">
            <!--返回按钮和搜索按钮、店铺背景-->
            <span style="margin-top: 10px;font-size: 25px; position: absolute;z-index: 10">
                <van-icon name="arrow-left" @click="back" />
            </span>
            <span style="margin-top: 10px;right: 10px; font-size: 25px; position: absolute;z-index: 10;">
                <van-icon name="search" @click="searchGoods" />
            </span>
            <van-image width="100%" height="150" :src="shopMessage.logo_src" style="position: relative" />


            <!--店面logo和相关信息-->
            <div
                style="top:80px;left: 30px;width: 80%;height: 100px;box-shadow:3px 3px 3px 2px rgba(0,0,0,.5);border-radius:10px;background-color: white;position: absolute;z-index: 10">
                <div style="width: 50px;display: inline-block;float: left">
                    <van-image width="50" height="50" :src="shopMessage.logo_src" round />
                </div>
                <div style="width: 150px;display: inline-block;margin-left: 5px;margin-top:5px;float: left">
                    <h3 style="margin: 0">{{ shopMessage.shop_name }}</h3>
                    <h6 style="margin: 0">月售{{ shopMessage.total_sales }}</h6>
                    <h6 style="margin: 0">配送时间约{{ shopMessage.delivery_time }}分钟</h6>
                </div>
                <!--<div style="width:100px">
                <h5>销量：{{shopMessage.totalSales}}</h5>
            </div>-->
            </div>
            <div style="margin-top: 30px">
                <!--商品和评价标签页-->
                <van-tabs v-model="activeName" @click="hide(activeName)">
                    <van-tab title="点餐" name="good" style="color: #409EFF;margin-left: 0;">
                        <el-tabs tab-position="left" style="width:100%;margin-top: 0; margin-bottom:0;height: 100%;">
                            <template v-for="(item, index1) in goodList">
                                <el-tab-pane :label="item.categoryName" type="border-card">
                                    <ul>
                                        <li v-for="(good, index2) in item.goods" :key="index2">
                                            <div style="border-radius: 10px;background-color: white;">
                                                <div style="display:inline-block;float: left">
                                                    <img :src="good.good_pic" width="70" height="70" alt="">
                                                </div>
                                                <div>
                                                    <h5 style="width: 200px;margin-bottom: 2px">{{ good.good_name }}</h5>
                                                    <p style="width: 220px;font-size: 10px;margin-top: 0">月售: {{ good.sales
                                                    }}
                                                    </p>
                                                    <div style="width: 100%;font-size: 15px;">
                                                        <span style="float: left;color: crimson;display: inline-block">￥{{
                                                            good.price }}</span>
                                                        <!--<van-stepper style="margin:10px;display:inline-block;" :default-value="0" theme="round" button-size="15" disable-input @plus="add" @minus="reduce"/>-->
                                                        <template>
                                                            <button style="border-radius: 50%;border: solid 1px"
                                                                @click="reduce(index1, index2)"
                                                                v-show="good.count > 0">-</button>
                                                            <input type="text" v-model="good.count"
                                                                style="border: none;width: 20px;text-align: center"
                                                                disabled="true" v-show="good.count > 0" />
                                                            <button style="border-radius: 50%;border: solid 1px; left: 0"
                                                                @click="add(index1, index2)">+</button>
                                                        </template>
                                                    </div>
                                                    <van-divider />
                                                </div>
                                            </div>

                                        </li>
                                    </ul>
                                </el-tab-pane>

                            </template>
                        </el-tabs>
                    </van-tab>

                    <van-tab title="评价" name="comment">
                        <template v-for="(item, index) in commentList ">
                            <div class="message-dist">
                                <div class="users-message">
                                    <div class="cont-name">
                                        <image src=""></image>
                                        <p>{{ item.username }}</p>
                                    </div>
                                    <div class="message-time">
                                        <p>{{ item.cm_date }}</p>
                                    </div>
                                </div>
                                <div class="cont-name-text">
                                    <p>{{ item.message }}</p>
                                </div>
                            </div>
                        </template>
                    </van-tab>

                    <!-- <van-tab title="商家" name="sell">


                    </van-tab> -->

                </van-tabs>
                <!--填充-->
                <div style="height:50px;display:block;"></div>
            </div>

            <!--购物车-->
            <div class="shoppingCartN" style="" v-if="shoppingCart">
                <div class="circleN" style="">
                    <!--购物车为空时-->
                    <div class="carN" style="" v-if="totalCount === null" @click="showSelectedGoods">
                        <van-icon name="shopping-cart" size="30" color="#80858a" :badge="totalCount" />
                    </div>
                    <!--购物车不为空时-->
                    <div class="carN" style="background-color: #00a0dc" v-if="totalCount > 0" @click="showSelectedGoods">
                        <van-icon name="shopping-cart" size="30" color="white" :badge="totalCount" />
                    </div>
                </div>
                <div style="border-right: 1px solid #51575f;left:55px;width: 80px;height: 30px;display: inline-block;position: absolute;margin: 10px 5px;color: #70757b"
                    v-if="total_price === 0">
                    ￥{{ good_total_price }}元
                </div>
                <div style="border-right: 1px solid #51575f;left:55px;width: 80px;height: 30px;display: inline-block;position: absolute;margin: 10px 5px;color: #fdfdfd"
                    v-if="total_price > 0">
                    ￥{{ good_total_price }}元
                </div>
                <div
                    style="left:150px;color: #70757b;font-size: 12px;display:inline-block;position: absolute;margin:12px 0 0 5px;height: 44px">
                    另需配送费￥{{ delivery_price }}
                </div>
                <div style="float: right;width: 80px;">
                    <van-button round size="large"
                        style="background-color: #70757b;border-color: #70757b;font-size: 12px;margin-top: 5px;height: 40px"
                        v-if="this.good_total_price === 0">￥{{ minPrice }}元起送</van-button>
                    <van-button round size="large"
                        style="color: white;background-color: #70757b;border-color: #70757b;font-size: 12px;margin-top: 5px;height: 40px"
                        v-if="this.good_total_price < this.minPrice">差￥{{ minPrice - good_total_price }}元起送</van-button>
                    <van-button round type="info" size="large" style="font-size: 12px;margin-top: 5px;height: 40px"
                        v-if="this.good_total_price >= this.minPrice" @click="toPay">去结算</van-button>
                </div>
            </div>

            <!--购物车详情-->
            <el-drawer :visible.sync="isShowSelectedGoods" direction="btt" :show-close="false" :with-header="false"
                :size="400" style="z-index: 10;">
                <div style="text-align: left">
                    <van-tag color="#ffe1e1" text-color="#ad0000"><span style="margin:10px;"> <van-icon
                                name="cart" />已选商品</span></van-tag>

                </div>
                <div style="height: 500px">
                    <template>
                        <ul>
                            <li v-for="(good, index) in selectedGoods" :key="index">
                                <div style="border-radius: 10px;background-color: white">
                                    <div style="display:inline-block;float: left">
                                        <img :src="good.good_pic" width="60" height="60" alt="">
                                    </div>
                                    <div>
                                        <h4 style="width: 160px;margin-bottom: 2px;margin-top: 10px">{{ good.good_name }}
                                        </h4>
                                        <div style="width: 100%;font-size: 15px;margin-top: 20px;height: 20px">
                                            <span
                                                style="float: left;color: crimson;display: inline-block;margin-left: 20px">￥{{
                                                    good.price * good.count }}</span>
                                            <div style="float: right;margin-right:10px">
                                                <button style="border-radius: 50%;border: solid 1px"
                                                    @click="reduceCar(index)" v-show="good.count > 0">-</button>
                                                <input type="text" v-model="good.count"
                                                    style="border: none;width: 20px;text-align: center" disabled="true"
                                                    v-show="good.count > 0" />
                                                <button style="border-radius: 50%;border: solid 1px; left: 0"
                                                    @click="addCar(index)">+</button>
                                            </div>
                                        </div>
                                        <van-divider />
                                    </div>
                                </div>

                            </li>
                        </ul>

                    </template>
                    <!--填充-->
                    <div style="height:50px;display:block;"></div>
                </div>
                <div>

                </div>
            </el-drawer>


            <!--遮挡板-->
            <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100"
                v-if="hider">

            </div>

        </div>

        <!--确认订单页面-->
        <div style="width: 100%;background-color:white;z-index: 99999;float:left" v-if="showPay">
            <van-nav-bar title="确认订单" left-text="返回" left-arrow @click-left="isShowPay" />
            <van-notice-bar left-icon="volume-o" text="节约粮食，从我做起，从现在做起。请适量点餐，避免浪费" />
            <div style="margin: 10px;border-radius: 10px;border: solid 1px">
                <div style="margin-bottom: 5px;" @click="chooseAddress">
                    <span v-if="addressMessage === null">
                        <h3
                            style="display:inline-block;margin-bottom:0;margin-top: 5px;margin-left: 5px;margin-right: 10px">
                            请选择收货地址</h3>
                    </span>
                    <span v-if="addressMessage !== null">
                        <div style="font-size: 13px;width: 150px;display: inline-block;">
                            <span>
                                {{ addressMessage.name }}
                            </span>
                            {{ addressMessage.tel }}
                            <span>
                            </span>
                            <div>
                                {{ addressMessage.address }}
                            </div>
                            <div>

                            </div>
                        </div>

                    </span>
                    <span style="margin-left: 120px;"><van-icon name="arrow" /></span>
                </div>
                <br>
                <div style="height: 40px;margin: 0;">
                    <span>
                        <h4
                            style="display:inline-block;margin-bottom:0;margin-top: 5px;margin-left: 5px;margin-right: 50px">
                            支付方式</h4>
                    </span>
                    <span style="margin-left: 100px;">微信<van-icon name="arrow" @click="choosePayType" /></span>

                </div>

            </div>

            <div style="margin: 10px;border-radius: 10px;border: solid 1px">
                <template>
                    <ul style="margin: 10px;">
                        <li v-for="(good, index) in selectedGoods" :key="index">
                            <div style="border-radius: 10px;background-color: white">
                                <div style="display:inline-block;float: left">
                                    <img :src="good.good_pic" width="60" height="60" alt="">
                                </div>
                                <div>
                                    <h4 style="width: 160px;margin-bottom: 2px;margin-top: 10px">{{ good.good_name }}</h4>
                                    <div style="width: 100%;font-size: 15px;margin-top: 20px;height: 20px">
                                        <span style="float: left;color: crimson;display: inline-block;margin-left: 20px">×{{
                                            good.count }}</span>
                                        <div style="float: right;margin-right:10px">
                                            <span
                                                style="float: left;color: crimson;display: inline-block;margin-left: 20px">￥{{
                                                    good.price * good.count }}</span>
                                        </div>
                                    </div>
                                    <van-divider />
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div>
                        <h4
                            style="width: 50px;margin-left: 10px;display: inline-block;margin-right: 180px;margin-top: 5px;margin-bottom: 5px">
                            配送费</h4>
                        <span>￥{{ delivery_price }}</span>
                    </div>

                </template>
            </div>
            <!--填充-->
            <div style="height:50px;display:block;"></div>

            <!--总价格+结算按钮-->
            <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #409EFF; z-index: 100">
                <van-submit-bar :price="(good_total_price * 100 + delivery_price * 100)" button-text="提交订单" @submit="submit"
                    button-type="info" />
            </div>
        </div>

        <!--选择收货地址页面-->
        <div v-if="showAddress">
            <div>
                <slot><van-nav-bar title="地址列表" left-text="返回" left-arrow @click-left="backPay" /></slot>
                <van-address-list v-model="chosenAddressId" :list="addressList" @add="onAdd" @select="onSelect" />
            </div>
        </div>

        <!--添加收货地址页面-->
        <div v-if="showAddAddress">
            <slot><van-nav-bar title="新的地址" left-text="返回" left-arrow @click-left="backChooseAddress" /></slot>
            <van-address-edit :show-area="false" show-set-default :area-columns-placeholder="['请选择', '请选择', '请选择']"
                @save="onSave" />
            <!--遮挡板-->
            <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100"
                v-if="showAddAddress">

            </div>
            <!--:search-result="searchResult"
            :search-result="searchResult"-->
        </div>

    </div>
</template>
<style scoped>
/*购物车为空时*/
.shoppingCartN {
    width: 100%;
    height: 50px;
    bottom: 0;
    position: fixed;
    background-color: #141d27;
    z-index: 9999;
}

.circleN {
    margin-left: 10px;
    width: 60px;
    height: 60px;
    background-color: #141d27;
    text-align: center;
    border-radius: 50%;
    position: absolute;
    bottom: 0
}

.carN {
    margin-top: 10px;
    background-color: #2b343c;
    border-radius: 50%;
    width: 40px;
    height: 40px;
    margin-left: 10px
}


.minPriceDesc {
    font-size: 12px;
    font-weight: 700;
    line-height: 48px;
}

.not-enough {
    background: #2b333b;
}

.enough {
    background: #00b43c;
    color: #fff
}

.users-message {
    display: flex;
    justify-content: space-between;
    height: 50px;
    align-items: center;
    margin: 0 20px;
}

.message-time {
    font-size: 20px;
    color: #A8A8A8;
}

.cont-name image {
    width: 50px !important;
    height: 50px !important;
    border-radius: 50%;
    margin-right: 9px;

}

.cont-name p {
    font-size: 20px;
    color: #A8A8A8;
}

.cont-name {
    display: flex;
    align-items: center;
    height: 50px;

}

.cont-name-text p {
    font-size: 20px;
    color: #333333;
    line-height: 1.5;
    /* display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; */
    display: flex;
    margin: 0 6%;
    text-align: left;
    overflow: hidden;
}

.message-dist {
    color: #cccccc;
    text-align: center;
}
</style>
<script>

import Toast from "vant/lib/toast";
import { Dialog } from 'vant';
import { areaList } from '@vant/area-data';
import { GetCategory, GetGood, GetAddress, CreateOrder, GetShopById, GetComment } from "../../api/api.js";
export default {
    name: "Shop",

    created() {
        const params = {
            s_id: this.$route.params.shop.s_id
        }
        GetGood(params).then(resp => {
            GetCategory(params).then((res) => {
                let goodList = resp.data;
                let categoryList = res.data;
                console.log(goodList, categoryList)
                goodList.map((item) => {
                    item.count = 0
                })
                let CategoryArr = [];
                goodList.map(mapItem => {
                    if (CategoryArr.length === 0) {
                        CategoryArr.push({ c_id: mapItem.c_id, goods: [mapItem] })
                    } else {
                        let res = CategoryArr.some(item => {
                            if (item.c_id == mapItem.c_id) {
                                item.goods.push(mapItem)
                                return true
                            }
                        })
                        if (!res) {
                            CategoryArr.push({ c_id: mapItem.c_id, goods: [mapItem] })
                        }
                    }
                })
                CategoryArr.map((item) => {
                    categoryList.map((e) => {
                        if (item.c_id === e.c_id) {
                            item.categoryName = e.category_name
                        }
                    })
                })
                console.log(CategoryArr)
                this.goodList = CategoryArr;
            })

        });
        GetComment(params).then(res => {
            console.log(res);
            if (res.code === '200') {
                this.commentList = res.data
            }
        })
        const param = {
            s_id: this.$route.params.shop.s_id
        }
        GetShopById(param).then(resp => {
            this.shopMessage = resp.data[0];
            this.minPrice = resp.data[0].min_cost;
            this.delivery_price = resp.data[0].delivery_cost;
        })
    },
    computed: {
        /*计算商品总价格*/
        good_total_price() {
            let good_total_price = 0;
            this.goodList.forEach(function (item) {
                item.goods.forEach(function (good) {
                    good_total_price += good.count * good.price;
                })
            });
            return good_total_price;
        },

        /*存放在购物车里的商品*/
        selectedGoods() {
            //存放good
            let goods = [];
            this.goodList.forEach(function (item) {
                item.goods.forEach(function (good) {
                    if (good.count > 0) {
                        goods.push(good);
                    }
                })
            });
            return goods;
        },


    },
    data() {
        return {

            orderInfo: {
                u_id: '',
                s_id: '',
                good_total_price: '',
                delivery_price: '',
                total_price: '',
                goods: '',
                addressMessage: '',
                shop_name: '',

            },

            /*显示店铺主页面*/
            showShopInfo: true,
            showPayMoney: false,

            /*所选择的收货地址*/
            addressMessage: null,

            /*默认显示请选择收货地址*/
            showChooseAddress: true,
            areaList,
            showAddAddress: false,
            showAddress: false,
            showPay: false,

            /*用来保存新增的address信息*/
            addressInfo: {
                /*(aId=0, areaCode=null, name=null, tel=null, addressDetail=null, province=null, city=null, county=null, u_id=1)*/
                u_id: localStorage.getItem("u_id"),
                areaCode: "",
                name: "",
                tel: "",
                addressDetail: "",
                province: "",
                city: "",
                county: ""
            },

            /*总商品个数*/
            totalCount: null,

            /*多少元起送*/
            minPrice: 0,

            /*当前总价：商品总价+配送费*/
            total_price: 0,

            /*配送费*/
            delivery_price: 0,

            shoppingCart: true,
            count: 0,
            hider: false,
            activeKey: "a",
            activeName: 'good',
            shopId: this.$route.params.shop.s_id,
            //当前登录用户id
            u_id: localStorage.getItem("u_id"),
            isShowSelectedGoods: false,
            chosenAddressId: 0,
            create_time: '',
            /*店铺信息*/
            shopMessage: '',
            commentList: [
                {
                    username: '张三',
                    message: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
                    cm_date: '130105',
                },
                {
                    username: '张三',
                    message: '13000000000',
                    cm_date: '130105',
                },
                {
                    username: '张三',
                    message: '13000000000',
                    cm_date: '130105',
                },
            ],

            addressList: [
                {
                    id: '1',
                    name: '张三',
                    tel: '13000000000',
                    areaCode: '130105',
                    address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
                    isDefault: true,
                },
                {
                    id: '2',
                    name: '李四',
                    tel: '1310000000',
                    address: '浙江省杭州市拱墅区莫干山路 50 号',
                },
            ],

            /*商品列表*/
            goodList: [
                {
                    categoryName: "优惠套餐",
                    goods: [
                        {
                            gid: '',
                            goodName: "法式汉堡",
                            desc: '法国现做汉堡',
                            goodPic: 'pic/dinner.png',
                            price: '1',
                            sales: '100',
                            c_id: '',
                            s_id: '',
                            count: 0
                        }
                    ]
                }
            ]
        }
    },

    methods: {
        /*提交订单,显示支付中加载框*/
        submit() {
            if (this.addressMessage === null) {
                Toast("请选择收货地址");
                return;
            }
            const that = this;
            this.orderInfo.u_id = that.u_id;
            this.orderInfo.s_id = that.shopId;
            this.orderInfo.good_total_price = that.good_total_price;
            this.orderInfo.delivery_price = that.delivery_price;
            this.orderInfo.total_price = (that.delivery_price * 1.0 + that.good_total_price * 1.0);
            this.orderInfo.addressMessage = that.addressMessage;
            this.orderInfo.shop_name = that.shopMessage.shop_name;
            this.orderInfo.goods = that.selectedGoods;
            console.log(this.orderInfo);

            //确认提示
            Dialog.confirm({
                title: '微信支付',
                message: '确认支付吗？',
            }).then(() => {
                // on confirm
                //Toast("提交订单");
                const toast = Toast.loading({
                    duration: 2000,
                    forbidClick: true,
                    message: "支付中..."
                });
                setTimeout(() => {
                    /*将订单信息发给后端*/

                    const data = { ...this.orderInfo, a_id: this.orderInfo.addressMessage.a_id }
                    console.log(data)
                    CreateOrder(data).then((res) => {
                        if (res.code === '200') {
                            console.log(1)
                            this.$router.push({
                                name: "paySuccess",
                                params: {
                                    o_id: res.data.o_id,
                                    order_Number: res.data.order_Number,
                                    good_total_price: res.data.good_total_price,
                                    delivery_price: res.data.delivery_price,
                                    total_price: res.data.total_price,
                                    // goods: this.selectedGoods,
                                    a_id: res.data.a_id,
                                    // shop_name: this.shopMessage.shop_name,
                                    order_Stat: res.data.order_Stat,
                                    create_time: res.data.create_time
                                }
                            });
                        } else {
                            Toast("由于网络或其他原因，创建订单失败！！！")
                        }
                    })
                }, 2000);
            });

        },

        /*选择收货地址*/
        onSelect(item) {
            console.log(item);
            this.addressMessage = item;
            console.log(this.addressMessage);
            this.showAddress = false;
            this.showPay = true;
            this.chosenAddressId = item.a_id
        },

        /*新增收货地址*/
        onSave(item) {
            /*let addressInfo = JSON.stringify(item);
            console.log(addressInfo);
            Toast(addressInfo);*/
            this.addressInfo.areaCode = item.areaCode;
            this.addressInfo.name = item.name;
            if (this.addressInfo.province === this.addressInfo.city) {
                this.addressInfo.addressDetail = item.province + "" + item.county + "" + item.addressDetail;
            } else {
                this.addressInfo.addressDetail = item.province + "" + item.city + "" + item.county + "" + item.addressDetail;
            }
            this.addressInfo.tel = item.tel;
            this.addressInfo.province = item.province;
            this.addressInfo.city = item.city;
            this.addressInfo.county = item.county;
            console.log(this.addressInfo.addressDetail);
            //console.log(this.addressInfo);
            this.axios.post("http://localhost:8084/addAddress",
                this.addressInfo
            ).then(resp => {
                console.log("sucess");
                Toast("添加成功！！！");
                setTimeout(() => {
                    this.showAddAddress = false;
                    this.chooseAddress();
                }, 2000);

            })
        },

        /*添加收货地址页面*/
        onAdd() {
            // this.showAddAddress = true;
            // this.showAddress = false;
            Toast("请到个人中心添加地址！");
        },

        /*返回选择收货地址*/
        backChooseAddress() {
            this.showAddress = true;
            this.showAddAddress = false;
        },

        /*返回订单界面*/
        backPay() {
            this.showPayMoney = false;
            this.showAddress = false;
            this.showPay = true;
        },

        /*点击选择收货地址，获取所有收货地址*/
        chooseAddress() {
            //Toast("选择收货地址");
            const params = {
                u_id: localStorage.getItem("u_id")
            }
            GetAddress(params).then(resp => {
                console.log(resp);
                this.addressList = resp.data
                this.showPay = false;
                this.showAddress = true;
                if (this.addressList.length > 0) {
                    this.addressList.map((item) => {
                        item.id = item.a_id
                    })
                    // this.chosenAddressId = this.addressList[0].id
                }

            })


        },

        /*选择送达时间*/
        chooseTime() {
            Toast("选择送达时间")
        },

        /*选择支付方式*/
        choosePayType() {
            Toast("选择支付方式")
        },

        /*返回店铺主页*/
        isShowPay() {
            this.showPay = false;
            this.showShopInfo = true;
        },

        /*显示购物车详情*/
        showSelectedGoods() {
            this.isShowSelectedGoods = !this.isShowSelectedGoods;
        },

        /*在购物车详情页面进行商品的修改*/
        addCar(index) {
            this.selectedGoods[index].count++;
            this.totalCount++;
        },


        reduceCar(index) {
            this.selectedGoods[index].count--;
            this.totalCount--;
        },


        /*去结算界面*/
        toPay() {
            console.log("进入结算界面");
            this.showPay = true;
            this.showShopInfo = false;

        },


        /*遮盖*/
        hide(index) {
            if (index === 'good') {
                this.shoppingCart = true;
                this.hider = false;
                console.log("进入点餐界面")
            } else if (index === 'comment') {
                this.shoppingCart = false;
                this.hider = true;
                console.log("进入评论界面")
            } else if (index === 'sell') {
                this.shoppingCart = false;
                this.hider = true;
                console.log("进入商家界面")
            }
        },


        /*点击添加按钮*/
        add(index1, index2) {
            let totalCount = null;
            const good = this.goodList[index1].goods[index2];
            good.count++;
            this.totalCount++;
            //Toast(this.goodList.category[index1].goods[index2].count);
        },

        /*点击减少按钮*/
        reduce(index1, index2) {
            const good = this.goodList[index1].goods[index2];
            good.count--;
            this.totalCount--;
            if (this.totalCount === 0) {
                this.totalCount = null;
            }
            //Toast(this.goodList.category[index1].goods[index2].count);

        },

        /*返回上一页*/
        back() {
            //this.$router.push('../views/user/Search.vue')
            //this.$router.go(-1);
            //this.$router.replace({name:'search'})
            history.go(-1)
        },

        showItem() {
            console.log(this.goodList[0])
        },

        searchGoods() {
            console.log("搜索")
        },
        /*购物车结算状态*/
        payDesc() {
            if (this.total_price === 0) {
                return `￥${this.minPrice}元起送`;
            } else if (this.total_price < this.minPrice) {
                let differPrice = this.minPrice - this.total_price;
                return `还差${differPrice}元起送`;
            } else if (this.total_price >= this.minPrice) {
                return '去结算';
            }
        },

    }
}
</script>


