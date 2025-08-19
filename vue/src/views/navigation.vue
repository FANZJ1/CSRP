<!--安全导航-->
<template>
  <div class="navigation">
    <sduheader></sduheader>
    <el-card class="ourinfo"
             shadow="always"
             style=" background-color:rgba(0,0,0,0.5);
             color: white">
      <el-row>
        <el-col :span="4">
          <div style="text-align:left">
            <el-image :src="imgsrc"
                      style="height:150px;
                      width:150px;
                      border-radius: 50%"/>
          </div>
        </el-col>
        <el-col :span="15" >
          <h1 style="text-align:left">山大软院网安fl4g团队</h1>
          <p style="font-size: 15px; line-height:30px; text-align: left;margin-top: 3%">
            团队致力于建立更好更完善的网安实验平台，为网安爱好者们提供课题和日常攻击实践平台.
            <br/>团队成员：乔未、陌兮_、island•、琛歌、番茄炒蛋不加蛋！
          </p>
        </el-col>
        <el-col :span="5">
          <div style="text-align:left">

            <h3 style="text-align:left">CONTACT US</h3>
            <el-row>
              <el-col :span="12">
                <el-image :src="require('@/assets/img/ourqrcode.jpg')"
                          style="height:90px;
                      width:90px;
                      "/>
              </el-col>
              <el-col :span="12">
                <el-link href="https://gitee.com/cgchacker/SduCSRP"
                 type="warning">
                  项目开源地址
                </el-link>

                <el-link href="https://blog.csdn.net/qq_50860232/category_11649911.html?spm=1001.2014.3001.5482"
                style="margin-top: 10%;" type="warning">
                  项目博客地址
                </el-link>
              </el-col>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </el-card>

    <div class="otherinfo">
      <div>
        <h2 style="text-align: left;
            color: white"> 精选推荐 </h2>
      </div>
      <div>
        <el-row>
          <el-col  v-for="(website, index) in webinfo"
                   :key="index"
                   :span="6">
            <el-card @click="gotopage(website.weburl)"  shadow="hover" style="margin:10px;height:100px;cursor:pointer;">
              <el-row>
                <el-col :span="6">
                  <el-image :src="website.imgurl" style="float: left; width: 85%;heigth:85%;margin-right: 15%; " />
                </el-col>
                <el-col :span="18">
                  <h4 style="margin:0px;
                      text-align: left;">{{ website.webname}}</h4>
                  <p style="margin:0px;
                      text-align: left;
                      font-size: 80%">{{ website.webcontent}}</p>
                </el-col>
              </el-row>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import Sduheader from "../components/sduheader.vue";
import animation from "../assets/js/background_black";

export default {
  components: {Sduheader},
  data(){
    return{
      imgsrc: require('@/assets/img/login-bg.jpg'),
      webinfo:[
        {
          webname:'HackerNews',
          webcontent:'为您提供最新国际威胁情报、黑客动向以及维基解密资讯',
          weburl:'https://hackernews.cc/?nsoad_com',
          imgurl:require('@/assets/img/websiteimg/09HackerNews.png'),
        },
        {
          webname:'I春秋学院',
          webcontent:'I春秋，新锐的信息安全在线教育品牌！',
          weburl:'https://www.ichunqiu.com/?milw0rm.cn',
          imgurl:require('@/assets/img/websiteimg/07I春秋学院.jpeg'),
        },
        {
          webname:'安全客',
          webcontent:'以与生俱来的安全防护能力,助推企业数字化安全发展 ！',
          weburl:'https://www.anquanke.com/?nsoad.com',
          imgurl:require('@/assets/img/websiteimg/02安全客.png'),
        },
        {
          webname:'CTF-Wiki',
          webcontent:'CTF Wiki 对 CTF 中的各个方向的知识和技术进行介绍，以便于初学者更好地学习 CTF 相关的知识。',
          weburl:'https://ctf-wiki.org/',
          imgurl:require('@/assets/img/websiteimg/08CTF-Wiki.png'),
        },
        {
          webname:'XCTF社区',
          webcontent:'XCTF联赛赛事资源信息及媒体发布平台,专注于为网络安全、白帽技术爱好者提供交流学习平台',
          weburl:'https://www.xctf.org.cn/?nsoad.com',
          imgurl:require('@/assets/img/websiteimg/13XCTF社区.jpeg'),
        },
        {
          webname:'攻防世界',
          webcontent:'CTF刷题和竞赛平台',
          weburl:'https://adworld.xctf.org.cn/',
          imgurl:require('@/assets/img/websiteimg/16攻防世界.png'),
        },
        {
          webname:'破壳学院',
          webcontent:'专注于白帽子、信息安全技术、网络攻防',
          weburl:'https://pockr.org/activity',
          imgurl:require('@/assets/img/websiteimg/14破壳学院.png'),
        },
        {
          webname:'SecWiki',
          webcontent:'SecWiki-安全维基,汇集国内外优秀安全资讯、工具和网站',
          weburl:'https://www.sec-wiki.com/',
          imgurl:require('@/assets/img/websiteimg/06安全维基-SecWiki.jpeg'),
        },
        {
          webname:'CTFHub',
          webcontent:'开箱即用的CTF学习解决方案',
          weburl:'https://www.ctfhub.com/#/index',
          imgurl:require('@/assets/img/websiteimg/17CTFhub.svg'),
        },
        {
          webname:'SecIN社区',
          webcontent:'SecIN是启明星辰云众可信旗下的信息安全技术社区。',
          weburl:'https://www.sec-in.com/',
          imgurl:require('@/assets/img/websiteimg/18secin.svg'),
        },
        {
          webname:'合天智汇',
          webcontent:'长期致力于网络靶场和网安人才培养相关技术,产品研究与应用',
          weburl:'https://www.heetian.com/',
          imgurl:require('@/assets/img/websiteimg/11合天智汇.jpeg'),
        },
        {
          webname:'补天',
          webcontent:'补天漏洞响应平台旨在建立企业与白帽子之间的桥梁,让企业更安全,让白帽子获益。',
          weburl:'https://www.butian.net/',
          imgurl:require('@/assets/img/websiteimg/19补天.jpeg'),
        },
      ]
    }
  },
  created() {
    animation()
  },
  methods: {
    gotopage(url){
      window.open(url);
    },
  }
};
</script>


<style scoped>
.ourinfo{
  height:200px;
  /*background-color: #475669;*/
  margin-left:10%;
  margin-right:10%;
  margin-top:2%;
}
.otherinfo{
  height: 600px;
  /*background-color: #f56c6c;*/
  margin-left:10%;
  margin-right:10%;
  margin-top:2%;
}
.navigation{
  background-color: rgba(128,138,135,0.8);
}

</style>