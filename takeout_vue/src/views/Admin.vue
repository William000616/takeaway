<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="12">
                <el-card shadow="hover">
                    <template #header>
                        <div class="clearfix">
                            <span>头像(点击直接修改)</span>
                        </div>
                    </template>
                    <div class="info">
                        <el-upload class="avatar-uploader" action="http://localhost:3000/file/upload"
                            :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                            <img :src="params.picSrc" class="avatar" />
                        </el-upload>
                        <el-button type="primary" @click="onSubmitPic">修改头像</el-button>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover">
                    <template #header>
                        <div class="clearfix">
                            <span>修改密码</span>
                        </div>
                    </template>
                    <el-form label-width="90px" :model="form" :rules="rules" ref="Create">
                        <el-form-item label="用户名："> {{ name }} </el-form-item>
                        <el-form-item label="旧密码：" prop="password">
                            <el-input type="password" v-model="form.password"></el-input>
                        </el-form-item>
                        <el-form-item label="新密码：" prop="newPassword">
                            <el-input type="password" v-model="form.newPassword"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">保存</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>
        </el-row>
        <el-dialog title="裁剪图片" v-model="dialogVisible" width="600px">
            <vue-cropper ref="cropper" :src="imgSrc" :ready="cropImage" :zoom="cropImage" :cropmove="cropImage"
                style="width: 100%; height: 400px"></vue-cropper>

            <template #footer>
                <span class="dialog-footer">
                    <el-button class="crop-demo-btn" type="primary">选择图片
                        <input class="crop-input" type="file" name="image" accept="image/*" @change="setImage" />
                    </el-button>
                    <el-button type="primary" @click="saveAvatar">上传并保存</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import { onMounted, reactive, ref } from "vue";
import { useStore } from "vuex";
import VueCropper from "vue-cropperjs";
import { ElMessage } from "element-plus";
import "cropperjs/dist/cropper.css";
import { userPicSrcEdit, UserLogin, GetAllUser, updatePassword } from "../api/index";
export default {
    name: "user",
    components: {
        VueCropper,
    },
    setup() {
        const store = useStore();
        const params = reactive({
            u_id: "",
            picSrc: ""
        });
        const Create = ref(null);
        const rules = {
            newPassword: [
                {
                    required: true,
                    message: "请输入新密码",
                    trigger: "blur",
                },
            ],
            password: [
                { required: true, message: "请输入原密码", trigger: "blur" },
            ],
        };
        const getData = () => {
            params.u_id = localStorage.getItem("u_id");
            GetAllUser(params).then((res) => {
                if (res.code === '200') {
                    params.picSrc = res.data.list[0].picSrc;
                    localStorage.setItem("head", params.picSrc);
                    store.commit('changeHead', params.picSrc);
                }

            });
        };
        getData();
        const handleAvatarSuccess = (res) => {
            if (res.code === '200') {
                params.picSrc = res.data
            }
        };
        const name = localStorage.getItem("ms_username");
        const form = reactive({
            username: "",
            password: "",
            newPassword: "",
        });
        const onSubmit = () => {
            Create.value.validate((valid) => {
                if (valid) {
                    form.username = localStorage.getItem("ms_username");
                    console.log(form);
                    UserLogin(form).then((res) => {
                        if (res.code === '200') {
                            updatePassword(form).then((res) => {
                                if (res.code === '200') {
                                    ElMessage.success("修改成功");
                                    form.newPassword = '';
                                    form.password = '';
                                }
                            })
                        } else {
                            ElMessage.error("原密码错误！");
                        }
                    })
                }
            })
        };
        const onSubmitPic = () => {
            userPicSrcEdit(params).then((res) => {
                if (res.code === '200') {
                    ElMessage.success("修改成功");
                    getData();
                }
            })
        };

        const avatarImg = ref("");
        const imgSrc = ref("");
        const cropImg = ref("");
        const dialogVisible = ref(false);
        const cropper = ref(null);

        const showDialog = () => {
            dialogVisible.value = true;
            imgSrc.value = avatarImg.value;
        };

        const setImage = (e) => {
            const file = e.target.files[0];
            if (!file.type.includes("image/")) {
                return;
            }
            const reader = new FileReader();
            reader.onload = (event) => {
                dialogVisible.value = true;
                imgSrc.value = event.target.result;
                cropper.value && cropper.value.replace(event.target.result);
            };
            reader.readAsDataURL(file);
        };

        const cropImage = () => {
            cropImg.value = cropper.value.getCroppedCanvas().toDataURL();
        };

        const saveAvatar = () => {
            avatarImg.value = cropImg.value;
            dialogVisible.value = false;
        };

        return {
            params,
            rules,
            name,
            form,
            Create,
            onSubmit,
            onSubmitPic,
            cropper,
            avatarImg,
            imgSrc,
            cropImg,
            showDialog,
            dialogVisible,
            setImage,
            cropImage,
            saveAvatar,
            getData,
            handleAvatarSuccess,
        };
    },
};
</script>

<style>
.info {
    text-align: center;
    padding: 35px 0;
}

.info-image {
    position: relative;
    margin: auto;
    width: 100px;
    height: 100px;
    background: #f8f8f8;
    border: 1px solid #eee;
    border-radius: 50px;
    overflow: hidden;
}

.info-image img {
    width: 100%;
    height: 100%;
}

.info-edit {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    opacity: 0;
    transition: opacity 0.3s ease;
}

.info-edit i {
    color: #eee;
    font-size: 25px;
}

.info-image:hover .info-edit {
    opacity: 1;
}

.info-name {
    margin: 15px 0 10px;
    font-size: 24px;
    font-weight: 500;
    color: #262626;
}

.crop-demo-btn {
    position: relative;
}

.crop-input {
    position: absolute;
    width: 100px;
    height: 40px;
    left: 0;
    top: 0;
    opacity: 0;
    cursor: pointer;
}

.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
    width: 180px;
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

/* .el-upload--text {
    width: 200px;
    height: 200px;
} */
</style>