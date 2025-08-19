<template>
  <div class="bf_form">
    <div class="bf_form_main">
      <h3 class="header blue lighter bigger">
        Please Enter Your Information
      </h3>
      <div>
        <el-input v-model="username" style="width: 250px;margin-right: 50px" placeholder="username"></el-input>
      </div>
      <div>
        <el-input v-model="password" style="width: 250px;margin-right: 50px" placeholder="password"></el-input>
      </div>
      <div>
        <el-input v-model="inputcode" style="width: 250px;margin-right: 50px" placeholder="验证码"></el-input>
      </div>
      <div>
        <el-card  style="width: 250px;margin-right: 50px;" @click="createCode" >{{code}}</el-card>
      </div>
      <div>
        <el-button style="width: 250px;margin-right: 50px" @click="validate()">登录</el-button>
      </div>

    </div><!-- /.widget-main -->
  </div><!-- /.widget-body -->
</template>



<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      username:'',
      password:'',
      code:'',
      inputcode:'',
    }
  },
  created(){
    this.createCode();
  },
  methods:{
    validate() {
      if (this.inputcode.length <= 0) {
        alert("请输入验证码！");
        return false;
      } else if (this.inputcode != this.code) {
        alert("验证码输入错误！");
        this.createCode();//刷新验证码
        return false;
      }
      else {
        // alert(this.username)
        // 验证码正确，传参到后端
        request.get("/force/client", {
          params: {
            username: this.username,
            password: this.password
          }
        }).then(res => {
          alert(res.data)
        })
      }
    },
    createCode() {
      this.code='';
      var codeLength = 5;//验证码的长度
      // var checkCode = document.getElementById("checkCode");
      var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的

      for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * 36);
        this.code += selectChar[charIndex];
      }
      // //alert(code);
      // if (checkCode) {
      //   checkCode.className = "code";
      //   checkCode.value = this.code;
      // }
    },
  },
}

</script>