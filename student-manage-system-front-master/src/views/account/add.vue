<template>
  <el-form ref="form" :model="form" label-width="80px" style="width:50%;margin-top:20px" >
    <el-form-item label="班级">
      <el-select v-model="clazz" placeholder="请选择班级">
        <el-option :label = item.clazz :value=item.clazz v-for="(item, i) in clazzList"></el-option>
      </el-select>
    </el-form-item>
    <el-collapse-transition>
      <el-form-item label="学生" v-show="classVisible">
        <el-select v-model="student" placeholder="请选择学生">
          <el-option :label=item.name :value=item.id v-for="(item, i) in studentList"></el-option>
        </el-select>
      </el-form-item>
    </el-collapse-transition>
    <el-form-item label="金额">
              <el-input v-model="form.usedAmount"></el-input>
            </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { listClazz } from '@/api/clazz';
import { addAccount } from '@/api/account';
import { listWithClazz } from '@/api/student';
  export default {
    data() {
      return {
        classVisible:false,
        studentList: [],
        clazzList: [],
        student: '',
        clazz:'',
        studentName: '',
        form: {
          usedAmount: ''
        }
      }
    },
    mounted: function(){
      //加载年级数据
      listClazz().then(
        response => {
          this.clazzList = response.data;
        }
      )
    },
    methods: {
      onSubmit() {
        //提交添加请求
        var data = {
             type:'0',
             studentId: this.student,
             usedAmount: this.form.usedAmount,
             studentName: this.studentName
           }
           addAccount(data).then(
             response=>{
               this.$message({
                 message: response.message,
                 type: "success"
               })
                this.student = "";
                this.form.usedAmount = "";
                this.studentName = "";
             }
           )
         }
      },
    watch:{
      clazz: {
        handler: function(newVal, oldVal){
          if(newVal != oldVal){
            listWithClazz(newVal).then(
              response => {
                this.studentList = response.data
              }
            )
            this.classVisible = true
          }
        },
        deep: true
      },
      student: {
        handler: function(newVal, oldVal){
          if(newVal != oldVal){
            this.studentName = newVal
          }
        },
        deep: true
      }
    }
  }
</script>
