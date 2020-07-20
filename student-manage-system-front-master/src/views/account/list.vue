<template>
<div class="">
  <el-row style="margin:10px 10px">
    <el-button type="primary" icon="el-icon-download"
    style = "float:right"
                @click="handleDownload()">导出</el-button>
  </el-row>
  <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        label="学号"
        width="94 rem"
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
          width="94 rem"
          align="center">
            <template slot-scope="scope">
              {{ scope.row.type | convertSex}}
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
                  label="金额"
                  width="94 rem"
                  align="center">
                    <template slot-scope="scope">
                      {{ scope.row.type | convertAmount}}  {{ scope.row.usedAmount}}
                    </el-popover>
                  </template>
                </el-table-column>
      <el-table-column
        label="总额"
        width="94 rem"
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

      <el-table-column
        label="操作"
        align="center">
        width="164 rem"
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


    <el-dialog title="编辑账单信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-input v-model="form.index" autocomplete="off" v-show="false"></el-input>
        <el-input v-model="form.id" autocomplete="off" v-show="false"></el-input>
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="form.num" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="金额" :label-width="formLabelWidth">
          <el-input v-model="form.usedAmount" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleUpdateStudent()">确 定</el-button>
      </div>
    </el-dialog>
</div>
</template>




<script type="text/javascript">
import {listAccount} from '@/api/account'
import {exportAccount} from '@/api/account'
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
        num: '',
        name: '',
        age: '',
        course: ''
      }

    }
  },
    methods: {
      handleEdit(index, row) {
        this.form.index = index;
        this.form.id = row.id
        this.form.num = row.studentId
        this.form.name = row.studentName
        this.form.usedAmount = row.usedAmount
        this.dialogFormVisible = true;
      },
      handleDelete(index, row) {
        //删除记录
        var id = row.id;
        this.$confirm('此操作将永久删除该条数据, 是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
        }).then(() => {
          deleteTeacher(id).then(
            response => {
              this.tableData.splice(index, 1);
              this.$message({
                  message: response.message,
                  type: 'success'
                });
            }
          )
         }).catch((response) => {
           console.log(response);
           this.$message({
             type: 'info',
             message: '已取消删除'
           });
         });
      },
      loadData(){
        listAccount().then(
          response => {
            this.tableData = response.data;
          }
        )
      },
      handleDownload() {
            exportAccount().then(response=>{
               this.$message({
                            type: 'info',
                            message: '下载成功'
                          });
            })
          },
      handleUpdateStudent(){
        var id = this.form.id;
        var age = this.form.age;
        var sex = this.formsex;
        var num = this.form.num;
        var name = this.form.name;
        var course = this.form.course;
        var data = {
          id: id,
          age: age,
          sex: sex,
          num: num,
          name: name,
          course: course
        }

        updateTeacher(data).then(
          //更新页面数据

          response => {
            // var i = this.form.index;
            // this.tableData[i].age = age;
            // this.tableData[i].sex = sex;
            // this.tableData[i].num = num;
            // this.tableData[i].name = name;
            // this.tableData[i].grade = grade;
            // this.tableData[i].clazz = clazz;
            // this.tableData[i].address = address;
            // this.dialogFormVisible = false;
            this.dialogFormVisible = false;
            this.loadData()
            this.$message({
              message: response.message,
              type: "success"
            })
          }
        )

      }
    },
  mounted: function(){
    this.loadData();
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
