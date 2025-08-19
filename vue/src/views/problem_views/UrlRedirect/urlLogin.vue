<template>
  <el-image
      :src="imgsrc4"
      style="height:30px;
                    width:15%;
                    margin-right: 70%;
                    margin-top: 1%;
                    margin-bottom: 2%;
                    "/>
  <div id='building1'>
    <el-card
        style="width: 25%;
        background-color: rgba(255,255,255,0.7);
        margin-left: 60%;
        margin-top: 10%"
    >
      <div class="bf_form">
        <div class="bf_form_main">
          <el-row style="margin-left: 18%;
                            margin-top: 2%"
          >
              <span style="font-size: 15px;

                          /*color: white*/
                          " class="header blue lighter bigger"
              >
                密码登录
              </span>
          </el-row>
          <el-row style="margin-left: 18%;
                            margin-top: 4%"
          >
            <el-input v-model="username"
                      style="width: 250px;
                        margin-right: 50px"
                      placeholder="username"
            >
            </el-input>
          </el-row>
          <el-row style="margin-left: 18%;
                            margin-top: 3%"
          >
            <el-input v-model="password"
                      style="width: 250px;
                        margin-right: 50px"
                      placeholder="password"
            >
            </el-input>

          </el-row>
          <el-row style="margin-left: 40%;
                            margin-top: 5%">
            <el-button @click="login">
              登录
            </el-button>
          </el-row>
          <el-row>
              <span
                  style="margin-top: 5%;
                  font-size: 10px;
                  margin-left: 25%">
                Tips:尝试使用urlLogin1页面进行登录？
              </span>
          </el-row>
        </div><!-- /.widget-main -->
      </div><!-- /.widget-body -->
    </el-card>
  </div>

</template>

<script>
import router from '../../../router/index'
import request from "@/utils/request";

export default {
  data() {
    return {
      imgsrc4: require('@/assets/img/bugimg/tianmaologo.png'),

      username:'',
      password:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods: {
    login() {
      request.get("/op1/login", {
        params: {
          username: this.username,
          password: this.password,
          username1:this.user.username
        }
        // if (res.data=="s") {
        //   //localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
        //   router.push("/")
        // } else {
        //   this.$message.error(res.msg)
        // }
      }).then(res => {
        if(res.data=="success"){
          router.push("/op1_person_center")
        }
        else if(res.data=="no"){
          alert("该用户未注册")
        }
        else if(res.data=="fail"){
          alert("用户名或密码错误")
        }
        else if(res.data=="op"){
          alert("禁止使用该用户登录")
        }
      })
    }
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #000000, 	#483D8B);
  overflow: hidden;
}

#building1{
  background:url("../../../assets/img/bugimg/tianmao6.jpg");
  width:110%;
  height:90%;
  position:fixed;
  background-size:100% 100%;
}
</style>

<!--<template>-->
<!--  <div class="wrapper">-->
<!--    <el-card class="card">-->
<!--      <div style="margin: 20px 0; text-align: center; font-size: 24px;color: #99a9bf"><b>SDUCSRP</b></div>-->
<!--      <el-form :model="user" :rules="rules" ref="userForm">-->
<!--        <el-form-item prop="username" style="width: 70%; margin-left: 15%">-->
<!--          <el-input size="medium" style="margin: 10px 0;" prefix-icon="el-icon-user" v-model="user.username"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item prop="password" style="width: 70%; margin-left: 15%">-->
<!--          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item style="margin-top: 10px; width: 70%; margin-left: 15%; text-align: center">-->
<!--          <el-button type="success" plain autocomplete="off" @click="login" style="width: 70%;">-->
<!--            现在进入-->
<!--          </el-button>-->
<!--          <el-button type="warning" plain size="small" autocomplete="off" @click="$router.push('/register')" style="width: 20%;">-->
<!--            注册-->
<!--          </el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </el-card>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import router from '../../../router/index.js'-->
<!--// import request from "../../../utils/request";-->
<!--import request from "@/utils/request";-->
<!--import animation from "../../../assets/js/background_black";-->
<!--export default {-->
<!--  name: "Login",-->
<!--  components: {},-->
<!--  data() {-->
<!--    return {-->
<!--      user: {},-->
<!--      rules: {-->
<!--        username: [-->
<!--          {required: true, message: '请输入用户名', trigger: 'blur'},-->
<!--          {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'}-->
<!--        ],-->
<!--        password: [-->
<!--          {required: true, message: '请输入密码', trigger: 'blur'},-->
<!--          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}-->
<!--        ],-->
<!--      },-->
<!--      url: this.$route.query.url,-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    animation()-->
<!--  },-->
<!--  methods: {-->
<!--    login() {-->
<!--      request.get("/op1/login", {-->
<!--        params: {-->
<!--          username: this.username,-->
<!--          password: this.password-->
<!--        }-->
<!--      }).then(res => {-->
<!--        router.push("/op1_person_center")-->
<!--        alert(res.data)-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.wrapper {-->
<!--  height: 100vh;-->
<!--  background: rgba(0,0,0,0);-->
<!--}-->
<!--.card{-->
<!--  margin: 200px auto;-->
<!--  /*background-color: #fff;*/-->
<!--  width: 550px;-->
<!--  height: 350px;-->
<!--  padding: 20px;-->
<!--  background-color: rgba(155,155,155,0.5);-->
<!--  border-style: none;-->
<!--  /*border-radius: 10px;*/-->
<!--}-->
<!--.el-input >>> .el-input__inner {-->
<!--  background-color: rgba(0, 0, 0, 0.2);-->
<!--  color: white;-->
<!--}-->
<!--</style>-->
