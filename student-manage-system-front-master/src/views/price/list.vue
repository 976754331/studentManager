<template>
<div class="">
  <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        label="序号"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.id }}
          </template>
      </el-table-column>
      <el-table-column
        label="单价"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.priceAmount }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="折扣"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.discount }}
          </el-popover>
        </template>
      </el-table-column>

    </el-table>



</div>
</template>




<script type="text/javascript">
import {listPrice} from '@/api/price'
export default {
  data () {
    return {
      formLabelWidth:'',
      sexRadio: '',
      tableData: [],
      dialogFormVisible: false,
      formsex: 0,
      form: {
        index: '',
        id: '',
        priceAmount: '',
        discount: '',
        deleteStatus: ''
      }

    }
  },
    methods: {

      loadData(){
        listPrice().then(
          response => {
            this.tableData = response.data;
          }
        )
      },



    },
  mounted: function(){
    this.loadData();
  },
  filters:{
    convertSex(sex){
      switch(sex){
        case 1:
          return "男"
          break
        case 0:
          return "女"
          break
      }
    }
  }
}
</script>
