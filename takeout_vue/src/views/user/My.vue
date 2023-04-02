<template>
    <div>
        <div style="height: 150px;margin-top: 70px">
            <div style="">
                <van-uploader :after-read="afterRead">
                    <van-image round width="150" height="150" :src="user.picSrc" />
                </van-uploader>
            </div>
            <div style="margin-top: 50px">
                <van-tag color="#ffe1e1" size="large" text-color="#ad0000">vvvip</van-tag>
                <span style="color: #409EFF;" v-model="user.username"> {{ user.username }}</span>
            </div>
            <div style=" margin-top: 20px">
                <van-button round type="info" style="width: 200px" @click="updateMessage">修改密码</van-button>
                <br>
                <br>
                <van-button round type="info" style="width: 200px" @click="getAddress">地址管理</van-button>
                <br>
                <br>
                <van-button round type="info" style="width: 200px" @click="loginOut">退出登录</van-button>
            </div>

        </div>

    </div>
</template>

<script>
import Toast from "vant/lib/toast";
import { Upload, userPicSrcEdit } from "../../api/api.js";
export default {
    name: "My",
    created() {
        this.user.picSrc = localStorage.getItem("head");
        this.user.username = localStorage.getItem("ms_username");
    },
    data() {
        return {
            username: 'lllllky',
            password: '123456',
            score: 12,
            user: {
                picSrc: ''
            }
        }
    },
    methods: {
        updateMessage() {
            console.log("修改个人信息");
            this.$router.push({
                name: 'message',
                params: {
                    user: this.user
                }
            });
        },
        afterRead(file) {
            // 此时可以自行将文件上传至服务器
            let formData = new FormData();
            formData.append('file', file.file);
            Upload(formData).then(res => {
                if (res.code === '200') {
                    this.user.picSrc = res.data
                    const params = {
                        picSrc: this.user.picSrc,
                        u_id: localStorage.getItem("ms_username")
                    }
                    userPicSrcEdit(params).then((resp) => {
                        if (res.code === '200') {

                            localStorage.setItem("head", res.data);
                            Toast("头像修改成功！");
                        }
                    })

                }

            })


        },
        getAddress() {
            this.$router.push('/address');
        },

        //退出登录
        loginOut() {
            Toast("退出登录");
            this.$router.push("/login")
        }
    }
}
</script>

<style scoped></style>
