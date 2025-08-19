<template>
  <div class="cryptoTool">

    <sduheader></sduheader>

    <!--    安全工具-->
    <div style="background-color: rgba(8,46,84,0.8);
          margin-top:0px;
          padding: 0px;">
      <toolscard></toolscard>
    </div>

    <!--    代码审计-->
    <div style="background-color: rgba(128,138,135,0.8)">
      <codeaudit></codeaudit>
    </div>
  </div>
</template>

<script>
import Sduheader from "../components/sduheader.vue";
import toolscard from "../components/ToolsCard";
import codeaudit from "../components/Codeaudit";
import animation from "../assets/js/background_black";

export default {
  components: {
    Sduheader,
    toolscard,
    codeaudit
  },
  created() {
    animation()
  },
  // data(){
  //   return{
  //     toolsinfo:[
  //       {
  //         toolname:'base64加解密',
  //         toolcontent:'在线Base64编码解码工具',
  //         toolurl:'/base64tool',
  //         imgurl:'el-icon-document',
  //       },
  //       {
  //         toolname:'CMS',
  //         toolcontent:'在线CMS指纹识别工具',
  //         toolurl:'/cmstool',
  //         imgurl:'el-icon-thumb',
  //       },
  //       {
  //         toolname:'子域名查询',
  //         toolcontent:'在线子域名查询工具',
  //         toolurl:'/domainfindtool',
  //         imgurl:'el-icon-monitor',
  //       },
  //       {
  //         toolname:'IP查询',
  //         toolcontent:'在线IP信息查询工具',
  //         toolurl:'/ipinfotool',
  //         imgurl:'el-icon-delete-location',
  //       },
  //       {
  //         toolname:'Port Scan',
  //         toolcontent:'在线端口扫描工具',
  //         toolurl:'/scanporttool',
  //         imgurl:'el-icon-search',
  //       },
  //       {
  //         toolname:'URL编码',
  //         toolcontent:'在线URL编码转换',
  //         toolurl:'/urlencode',
  //         imgurl:'el-icon-search',
  //       },
  //       {
  //         toolname:'port scan',
  //         toolcontent:'在线端口扫描工具',
  //         toolurl:'/scanporttool',
  //         imgurl:'el-icon-search',
  //       },
  //       {
  //         toolname:'port scan',
  //         toolcontent:'在线端口扫描工具',
  //         toolurl:'/scanporttool',
  //         imgurl:'el-icon-search',
  //       },
  //     ]
  //   }
  // },
  // methods: {
  //   gotopage(url){
  //     let current_url=window.location.href.slice(0,-11);
  //     let new_url=current_url+url;
  //     window.open(new_url);
  //   },
  // }
};
</script>


<style scoped>
/*.search{*/
/*  height:200px;*/
/*  !*background-color: #475669;*!*/
/*  margin-left:10%;*/
/*  margin-right:10%;*/
/*  !*margin-top:2%;*!*/
/*}*/
/*.tools{*/
/*  !*height: 600px;*!*/
/*  !*!*background-color: #f56c6c;*!*!*/
/*  !*margin-left:10%;*!*/
/*  !*margin-right:10%;*!*/
/*  !*margin-top:2%;*!*/
/*}*/
/*.dv-border{*/
/*  !*margin:10px;*!*/
/*  height:250px;*/
/*  cursor:pointer;*/
/*  background-color: #f56c6c;*/
/*}*/
</style>