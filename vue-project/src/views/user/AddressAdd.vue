<template>
    <div>
        <slot><van-nav-bar title="新的地址" left-text="返回" left-arrow @click-left="onClickLeft" /></slot>
        <van-address-edit :show-area="false" :address-info="addressInfo" save-button-text="新建" @save="onSave"
            @delete="onDelete" @change-detail="onChangeDetail" />
        <!--遮挡板-->
        <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100">

        </div>
        <!--:search-result="searchResult"
        :search-result="searchResult"-->
    </div>
</template>

<script>
/*引入Toast*/
import Toast from 'vant/lib/toast';
import { areaList } from '@vant/area-data';
import { CreateAddress } from "../../api/api.js";
export default {
    name: "AddressAdd",
    created() {

    },
    data() {
        return {
            areaList,
            addressInfo: {
                uid: '',
                address: '',
                tel: '',
                name: '',
            }
        };
    },
    methods: {

        onClickLeft() {
            this.$router.replace('/address')
        },

        onSave(item) {
            item.u_id = localStorage.getItem("u_id");
            item.address = item.addressDetail;
            console.log(item)
            CreateAddress(item).then(resp => {
                console.log(resp)
                if (resp.code === '200') {
                    Toast("创建成功");
                } else {
                    Toast("创建失败");
                }
                this.$router.push("/address")
            })
        },

        onDelete() {
            Toast('delete');
        },

        onChangeDetail(val) {
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
        },
    },
}
</script>

<style scoped></style>
