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
            width="64 rem"
            align="center">
              <template slot-scope="scope">
                {{ scope.row.studentId }}
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
            label="姓名"
            width="104 rem"
            align="center">
              <template slot-scope="scope">
                {{ scope.row.studentName }}
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
            label="班级"
            width="104 rem"
            align="center">
              <template slot-scope="scope">
                {{ scope.row.classId }}
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
            label="考试类型"
             width="140 rem"
             align="center">
               <template slot-scope="scope">
                 {{ scope.row.examType }}
               </el-popover>
             </template>
          </el-table-column>
          <el-table-column
            label="考试名称"
            width=""
            align="center">
              <template slot-scope="scope">
                {{ scope.row.examName }}
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
                  label="考试日期"
                  width="140 rem"
                  align="center">
                    <template slot-scope="scope">
                      {{ scope.row.examTime }}
                    </el-popover>
                  </template>
                </el-table-column>
          <el-table-column
                  label="成绩"
                  width="64 rem"
                  align="center">
                    <template slot-scope="scope">
                      {{ scope.row.score }}
                    </el-popover>
                  </template>
                </el-table-column>

          <el-table-column
            label="操作"
            width="164 rem"
            align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
import {searchCourse} from '@/api/score'
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
      searchCourse(data).then(
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
  }
}
</script>
