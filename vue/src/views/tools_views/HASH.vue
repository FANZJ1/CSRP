<template>
  <br>
  <br>
  <el-card shadow="always" style="text-align: center; margin-right: 100px;margin-left: 100px;height: 700px">
<!--    标题-->
    <el-row>
      <el-col :span="8">
      </el-col>
      <el-col :span="8">
        <div style="font-size: x-large;background-color: darkgreen;color:white;margin: 5px;">
          HASH计算
        </div>
<!--        <el-button style="text-align: right" size="small" icon="el-icon-thumb">-->
<!--          <el-link href="https://blog.csdn.net/local_752/article/details/121970823"-->
<!--                   target="_blank"-->
<!--                   style="font-size: 20px;color: darkgreen"-->

<!--          >什么是BASE64？-->
<!--          </el-link>-->
<!--        </el-button>-->
      </el-col>
      <el-col :span="8">

      </el-col>
    </el-row>

<!--输入框选择框-->
    <div class="el-common-layout">
      <div class="text-area" style="margin: 10px">
        <!--    <textarea v-model="textdata" placeholder="请输入编码字符串或待解码字符串">-->
        <!--    </textarea>-->
        <el-input
            v-model="textdata"
            type="textarea"
            placeholder="Please input"
            style="width: 700px;font-size: 20px"
            :rows=5
        />

      </div>
      <el-radio-group v-model="thetype">
        <el-radio :label="1">MD5</el-radio>
        <el-radio :label="2">SHA256</el-radio>
        <el-radio :label="3">SHA512</el-radio>
      </el-radio-group>
      <!--      <div  >-->
      <!--        <el-row>-->
      <!--          <el-col  :span="12">-->
      <!--            <el-link href=""-->
      <!--                     target="_blank"-->
      <!--                     style="font-size: x-large"-->
      <!--            >-->
      <!--              什么是base64？</el-link>-->
      <!--          </el-col>-->
      <!--        </el-row>-->
      <!--      </div>-->
    </div>

<!--    加密按钮-->
    <el-button @click="encode(thetype)" type="info" style="margin: 20px">EnCode</el-button>

<!--    结果展示-->
    <el-card style="width: 40%;height: 250px;margin-left: 30%;margin-top: 3%">
      <h2 style="text-align: left">加密/解密结果:</h2>
      <el-input
          v-model="myresult"
          type="textarea"
          placeholder=""
          style="font-size: 20px"
          disabled
          :rows=4
      />
    </el-card>
  </el-card>


</template>

<script>
import request from "@/utils/request";
import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      textdata: '',
      myresult: '',
      thetype:'',
    }

  },
  methods: {
    encode(type) {
      console.log(this.thetype)
      if (type==1){
        //md5加密
        request.get("/HASH/MD5", {
          params: {
            Text: this.textdata
          }
        }).then(res => {
          //alert(res.data);
          this.myresult = res.data
        })

      }else if (type==2){
        //sha256加密
        request.get("/HASH/SHA256", {
          params: {
            Text: this.textdata
          }
        }).then(res => {
          //alert(res.data);
          this.myresult = res.data
        })

      }else if (type==3){
        //sha512加密
        request.get("/HASH/SHA512", {
          params: {
            Text: this.textdata
          }
        }).then(res => {
          //alert(res.data);
          this.myresult = res.data
        })

      }else{
        //未选择
        ElMessage({
          message:"请选择加密方式",
          center: true,
          type: 'warning',
        })

      }
    },
    decode() {
      request.get("/tools/Base64/decode", {
        params: {
          data: this.textdata,
        }
      }).then(res => {
        this.myresult = res.data
      })
    }

  }
}
</script>

<style scoped>
/*文本域*/
.text-area {
  width: 100%;
  /*border-top: 1px solid gainsboro;*/
  /*border-bottom: 1px solid gainsboro;*/

}

.text-area textarea {
  width: 50%;
  height: 100%;
  /*margin: 0.75rem  0.75rem;*/
  /*border: none;*/
  /*!*outline: none;*!*/
  /*padding-left: 1.125rem;*/
  height: 10.5rem;
}

.text-area textarea::-webkit-input-placeholder {
  color: #9E9E9E;

}

</style>