<!--&lt;!&ndash;<!DOCTYPE html>&ndash;&gt;-->
<!--&lt;!&ndash;<html lang="en"  xmlns:th="http://www.thymeleaf.org">&ndash;&gt;-->
<!--&lt;!&ndash;<head>&ndash;&gt;-->
<!--&lt;!&ndash;    <meta charset="UTF-8">&ndash;&gt;-->
<!--&lt;!&ndash;    <title>删除SQL注入</title>&ndash;&gt;-->
<!--&lt;!&ndash;</head>&ndash;&gt;-->
<!--&lt;!&ndash;<body>&ndash;&gt;-->
<!--<template>-->
<!--    <form action="http://localhost:8080/sqli/delete" method="get">-->
<!--        <p >我是一个不正经的留言板：</p>-->
<!--        <textarea  name = messahe value = "sub" ></textarea>-->
<!--        <br><input type="submit" value="submit" />-->
<!--    </form>-->

<!--    <form action="http://localhost:8080/sqli/delete1" method="get">-->
<!--        <p>最新留言:</p>-->

<!--        <input th:value1="${id}"-->
<!--               th:value="${message}" name="messahe"/>-->
<!--        <input type="submit" value="删除" />-->

<!--    </form>-->
<!--</template>-->

<template>
  <h1>留言板</h1>
  <el-input v-model="content" style="width: 50%;" ></el-input>
  <el-button @click="submit" >提交</el-button>

  <el-input v-model="content" style="width: 50%;" disabled></el-input>

  <el-button >删除</el-button>
</template>

<script>
import request from "@/utils/request";
import router from "../../../router";

export default {
  data(){
    return{
      content:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    submit(){
      request.get("sqli/delete",{
        params:{
          messahe:this.content,
        }
      }).then(res =>{
        console.log(res)

      })
    },

    delete(){
      request.get("sqli/delete",{
        params:{
          messahe:this.content,
        }
      }).then(res =>{
        console.log(res)
        if(res=='delete'){
          this.content=''
        }else{
          router.push('/delete1');
        }
      })
    },

  }
}
</script>

