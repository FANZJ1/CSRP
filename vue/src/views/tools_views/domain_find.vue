<template style="background-color: #000000">
  <el-row>
    <el-col
        :span="10"
        style="margin-top: 4%">
      <el-image
          :src="imgsrc"
          style="height: 75%;
          width: 40%;
          margin-left: 45%;
          margin-top: 5%"/>
    </el-col>

    <el-col :span="14" style="">
      <h1
          style=";margin-top: 16%;
          width: 500px;">
        SDUCSRP在线子域名查询系统
      </h1>
    </el-col>
  </el-row>

  <el-row style="margin-top: 5%">
    <div style="">
      <el-col :span="12">
        <el-input v-model="urlP"
                  style="width: 240%;
                  margin-left: 350%"
                  placeholder="域名,注意不要带有www">

        </el-input>
      </el-col>

      <el-col :span="12">
        <el-button @click="subdomain"
                   type="primary"
                   style="margin-left: 880%">
          Query
        </el-button>
      </el-col>
    </div>
  </el-row>

  <el-row style="margin-left: 30%;
          margin-top: 2%">
    <span class="tishi"
          style="font-size: 15px;
          color: red;">
      *声明：本网站仅供渗透测试学习使用，严禁用于非法渗透测试，所产生后果与本网站无关！*
    </span>
  </el-row>

  <el-card v-if="show==1"
           style="width: 60%;
           margin-left: 20%;
           margin-top: 5%;
           word-break:break-all">
    <el-table
        :data="myresult"
        border="true"
        style="width: 100%;">
      <el-table-column prop="id" label="序号" />
      <el-table-column prop="subDomain" label="子域名" />
    </el-table>
  </el-card>


  <el-card v-if="show1==1">
    <el-divider style="margin-top: 10%"></el-divider>
    <el-row :gutter="10" >
      <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
        <div class="grid-content bg-purple">
          <p>WEBSITE</p>
          <a class="aaa" href="https://gitee.com/cgchacker/SduCSRP">Project Code</a><br>
          <br>
          <a class="aaa" href="https://blog.csdn.net/qq_50860232/category_11649911.html">BLOG</a><br>
        </div>
      </el-col>
      <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
        <div class="grid-content bg-purple">
          <el-divider direction="vertical" style="height: 100px"></el-divider>
        </div>
      </el-col>
      <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
        <div class="grid-content bg-purple">
          <p>CONTACT US</p>
          <a class="aaa" href="https://blog.csdn.net/qq_50860232">Ling Chen</a><br><br>
          <a class="aaa" href="https://blog.csdn.net/m0_52100140">Fan Zhijie</a><br><br>
          <a class="aaa" href="https://blog.csdn.net/m0_51588039">Qiao Jingbo</a><br><br>
          <a class="aaa" href="https://blog.csdn.net/m0_47470899">Li Hongwei</a><br><br>
          <a class="aaa" href="https://blog.csdn.net/weixin_50803761">Liu Jingyou</a><br><br>
        </div>
      </el-col>
      <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      show:0,
      imgsrc: require('@/assets/img/toolsimg/domain1.png'),
      urlP:'',
      myresult:''
    }
  },
  methods:{
    subdomain(){
      this.show=1
      request.get("/DomainFind",{
        params:{
          urlP:this.urlP
        }
      }).then(res =>{
        this.myresult=res.data
        console.log(res)
      })

    },
    //实例创建之前钩子函数--给body添加行内样式
    beforeCreate () {
      this.$nextTick(()=>{
        document.body.setAttribute('style', 'background:#000000')
      })
    }

  }
}
</script>

<style scoped>
.tishi{
  font-weight:bold;
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