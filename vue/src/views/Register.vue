<template>
  <div class="wrapper">
    <el-card class="card">

      <div style="margin: 20px 0;
            text-align: center;
            font-size: 24px;
            color: #99a9bf">
        <b>注 册</b>
      </div>

      <el-form :model="user"
               :rules="rules"
               ref="userForm" >
        <el-form-item prop="username"
                      style="width: 70%;
                      margin-left: 15%">
          <el-input placeholder="请输入学号"
                    size="medium"
                    style="margin: 5px 0"
                    prefix-icon="el-icon-user"
                    v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password"
                      style="width: 70%;
                      margin-left: 15%">
          <el-input placeholder="请输入密码"
                    size="medium"
                    style="margin: 5px 0"
                    prefix-icon="el-icon-lock"
                    show-password
                    v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword"
                      style="width: 70%;
                      margin-left: 15%">
          <el-input placeholder="请确认密码"
                    size="medium"
                    style="margin: 5px 0"
                    prefix-icon="el-icon-lock"
                    show-password
                    v-model="user.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item prop="realname"
                      style="width: 70%;
                      margin-left: 15%">
          <el-input placeholder="请输入姓名"
                    size="medium"
                    style="margin: 5px 0"
                    prefix-icon="el-icon-edit"
                    v-model="user.realname"></el-input>
        </el-form-item>
        <el-form-item prop="phone"
                      style="width: 70%;
                      margin-left: 15%">
          <el-input placeholder="请输入电话"
                    size="medium"
                    style="margin: 5px 0"
                    prefix-icon="el-icon-phone-outline"
                    v-model="user.phone"></el-input>
        </el-form-item>
        <el-form-item style="margin: 5px 0;
                      text-align: center;
                      width: 70%;
                      margin-left: 15%;">
          <el-button type="success"
                     plain
                     style="width: 60%"
                     autocomplete="off"
                     @click="register">
            注册
          </el-button>
          <el-button type="warning"
                     plain
                     size="small"
                     autocomplete="off"
                     @click="$router.push('/login')"
                     style="width: 30%;">
            返回登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import request from "../utils/request";
import router from "@/router";
import animation from "../assets/js/background_black";
export default {
  name: "Login",
  data() {
    return {
      user: {},
      rules: {
        username: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        realname: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { min: 11, max: 11, message: '长度为11', trigger: 'blur' }
        ],
      }
    }
  },
  created() {
    animation()
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.$message.error("两次输入的密码不一致")
        return false
      }
      if (this.user.phone.length != 11) {
        this.$message.error("电话号码出错")
        return false
      }
      request.post("/user/register", this.user).then(res => {
        if(res.code === '200') {
          this.$message.success("注册成功")
          router.push("/login")
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background: rgba(255,255,255,0.2);
}
.card{
  margin: 100px auto;
  background-color: #fff;
  width: 500px;
  height: 520px;
  padding: 20px;
  background-color: rgba(155,155,155,0.5);
  border-style: none;
}
.el-input >>> .el-input__inner {
  background-color: rgba(0, 0, 0, 0.2);
  color: white;
}
</style>


