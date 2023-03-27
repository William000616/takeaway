<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">餐饮外卖管理系统</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.username" placeholder="username">
                        <template #prepend>
                            <el-button icon="el-icon-user"></el-button>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="param.password" @keyup.enter="submitForm()">
                        <template #prepend>
                            <el-button icon="el-icon-lock"></el-button>
                        </template>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
                <div class="login-btn">
                    <el-button type="primary" @click="register()">注册</el-button>
                </div>
                <el-radio-group v-model="radio">
                    <el-radio :label="3">顾客</el-radio>
                    <el-radio :label="6">商家</el-radio>
                    <el-radio :label="9">管理员</el-radio>
                </el-radio-group>
                <p class="login-tips"></p>
            </el-form>
        </div>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import { UserLogin, ManagerLogin } from "../api/index";
export default {
    setup() {
        const router = useRouter();
        const param = reactive({
            username: "",
            password: "",
        });

        const rules = {
            username: [
                {
                    required: true,
                    message: "请输入用户名",
                    trigger: "blur",
                },
            ],
            password: [
                { required: true, message: "请输入密码", trigger: "blur" },
            ],
        };
        const login = ref(null);
        const radio = ref(3)
        const register = () => {
            if (radio.value === 3) {
                router.push("/register");
                localStorage.setItem("ms_username", 'param.username');
            } else if (radio.value === 6) {
                router.push("/shopRegister");
            } else {
                ElMessage.error('不能注册管理员账号！');
            }
        }
        const submitForm = () => {
            login.value.validate((valid) => {
                if (valid) {
                    if (radio.value === 3) {
                        UserLogin(param).then((res) => {
                            if (res.code === '200') {
                                if (res.data.r_id === 1) {
                                    if (res.data.stat === 1) {
                                        router.push("/allShop");
                                        ElMessage.success("登录成功");
                                        localStorage.setItem("r_id", res.data.r_id);
                                        localStorage.setItem("u_id", res.data.u_id);
                                        localStorage.setItem("ms_username", res.data.username);
                                        localStorage.setItem("head", res.data.picSrc);
                                    } else {
                                        ElMessage.error("该账号已被停用！");
                                    }

                                } else {
                                    ElMessage.error('非普通用户账号！');
                                }
                            } else {
                                ElMessage.error(res.msg);
                            }
                        })
                    } else if (radio.value === 6) {
                        ManagerLogin(param).then((res) => {
                            if (res.code === '200') {
                                if (res.data.stat === 1) {
                                    router.push("/shopInfoForm");
                                    ElMessage.success("登录成功");
                                    localStorage.setItem("r_id", res.data.r_id);
                                    localStorage.setItem("s_id", res.data.s_id);
                                    localStorage.setItem("ms_username", res.data.username);
                                    localStorage.setItem("head", "res.data.picSrc");
                                } else {
                                    ElMessage.error("该账号已被停用！");
                                }

                            } else {
                                ElMessage.error(res.msg);
                            }
                        })

                    } else {
                        UserLogin(param).then((res) => {
                            if (res.code === '200') {
                                if (res.data.r_id === 3) {
                                    router.push("/dashboard");
                                    ElMessage.success("登录成功");
                                    localStorage.setItem("u_id", res.data.u_id);
                                    localStorage.setItem("r_id", res.data.r_id);
                                    localStorage.setItem("ms_username", 'admin');
                                    localStorage.setItem("head", res.data.picSrc);
                                } else {
                                    ElMessage.error('非管理员账号！');
                                }

                            } else {
                                ElMessage.error(res.msg);
                            }
                        })

                    }
                }
            })


        };
        const store = useStore();
        store.commit("clearTags");

        return {
            param,
            rules,
            login,
            submitForm,
            register,
            radio,
        };
    },
};
</script>

<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../assets/img/login.jpg);
    background-size: 100%;
}

.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 25px;
    color: #000;
    border-bottom: 1px solid #ddd;
}

.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.8);
    overflow: hidden;
}

.ms-content {
    padding: 30px 30px;
}

.login-btn {
    text-align: center;
}

.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}

.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #fff;
}
</style>