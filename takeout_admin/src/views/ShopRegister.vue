<template>
    <div>
        <div class="header">
            <div class="collapse-btn" @click="back">
                <i class="el-icon-lx-back"></i>
            </div>
            <div class="logo">商家注册</div>
        </div>
        <div class="container">
            <el-steps :active="active" finish-status="success" align-center>
                <el-step title="商家账号注册" />
                <el-step title="店铺申请" />
            </el-steps>
            <div class="form-box">
                <el-form ref="formRef1" :rules="rules1" :model="form1" label-width="200px" v-show="active === 0">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="form1.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="form1.password"></el-input>
                    </el-form-item>
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="form1.name"></el-input>
                    </el-form-item>
                    <el-form-item label="身份证号" prop="identity_number">
                        <el-input v-model="form1.identity_number"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                        <el-input v-model="form1.phone"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="next">下一步</el-button>
                    </el-form-item>
                </el-form>
                <el-form ref="formRef2" :rules="rules2" :model="form2" label-width="200px" v-show="active === 1">
                    <el-form-item label="店铺名" prop="shop_name">
                        <el-input v-model="form2.shop_name"></el-input>
                    </el-form-item>
                    <el-form-item label="店铺Logo" prop="logo_src">
                        <el-upload class="avatar-uploader" action="http://localhost:3000/file/upload"
                            :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                            <img v-if="form2.logo_src" :src="form2.logo_src" class="avatar" />
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="营业许可证" prop="license">
                        <el-input v-model="form2.license"></el-input>
                    </el-form-item>
                    <el-form-item label="配送费" prop="delivery_cost">
                        <el-input v-model="form2.delivery_cost"></el-input>
                    </el-form-item>
                    <el-form-item label="起送费" prop="min_cost">
                        <el-input v-model="form2.min_cost"></el-input>
                    </el-form-item>
                    <el-form-item label="配送时间" prop="delivery_time">
                        <el-input v-model="form2.delivery_time"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" prop="shop_phone">
                        <el-input v-model="form2.shop_phone"></el-input>
                    </el-form-item>
                    <el-form-item label="详细地址" prop="address_detail">
                        <el-input v-model="form2.address_detail"></el-input>
                    </el-form-item>
                    <el-form-item prop="checked">
                        <el-checkbox
                            v-model="form2.checked">勾选表示您同意注册成为本系统的入驻商家，并同意管理员到您提供的详细地址考察以及对您提供的营业许可证进行核验。</el-checkbox>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="back0">上一步</el-button>
                        <el-button type="primary" @click="next">注册</el-button>
                    </el-form-item>
                </el-form>

            </div>
        </div>
    </div>
</template>

