<script setup>
import { getCategory } from '@/api/category.js'
import { ref } from 'vue'

defineProps({
  modelValue: {
    type: [Number, String]
  },
  width: {
    type: String
  }
})
const emit = defineEmits(['update:modelValue'])
const channelList = ref([])
const getChannelList = async () => {
  const res = await getCategory()
  channelList.value = res.data.data
}
getChannelList()
</script>

<template>
  <!-- label 展示给用户看的，value 收集起来提交给后台的 -->
  <el-select
    :modelValue="modelValue"
    @update:modelValue="emit('update:modelValue', $event)"
    :style="{ width }"
  >
    <el-option
      v-for="channel in channelList"
      :key="channel.id"
      :label="channel.categoryName"
      :value="channel.id"
    ></el-option>
  </el-select>
</template>
