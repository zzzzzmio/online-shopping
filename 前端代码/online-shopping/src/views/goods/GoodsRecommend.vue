<script setup>
import { ref } from 'vue'
import image1 from '@/assets/1.jpeg'
import image2 from '@/assets/2.jpeg'
import image3 from '@/assets/3.jpeg'
import image4 from '@/assets/4.jpeg'
import image5 from '@/assets/5.jpeg'
import image6 from '@/assets/6.jpeg'
import router from '@/router'
const imageUrl = ref([
  { url: image1, id: 10 },
  { url: image2, id: 11 },
  { url: image3, id: 12 },
  { url: image4, id: 13 },
  { url: image5, id: 14 },
  { url: image6, id: 15 }
])
const into = (item) => {
  router.replace(`/goods/detail/${item.id}`)
}
const go = (id) => {
  router.replace(`/goods/detail/${id}`)
}
</script>
<template>
  <el-carousel :interval="4000" type="card" height="350px">
    <el-carousel-item v-for="item in imageUrl" :key="item">
      <img class="carousel-image" :src="item.url" @click="into(item)" />
    </el-carousel-item>
  </el-carousel>
  <div style="display: flex; justify-content: space-between">
    <el-card style="max-width: 300px; height: 250px">
      <template #header>野生桃胶</template>
      <img
        src="@/assets/9.jpeg"
        style="width: 100%; height: 100%; object-fit: cover"
        @click="go(16)"
      />
    </el-card>
    <el-card style="max-width: 300px; height: 250px">
      <template #header>猕猴桃干酒</template>
      <img
        src="@/assets/10.jpeg"
        style="width: 100%; height: 100%; object-fit: cover"
        @click="go(17)"
      />
    </el-card>
    <el-card style="max-width: 300px; height: 250px">
      <template #header>桂面</template>
      <img
        src="@/assets/11.jpeg"
        style="width: 100%; height: 100%; object-fit: cover"
        @click="go(18)"
      />
    </el-card>
    <el-card style="max-width: 300px; height: 250px">
      <template #header>小鸭子玩偶</template>
      <img
        src="@/assets/12.jpeg"
        style="width: 100%; height: 100%; object-fit: cover"
        @click="go(19)"
      />
    </el-card>
  </div>
  <div style="display: flex; justify-content: space-between">
    <el-card style="max-width: 300px; height: 250px">
      <template #header>化妆品</template>
      <img
        src="@/assets/13.jpeg"
        style="width: 100%; height: 100%; object-fit: cover"
        @click="go(20)"
      />
    </el-card>
    <el-card style="max-width: 300px; height: 250px">
      <template #header>水杯</template>
      <img
        src="@/assets/14.jpeg"
        style="width: 100%; height: 100%; object-fit: cover"
        @click="go(21)"
      />
    </el-card>
    <el-card style="max-width: 300px; height: 250px">
      <template #header>薯片</template>
      <img
        src="@/assets/15.jpeg"
        style="width: 100%; height: 100%; object-fit: cover"
        @click="go(22)"
      />
    </el-card>
    <el-card style="max-width: 300px; height: 250px">
      <template #header>东北大米</template>
      <img
        src="@/assets/16.jpeg"
        style="width: 100%; height: 100%; object-fit: cover"
        @click="go(23)"
      />
    </el-card>
  </div>
</template>

<style scoped>
/* 样式保持不变 */
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
} /* 保持图片比例，填充整个容器 */
</style>
