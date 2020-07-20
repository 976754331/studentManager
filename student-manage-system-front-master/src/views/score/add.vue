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
    <el-form-item label="考试类型">
              <el-input v-model="form.examType"></el-input>
            </el-form-item>

   <el-form-item label="考试名称">
              <el-input v-model="form.examName"></el-input>
            </el-form-item>
    <el-form-item label="考试时间">
          <el-date-picker
            v-model="startTime"
            type="date"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            align="right">
          </el-date-picker>
    </el-form-item>

    <el-form-item label="成绩">
          <el-input v-model="form.score"></el-input>
        </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { listClazz } from '@/api/clazz';
import { addCourse } from '@/api/score';
import { listWithClazz } from '@/api/student';
import { listTeacher } from '@/api/teacher';
  export default {
    data() {
      return {
        classVisible:false,
        studentList: [],
        clazzList: [],
        teacherList: [],
        startTime:'',
        endTime:'',
        clazz: '',
        student: '',
        score:'',
        studentName: '',
        examType:'',
        examName: '',
        form: {
          courseContent: '',
          teacherId: '',
          timeSize: ''
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
      //加载年级数据
      listTeacher().then(
        response => {
          this.teacherList = response.data;
        }
      )
    },
    methods: {
      getStartData(){
        this.startTime=this.startTime;
      },
      getEndData(){
        //console.log(this.endTime);
      },
      onSubmit() {
        //提交添加请求
        var data = {
             examTime:this.startTime,
             classId:this.clazz,
             studentId: this.student,
             courseContent: this.form.courseContent,
             teacherId: this.form.teacherId,
             timeSize: this.form.timeSize,
             studentName: this.studentName,
             score:this.form.score,
             examType:this.form.examType,
             examName: this.form.examName
           }
           addCourse(data).then(
             response=>{
               this.$message({
                 message: response.message,
                 type: "success"
               })
                this.value1 = [];
                this.clazz = "";
                this.student = "";
                this.form.courseContent = "";
                this.form.teacherId = "";
                this.form.timeSize = "";
                this.studentName = "";
                this.form.score= "";
                this.form.examType= "";
                this.form.examName= "";
                this.startTime== "";

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
