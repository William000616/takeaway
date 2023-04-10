<template>
    <div>
        <van-nav-bar title="个人信息" left-text="返回" left-arrow @click-left="onClickLeft" />
        <div>
            <h3>修改密码</h3>
            <van-form @submit="onSubmit">
                <van-field v-model="password" type="password" name="password" label="旧密码" placeholder="旧密码"
                    :rules="[{ required: true, message: '请填写旧密码' }]" />
                <van-field v-model="newPassword" type="password" name="newPassword" label="新密码" placeholder="新密码"
                    :rules="[{ required: true, message: '请填写新密码' }]" />
                <div style="margin: 16px;">
                    <van-button round block type="info" native-type="submit">提交</van-button>
                </div>
            </van-form>
        </div>
        <!--遮挡板-->
        <div style="width:100%; height: 50px;bottom: 0; position: fixed;background-color: #ffffff; z-index: 100">

        </div>
    </div>
</template>

<script>
import { Notify } from 'vant';
import Toast from "vant/lib/toast";
import { UserLogin, updatePassword } from "../../api/api.js";
export default {
    name: "Message",
    created() {
    },
    data() {
        return {
            user: '',
            uploader: [],
            username: '',
            password: '',
            newPassword: '',
        }
    },
    methods: {
        onSubmit(values) {
            console.log('submit:', values);
            const params = {
                username: localStorage.getItem("ms_username"),
                password: this.password,
                newPassword: this.newPassword
            }
            UserLogin(params).then((res) => {
                if (res.code === '200') {
                    updatePassword(params).then((res) => {
                        if (res.code === '200') {
                            Toast("修改成功");
                            this.newPassword = '';
                            this.password = '';
                        }
                    })
                } else {
                    Toast("原密码错误！");
                }
            })
        },

        onClickLeft() {
            this.$router.replace('/my_user');
        },

        /*上传图片文件*/
        uploadImg(file) {
            let data = new FormData();
            data.append('picture', file.file);
            this.axios.post("http://localhost:8084/upload", data, {
                headers: { "Content-Type": "multipart/form-data" }
            }).then(resp => {
                console.log("上传后");
                console.log(resp.data);
                this.user.picSrc = resp.data.message
            })
        }

    }
}
</script>

<style scoped></style>
