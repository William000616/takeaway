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
                        <el-upload class="avatar-uploader" action="http://localhost:3000/file/upload"
                            :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                            <img v-if="form.logo_src" :src="form.logo_src" class="avatar" />
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="联系电话" prop="shop_phone">
                        <el-input v-model="form.shop_phone"></el-input>
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
            shop_phone: "",
            shop_name: "",
            license: "",
            logo_src: ""

        });
        const handleAvatarSuccess = (res) => {
            if (res.code === '200') {
                form.value.logo_src = res.data
            }
        };
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
            handleAvatarSuccess,
        };
    },
};
</script>
<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}

.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>