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
        label="学号"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.num }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="姓名"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="家庭住址"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.address }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
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

    <el-dialog title="编辑学生信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-input v-model="form.index" autocomplete="off" v-show="false"></el-input>
        <el-input v-model="form.id" autocomplete="off" v-show="false"></el-input>
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="form.num" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth">
          <el-input v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-collapse-transition>
              <el-form-item label="课时费用" v-show="classVisible">
                <el-select v-model="form.clazz" placeholder="请选择课时费用">
                  <el-option :label=item :value=item v-for="(item, i) in listPrice"></el-option>
                </el-select>
              </el-form-item>
            </el-collapse-transition>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleUpdateStudent()">确 定</el-button>
      </div>
    </el-dialog>
</div>
</template>




<script type="text/javascript">
import {listPrice} from '@/api/price'
import { listStudent, updateStudent, deleteStudent } from '@/api/student';

export default {
  data () {
    return {
      formLabelWidth:'',
      dialogFormVisible: false,
      clazzVisible: false,
      tableData: [],
      gradeList:[],
      listPrice: [],
      formGrade:'',
      formsex: 1,
      form: {
        index: '',
        id: '',
        num: '',
        name: '',
        age: '',
        clazz: '',
        address: ''
      }
    }
  },
  methods: {
      handleEdit(index, row) {
        this.form.index = index;
        this.form.id = row.id
        this.form.age = row.age
        this.formsex = row.sex
        this.form.num = row.num
        this.form.name = row.name
        this.form.clazz = row.clazz
        this.form.address = row.address
        listPrice().then(
          response => {
            this.listPrice = response.data
          }
        )
        this.formsex = row.sex
        this.formGrade = row.grade
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
          deleteStudent(id).then(
            response => {
              this.tableData.splice(index, 1);
              this.$message({
                  message: response.message,
                  type: 'success'
                });
            }
          )
         }).catch(() => {
           this.$message({
             type: 'info',
             message: '已取消删除'
           });
         });

      },
      handleUpdateStudent(){
        var studentId = this.form.id;
        var priceId = this.formGrade;
          //请求后台
          var data = {
            studentId: studentId,
            priceId: priceId,
          }
          updateStudent(data).then(
            //更新页面数据
            response => {
              this.dialogFormVisible = false;
              this.loadData()
              this.$message({
                message: response.message,
                type: "success"
              })
            }
          )

      },
      loadData(){
        listStudent().then(
          response=>{
            this.tableData = response.data;
          }
        )
      }
  },
  mounted: function(){
    this.loadData()
  },
  filters: {
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
  },
  watch: {
    formGrade: {
      handler: function(newVal, oldVal){
        if(newVal != oldVal){
          listPrice(newVal).then(
            response => {
              this.listPrice = response.data
            }
          )
          this.clazzVisible = true
        }
      },
      deep: true
    }
  }
}
</script>
