<script setup>
import { cusOrders, updateOrders, selOrders } from '@/api/orders'
import { useUserStore } from '@/stores'
import { ref } from 'vue'
const userStore = useUserStore()
const total = ref(0)
const page = ref({
  pageNum: 1,
  pageSize: 5
})
const cList = ref([])
const sList = ref([])
const getCusList = async () => {
  const res = await cusOrders(page.value)
  total.value = res.data.data.total
  cList.value = res.data.data.items
}
const getSelList = async () => {
  const res = await selOrders(page.value)
  total.value = res.data.data.total
  sList.value = res.data.data.items
}
const getList = () => {
  if (userStore.user.usertype === 1) {
    getCusList()
  }
  if (userStore.user.usertype === 2) {
    getSelList()
  }
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
const formatState = (row, column, cellValue) => {
  if (cellValue === 1) {
    return '待发货'
  } else if (cellValue === 2) {
    return '已发货'
  } else {
    return '已签收'
  }
}
const signFor = async (id) => {
  await updateOrders(id, 3)
  getList()
}
const Dispatch = async (id) => {
  await updateOrders(id, 2)
  getList()
}
</script>
<template>
  <h1>我的订单</h1>
  <!-- 客户 -->
  <el-table
    :data="cList"
    stripe
    style="width: 100%"
    v-if="userStore.user.usertype === 1"
  >
    <el-table-column prop="id" label="订单编号"></el-table-column>
    <el-table-column prop="goodsName" label="商品名称"></el-table-column>
    <el-table-column prop="totalPrice" label="订单价格"></el-table-column>
    <el-table-column prop="goodsNum" label="商品数量"></el-table-column>
    <el-table-column
      prop="state"
      label="状态"
      :formatter="formatState"
    ></el-table-column>
    <el-table-column label="操作">
      <template #default="{ row }">
        <el-button
          v-if="row.state === 2"
          type="warning"
          round
          @click="signFor(row.id)"
          >签收</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <!-- 商家 -->
  <el-table stripe style="width: 100%" :data="sList" v-else>
    <el-table-column prop="id" label="订单编号"></el-table-column>
    <el-table-column prop="goodsName" label="商品名称"></el-table-column>
    <el-table-column prop="totalPrice" label="订单价格"></el-table-column>
    <el-table-column prop="goodsNum" label="商品数量"></el-table-column>
    <el-table-column
      prop="state"
      label="状态"
      :formatter="formatState"
    ></el-table-column>
    <el-table-column label="操作">
      <template #default="{ row }">
        <el-button
          v-if="row.state === 1"
          type="warning"
          round
          @click="Dispatch(row.id)"
          >发货</el-button
        >
      </template>
    </el-table-column>
  </el-table>
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
<style></style>