<script>
import { onMounted, reactive, ref } from "vue";
import { ElMessage } from "element-plus";
import { shopRegister, shopManagerRegister, } from "../api/index";
import { useRouter } from "vue-router";
export default {
    name: "baseform",
    setup() {
        const router = useRouter();
        const back = () => {
            router.push("/Login");
        }
        const params = reactive({
            s_id: ""
        });
        const rules = {
            name: [
                { required: true, message: "请输入真实姓名", trigger: "blur" },
            ],
            phone: [
                { required: true, message: "请输入电话号码", trigger: "blur" },
            ],
            password: [
                { required: true, message: "请输入密码", trigger: "blur" },
            ],
            username: [
                { required: true, message: "请输入用户名", trigger: "blur" },
            ],
            identity_number: [
                { required: true, message: "请输入身份证号", trigger: "blur" },
            ],
            shop_name: [
                { required: true, message: "请输入店铺名称", trigger: "blur" },
            ],
            logo_src: [
                { required: true, message: "请上传店铺logo", trigger: "blur" },
            ],
            license: [
                { required: true, message: "请输入营业许可证", trigger: "blur" },
            ],
            delivery_cost: [
                { required: true, message: "请输入配送费", trigger: "blur" },
            ],
            min_cost: [
                { required: true, message: "请输入起送费", trigger: "blur" },
            ],
            delivery_time: [
                { required: true, message: "请输入配送时间", trigger: "blur" },
            ],
            shop_phone: [
                { required: true, message: "请输入店铺联系电话", trigger: "blur" },
            ],
            address_detail: [
                { required: true, message: "请输入详细地址", trigger: "blur" },
            ],

        };
        const rules1 = {
            name: [
                { required: true, message: "请输入真实姓名", trigger: "blur" },
            ],
            phone: [
                { required: true, message: "请输入电话号码", trigger: "blur" },
            ],
            password: [
                { required: true, message: "请输入密码", trigger: "blur" },
            ],
            username: [
                { required: true, message: "请输入用户名", trigger: "blur" },
            ],
            identity_number: [
                { required: true, message: "请输入身份证号", trigger: "blur" },
            ],

        };
        const rules2 = {
            shop_name: [
                { required: true, message: "请输入店铺名称", trigger: "blur" },
            ],
            logo_src: [
                { required: true, message: "请上传店铺logo", trigger: "blur" },
            ],
            license: [
                { required: true, message: "请输入营业许可证", trigger: "blur" },
            ],
            delivery_cost: [
                { required: true, message: "请输入配送费", trigger: "blur" },
            ],
            min_cost: [
                { required: true, message: "请输入起送费", trigger: "blur" },
            ],
            delivery_time: [
                { required: true, message: "请输入配送时间", trigger: "blur" },
            ],
            shop_phone: [
                { required: true, message: "请输入店铺联系电话", trigger: "blur" },
            ],
            address_detail: [
                { required: true, message: "请输入详细地址", trigger: "blur" },
            ],
            address_detail: [
                { required: true, message: "请输入详细地址", trigger: "blur" },
            ],
            checked: [
                { type: "array", required: true, message: "请勾选", trigger: "blur" },
            ],

        };
        const formRef = ref(null);
        const formRef1 = ref(null);
        const formRef2 = ref(null);
        const active = ref(0)

        const next = () => {
            if (active.value === 0) {
                formRef1.value.validate((valid) => {
                    if (valid) {
                        active.value++
                    }
                })
            } else {
                formRef2.value.validate((valid) => {
                    if (valid) {
                        onSubmit();
                    }
                })
            }
        }
        const back0 = () => {
            active.value--;
        }
        const form = ref({
            password: "",
            phone: "",
            name: "",
            username: "",
            identity_number: "",
            shop_name: "",
            logo_src: "",
            license: "",
            delivery_cost: "",
            min_cost: "",
            delivery_time: "",
            shop_phone: "",
            address_detail: "",
            s_m_id: 0,

        });
        const form1 = ref({
            password: "",
            phone: "",
            name: "",
            username: "",
            identity_number: "",


        });
        const form2 = ref({

            shop_name: "",
            logo_src: "",
            license: "",
            delivery_cost: "",
            min_cost: "",
            delivery_time: "",
            shop_phone: "",
            address_detail: "",
            s_m_id: 0,
            checked: [],

        });

        const handleAvatarSuccess = (res) => {
            if (res.code === '200') {
                form2.value.logo_src = res.data
            }
        };

        // 提交
        const onSubmit = () => {
            form.value = { ...form1.value, ...form2.value }
            shopManagerRegister(form.value).then((res) => {
                if (res.code === '200') {
                    form.value.s_m_id = res.data
                    shopRegister(form.value).then((res) => {
                        if (res.code === '200') {
                            router.push("/Login");
                            ElMessage.success('注册成功！');
                        } else {
                            ElMessage.error('注册失败！');
                        }
                    })
                } else {
                    ElMessage.error('注册失败！');
                }
            })
        };

        return {
            rules,
            formRef,
            formRef1,
            formRef2,
            form,
            form1,
            form2,
            back,
            onSubmit,
            handleAvatarSuccess,
            next,
            active,
            rules1,
            rules2,
            back0,
        };
    },
};
</script>
<style >
.header {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    height: 70px;
    font-size: 22px;
    color: #fff;
}

.collapse-btn {
    float: left;
    padding: 0 21px;
    cursor: pointer;
    line-height: 70px;
}

.header .logo {
    float: left;
    width: 250px;
    line-height: 70px;
}

.header-right {
    float: right;
    padding-right: 50px;
}

.header-user-con {
    display: flex;
    height: 70px;
    align-items: center;
}

.btn-fullscreen {
    transform: rotate(45deg);
    margin-right: 5px;
    font-size: 24px;
}

.btn-bell,
.btn-fullscreen {
    position: relative;
    width: 30px;
    height: 30px;
    text-align: center;
    border-radius: 15px;
    cursor: pointer;
}

.btn-bell-badge {
    position: absolute;
    right: 0;
    top: -2px;
    width: 8px;
    height: 8px;
    border-radius: 4px;
    background: #f56c6c;
    color: #fff;
}

.btn-bell .el-icon-bell {
    color: #fff;
}

.user-name {
    margin-left: 10px;
}

.user-avator {
    margin-left: 20px;
}

.user-avator img {
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}

.el-dropdown-link {
    color: #fff;
    cursor: pointer;
}

.el-dropdown-menu__item {
    text-align: center;
}

.container {
    overflow: scroll;
    height: 85vh;
}

.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
    width: 178px;
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
    width: 178px;
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