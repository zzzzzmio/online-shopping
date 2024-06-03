<script setup>
import { ref } from 'vue'
import { goodsDetail, updateGoods, addGoods } from '@/api/goods'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import { useUserStore } from '@/stores'
const userStore = useUserStore()
const form = ref()
const editorRef = ref()
const formModel = ref({
  id: '',
  goodsName: '',
  content: '',
  coverImg: '',
  price: ''
})
const update = ref(false)
const rules = {}
const open = async (row) => {
  update.value = true
  const {
    data: { data }
  } = await goodsDetail(row.id)
  formModel.value = data
}
const onUploadFile = (uploadFile) => {
  formModel.value.coverImg = uploadFile.data
}
const emit = defineEmits(['success'])
const submit = async () => {
  await updateGoods(formModel.value)
  formModel.value = {
    id: '',
    goodsName: '',
    content: '',
    coverImg: '',
    price: ''
  }
  editorRef.value.setHTML('')
  update.value = false
  ElMessage.success('更新成功')
  emit('success')
}
const add = async () => {
  await addGoods(formModel.value)
  formModel.value = {
    id: '',
    goodsName: '',
    content: '',
    coverImg: '',
    price: ''
  }
  editorRef.value.setHTML('')
  ElMessage.success('添加成功')
  emit('success')
}
defineExpose({
  open
})
</script>
<template>
  <div class="product-details">
    <h1>商品详情</h1>
    <el-form :model="formModel" :rules="rules" ref="form" class="form">
      <el-form-item label="商品名称:" prop="goodsName">
        <el-input v-model="formModel.goodsName"></el-input>
      </el-form-item>
      <el-form-item label="商品描述:" prop="content">
        <div class="editor">
          <quill-editor
            theme="snow"
            contentType="html"
            v-model:content="formModel.content"
            ref="editorRef"
          ></quill-editor>
        </div>
      </el-form-item>
      <el-form-item label="商品封面" prop="coverImg">
        <el-upload
          class="avatar-uploader"
          auto-upload="true"
          :show-file-list="false"
          name="file"
          :headers="{ Authorization: userStore.token }"
          action="/api/upload"
          :on-success="onUploadFile"
        >
          <img
            v-if="formModel.coverImg !== ''"
            :src="formModel.coverImg"
            class="avatar"
          />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          <div class="upload-text">点击上传图片</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="商品价格" prop="price">
        <el-input v-model="formModel.price"></el-input>
      </el-form-item>
      <el-form-item class="button-container">
        <el-button type="primary" round v-if="update" @click="submit"
          >更新</el-button
        >
        <el-button type="primary" round v-else @click="add">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style>
.product-details {
  padding: 20px;
}

.form {
  max-width: 600px;
  margin: 0 auto;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: border-color 0.3s ease;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar {
  width: 100px;
  height: 100px;
  object-fit: cover;
}

.upload-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.editor {
  margin-bottom: 20px;
  border: 1px solid #d9d9d9;
  border-radius: 6px;
}

.button-container {
  display: flex;
  justify-content: flex-end; /* 将按钮容器推到右边 */
  margin-top: 20px;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
