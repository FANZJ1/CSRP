<!--&lt;!&ndash;题目名字：Over Permission-水平越权&ndash;&gt;-->
<!--&lt;!&ndash;题目类型：逻辑越权漏洞&ndash;&gt;-->
<!--&lt;!&ndash;题目描述：尝试登录到个人中心，测试水平越权漏洞（user1：123，123；user2：lc，123）&ndash;&gt;-->
<!--&lt;!&ndash;题目难度：2星&ndash;&gt;-->
<template>
    <div id='building'>
      <el-card style="width: 30%;
                      margin-left: 35%;
                      margin-top: 15%"
      >
        <div class="bf_form">
          <div class="bf_form_main">
            <el-row style="margin-left: 18%;
                            margin-top: 2%"
            >
              <span style="font-size: 20px;

                          /*color: white*/
                          " class="header blue lighter bigger"
              >
                Please Enter Your Information
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
              <el-input v-model="password" style="width: 250px;
                        margin-right: 50px"
                        placeholder="password"
              >
              </el-input>

            </el-row>
            <el-row style="margin-left: 40%;
                            margin-top: 5%"
            >
              <el-button @click="login">登录</el-button>
            </el-row>
            <el-row>
              <span style="margin-top: 5%;
              font-size: 6px;
              margin-left: 5%"
              >
                Tips:测试用户:curry,密码:123;尝试使用该用户获取lucy的个人信息
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
          router.push("/oppc")
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

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #000000, 	#483D8B);
  overflow: hidden;
}
#building{
  background:url("../../../assets/img/toolsimg/ip3.jpg");
  width:100%;
  height:120%;
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
