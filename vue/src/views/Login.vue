<template>
  <div class="wrapper">
    <el-card class="card">
      <div style="margin: 20px 20px;
          text-align: center;
          font-size: 24px;
          color: #99a9bf">
        <h2>SDUCSRP</h2>
      </div>
      <el-form :model="user"
               :rules="rules"
               ref="userForm">
        <el-form-item prop="username"
                      style="width: 70%;
                      margin-left: 15%">
          <el-input size="medium"
                    style="margin: 10px 0;"
                    prefix-icon="el-icon-user"
                    v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password"
                      style="width: 70%;
                      margin-left: 15%">
          <el-input size="medium"
                    style="margin: 10px 0"
                    prefix-icon="el-icon-lock"
                    show-password
                    v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin-top: 10px;
                      width: 70%;
                      margin-left: 15%;
                      text-align: center">
          <el-button type="success"
                     plain
                     autocomplete="off"
                     @click="login"
                     style="width: 70%;">
            现在进入
          </el-button>
          <el-button type="warning"
                     plain
                     size="small"
                     autocomplete="off"
                     @click="$router.push('/register')"
                     style="width: 20%;">
            注册
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import router from '../router/index.js'
import request from "../utils/request";
import animation from "../assets/js/background_black";
export default {
  name: "Login",
  components:{
  },
  data() {
    return {
      user: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
      },
      url:this.$route.query.url,
    }
  },
  created() {
    animation()
  },
  methods: {
    login() {
      request.post("/user/login", this.user).then(res => {
        if (res.code === '200') {
          localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器

          //决定登陆后去哪个界面
          if (this.url!=null){
            console.log('bbb'+this.url)
            let Base64 = require('js-base64').Base64;
            this.url=Base64.decode(this.url);
            console.log('bbb'+this.url)

            router.push(this.url);
          }else router.push('/')

        }
        else {
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
  margin: 200px auto;
  /*background-color: #fff;*/
  width: 550px;
  height: 350px;
  padding: 20px;
  background-color: rgba(155,155,155,0.5);
  border-style: none;
  /*border-radius: 10px;*/
}
.el-input >>> .el-input__inner {
  background-color: rgba(0, 0, 0, 0.2);
  color: white;
}
</style>