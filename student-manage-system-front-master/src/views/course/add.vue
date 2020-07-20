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
    <el-form-item label="上课内容">
              <el-input v-model="form.courseContent"></el-input>
            </el-form-item>
    <el-form-item label="教师">
          <el-select v-model="form.teacherId" placeholder="请选择教师">
            <el-option :label = item.name :value=item.name v-for="(item, i) in teacherList"></el-option>
          </el-select>
        </el-form-item>
    <el-form-item label="开始-结束">
      <el-date-picker
        v-model="value1"
        type="datetimerange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        format="yyyy-MM-dd HH:mm:ss"
        value-format="yyyy-MM-dd HH:mm:ss"
        >
      </el-date-picker>
    </el-form-item>
    <!--
    <el-form-item label="开始时间">
          <el-date-picker
            v-model="startTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            @change="getStartData"
            placeholder="选择日期时间"
            align="right">
          </el-date-picker>
    </el-form-item>
    <el-form-item label="结束时间">
          <el-date-picker
            v-model="endTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            @change="getEndData"
            placeholder="选择日期时间"
            align="right">
          </el-date-picker>
      </el-form-item>
      -->
    <el-form-item label="时长">
          <el-input v-model="form.timeSize"></el-input>
        </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { listClazz } from '@/api/clazz';
import { addCourse } from '@/api/course';
import { listWithClazz } from '@/api/student';
import { listTeacher } from '@/api/teacher';
  export default {
    data() {
      return {
        value1:[],
        classVisible:false,
        studentList: [],
        clazzList: [],
        teacherList: [],
        startTime:'',
        endTime:'',
        clazz: '',
        student: '',
        studentName: '',
        form: {
          courseContent: '',
          teacherId: '',
          timeSize: ''
        }
      }
    },
    mounted: function(){

       const end = new Date();
       const start = new Date();
       this.value1=[start.getTime(),end.getTime()];

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
        //console.log(this.startTime);
      },
      getEndData(){
        //console.log(this.endTime);
      },
      onSubmit() {
        //提交添加请求
        var data = {
             startTime:this.value1[0],
             endTime:this.value1[1],
             classId:this.clazz,
             studentId: this.student,
             courseContent: this.form.courseContent,
             teacherId: this.form.teacherId,
             timeSize: this.form.timeSize,
             studentName: this.studentName
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
