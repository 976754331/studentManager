<template>
  <el-form ref="form" :model="form" label-width="100px" style="width:30%;margin-top:20px" >
    <el-form-item label="年级">
      <el-input v-model="grade"></el-input>
    </el-form-item>
    <el-form-item label="班级">
      <el-input v-model="clazz"></el-input>
    </el-form-item>
    <el-form-item label="班主任">
      <el-input v-model="headTeacher"></el-input>
    </el-form-item>
    <el-form-item label="限定总人数">
      <el-input v-model="totalStudent"></el-input>
    </el-form-item>
    <el-form-item label="价格">
          <el-select v-model="price" placeholder="请选择价格">
            <el-option :label = item.display :value=item.id v-for="(item, i) in priceList"></el-option>
          </el-select>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import {addClazz} from '@/api/clazz'
import {listPrice} from '@/api/price'
  export default {
    data() {
      return {
          grade: '',
          clazz: '',
          headTeacher: '',
          totalStudent: '',
          price: '',
          priceList: [],
      }
    },
    mounted: function(){
      //加载年级数据
      listPrice().then(
        response => {
          this.priceList = response.data;
        }
      )
    },
    methods: {
      onSubmit() {
        var grade = this.grade;
        var clazz = this.clazz;
        var headTeacher = this.headTeacher;
        var totalStudent = this.totalStudent;
        var price = this.price;
        if(null === grade || "" === grade
           || null === clazz || "" === clazz
           || null === headTeacher || "" === headTeacher
           || null === totalStudent || "" === totalStudent){
             this.$message({
                 message: "请填写完整的班级信息",
                 type: 'error'
               });
           }else{
             //发送新增请求
             var data = {
                clazz:{
                    grade: grade,
                   clazz: clazz,
                   headTeacher: headTeacher,
                   totalStudent: totalStudent
                },

               price: price
             }
             addClazz(data).then(
               response => {
                 if(response.code === "3002"){
                   this.$message({
                     message: "班级已存在",
                     type: "error"
                   })
                 }
                 if(response.code === 20000){
                   this.$message({
                     message: response.message,
                     type: "success"
                   })
                   this.grade = ""
                   this.clazz = ""
                   this.headTeacher = ""
                   this.totalStudent = ""
                   this.grade = ""
                 }
               }
             )

           }
      }
    }
  }
</script>
