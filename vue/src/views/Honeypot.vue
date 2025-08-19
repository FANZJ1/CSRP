<template>
<!--  靶场模块-->
  <div class="honeypot">
    <sduheader></sduheader>
    <bug-card></bug-card>
  </div>
</template>

<script>
import Sduheader from "../components/sduheader.vue";
import BugCard from "../components/BugCard.vue";
import animation from "../assets/js/background_black";
export default {
  components: {
    Sduheader,
    BugCard,
    // Sdusider
  },
  created() {
    animation()
  },
  setup() {},
};
</script>


<style scoped>
.honeypot {
  /*background-image: url("../assets/img/bg4.jpg");*/
  /*background-size:100% 100%;*/

  background-color:rgba(105,105,105,0.5);
  margin: 0px;
  padding: 0px;
  min-height: 100vh;
}
</style>