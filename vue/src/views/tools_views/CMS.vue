<template style="background-color: #F6F6F6">
<!--  <el-menu-->
<!--      :default-active="activeIndex"-->
<!--      class="el-menu-demo"-->
<!--      mode="horizontal"-->
<!--      @select="handleSelect"-->
<!--      background-color="#0684FF"-->
<!--      text-color="#fff"-->
<!--      active-text-color="#ffd04b">-->
<!--    <el-menu-item index="0">-->
<!--      <el-image :src="imgsrc" style="height:90%;width:105%;margin-left: 20%;margin-top: 5%;border-radius: 30%;"/>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="1" style="font-size: 100%;margin-left: 3%">首页</el-menu-item>-->
<!--    <el-menu-item index="2" style="font-size: 100%" @click="info">系统简介</el-menu-item>-->
<!--    <el-menu-item index="3" style="font-size: 100%" @click="team">开发团队</el-menu-item>-->
<!--  </el-menu>-->
  <el-row>
    <el-col :span="10"
            style="margin-top: 4%">
      <el-image
          :src="imgsrc"
          style="height: 85%;
          width: 35%;
          margin-left: 45%;
          margin-top: 5%"/>
    </el-col>
    <el-col
        :span="14" style="">
      <h1
          style="margin-right: 90%;
          margin-top: 20%;
          width: 500px">
        SDUCSRP在线指纹识别系统
      </h1>
    </el-col>
  </el-row>
<!--  <div>-->
<!--    <p>动态组件</p>-->
<!--    <component :is="comName"></component>-->
<!--    <button @click="comName = 'A'">展示A组件</button>-->
<!--    <button @click="comName = 'B'">展示B组件</button>-->
<!--  </div>-->
  <el-row style="margin-top: 5%">
    <div style="margin-left: 3%">
      <el-col :span="12">
        <el-input v-model="param"
                  style="width: 240%;
                  margin-left: 350%"
                  placeholder="请输入域名">

        </el-input>
      </el-col>

      <el-col :span="12">
        <el-button @click="getCMSInfo"
                   type="primary"
                   style="margin-left: 880%">
          Query
        </el-button>
      </el-col>
    </div>
  </el-row>

  <el-row>
<!--    <el-button @click="drawer = true" type="primary" style="">-->
<!--      点我打开-->
<!--    </el-button>-->

<!--    <el-drawer-->
<!--        title="我是标题"-->
<!--        :visible="drawer"-->
<!--        :direction="direction"-->
<!--        :before-close="handleClose">-->
<!--      <span>我来啦!</span>-->
<!--    </el-drawer>-->
  </el-row>
  <el-row
      style="margin-left: 30%;
      margin-top: 2%">
    <span class="tishi"
          style="font-size: 15px;
          color: red;">
      *声明：本网站仅供渗透测试学习使用，严禁用于非法渗透测试，所产生后果与本网站无关！*
    </span>
  </el-row>
<!--  <el-card style="width: 40%;height: 500px;margin-left: 30%;margin-top: 5%">-->
<!--    <p>返回结果:</p>-->
<!--    <p>{{ myresult }}</p>-->
<!--  </el-card>-->
  <el-row
      style="margin-left: 20%;
      margin-top: 4%;
      width: 1000px;
      margin-bottom: 20%">
    <el-col>
