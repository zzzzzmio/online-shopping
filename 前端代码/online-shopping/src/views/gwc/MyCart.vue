<script setup>
import { getCarts, updateCarts, deleteCarts } from '@/api/gwc'
import { ref } from 'vue'
import { Plus, Delete, Pointer } from '@element-plus/icons-vue'
import { Minus } from '@element-plus/icons-vue'
import { addOrders } from '@/api/orders'
const total = ref(0)
const page = ref({
  pageNum: 1,
  pageSize: 5
})
const list = ref([])
const getList = async () => {
  const res = await getCarts(page.value)
  total.value = res.data.data.total
  list.value = res.data.data.items
}
getList()
//每页条数切换
const handleSizeChange = (size) => {
  page.value.size = size
  page.value.pageNum = 1
  getList()
}
//切换页数
const handleCurrentChange = (num) => {
  page.value.pageNum = num
  getList()
}
const add = async (row) => {
  const data = {
    id: row.id,
    totalPrice: row.totalPrice + row.price,
    goodsNum: row.goodsNum + 1
  }
  await updateCarts(data)
  getList()
}
const sub = async (row) => {
  if (row.goodsNum > 1) {
    const data = {
      id: row.id,
      totalPrice: row.totalPrice - row.price,
      goodsNum: row.goodsNum - 1
    }
    await updateCarts(data)
    getList()
  }
}
const del = async (id) => {
  await deleteCarts(id)
  getList()
}
const pay = async (row) => {
  const res = [
    {
      goodsId: row.goodsId,
      goodsName: row.goodsName,
      totalPrice: row.totalPrice,
      shopUser: row.shopUser,
      goodsNum: row.goodsNum
    }
  ]
  await addOrders(res)
  await del(row.id)
  getList()
}
const carts = ref([])
const handleSelectionChange = (selection) => {
  carts.value = selection
  console.log(carts.value)
}
const delAll = async () => {
  for (let i = 0; i < carts.value.length; i++) {
    await deleteCarts(carts.value[i].id)
  }
  getList()
}
const payAll = async () => {
  const ordersToAdd = carts.value.map((cart) => ({
    goodsId: cart.goodsId,
    goodsName: cart.goodsName,
    totalPrice: cart.totalPrice,
    shopUser: cart.shopUser,
    goodsNum: cart.goodsNum
  }))

  await addOrders(ordersToAdd)

  for (const cart of carts.value) {
    await del(cart.id)
  }

  getList()
}
</script>
<template>
  <h1>我的购物车</h1>
  <el-table
    ref="multipleTableRef"
    :data="list"
    style="width: 100%"
    @selection-change="handleSelectionChange"
  >
    <el-table-column type="selection" width="55" />
    <el-table-column label="商品封面" width="120">
      <template #default="{ row }">
        <div class="cover-container">
          <img :src="row.coverImg" class="cover-image" />
        </div>
      </template>
    </el-table-column>
    <el-table-column property="goodsName" label="商品名称" width="120" />
    <el-table-column property="price" label="商品单价" show-overflow-tooltip />
    <el-table-column label="商品数量">
      <template #default="{ row }">
        <div class="quantity-container">
          <el-button
            type="success"
            :icon="Minus"
            circle
            @click="sub(row)"
            class="quantity-button"
          />
          <input
            type="number"
            id="num"
            v-model="row.goodsNum"
            min="1"
            class="quantity-input"
          />
          <el-button
            type="primary"
            :icon="Plus"
            circle
            @click="add(row)"
            class="quantity-button"
          />
        </div>
      </template>
    </el-table-column>
    <el-table-column property="totalPrice" label="订单总价"></el-table-column>
    <el-table-column label="操作">
      <template #default="{ row }">
        <div class="action-buttons">
          <el-button
            type="danger"
            :icon="Delete"
            circle
            class="action-button"
            @click="del(row.id)"
          />
          <el-button
            type="success"
            :icon="Pointer"
            circle
            class="action-button"
            @click="pay(row)"
          />
        </div>
      </template>
    </el-table-column>
  </el-table>
  <div style="display: flex; justify-content: flex-end; margin: 30px">
    <el-button type="warning" round @click="delAll">删除</el-button>
    <el-button type="danger" round @click="payAll">购买</el-button>
  </div>

  <el-pagination
    style="margin-top: 20px; justify-content: flex-end"
    v-model:current-page="page.pageNum"
    v-model:page-size="page.pageSize"
    :page-sizes="[2, 3, 5, 10]"
    background
    layout="total, sizes, prev, pager, next, jumper"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>
<style>
.cover-container {
  height: 100px;
  width: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.cover-image {
  max-width: 100%;
  max-height: 100%;
}

.quantity-container {
  display: flex;
  align-items: center;
}

.quantity-button {
  margin: 0 5px;
}

.quantity-input {
  width: 50px;
  height: 30px;
  text-align: center;
}

.action-buttons {
  display: flex;
}

.action-button {
  margin: 0 5px;
}
</style>
