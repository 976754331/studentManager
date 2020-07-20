<template>
  <div>
    <el-input placeholder="请输入学生姓名搜索内容" v-model="content" class="input-with-select"
    style="margin-top: 15px;width:50%;margin-left: 15px;">
       <el-option label="姓名" value="2"></el-option>

      <el-button slot="append" icon="el-icon-search" @click="handleSearch()"></el-button>
    </el-input>

   <el-table
         :data="tableData"
         border
         style="width: 100%">
         <el-table-column
           label="学号"
           width=""
           align="center">
             <template slot-scope="scope">
               {{ scope.row.studentId }}
             </el-popover>
           </template>
         </el-table-column>
         <el-table-column
           label="姓名"
           width=""
           align="center">
             <template slot-scope="scope">
               {{ scope.row.studentName }}
             </el-popover>
           </template>
         </el-table-column>
         <el-table-column
             label="收费类型"
             width=""
             align="center">
               <template slot-scope="scope">
                 {{ scope.row.type | convertSex}}
               </el-popover>
             </template>
           </el-table-column>
           <el-table-column
                     label="金额"
                     width=""
                     align="center">
                       <template slot-scope="scope">
                         {{ scope.row.type | convertAmount}}  {{ scope.row.usedAmount}}
                       </el-popover>
                     </template>
                   </el-table-column>
         <el-table-column
           label="总额"
           width=""
           align="center">
             <template slot-scope="scope">
               {{ scope.row.totalAmount }}
             </el-popover>
           </template>
         </el-table-column>

         <el-table-column
           label="上课时间"
           width=""
           align="center">
             <template slot-scope="scope">
               {{ scope.row.startTime }}
             </el-popover>
           </template>
         </el-table-column>

         <el-table-column
                 label="创建时间"
                 width=""
                 align="center">
                   <template slot-scope="scope">
                     {{ scope.row.createTime }}
                   </el-popover>
                 </template>
               </el-table-column>
       </el-table>
  </div>
</template>

<style>
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>
<script type="text/javascript">
import {searchAccount} from '@/api/account'
export default {
  data() {
    return {
      tableData: [],
      content: '',
      select: '2'
    }
  },

  methods: {
    handleSearch(){
      var content = this.content;
      var select = this.select;
      var data = {
        content:content,
        select:select
      }
      if(select == null || "" == select){
        this.$message({
          message: "选择查询类别",
          type: 'error'
        });
        return;
      }

      if(content == null || "" == content){
        this.$message({
          message: "查询内容不能为空",
          type: 'error'
        });
        return;
      }
      searchAccount(data).then(
        response=>{
          this.tableData = [];
          if(response.data.length == 0){
            this.$message({
              message: "无匹配结果",
              type: 'warning'
            });
          }else{
            this.tableData = response.data;
          }

        }
      )
    }
  },
    filters:{
      convertSex(sex){
        switch(sex){
          case '1':
            return "消费"
            break
          case '0':
            return "缴费"
            break
        }
      },
      convertAmount(sex){
      switch(sex){
            case '1':
              return "- "
              break
            case '0':
              return "+ "
              break
          }
          }
    }
}
</script>
