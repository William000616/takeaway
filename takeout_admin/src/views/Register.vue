<template>
    <div>
        <div class="header">
            <div class="collapse-btn" @click="back">
                <i class="el-icon-lx-back"></i>
            </div>
            <div class="logo">用户注册</div>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="formRef" :rules="rules" :model="form" label-width="200px">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="form.username"></el-input>
                    </el-form-item>
                    <el-form-item label="头像" prop="picSrc">
                        <el-upload class="avatar-uploader" action="http://localhost:3000/file/upload"
                            :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                            <img v-if="form.picSrc" :src="form.picSrc" class="avatar" />
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="form.password"></el-input>
                    </el-form-item>
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                        <el-input v-model="form.phone"></el-input>
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
import { GetShopById, UserRegister } from "../api/index";
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
            password: [
                { required: true, message: "请输入密码", trigger: "blur" },
            ],
            username: [
                { required: true, message: "请输入用户名", trigger: "blur" },
            ],
            name: [
                { required: true, message: "请输入姓名", trigger: "blur" },
            ],
            phone: [
                { required: true, message: "请输入电话号码", trigger: "blur" },
            ],
            picSrc: [
                { required: true, message: "请上传头像", trigger: "blur" },
            ],
        };
        const formRef = ref(null);
        const form = ref({
            password: "",
            phone: "",
            name: "",
            username: "",
            picSrc: ""

        });
        const handleAvatarSuccess = (res) => {
            if (res.code === '200') {
                form.value.picSrc = res.data
            }
        };
        // 提交
        const onSubmit = () => {
            // 表单校验
            formRef.value.validate((valid) => {
                if (valid) {
                    console.log(form.value);
                    UserRegister(form.value).then((res) => {
                        if (res.code === '200') {
                            router.push("/Login");
                            ElMessage.success('注册成功！');
                        } else {
                            ElMessage.error('注册失败！');
                        }
                    })


                } else {
                    return false;
                }
            });
        };

        return {
            rules,
            formRef,
            form,
            back,
            onSubmit,
            handleAvatarSuccess,
        };
    },
};
</script>
<style scoped>
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