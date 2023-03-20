<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-calendar"></i> 我的店铺
                </el-breadcrumb-item>
                <el-breadcrumb-item>店铺信息</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="formRef" :rules="rules" :model="form" label-width="200px">
                    <el-form-item label="店铺名" prop="shop_name">
                        <el-input v-model="form.shop_name"></el-input>
                    </el-form-item>
                    <el-form-item label="店铺Logo" prop="logo_src">
                        <el-input v-model="form.logo_src"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" prop="phone">
                        <el-input v-model="form.phone"></el-input>
                    </el-form-item>
                    <el-form-item label="营业许可证" prop="license">
                        <el-input v-model="form.license" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="配送费￥" prop="delivery_cost">
                        <el-input v-model="form.delivery_cost"></el-input>
                    </el-form-item>
                    <el-form-item label="预估配送时间(分钟)" prop="delivery_time">
                        <el-input v-model="form.delivery_time"></el-input>
                    </el-form-item>
                    <el-form-item label="起送费￥" prop="min_cost">
                        <el-input v-model="form.min_cost"></el-input>
                    </el-form-item>
                    <el-form-item label="店铺详细地址" prop="address_detail">
                        <el-input v-model="form.address_detail"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
import { onMounted, reactive, ref } from "vue";
import { ElMessage } from "element-plus"; GetShopById
import { GetShopById, UpdateShop } from "../api/index";
export default {
    name: "baseform",
    setup() {
        const params = reactive({
            s_id: ""
        });
        const rules = {
            // name: [
            //     { required: true, message: "请输入表单名称", trigger: "blur" },
            // ],
        };
        const formRef = ref(null);
        const form = ref({
            s_id: "",
            address_detail: "",
            delivery_time: "",
            min_cost: "",
            stat: "",
            delivery_cost: "",
            phone: "",
            shop_name: "",
            license: "",
            logo_src: ""

        });
        const getData = () => {
            params.s_id = localStorage.getItem("s_id");
            GetShopById(params).then((res) => {
                if (res.code === '200') {
                    var list = res.data
                    form.value = list[0];
                }

            });
        };
        getData();

        // 提交
        const onSubmit = () => {
            // 表单校验
            formRef.value.validate((valid) => {
                if (valid) {
                    console.log(form.value);
                    UpdateShop(form.value).then((res) => {
                        if (res.code === '200') {
                            ElMessage.success("修改成功！");
                            getData();
                        }
                    })

                } else {
                    return false;
                }
            });
        };
        // 重置
        const onReset = () => {
            formRef.value.resetFields();
        };

        return {
            rules,
            formRef,
            form,
            onSubmit,
            onReset,
        };
    },
};
</script>