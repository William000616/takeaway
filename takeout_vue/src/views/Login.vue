<template>
    <div id="login">
        <h1 style="display: flex; justify-content: center;margin-top: 20%;">餐饮外卖管理系统前台</h1>
        <h3 style="display: flex; justify-content: center;margin-top: 20%;">用户登录</h3>
        <van-form @submit="onSubmit">
            <div style="margin: 16px; border-style: solid; border-radius: 10px;border-color: dimgray;">
                <van-field v-model="username" name="username" label="用户名"
                    :rules="[{ required: true, message: '请填写用户名' }]" />
            </div>
            <div style="margin: 16px; border-style: solid; border-radius: 10px;border-color: dimgray;">
                <van-field v-model="password" type="password" name="password" label="密码"
                    :rules="[{ required: true, message: '请填写密码' }]" />
            </div>
            <div style="margin: 16px;">
                <van-button round block type="info" native-type="submit">登录</van-button>
            </div>
            <div style="margin: 16px;">
                <van-button round block type="info" @click="register()">用户注册</van-button>
            </div>
        </van-form>
    </div>
</template>

<script>

import Toast from "vant/lib/toast";
import { UserLogin } from "../api/api.js";
export default {
    data() {
        return {
            username: '',
            password: '',
        };
    },
    methods: {
        onSubmit(values) {
            UserLogin(values).then((res) => {
                if (res.code === '200') {
                    if (res.data.r_id === 1) {
                        if (res.data.stat === 1) {
                            this.$router.push('/user/index')
                            Toast("登录成功");
                            localStorage.setItem("r_id", res.data.r_id);
                            localStorage.setItem("u_id", res.data.u_id);
                            localStorage.setItem("ms_username", res.data.username);
                            localStorage.setItem("head", res.data.picSrc);
                        } else {
                            Toast("该账号已被停用！");
                        }

                    } else {
                        Toast('非普通用户账号！');
                    }

                } else {
                    Toast(res.msg);
                }
            })
        },
        register() {
            this.$router.push('/register')
        }
    },
};
</script>

<style scoped></style>
