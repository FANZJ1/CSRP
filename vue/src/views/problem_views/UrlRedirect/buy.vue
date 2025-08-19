<template>
<!--  <el-menu-->
<!--      :default-active="activeIndex2"-->
<!--      class="el-menu-demo"-->
<!--      mode="horizontal"-->
<!--      @select="handleSelect"-->
<!--      background-color="#545c64"-->
<!--      text-color="#fff"-->
<!--      active-text-color="#ffd04b">-->

  <el-row style="">
    <el-image
        :src="imgsrc1"
        style="height:320px;
                    width:100%;

                    "/>
  </el-row>
  <el-row>
    <el-col>
            <el-image
                :src="imgsrc2"
                style="height:80%;
                          width:70%;
                          margin-left: 0%;
                          margin-top: 5%"/>
    </el-col>
    <el-col>
      <el-row>
        <el-col :span="12">
          <el-button @click="login"
                     style="width:20%;
                   height: 40%;
                   margin-left:110%;
                   background-color: #FFEDED;">
<!--            <el-col>-->
<!--              <i :class="imgurl"-->
<!--                 style="color: white;-->
<!--               font-size: 30px;-->
<!--               margin-left: 8%">-->
<!--              </i>-->
<!--            </el-col>-->
<!--            <el-col>-->
              <span
                  style="color: red;
                margin-left: 10%;
                margin-bottom: 5px;
                font-size: 120%;">
                立即购买
              </span>
<!--            </el-col>-->
          </el-button>
        </el-col>
        <el-col :span="12">
          <el-button @click="login"
                     style="width:20%;
                   height: 40%;
                   margin-left:0%;
                   background-color: #FE0036;">
            <el-col :span="8" style="margin-bottom: 3px">
              <i :class="imgurl"
                 style="color: white;
                 font-size: 20px;
                 margin-bottom: 10px;
                 margin-left: 0%">
              </i>
            </el-col>
            <el-col :span="8">
                <span
                    style="color: white;
                  margin-left: 0%;
                  font-size: 120%;">
                  加入购物车
                </span>
            </el-col>
          </el-button>
        </el-col>
      </el-row>

    </el-col>

  </el-row>

  <el-row>
    <el-image
        :src="imgsrc4"
        style="height:350px;
                    width:90%;
                    margin-left: 3%;
                    margin-top: 10%;
                    "/>
  </el-row>

  <el-divider style="margin-top: 10%;">
  </el-divider>
  <el-row
      :gutter="10"
      style="margin-bottom: 8%">
    <el-col
        :xs="4"
        :sm="4"
        :md="4"
        :lg="4"
        :xl="4">
      <div class="grid-content bg-purple"></div>
    </el-col>
    <el-col
        :xs="6"
        :sm="6"
        :md="6"
        :lg="6"
        :xl="6">
      <div class="grid-content bg-purple">
        <p style="font-size: 20px">
          版权声明
        </p>
        本网站所有图片资源以及排版样式均来自于第
        三方网站，并不提供购买资源，
        仅供测试和学习交流使用。如果有侵犯版
        权的行为请发送邮箱至
        2212224917@qq.com，我们会在
        24小时之内删除处理
      </div>
    </el-col>
    <el-col
        :xs="4"
        :sm="4"
        :md="4"
        :lg="4"
        :xl="4">
      <div class="grid-content bg-purple">
        <el-divider
            direction="vertical"
            style="height: 100px">

        </el-divider>
      </div>
    </el-col>
    <el-col
        :xs="6"
        :sm="6"
        :md="6"
        :lg="6"
        :xl="6">
      <div class="grid-content bg-purple">
        <p style="font-size: 20px;">
          微信公众号
        </p>
        <el-image :src="imgsrc17" style=""/>
      </div>
    </el-col>
    <el-col
        :xs="4"
        :sm="4"
        :md="4"
        :lg="4"
        :xl="4">
      <div class="grid-content bg-purple">

      </div>
    </el-col>
  </el-row>
</template>

<script>
// import request from "@/utils/request";
// import router from "@/router";

import request from "@/utils/request";
// import router from "@/router";

export default {
  name: "buy",
  data(){
    return{
      imgsrc: require('@/assets/img/bugimg/huawei.jpg'),
      imgsrc1: require('@/assets/img/bugimg/tianmao2.png'),
      imgsrc2: require('@/assets/img/bugimg/huawei2.png'),
      imgsrc4: require('@/assets/img/bugimg/tianmao5.png'),
      imgsrc17: require('@/assets/img/bugimg/fileupload0.png'),

      imgurl:'el-icon-shopping-cart-2',
    }
  },
  methods:{
    login(){
      alert("请先登录")
      request.get("/url_redirect", {
        params: {
          url:"http://82.157.124.157:8080/urllogin"
        }
      }).then(res => {
        window.location.href=res.data;
      })

      // router.push("/urlLogin")
    }
  }
}
</script>

<style scoped>

</style>