<script setup>
import { useUserStore } from '@/stores'
import { userAddress } from '@/api/user'
import { ref, onMounted } from 'vue'
const form = ref()
const userStore = useUserStore()
onMounted(async () => {
  userStore.Address()
})
const formModel = ref({
  address: ''
})
let oldAddress = userStore.address
const rules = {
  address: [{ required: true, message: '请输入收货地址', trigger: 'blur' }]
}
//提交
const onSubmit = async () => {
  await form.value.validate
  await userAddress(formModel.value.address)
  ElMessage.success('更新成功!')
  userStore.Address()
  location.reload() // 刷新页面
}
</script>
<template>
  <page-container title="收货地址">
    <el-card>
      <template #header>
        <div class="card-header">
          <span style="font-weight: bold; font-size: 16px">收货地址</span>
        </div>
      </template>
      <el-row>
        <el-col :span="12">
          <el-form
            label-width="100px"
            size="large"
            :model="formModel"
            :rules="rules"
            ref="form"
          >
            <el-form-item label="原收货地址">
              <el-input v-model="oldAddress" disabled></el-input>
            </el-form-item>
            <el-form-item label="现收货地址" prop="address">
              <el-input v-model="formModel.address"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">提交修改</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-card>
  </page-container>
</template>
