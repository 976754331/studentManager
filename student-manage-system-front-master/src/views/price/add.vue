<template>
  <el-form ref="form" :model="form" label-width="80px" style="width:50%;margin-top:20px" >
    <el-form-item label="单价">
      <el-input v-model="form.priceAmount"></el-input>
    </el-form-item>
    <el-form-item label="折扣">
      <el-input v-model="form.discount"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import {addPrice} from '@/api/price'
  export default {
    data() {
      return {
        classVisible:false,
        form: {
          priceAmount: '',
          discount: ''
        }
      }
    },
    methods: {
      onSubmit() {
        var priceAmount = this.form.priceAmount;
        var discount = this.form.discount;
        var data = {
          priceAmount: priceAmount,
          discount: discount
        }

        addPrice(data).then(
          response => {
            this.$message({
              message: response.message,
              type: "success"
            })
          }
        )

      }
    },
    // watch:{
    //   grade: {
    //     handler: function(newVal, oldVal){
    //       if(newVal != oldVal){
    //         this.classVisible = true
    //       }
    //     },
    //     deep: true
    //   }
    // }
  }
</script>
