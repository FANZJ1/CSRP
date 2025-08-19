<template>
  <div id="building">
<!--  <p>SQLi-不安全的过滤</p>-->
    <div style="margin-top: 100px;">
<!--      <h1 style="color: white">FL4G做了一个查询信息的网站，输入ID(如：1)来试着查询信息吧~</h1>-->
<!--      <h1 style="color: white">flag就藏在sqlifl4g表中哦~</h1>-->
    </div>
    <div style="margin-top: 100px">
      <el-input v-model="id" style="width: 40%;margin-right: 50px" placeholder="请输入学号ID"></el-input>
      <el-button @click="pingfun" type="primary">查找</el-button>
    </div>
    <el-card v-if="show==1" style="width: 60%;margin-left: 20%;margin-top: 5%;word-break:break-all">
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
  methods:{
    pingfun(){
      this.show=1
      request.get("/sqli/unsafe2",{
        params:{
          id:this.id,
          username:this.user.username
        }
      }).then(res =>{
        this.myresult=res.data
        console.log(res)
      })
    }

  }
}
</script>

<style scoped>
#building{
  background:url("../../../assets/img/bugimg/hacker2.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}

</style>


<!--<template>-->
<!--  <head>-->
<!--    <meta charset="UTF-8">-->
<!--    <title>字符型SQL注入</title>-->
<!--  </head>-->
<!--  <body>-->

<!--  <form action="http://localhost:8080/sqli/char" method="get">-->
<!--    请输入学号ID: <input type="text" name="id" />-->
<!--    <input type="submit" value="查找" />-->
<!--  </form>-->

<!--  </body>-->
<!--</template>-->