<script setup>
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { goodsDetail } from '@/api/goods'
import { Plus } from '@element-plus/icons-vue'
import { Minus } from '@element-plus/icons-vue'
import { addCarts } from '@/api/gwc'
import router from '@/router'

const route = useRoute()
const res = ref({}) // 设置一个初始值
const num = ref(1)
onMounted(async () => {
  try {
    const response = await goodsDetail(route.params.id)
    res.value = response.data.data
    console.log(res.value)
  } catch (error) {
    console.error('Failed to fetch goods details:', error)
    // 处理错误，例如显示错误消息给用户或者重试
  }
})
const add = () => {
  num.value++
}
const sub = () => {
  if (num.value > 1) {
    num.value--
  }
}
const addOrder = (id) => {
  router.replace(`/orders/detail/${id}/${num.value}`)
}
const addCart = async () => {
  const data = {
    goodsId: res.value.id,
    goodsName: res.value.goodsName,
    price: res.value.price,
    coverImg: res.value.coverImg,
    totalPrice: num.value * res.value.price,
    shopUser: res.value.createUser,
    goodsNum: num.value
  }
  await addCarts(data)
  ElMessage.success('添加成功')
}
</script>

<template>
  <div class="product-details-container">
    <h1>商品详情:</h1>
    <div class="product-details-content">
      <div class="product-image-container">
        <el-image
          class="product-image"
          :src="res.coverImg"
          style="width: 500px; height: 500px"
          :zoom-rate="1.2"
          :max-scale="7"
          :min-scale="0.2"
          :preview-src-list="srcList"
          fit="cover"
        />
      </div>
      <div class="product-info">
        <h1 class="product-name">商品名称：{{ res.goodsName }}</h1>
        <p class="product-price">商品单价：{{ res.price }}</p>
        <div class="quantity-container">
          <label for="quantity">数量：</label>
          <el-button type="success" :icon="Minus" circle @click="sub" />
          <input
            type="number"
            id="num"
            v-model="num"
            min="1"
            style="width: 30px; height: 20px"
          />
          <el-button type="primary" :icon="Plus" circle @click="add" />
        </div>
        <div class="product-description">
          <h1>商品描述:</h1>
          <p>{{ res.content }}</p>
        </div>
        <div class="action-buttons">
          <el-button type="warning" round @click="addOrder(res.id)"
            >立即购买</el-button
          >
          <el-button type="danger" round @click="addCart">加入购物车</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.product-details-container {
  margin: 150px;
}

.product-details-content {
  display: flex;
}

.product-image-container {
  margin: 30px;
}

.product-info {
  margin: 30px;
}

.product-name {
  font-size: 24px;
}

.product-price {
  font-size: 18px;
}

.quantity-container {
  margin-bottom: 20px;
}

.product-description {
  margin-bottom: 20px;
}

.action-buttons {
  margin-top: 20px;
}
</style>
