<template>
  <div class="home">
    <sduheader></sduheader>
<!--    <el-image :src="imgsrc" style="width: 100%;"/>-->
    <sducarousel></sducarousel>
    <div style="margin-top: 5%">
      <el-row style="margin-left: 5%;width: 90%">
        <el-col :span="8">
          <el-card v-if="see1==0" class="Functioncard"  id="card1"  @mouseenter="enter(1)" @click="goto(1)">
            <div style="color: white;margin-bottom:0px;padding-bottom: 0px;position: absolute;bottom: 5%">
              <div>
                <h1 style="text-align: left;font-family:宋体;font-size: 35px;">CTF靶场</h1>
              </div>
              <p style="font-size: 25px;text-align: left;margin-bottom: 0px;">
                <i class="el-icon-right" style=""></i>
                Learn more about it
              </p>
            </div>
          </el-card>
          <el-card v-if="see1==1" class="Functioncard"  id="card1_2"  @mouseleave="leave(1)" @click="goto(1)">
            <div style="color: white;margin-bottom:0px;padding-bottom: 0px;position: absolute;bottom: 5%">
              <div >
                <h1 style="text-align: left;font-family:宋体;font-size: 35px;">CTF靶场</h1>
                <el-divider   style="margin-bottom: 0px;"/>
                <h3 style="text-align: left">集成了BAC、CSRF等十余种漏洞，渴望你的攻击与探索</h3>
              </div>
              <p style="font-size: 25px;text-align: left;margin-bottom: 0px;">
                <i class="el-icon-right" style=""></i>
                Learn more about it
              </p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card v-if="see2==0" class="Functioncard"  id="card2"  @mouseenter="enter(2)" @click="goto(2)">
            <div style="color: white;margin-bottom:0px;padding-bottom: 0px;position: absolute;bottom: 5%">
              <div >
                <h1 style="text-align: left;font-family:宋体;font-size: 35px;">能力提升</h1>
              </div>
              <p style="font-size: 25px;text-align: left;margin-bottom: 0px;">
                <i class="el-icon-right" style=""></i>
                Learn more about it
              </p>
            </div>
          </el-card>
          <el-card v-if="see2==1" class="Functioncard"  id="card2_2"   @mouseleave="leave(2)" @click="goto(2)">
            <div style="color: white;margin-bottom:0px;padding-bottom: 0px;position: absolute;bottom: 5%">
              <div>
                <h1 style="text-align: left;font-family:宋体;font-size: 35px;">能力提升</h1>
                <el-divider   style="margin-bottom: 0px;"/>
                <h3 style="text-align: left">使用丰富的网安工具和提升你的的代码审计能力</h3>
              </div>
              <p style="font-size: 25px;text-align: left;margin-bottom: 0px;">
                <i class="el-icon-right" style=""></i>
                Learn more about it
              </p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card v-if="see3==0" class="Functioncard"  id="card3"  @mouseenter="enter(3)"  @click="goto(3)">
            <div style="color: white;margin-bottom:0px;padding-bottom: 0px;position: absolute;bottom: 5%">
              <div>
                <h1 style="text-align: left;font-family:宋体;font-size: 35px;">writeup</h1>
              </div>
              <p style="font-size: 25px;text-align: left;margin-bottom: 0px;">
                <i class="el-icon-right" style=""></i>
                Learn more about it
              </p>
            </div>
          </el-card>
          <el-card v-if="see3==1" class="Functioncard"  id="card3_2"  @mouseleave="leave(3)" @click="goto(3)">
            <div style="color: white;margin-bottom:0px;padding-bottom: 0px;position: absolute;bottom: 5%">
              <div >
                <h1 style="text-align: left;font-family:宋体;font-size: 35px;">writeup</h1>
                <el-divider   style="margin-bottom: 0px;"/>
                <h3 style="text-align: left">自由的分享题解，交流攻击心得，展现你的聪明才智</h3>
              </div>
              <p style="font-size: 25px;text-align: left;margin-bottom: 0px;">
                <i class="el-icon-right" style=""></i>
                Learn more about it
              </p>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <sdufooter></sdufooter>
  </div>
</template>

<script>
import Sduheader from '../components/sduheader.vue';
import sducarousel from '../components/sducarousel.vue';
import sdufooter from '../components/sdufooter.vue'
import animation from "../assets/js/background_black";
import router from "../router";
export default {
  name: "Home",
  components: {
    sducarousel,
    Sduheader,
    sdufooter
    // HelloWorld,
  
  },
  data(){
    return{
      imgsrc: require('@/assets/img/home.png'),
      see1:0,
      see2:0,
      see3:0,
    }
  },
  created() {
    animation()
//     var thispath=window.location.href
//
// //本地用这个字符串截取
//     var thisport=Number(thispath.substring(17,21))
// //服务器用这个字符串截取
// // var thisport=thispath.slice(14,4)
//
//     var newport=String(thisport+1010)
//     console.log(newport)
  },
  methods: {
    enter(n){
      if (n==1) this.see1=1;
      else if(n==2) this.see2=1;
      else this.see3=1;
    },
    leave(n){
      if (n==1) this.see1=0;
      else if(n==2) this.see2=0;
      else this.see3=0;
    },
    goto(n){
      if (n==1)  router.push('/honeypot')
      else if(n==2) router.push('/abilitypromotion')
      else router.push('/forum')
    }
  }
  // setup() {
  //   //测试
  //   onBeforeMount(() => {
  //     console.log("onBeforeMount");
  //   });
  //   console.log("setup执行了");
  //   //ref方法是让简单数据类型变成响应式的，需要用.value进行操作（reactive是让复杂数据变成响应式的）
  //   let m = ref(1);
  //
  //   let imgsrc= require('@/assets/img/home.png');
  //   let addfun = () => {
  //     m.value++;
  //   };
  //   return {
  //     m,
  //     imgsrc,
  //     addfun,
  //   };
  // },
};
</script>

<style scoped>
div.home{
  /*background-color: black;*/
  /* background-image: url("../assets/img/background.jpg"); */
  margin: 0px;
  padding: 0px;
}
.Functioncard{

  /*background:url("../assets/img/home.png");*/
  /*background-color: #303133;*/
  /*width: 100%;*/
  height:500px;
  margin: 5%;
  border-style: none;
  position: relative;
  cursor:pointer;
}
#card1{
  background:url("../assets/img/攻击靶场.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
#card1_2{
  background:url("../assets/img/攻击靶场2.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
#card2{
   background:url("../assets/img/能力提升.jpg");
   background-size: 100% 100%;
   background-repeat: no-repeat;
}
#card2_2{
  background:url("../assets/img/能力提升2.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
#card3{
    background:url("../assets/img/writeup.jpg");
    background-size: 100% 100%;
    background-repeat: no-repeat;
}
#card3_2{
  background:url("../assets/img/writeup2.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
</style>