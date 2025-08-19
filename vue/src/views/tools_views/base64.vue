<template>
  <br>
  <br>
<!--  <p>Base64在线编码解码</p>-->
  <el-card shadow="always" style="text-align: center; margin-right: 100px;margin-left: 100px;height: 600px">
    <el-row>
      <el-col :span="8">

      </el-col>
      <el-col :span="8">
        <div style="font-size: x-large;background-color: darkgreen;color:white;margin: 5px;">
          BASE64在线编码解码
        </div>
        <el-button style="text-align: right" size="small" icon="el-icon-thumb">
          <el-link href="https://blog.csdn.net/local_752/article/details/121970823"
                   target="_blank"
                   style="font-size: 20px;color: darkgreen"

          >什么是BASE64？
          </el-link>
        </el-button>
      </el-col>
      <el-col :span="8">

      </el-col>
    </el-row>


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

    <!--  <div class="text-area">-->
    <!--    <textarea v-model="textdata" placeholder="请输入编码字符串或待解码字符串">-->

    <!--    </textarea>-->

    <!--  </div>-->
    <el-button @click="encode" type="info" style="margin: 20px">EnCode</el-button>
    <el-button @click="decode" type="info">DeCode</el-button>

    <el-card style="width: 40%;height: 100px;margin-left: 30%;margin-top: 5%">
      <p style="text-align: left">编码/解码结果:</p>
      <p>{{ myresult }}</p>
    </el-card>
  </el-card>


</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      textdata: '',
      myresult: ''
    }

  },
  methods: {
    encode() {
      // alert(this.textdata)
      request.get("/tools/Base64/encode", {
        params: {
          data: this.textdata
        }
      }).then(res => {
        //alert(res.data);
        this.myresult = res.data
      })
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