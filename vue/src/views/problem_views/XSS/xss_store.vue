<!--题目名字：XSS-Store-->
<!--题目类型：XSS-->
<!--题目描述：存储型XSS漏洞-->
<!--题目难度：3星-->
<template>
<div id="building">

<!--  <div style="margin-top: 10%;">-->
<!--    <h1 style="color: white">该页面存在存储型XSS漏洞，能否构造payload，</h1>-->
<!--    <h1 style="color: white">使得在控制台输出你的学号？</h1>-->
<!--  </div>-->
  <div style="margin-top: 0.5%;">
    <el-button @click="problem"
               style="float: left;
               margin-left: 15%">题目要求</el-button>
    <el-input v-model="xss_store"
              style="width: 25%;
              margin-left:20%;"
              placeholder="payload"></el-input>
    <el-button @click="xss_store_fun"
               style="margin-left: 1%">搜索</el-button>
    <el-button v-if="payload==1"
               @click="show"> 查看 </el-button>
  </div>

</div>

  <el-dialog v-model="problemdetail"
             title="题目要求"
             width="30%">
    <p>
      该页面存在存储型XSS漏洞，能否构造payload，使得在控制台输出你的学号？
    </p>
    <p>
      输入payload，搜索后，点击查看按钮，查看结果。
    </p>
  </el-dialog>

</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      xss_store:'',
      myresult:'',
      myresult_cookie:'',
      payload:0,
      problemdetail:false,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    xss_store_fun(){
      request.get("/xss/stored/store",{
        params:{
          xss:this.xss_store,
          username:this.user.username
        }
      }).then(res =>{
        // this.myresult=res.data;
        // alert(this.myresult)
        console.log(res)
        this.payload=1
      })
    },
    show(){
      request.get("/xss/stored/show",{
        params:{
          username:this.user.username
        }
      }).then(res =>{
        // this.myresult=res.data;
        // if(this.myresult==null){
        //   alert("请先输入payload，再点击该按钮进行验证。")
        // }
        // else {
        //   // alert(this.myresult)
        //   // this.myresult
        //   document.write(this.myresult);
        //   //window.location.href="pro011";
        //   router.push("/pro011");
        //   //router.push("/pro011");
        //   //self.location.href="/pro011"
        // }

        if(res.code==200){
          alert("答案正确！")
          console.log(res.data)
          document.write(res.msg)
        }
        else if(res.code==500){
          alert("答案错误！")
          document.write(res.msg)
        }
      })
    },
    problem(){
      this.problemdetail=true
    }
  }
}
</script>

<!--<%&#45;&#45;-->
<!--  Created by IntelliJ IDEA.-->
<!--  User: Lenovo-->
<!--  Date: 2022/3/27-->
<!--  Time: 16:18-->
<!--  To change this template use File | Settings | File Templates.-->
<!--&#45;&#45;%>-->
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!--<html>-->
<!--<head>-->
<!--    <title>存储型XSS</title>-->
<!--</head>-->
<!--<body>-->
<!--<form action="http://localhost:8080/xss/stored/store" method="get">-->
<!--    请输入payload: <input type="text" name="xss" />-->
<!--    <input type="submit" value="查找" />-->
<!--</form>-->
<!--</body>-->
<!--</html>-->


<style scoped>
#building{
  background:url("../../../assets/img/bugimg/weibo.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}

</style>