<!--题目名字：Java Deserialization-->
<!--题目类型：代码审计-Java反序列化-->
<!--题目描述：根据提供的flag类的序列化后的数据，阅读序列化源码，写出反序列化代码，尝试获取flag-->
<!--题目难度：4星-->

<template>
  <div class="building">
    <title>java反序列化</title>

    <div style="margin-top: 5%;margin-bottom: 2%">
      <h1 style="color: white">java反序列化漏洞（源码分析），部分序列化代码通过“下载部分源码”按钮获取</h1>
      <!--    <h1 style="color: white">flag就藏在sqlifl4g表中哦~</h1>-->
    </div>

    <!--  <p>-->
    <!--    java反序列化漏洞（源码分析），部分序列化代码通过“下载部分源码”按钮获取-->
    <!--  </p>-->

    <el-button type="info" @click="download" plain> 下载部分源码</el-button>
    <el-button type="info" @click="ser" plain> 点击执行java序列化代码 </el-button>

    <el-card style="width: 50%;height: 50%;margin-left: 25%;margin-top: 5%;word-break:break-all;background-color: rgba(135,135,135,0.8)">
      <!--    style=word-break:break-all-->

      <h1 style="color: white;">返回结果:</h1>
      <h3 style="color: white;"> {{ myresult }}</h3>

    </el-card>
  </div>


  <!--  <p>-->
  <!--    （没做出来的话）输入序列化后的16进制形式，查看答案:-->
  <!--  </p>-->

  <!--  <div style="margin-top: 100px">-->
  <!--    <el-input v-model="der_data" style="width: 250px;margin-right: 50px" placeholder="payload"></el-input>-->
  <!--    <el-button @click="der" type="primary">submit</el-button>-->
  <!--  </div>-->

  <!--  <el-card style="width: 40%;height: 500px;margin-left: 30%;margin-top: 5%">-->
  <!--    <p>flag:</p>-->
  <!--    <p>{{ flag }}</p>-->
  <!--  </el-card>-->


</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      der_data:'',
      myresult:'',
      flag:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      file:{
        name:'SourceCode.java',
      }
    }
  },
  methods:{
    ser(){
      request.get("/java_deser/start",{
        params:{
          username:this.user.username
        }
      }).then(res =>{
        //alert(res.data)
        this.myresult = res.data;
      })
    },
    der(){
      request.post("/java_deser_answer",this.der_data).then(res =>{
        //alert(res.data)
        this.flag = res.data;
      })
    },
    download(){
      request.get("/java_deser/Download",{
        params:{
          filename:'SourceCode.java',
        }
      }).then(res =>{
        alert(res.data)
        this.downloadFile('SourceCode.java',res.data)
      })
    },
    downloadFile(fileName, data) {
      if (!data) {
        return;
      }
      let url = window.URL.createObjectURL(new Blob([data]));
      let link = document.createElement('a');
      link.style.display = 'none';
      link.href = url;
      link.setAttribute('download', fileName);
      document.body.appendChild(link);
      link.click();
    }
  }
}
</script>

<style scoped>
.building{
  background:url("../../../assets/img/bugimg/javadeser.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}
</style>


<!--<input type="button" value="点击执行java序列化代码"-->
<!--       onclick="javascrtpt:window.location.href='http://localhost:8080/java_deser'" />-->

<!--<p>-->
<!--    序列化后转化为字符数组形式，16进制表示为：-->
<!--    （你需要自己编写代码，把该序列化后的字符反序列化，即可得到flag）-->
<!--</p>-->

<!--<form action="http://localhost:8080/java_deser_test" method="get">-->
<!--    输入flag: <input type="text" name="id" />-->
<!--    <input type="submit" value="提交" />-->
<!--</form>-->


<!--<form action="http://localhost:8080/answer_code" method="get">-->
<!--    （没做出来的话）输入序列化后的16进制形式，查看答案: <input type="text" name="id" />-->
<!--    <input type="submit" value="查看" />-->
<!--</form>-->