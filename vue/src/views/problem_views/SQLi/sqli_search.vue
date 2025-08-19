
<!--题目名字：SQLi-Char-->
<!--题目类型：SQLi-->
<!--题目描述：这是一道字符类型的sql注入习题，尝试找到flag吧-->
<!--题目难度：4星-->

<template>
    <div id="building">
        <div style="margin-top: 100px;">
<!--            <h1 style="color: white">FL4G做了一个查询用户信息的界面，输入ID(如：1)来试着查询信息吧~</h1>-->
<!--            <h1 style="color: white">flag就藏在sqlifl4g表中哦~</h1>-->
        </div>
        <div style="margin-top: 100px">
            <el-input v-model="id" style="width: 40%;margin-right: 50px" placeholder="请输入学号ID"></el-input>
            <el-button @click="pingfun" type="primary">查找</el-button>
        </div>

        <el-card style="width: 50%;margin-left: 25%;margin-top: 5%;word-break:break-all">
            <el-table :data="myresult" border="true" style="width: 100%;">
                <el-table-column prop="id" label="学号" />
                <el-table-column prop="name" label="姓名" />
                <el-table-column prop="level" label="权限" />
            </el-table>
        </el-card>
    </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      show:0,
      id:'',
      myresult:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },

  created(){
    this.loaddata()
  },
  methods:{
    pingfun(){
      this.show=1
      request.get("/sqli/search",{
        params:{
          id:this.id,
          username:this.user.username
        }
      }).then(res =>{
        this.myresult=res.data
        console.log(res)
      })
    },


    loaddata(){
      request.get("/sqli/load").then(res =>{
        this.myresult=res.data
        console.log(res)
      })
    },
  }
}
</script>

<style scoped>
    #building{
        background:url("../../../assets/img/bugimg/hacker1.jpg");
        width:100%;
        height:100%;
        position:fixed;
        background-size:100% 100%;
    }

</style>


<!--<!DOCTYPE html>-->
<!--<html lang="en" xmlns:th="http://www.thymeleaf.org">-->
<!--<head>-->
<!--    <meta charset="UTF-8">-->
<!--    <title>搜索型SQL注入</title>-->
<!--</head>-->
<!--<body>-->

<!--<form action="http://localhost:8080/sqli/search" method="get">-->
<!--    请输入学号ID: <input type="text" name="id" />-->
<!--    <input type="submit" value="查找" />-->

<!--    <p  th:text="${id0}" th:if="${not#strings.isEmpty(id0)}"></p>-->
<!--    <p  th:text="${id1}" th:if="${not#strings.isEmpty(id1)}"></p>-->
<!--    <p  th:text="${id2}" th:if="${not#strings.isEmpty(id2)}"></p>-->
<!--    <p  th:text="${id3}" th:if="${not#strings.isEmpty(id3)}"></p>-->
<!--    <p  th:text="${id4}" th:if="${not#strings.isEmpty(id4)}"></p>-->

<!--</form>-->

<!--</body>-->
<!--</html>-->