<template>
    <div>
        <h1 style="display: flex; justify-content: center;margin-top: 10%;">餐饮外卖管理系统前台</h1>

        <van-form @submit="onSubmit">

            <div style="margin: 16px;">
                <div style="display: flex; justify-content: center;margin-top: 5%;">
                    <van-uploader :after-read="afterRead">
                        <van-image round width="150" height="150" :src="picSrc" />
                    </van-uploader>
                </div>
            </div>
            <p style="display: flex; justify-content: center;">点击上传头像</p>
            <div style="margin: 16px; border-style: solid; border-radius: 10px;border-color: dimgray;">
                <van-field v-model="username" name="username" label="用户名"
                    :rules="[{ required: true, message: '请填写用户名' }]" />
            </div>
            <div style="margin: 16px; border-style: solid; border-radius: 10px;border-color: dimgray;">
                <van-field v-model="password" type="password" name="password" label="密码"
                    :rules="[{ required: true, message: '请填写密码' }]" />
            </div>
            <div style="margin: 16px; border-style: solid; border-radius: 10px;border-color: dimgray;">
                <van-field v-model="name" name="name" label="姓名" :rules="[{ required: true, message: '请填写姓名' }]" />
            </div>
            <div style="margin: 16px; border-style: solid; border-radius: 10px;border-color: dimgray;">
                <van-field v-model="phone" name="phone" label="联系方式" :rules="[{ required: true, message: '请填写联系方式' }]" />
            </div>
            <div style="margin: 16px;">
                <van-button round block type="info" native-type="submit">注册</van-button>

            </div>
            <div style="margin: 16px;">
                <van-button round block type="info" @click="onClickLeft">返回</van-button>
            </div>
        </van-form>
    </div>
</template>

<script>

import Toast from "vant/lib/toast";
import { UserRegister, Upload } from "../api/api.js";
export default {
    data() {
        return {
            username: '',
            password: '',
            name: '',
            phone: '',
            picSrc: '',
        };
    },
    methods: {
        onSubmit(values) {
            const data = { ...values, picSrc: this.picSrc }
            UserRegister(data).then((res) => {
                if (res.code === '200') {
                    this.$router.push('/login')
                    Toast("注册成功！");
                } else {
                    Toast("用户已存在！")
                }
            })
        },
        afterRead(file) {
            let formData = new FormData();
            formData.append('file', file.file);
            console.log(formData);
            Upload(formData).then(res => {
                console.log(res)
                if (res.code === '200') {
                    this.picSrc = res.data
                }

            })


        },
        onClickLeft() {
            this.$router.push("/login");
        }
    },
};
</script>

<style scoped></style>
