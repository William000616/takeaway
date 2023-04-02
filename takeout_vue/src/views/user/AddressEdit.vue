<template>
    <div>
        <slot><van-nav-bar title="地址编辑" left-text="返回" left-arrow @click-left="onClickLeft" /></slot>
        <van-address-edit :show-area="false" :address-info="addressInfo" save-button-text="保存修改" @save="onSave"
            @delete="onDelete" />
        <!--遮挡板-->
        <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100">

        </div>
        <!--显示搜索结果-->
        <!--:search-result="searchResult"-->
        <!--show-search-result-->
        <!--@change-detail="onChangeDetail"-->
    </div>
</template>

<script>
/*引入Toast*/
import Toast from 'vant/lib/toast';
import { areaList } from '@vant/area-data';
import { UpdateAddressById } from "../../api/api.js";
export default {
    name: "AddressEdit",
    created() {
        let data = JSON.parse(this.$route.params.addressMessage);
        this.addressInfo = data;
        let index = data.address.indexOf("区");
        if (index < 0) index = data.address.indexOf("县");
        this.addressInfo.addressDetail = data.address.substring(index + 1);
    },
    data() {
        return {
            addressInfo: null,
            areaList,
        };
    },
    methods: {
        onClickLeft() {
            this.$router.push('/address')
        },
        onSave(item) {
            item.address = item.addressDetail;
            UpdateAddressById(item).then(resp => {
                if (resp.code === "200") {
                    Toast("修改成功");
                } else {
                    Toast("修改失败")
                }
                this.$router.push("/address")
            })
        },
        onDelete() {
            Toast('delete');
        },
        /*onChangeDetail(val) {
            if (val) {
                this.searchResult = [
                    {
                        name: '黄龙万科中心',
                        address: '杭州市西湖区',
                    },
                ];
            } else {
                this.searchResult = [];
            }
        },*/
    },
}
</script>

<style scoped></style>
