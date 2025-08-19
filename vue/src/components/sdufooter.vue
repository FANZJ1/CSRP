<template>
  <el-row :gutter="10">
    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
      <div class="grid-content bg-purple"></div>
    </el-col>
    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
      <div class="grid-content bg-purple">
        <p>WEBSITE</p>
        <a class="aaa" href="https://gitee.com/cgchacker/SduCSRP">Project Code</a><br>
        <a class="aaa" href="https://blog.csdn.net/qq_50860232/category_11649911.html">BLOG</a><br>
      </div>
    </el-col>
    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
      <div class="grid-content bg-purple">
        <el-divider direction="vertical" style="color: gray"></el-divider>
      </div>
    </el-col>
    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
      <div class="grid-content bg-purple">
        <p>CONTACT US</p>
        <a class="aaa" href="https://blog.csdn.net/qq_50860232">Ling Chen</a><br>
        <a class="aaa" href="https://blog.csdn.net/m0_52100140">Fan Zhijie</a><br>
        <a class="aaa" href="https://blog.csdn.net/m0_51588039">Qiao Jingbo</a><br>
        <a class="aaa" href="https://blog.csdn.net/m0_47470899">Li Hongwei</a><br>
        <a class="aaa" href="https://blog.csdn.net/weixin_50803761">Liu Jingyou</a><br>
      </div>
    </el-col>
    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
      <div class="grid-content bg-purple"></div>
    </el-col>
  </el-row>
</template>

<style  scoped>
.el-row {
  color: azure;
  height: 500px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple {
  margin-top: 40%;
  text-align: center;
  font-size: 25px;
  /* background-color: antiquewhite; */
  height: 300px;
}
.bg-purple .aaa {
  text-align: center;
  font-size: 20px;
  text-decoration:none;
  color: gray;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.el-divider {
  position: relative;
  vertical-align: middle;
  height: 20ex;
}
</style>