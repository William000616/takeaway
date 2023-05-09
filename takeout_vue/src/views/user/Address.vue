<template>
    <div>
        <slot><van-nav-bar title="地址列表" left-text="返回" left-arrow @click-left="onClickLeft" /></slot>
        <div v-if="list.length === 0">
            <van-empty description="您还没有添加地址噢，点击新增地址添加吧~~" />
        </div>
        <van-address-list v-model="chosenAddressId" :list="list" :disabled-list="disabledList" default-tag-text="默认"
            @add="onAdd" @edit="onEdit" />
    </div>
</template>

<script>
/*引入Toast*/
import Toast from 'vant/lib/toast';
import { GetAddress } from "../../api/api.js";
export default {
    name: "Address",
    created() {
        const params = {
            u_id: localStorage.getItem("u_id")
        }
        GetAddress(params).then(resp => {
            this.list = resp.data
            if (this.list.length > 0) {
                this.list.map((item) => {
                    item.id = item.a_id

                })
                this.chosenAddressId = this.list[0].id
            }

        })
    },
    data() {
        return {
            chosenAddressId: '1',
            list: [
            ],
            disabledList: [
            ],
        };
    },
    methods: {
        onAdd() {
            Toast('新增地址');
            this.$router.push('/addressAdd')
        },
        onEdit(item, index) {
            let data = JSON.stringify(item);
            this.$router.push({
                name: 'addressEdit',
                params: {
                    addressMessage: data
                }
            })
        },
        onClickLeft() {
            this.$router.push('/my_user');
        }
    },
}
</script>

<style scoped></style>