<!--      <el-card style="width: 105%;margin-right: 2%">-->
        <el-descriptions class="margin-top"
                         title="指纹信息"
                         :column="2"
                         style="width: 1000px" border>
          <template v-slot:extra>
            <el-button type="primary" size="small">
              刷新
            </el-button>
          </template>
          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-user"></i>
              URL地址
            </template>
            {{urlAdd}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-mobile-phone"></i>
              网站标题
            </template>
            {{title}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-cpu"></i>
              中间件
            </template>
            {{http_server}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-tickets"></i>
              CMS信息
            </template>
            {{CMSInfo}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-office-building"></i>
              Language
            </template>
            {{Language}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-place"></i>
              IP地址
            </template>
            {{ip}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-mobile"></i>
              ISP信息
            </template>
            {{isp}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-map-location"></i>
              GPS信息
            </template>
            {{gps}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template v-slot:label>
              <i class="el-icon-monitor"></i>
              操作系统
            </template>
            {{os}}
          </el-descriptions-item>
        </el-descriptions>
<!--      </el-card>-->

    </el-col>
    <el-col style="margin-top: 5%">
      <el-descriptions class="margin-top"
                       title="域名信息"
                       :column="2"
                       style="width: 1000px" border>
        <template v-slot:extra>
          <el-button type="primary" size="small">
            刷新
          </el-button>
        </template>

        <el-descriptions-item>
          <template v-slot:label>
            <i class="el-icon-user"></i>
            域名注册商
          </template>
          {{DomainNameRegistrar}}
        </el-descriptions-item>

        <el-descriptions-item>
          <template v-slot:label>
            <i class="el-icon-date"></i>
            域名注册期限
          </template>
<!--          {{creaction_date}}-{{expiration_date}}-->
          {{register_date}}
        </el-descriptions-item>

        <el-descriptions-item>
          <template v-slot:label>
            <i class="el-icon-monitor"></i>
            DNS服务器
          </template>
          {{DNS}}
        </el-descriptions-item>

        <el-descriptions-item>
          <template v-slot:label>
            <i class="el-icon-message"></i>
            注册人邮箱
          </template>
          {{mail}}
        </el-descriptions-item>

        <el-descriptions-item>
          <template v-slot:label>
            <i class="el-icon-tickets"></i>
            备案主体
          </template>
          {{zhuti}}
        </el-descriptions-item>

        <el-descriptions-item>
          <template v-slot:label>
            <i class="el-icon-office-building"></i>
            备案性质
          </template>
          {{xingzhi}}
        </el-descriptions-item>

        <el-descriptions-item>
          <template v-slot:label>
            <i class="el-icon-notebook-1"></i>
            备案标题
          </template>
          {{biaoti}}
        </el-descriptions-item>

        <el-descriptions-item>
          <template v-slot:label>
            <i class="el-icon-time"></i>
            备案时间
          </template>
          {{time}}
        </el-descriptions-item>
      </el-descriptions>
    </el-col>
    <br>
    <br>
    <br>
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
</template>


<script>
import request from "@/utils/request";
// import router from "@/router";
// import A from "./CMS_ret"
//
// let B = "test";


export default {
  setup() {
    let info=()=>{
      alert("        在对目标主机进行渗透测试的前期，通常需要对目标主机进行相关的信息收集，" +
          "而对目标主机进行Web指纹信息扫描也是信息收集当中很关键的一部分。\n" +
          "\n" +
          "        能否有效识别出目标主机的Web指纹信息，主要还是取决于扫描脚本&工具内置的「指纹信息特征库」")
    };
    let team=()=>{
      alert("SDU Fl4g安全团队")
    };
    return{
      info,
      team
    }
  },

  name: '',
  // components: {
  //   A,B
  // },
  props : {},
  data(){
      return{
        imgsrc: require('@/assets/img/toolsimg/cms3.jpeg'),
        activeIndex: '1',
        activeIndex2: '1',
        comName: '',
        param:'',
        drawer: false,
        direction: 'rtl',
        //for 指纹信息
        urlAdd:'',
        title:'',
        isp:'',
        area:'',
        gps:'',
        CMSInfo:'',
        Language:'',
        mail:'',
        waf:'',
        ip:'',
        os:'',
        //for 域名信息
        domain:'',
        main_domain:'',
        DomainNameRegistrar:'',
        creaction_date:'',
        expiration_date:'',
        register_date:'',
        DNS:'',
        http_server:'',
        zhuti:'',
        xingzhi:'',
        biaoti:'',
        time:'',
        imgsrc17: require('@/assets/img/bugimg/fileupload0.png'),
      }
    },
  methods:{
    getCMSInfo(){
      request.get("/tools/cms",{
        params:{
          param:this.param,
        }
      }).then(res =>{
        this.urlAdd=res.data.url1;
        this.isp=res.data.isp;
        this.area=res.data.area;
        this.gps=res.data.gps;
        this.CMSInfo=res.data.cms;
        this.Language=res.data.language;
        this.mail=res.data.mail;
        this.os=res.data.os;
        this.waf=res.data.waf;
        this.ip=res.data.ip;
        this.domain=res.data.domain;
        this.main_domain=res.data.main_domain;

        this.DomainNameRegistrar=res.data.reg;
        this.creaction_date=res.data.creaction_date;
        this.expiration_date=res.data.expiration_date;
        this.name_servers=res.data.name_servers;
        this.title=res.data.title;
        this.http_server=res.data.httpserver;
        this.zhuti=res.data.gongsi;
        this.xingzhi=res.data.xingzhi;
        this.biaoti=res.data.title1;
        this.time=res.data.time;
        this.register_date=res.data.register_date;
        this.DNS=res.data.name_servers;

      })
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  //实例创建之前钩子函数--给body添加行内样式
  beforeCreate () {
    this.$nextTick(()=>{
      document.body.setAttribute('style', 'background:#F6F6F6')
    })
  },
  handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
            console.log(_)
          })
          .catch(_ => {
            console.log(_)
          });
    }
}
</script>

<style scoped>
.tishi{
  font-weight:bold;
}
</style>