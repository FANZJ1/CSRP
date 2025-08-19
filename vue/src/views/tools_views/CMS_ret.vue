<template>
  <el-card style="width: 40%;height: 500px;margin-left: 30%;margin-top: 5%">
    <p>返回结果:</p>
    <p>{{ myresult }}</p>
  </el-card>
</template>

<script>
import CMS from "./CMS"
export default {
  name: "CMS_ret",
  data(){
    return{
      myresult:CMS.myresult
    }
  }
}
</script>

<style scoped>

</style>