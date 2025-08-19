<!--题目名字：XSS-Reflect-->
<!--题目类型：XSS-->
<!--题目描述：反射型XSS漏洞-->
<!--题目难度：3星-->
<template>
  <div id="building">
<!--    <div class="menu">-->
<!--      <el-row>-->
<!--        &lt;!&ndash; 按钮菜单部分 &ndash;&gt;-->
<!--        <el-col :span="24">-->
<!--          <el-menu-->
<!--              :default-active="mypath"-->
<!--              class="el-menu-demo"-->
<!--              mode="horizontal"-->
<!--              background-color="#000"-->
<!--              text-color="#fff"-->
<!--              active-text-color="#FF9912"-->
<!--          >-->
<!--            <el-menu-item>  </el-menu-item>-->
<!--            <el-menu-item>  </el-menu-item>-->
<!--            <el-menu-item>  </el-menu-item>-->
<!--            <el-menu-item>  </el-menu-item>-->
<!--            <el-menu-item @click="goto_honeypot"> 用户信息管理 </el-menu-item>-->
<!--            <el-menu-item @click="problem"> 题目要求 </el-menu-item>-->

<!--          </el-menu>-->
<!--        </el-col>-->
<!--      </el-row>-->
<!--    </div>-->

    <div style="margin-top: 1%;
          margin-left: 50%">
      <el-input v-model="xss_reflect"
                style="width: 45%;
                margin-right: 5%;"
                placeholder="payload"></el-input>
      <el-button @click="xss_reflect_fun"
                 type="success"
                 plain>搜索</el-button>
      <el-button @click="problem"
                 type="success"
                 plain>题目要求</el-button>
    </div>
<!--    <div style="margin-top: 10%;">-->
<!--      <h1 style="color: white">该页面存在反射型XSS漏洞，能否构造payload，</h1>-->
<!--      <h1 style="color: white">使得页面出现弹窗(弹窗内容为你的学号)？</h1>-->
<!--    </div>-->
  </div>

  <el-dialog v-model="problemdetail"
             title="题目要求"
             width="30%">
    <p>该页面存在反射型XSS漏洞，能否构造payload，使得页面出现弹窗(弹窗内容为你的学号)？</p>
  </el-dialog>



<!--  <div v-is="'script'">-->
<!--    alert(this.myresult),-->
<!--    {{this.myresult}}-->
<!--  </div>-->


<!--  <component :is="'script'">-->
<!--    <div style="width: 40rem; height: 5rem" />-->
<!--    <script>-->
<!--      alert("1")-->
<!--    </script>-->
<!--  </component>-->


</template>

<script>
import request from "@/utils/request";
// import router from "@/router";

export default {
  data(){
    return{
      xss_reflect:'',
      myresult:'',
      problemdetail:false,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    xss_reflect_fun(){
      request.get("/xss/reflect",{
        params:{
          xss:this.xss_reflect,
          username:this.user.username
        }
      }).then(res =>{
        this.myresult=res.data;
        // alert(this.myresult)
        // this.myresult

        //window.location.href="pro012";
        // router.push("/pro012");
        // router.push("/pro012");
        // this.$router.go(0)
        console.log(res.code)
        console.log(res.data)

        // location.reload()

        //self.location.href="/pro012"

        if(res.code==200)
          document.write(this.myresult);
        else
          location.reload()


      })
    },
    problem(){
      this.problemdetail=true
    }

  }
}
</script>
<!--&#45;&#45;%>-->
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!--<html>-->
<!--<head>-->
<!--    <title>反射型XSS</title>-->
<!--</head>-->
<!--<body>-->

<!--<form action="http://localhost:8080/xss/reflect" method="get">-->
<!--    请输入payload: <input type="text" name="xss" />-->
<!--    <input type="submit" value="提交" />-->
<!--</form>-->

<!--</body>-->
<!--</html>-->
<style scoped>
#building{
  background:url("../../../assets/img/bugimg/MOOC.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}

/deep/ .el-input__inner {
  background-color: rgba(255, 255, 255, 0.5) !important;
}


</style>