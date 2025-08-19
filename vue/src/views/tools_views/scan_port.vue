<!--端口扫描工具-->
<!--把后端识别端口指纹的功能关闭掉了，不然太慢
1.设置响应时间更长
2.关闭指纹识别-->
<template>
  <el-row>
    <el-col
        :span="10"
        style="margin-top: 4%">
      <el-image
          :src="imgsrc"
          style="height: 75%;
          width: 40%;
          margin-left: 45%;
          margin-top: 5%"/>
    </el-col>

    <el-col :span="14" style="">
      <h1
          style=";margin-top: 16%;
          width: 500px;">
        SDUCSRP在线端口扫描系统
      </h1>
    </el-col>
  </el-row>

  <el-row
      style="margin-left: 15%;
      margin-top: 3%;">
      <el-card
          style="width: 80%;
          height: 250px">
        <div class="bf_form">
          <div class="bf_form_main">
            <el-col
                :span="24"
                style="width: 90%;
                margin-top: 3%">
              <span
                  style="font-size: 20px;
                  color: black;
                  margin-left: 17%;
                  width: 50%">
                Please Enter Ip And Ports(Port1-Ports2)
              </span>
            </el-col>

            <el-col
                :span="24"
                style="margin-left: 20%;
                margin-top: 2%">
              <span class="tishi"
                    style="font-size: 15px;
                    color: red;">
                *声明：本网站仅供渗透测试学习使用，严禁用于非法渗透测试，所产生后果与本网站无关！*
              </span>
            </el-col>

            <el-col
                :span="24"
                style="margin-top: 5%;
                margin-left: 15%">
              <el-input v-model="ips"
                        style="width: 250px;
                        margin-right: 50px"
                        placeholder="ips">

              </el-input>
              <el-input v-model="ports"
                        style="width: 250px;
                        margin-right: 50px"
                        placeholder="ports">

              </el-input>
              <el-button @click="scan_start">
                scan
              </el-button>
            </el-col>

          </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->


      </el-card>


      <el-card v-if="show==1"
               style="margin-left: 0%;
               margin-top: 5%;
               width: 80%;
               margin-bottom: 10%;
               word-break:break-all">
        <el-table
            :data="myresult"
            border="true"
            style="width: 100%;">
          <el-table-column prop="ip" label="IP" />
          <el-table-column prop="port" label="端口" />
          <el-table-column prop="service" label="服务" />
          <el-table-column prop="isOpen" label="状态" />
          <!--      <el-table-column prop="banner" label="指纹" />-->
          <!--      <el-table-column prop="service" label="服务" />-->
        </el-table>
      </el-card>
  </el-row>

<!--  <div class="bf_form">-->
<!--    <div class="bf_form_main">-->
<!--      <h3 class="header blue lighter bigger">-->
<!--        Please Enter Ip And Ports(Port1-Ports2)-->
<!--      </h3>-->
<!--      <el-input v-model="ips" style="width: 250px;margin-right: 50px" placeholder="ips"></el-input>-->
<!--      <el-input v-model="ports" style="width: 250px;margin-right: 50px" placeholder="ports"></el-input>-->
<!--      <el-button @click="scan_start">scan</el-button>-->

<!--    </div>&lt;!&ndash; /.widget-main &ndash;&gt;-->
<!--  </div>&lt;!&ndash; /.widget-body &ndash;&gt;-->


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
// import router from '../../../router/index'
import request from "@/utils/request";

export default {
  data() {
    return {
      show:0,
      ips:'',
      ports:'',
      myresult:'',
      imgsrc: require('@/assets/img/toolsimg/scanport.jpeg'),
    }
  },
  methods:{
    scan_start(){
      this.show=1
      request.get("/tools/scan_port",{
        params:{
          ips:this.ips,
          ports:this.ports
        }
      }).then(res =>{
        this.myresult=res.data
        console.log(res)
      })
    }

  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #000000, 	#483D8B);
  overflow: hidden;
}


</style>