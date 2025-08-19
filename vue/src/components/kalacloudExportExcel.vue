<template>

  <div class="result-table">

    <el-table :data="items">
      <el-table-column prop="name" label="姓名" width="120" />
      <el-table-column prop="age" label="年龄" width="120" />
      <el-table-column prop="phone" label="电话" width="120" />
    </el-table>

    <button type="button" class="kalacloudExportExcel-button" @click="download">导出至 Excel</button>
  </div>

</template>

<style scoped>
.result-table {
  width: 70%;
  text-align: center;
}
.kalacloudExportExcel-button {
  background-color: DodgerBlue;
  border: none;
  color: white;
  padding: 12px 30px;
  margin: 12px 0;
  cursor: pointer;
  font-size: 20px;
}

.kalacloudExportExcel-button:hover {
  background-color: RoyalBlue;
}
</style>

<!--<script>-->
<!--import XLSX from 'xlsx';-->
<!--export default {-->
<!--  name: 'kalacloudExportExcel',-->
<!--  data() {-->
<!--    return {-->
<!--      items: [-->
<!--        {-->
<!--          name: '谢国庆',-->
<!--          age: 29,-->
<!--          phone: '13697653219'-->
<!--        },-->
<!--        {-->
<!--          name: '吕小果',-->
<!--          age: 25,-->
<!--          phone: '13235789213'-->
<!--        },-->
<!--        {-->
<!--          name: '宋阿美',-->
<!--          age: 27,-->
<!--          phone: '13756776977'-->
<!--        },-->
<!--        {-->
<!--          name: '蒋铁柱',-->
<!--          age: 33,-->
<!--          phone: '15003373377'-->
<!--        }-->
<!--      ]-->
<!--    }-->
<!--  },-->
<!--  methods:{-->
<!--    download () {-->
<!--      const data = XLSX.utils.json_to_sheet(this.items)-->
<!--      const wb = XLSX.utils.book_new()-->
<!--      XLSX.utils.book_append_sheet(wb, data, 'kalacloud-data')-->
<!--      XLSX.writeFile(wb,'kalacloudExportExcel.xlsx')-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->
