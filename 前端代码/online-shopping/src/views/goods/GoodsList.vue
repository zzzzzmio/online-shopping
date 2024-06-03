<script setup>
// import { useUserStore } from '@/stores'
import { ref, nextTick } from 'vue'
import { customerList, sellerList } from '@/api/goods'
import { Search } from '@element-plus/icons-vue'
import ChannelSelect from './components/ChannelSelect.vue'
import router from '@/router'
import { useUserStore } from '@/stores'
import GoodsDetail from '@/components/GoodsDetail.vue'
// const userStore = useUserStore()
const userStore = useUserStore()
const drawer = ref(false)
const Detail = ref()
const cList = ref([])
const sList = ref([])
const total = ref(0)
const page = ref({
  pageNum: 1,
  pageSize: 5,
  categoryId: ''
})
const getCusList = async () => {
  const res = await customerList(page.value)
  total.value = res.data.data.total
  cList.value = res.data.data.items
  // console.log(cList.value)
}
const getSelList = async () => {
  const res = await sellerList(page.value)
  total.value = res.data.data.total
  sList.value = res.data.data.items
}
const getList = async () => {
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
// 搜索逻辑 => 按照最新的条件，重新检索，从第一页开始展示
const onSearch = () => {
  page.value.pageNum = 1 // 重置页面
  getList()
  console.log(sList.value)
}
// 重置逻辑 => 将筛选条件清空，重新检索，从第一页开始展示
const onReset = () => {
  page.value.pageNum = 1 // 重置页面
  page.value.categoryId = ''
  getList()
}
const search = (id) => {
  router.replace(`/goods/detail/${id}`)
}
const check = (row) => {
  drawer.value = true
  // 使用 nextTick 来确保 Detail 被正确创建和挂载
  nextTick(() => {
    // 访问 ref 并确保 Detail 组件已经被正确创建
    if (Detail.value && Detail.value.open) {
      Detail.value.open(row)
    }
  })
}
const onSuccess = () => {
  drawer.value = false
  getList()
}
const add = () => {
  drawer.value = true
}
</script>
<template>
  <div>
    <el-form inline>
      <el-form-item label="商品分类">
        <channel-select
          v-model="page.categoryId"
          :width="'200px'"
        ></channel-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="onSearch" type="primary">搜索</el-button>
        <el-button @click="onReset">重置</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="add" v-if="userStore.user.usertype === 2"
      >添加商品</el-button
    >
  </div>

  <!-- //客户 -->
  <el-table
    v-if="userStore.user.usertype === 1"
    :data="cList"
    stripe
    style="width: 100%"
  >
    <el-table-column label="商品封面">
      <template #default="{ row }">
        <div class="cover-container">
          <img :src="row.coverImg" class="cover-image" />
        </div>
      </template>
    </el-table-column>

    <el-table-column prop="goodsName" label="商品名称" width="180" />
    <el-table-column prop="price" label="价格" />
    <el-table-column label="操作">
      <template #default="{ row }">
        <el-button
          :icon="Search"
          circle
          @click="search(row.id)"
          class="action-button"
        />
      </template>
    </el-table-column>
  </el-table>
  <!-- //商家 -->
  <el-table v-else :data="sList" stripe style="width: 100%">
    <el-table-column label="商品封面">
      <template #default="{ row }">
        <div class="cover-container">
          <img :src="row.coverImg" class="cover-image" />
        </div>
      </template>
    </el-table-column>

    <el-table-column prop="goodsName" label="商品名称" width="180" />
    <el-table-column prop="price" label="价格" />
    <el-table-column label="操作">
      <template #default="{ row }">
        <el-button
          :icon="Search"
          circle
          @click="check(row)"
          class="action-button"
        />
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
  <el-drawer v-model="drawer">
    <GoodsDetail ref="Detail" @success="onSuccess"></GoodsDetail>
  </el-drawer>
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

.action-button {
  margin: 0 5px;
}
</style>
