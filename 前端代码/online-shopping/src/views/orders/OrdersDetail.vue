<script setup>
import { useUserStore } from '@/stores'
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { goodsDetail } from '@/api/goods'
import { addOrders } from '@/api/orders'
import router from '@/router'
const dialogVisible = ref(false)
const detail = ref()
const goodsName = ref('')
const totalPrice = ref(0)
onMounted(async () => {
  try {
    const response = await goodsDetail(route.params.id)
    detail.value = response.data.data
    goodsName.value = detail.value.goodsName
    totalPrice.value = detail.value.price * route.params.num
  } catch (error) {
    console.error('Failed to fetch goods details:', error)
    // 处理错误，例如显示错误消息给用户或者重试
  }
})
const route = useRoute()
const userStore = useUserStore()
const userInfo = ref(userStore.user)
const address = ref(userStore.address)
const addOrder = async () => {
  const orderData = [
    {
      goodsId: detail.value.id,
      goodsName: detail.value.goodsName,
      totalPrice: route.params.num * detail.value.price,
      shopUser: detail.value.createUser,
      goodsNum: route.params.num
    }
  ]
  dialogVisible.value = true // 显示弹窗
  setTimeout(() => {
    dialogVisible.value = false // 5秒后自动关闭弹窗
    router.replace('/orders') // 弹窗关闭后跳转
  }, 5000)
  await addOrders(orderData)
  ElMessage.success('购买成功')
}
const goBack = () => {
  router.go(-1)
}
</script>
<template>
  <div>
    <el-descriptions
      class="margin-top"
      title="订单详情"
      :column="2"
      :size="size"
      border
    >
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <user />
            </el-icon>
            用户名称
          </div>
        </template>
        {{ userInfo.username }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <iphone />
            </el-icon>
            电子邮箱
          </div>
        </template>
        {{ userInfo.email }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <location />
            </el-icon>
            收货地址
          </div>
        </template>
        {{ address }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <tickets />
            </el-icon>
            商品名称
          </div>
        </template>
        {{ goodsName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <tickets />
            </el-icon>
            价格
          </div>
        </template>
        {{ totalPrice }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <office-building />
            </el-icon>
            数量
          </div>
        </template>
        {{ route.params.num }}
      </el-descriptions-item>
    </el-descriptions>
    <el-button type="primary" @click="addOrder">提交订单</el-button>
    <el-button @click="goBack">取消</el-button>
  </div>
  <el-dialog
    v-model="dialogVisible"
    title="订单成功"
    @close="dialogVisible = false"
  >
    <img
      src="@/assets/5b22df963b261bc6e7213b9b300fe40c.jpg"
      alt="订单成功图片"
    />
  </el-dialog>
</template>
<style>
.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}
</style>
