<script setup>
import { getList, getInfo, updateCategory, addCategory } from '@/api/category'
import { ref } from 'vue'
import { Edit } from '@element-plus/icons-vue'
const total = ref(0)
const page = ref({
  pageNum: 1,
  pageSize: 5
})
const formModel = ref({
  categoryName: ''
})
const dia = ref(false)
const form = ref({
  categoryName: ''
})
const list = ref([])
const dialog = ref(false)
const getCategory = async () => {
  const res = await getList(page.value)
  total.value = res.data.data.total
  list.value = res.data.data.items
}
getCategory()
//每页条数切换
const handleSizeChange = (size) => {
  page.value.pageSize = size
  page.value.pageNum = 1
  getCategory()
}
//切换页数
const handleCurrentChange = (num) => {
  page.value.pageNum = num
  getCategory()
}
const edit = async (id) => {
  const {
    data: { data }
  } = await getInfo(id)
  formModel.value.categoryName = data.categoryName
  formModel.value.id = data.id
  dialog.value = true
}
const dialogFormVisible = () => {
  formModel.value.categoryName = ''
  dialog.value = false
}
const submit = async () => {
  await updateCategory(formModel.value.categoryName, formModel.value.id)
  dialog.value = false
  formModel.value.categoryName = ''
  ElMessage.success('修改成功')
  getCategory()
}
const add = () => {
  dia.value = true
}
const cancel = () => {
  form.value.categoryName = ''
  dia.value = false
}
const addC = async () => {
  await addCategory(form.value)
  form.value.categoryName = ''
  dia.value = false
  ElMessage.success('添加成功')
  getCategory()
}
</script>
<template>
  <div class="category-management">
    <h1 class="title">分类管理</h1>
    <div class="button-container">
      <el-button type="primary" @click="add">添加分类</el-button>
    </div>
  </div>
  <el-table stripe style="width: 100%" :data="list">
    <el-table-column label="分类id" prop="id"></el-table-column>
    <el-table-column label="分类名称" prop="categoryName"></el-table-column>
    <el-table-column label="操作">
      <template #default="{ row }">
        <el-button type="primary" :icon="Edit" circle @click="edit(row.id)" />
      </template>
    </el-table-column>
  </el-table>
  <!-- 更新 -->
  <el-dialog v-model="dialog" title="分类编辑" width="400">
    <el-form :model="formModel">
      <el-form-item label="分类名称" prop="categoryName">
        <el-input v-model="formModel.categoryName"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible">取消</el-button>
        <el-button type="primary" @click="submit()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 添加 -->
  <el-dialog v-model="dia" title="分类添加" width="400">
    <el-form :model="form">
      <el-form-item label="分类名称" prop="categoryName">
        <el-input v-model="form.categoryName"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="addC"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
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
.category-management {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.title {
  margin: 0;
}

.button-container {
  margin-left: auto;
}
</style>
