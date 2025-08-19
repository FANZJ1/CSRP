<template>
<!--  <div id='building'>-->
    <div>
      <el-row>
        <el-col
            :span="10"
            style="margin-top: 4%">
          <el-image
              :src="imgsrc"
              style="height: 80%;
              width: 10%;
              margin-left: 85%;
              border-radius: 30%;"/>
        </el-col>
        <el-col :span="14" style="">
          <p class="title"
             style="font-size: 20px;
             margin-top: 8%;
             color: black;
             margin-right: 60%">
            SDUCSRP在线IP信息查询系统
          </p>
        </el-col>
      </el-row>

      <el-row style="margin-top: 2%">
        <div style="">
          <el-col :span="12">
            <el-input v-model="ip1"
                      style="width: 240%;
                      margin-left: 350%;"
                      placeholder="IP/域名">

            </el-input>
          </el-col>
          <el-col :span="12">
            <el-button @click="getIPInfo"
                       type="primary"
                       style="margin-left: 880%">
              Query
            </el-button>
          </el-col>
        </div>
      </el-row>

      <el-row
          style="margin-left: 30%;
          margin-top: 2%">
          <span class="tishi"
                style="font-size: 15px;">
            *声明：本网站仅供渗透测试学习使用，严禁用于非法渗透测试，所产生后果与本网站无关！*
          </span>
      </el-row>

      <el-row
          style="margin-left: 20%;
          margin-top: 4%;
          width: 1000px;">
          <el-col>
            <el-descriptions class="margin-top"
                             title="IP信息"
                             :column="2"
                             style="width: 900px;
                             color: white;" border>
              <template v-slot:extra>
                <el-button type="primary"
                           size="small"
                           @click="getIPInfo">
                  刷新
                </el-button>
              </template>
              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-user"></i>
                  IP地址
                </template>
                {{ip}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-mobile-phone"></i>
                  国家
                </template>
                {{country}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-cpu"></i>
                  省份
                </template>
                {{province}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-tickets"></i>
                  城市
                </template>
                {{city}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-office-building"></i>
                  区域
                </template>
                {{distinct}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-place"></i>
                  网络提供商
                </template>
                {{isp}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-map-location"></i>
                  纬度
                </template>
                {{lon}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-monitor"></i>
                  经度
                </template>
                {{lat}}
              </el-descriptions-item>


              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-mobile"></i>
                  运营商
                </template>
                {{operator}}
              </el-descriptions-item>

              <el-descriptions-item>
                <template v-slot:label>
                  <i class="el-icon-monitor"></i>
                  运营商名称
                </template>
                {{net_str}}
              </el-descriptions-item>
            </el-descriptions>
          </el-col>
          <el-col style="margin-top: 5%">
          </el-col>
      </el-row>


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
    </div>
<!--  </div>-->

</template>

<style scoped>
#building{
  background:url("../../assets/img/toolsimg/ip3.jpg");
  width:100%;
  height:120%;
  position:fixed;
  background-size:100% 100%;
}
 .tishi{
   font-weight:bold;
 }

.title{
  font-weight:bold;
}

</style>


<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      show:0,
      show1:0,
      imgsrc: require('@/assets/img/toolsimg/ipinfo.png'),
      ip1:'',
      ip:'',
      country:'',
      country_code:'',
      province:'',
      city:'',
      distinct:'',
      isp:'',
      operator:'',
      lon:'',
      lat:'',
      net_str:''
    }
  },
  methods:{
    getIPInfo(){
      this.show=1
      request.get("/tools/IPInfo",{
        params:{
          ip1:this.ip1
        }
      }).then(res =>{
        this.ip=res.data.ip;
        this.country=res.data.country;
        this.country_code=res.data.country_code;
        this.province=res.data.province;
        this.city=res.data.city;
        this.distinct=res.data.distinct;
        this.isp=res.data.isp;
        this.operator=res.data.operator;
        this.lon=res.data.lon;
        this.lat=res.data.lat;
        this.net_str=res.data.net_str;
      })
    }

  }
}
</script>