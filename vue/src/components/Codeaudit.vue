<template>
  <div class="codeaudit">
    <div>
      <h1 style="text-align: left;
          color: white;padding-top: 35px;
          margin-top: 0px;
          margin-bottom:25px;
          font-size: 35px">
        Code Audit
        <i class="el-icon-d-arrow-right"></i>
      </h1>
    </div>
    <div>
      <el-row>
        <el-col  v-for="(code, index) in codesinfo"
                 :key="index"
                 :span="8">
          <el-card shadow="hover"
                   style=" margin:20px;
                    height:250px;
                    cursor:pointer;
                    background-color: rgba(0,0,0,0.5);
                    color: white;
                    border-style: none">
            <el-row style="height: 120px;">
              <el-col :span="6">
                <!--<el-image :src="tool.imgurl" style="float: left; width: 85%;heigth:85%;margin-right: 15%;" />-->
                <i :class="code.imgurl"
                   style="font-size: 120px;"></i>
              </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="16">
                <h1 style="margin-top:5px;
                    text-align: left; ">{{ code.codename}}</h1>
                <p style="margin-top:20px;
                    text-align: left;
                    font-size: 15px;
                    line-height: 30px;">{{ code.codecontent}}</p>
              </el-col>
            </el-row>
            <el-divider style="background-color: rgba(255,255,255,0.5)"/>
            <el-button @click="download(code.filename)"
                       style="width: 100%;
                       background-color: rgba(128,138,135,0.2);
                       color: white">
              下载 <i class="el-icon-download"></i>
            </el-button>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>


<script>
import animation from "../assets/js/background_black";
import request from "@/utils/request";

export default {
  data(){
    return{
      codesinfo:[
        {
          codename:'异或加密',
          codecontent:'阅读异或加密算法源码和密文，写出解密算法和明文',
          filename:['cipherText1.txt','enc1Demo.java'],
          imgurl:'el-icon-document',
        },
        {
          codename:'按位加密',
          codecontent:'阅读按位加密算法源码和密文，写出解密算法和明文',
          filename:['cipherText2.txt','enc2Demo.java'],
          imgurl:'el-icon-document',
        },
        {
          codename:'ssti',
          codecontent:'服务端模版注入攻击',
          filename:'cipherText2.txt',
          imgurl:'el-icon-document',
        },
        {
          codename:'spel',
          codecontent:'SpEL是一种功能强大的表达式语言，支持 运行时 查询和操作对象图',
          filename:'cipherText2.txt',
          imgurl:'el-icon-document',
        },
        {
          codename:'commandinject',
          codecontent:'CommandInject 命令注入',
          filename:'cipherText2.txt',
          imgurl:'el-icon-document',
        },
        {
          codename:'fastjson',
          codecontent:'Fastjson是一个Java库，可以用来将Java对象转换成它们的JSON表示',
          filename:'cipherText2.txt',
          imgurl:'el-icon-document',
        },
        {
          codename:'rce',
          codecontent:'远程代码执行分析',
          filename:'cipherText2.txt',
          imgurl:'el-icon-document',
        },
      ]
    }
  },
  created() {
    animation()
  },
  methods: {
    download(name){
      request.get("/download/txt",{
        params:{
          filename:name[0],
        }
      }).then(res =>{
        this.downloadFile('密文',res.data)
      })
      request.get("/download/txt",{
        params:{
          filename:name[1],
        }
      }).then(res =>{
        this.downloadFile('算法',res.data)
      })
    },
    downloadFile(fileName, data) {
      if (!data) {
        return;
      }
      let url = window.URL.createObjectURL(new Blob([data]));//{type:"application/zip"}
      let link = document.createElement('a');
      link.style.display = 'none';
      link.href = url;
      link.setAttribute('download', fileName);
      document.body.appendChild(link);
      link.click();
    },
  }
};
</script>


<style scoped>
.search{
  height:200px;
  /*background-color: #475669;*/
  margin-left:10%;
  margin-right:10%;
  margin-top:2%;
}
.codeaudit{
  height: 1000px;
  /*background-color: #f56c6c;*/
  margin-left:8%;
  margin-right:8%;
  /*margin-top:2%;*/
}
.dv-border{
  margin:10px;
  height:250px;
  cursor:pointer;
  background-color: #f56c6c;
}
</style>