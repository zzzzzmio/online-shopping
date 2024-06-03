import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/stores'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      component: () => import('@/views/login/LoginIndex.vue')
    },
    {
      path: '/',
      component: () => import('@/views/layout/LayoutIndex.vue'),
      redirect: '/goods/recommend',
      children: [
        {
          path: '/goods/recommend',
          component: () => import('@/views/goods/GoodsRecommend.vue')
        },
        {
          path: '/goods/list',
          component: () => import('@/views/goods/GoodsList.vue')
        },
        {
          path: '/goods/detail/:id',
          component: () => import('@/views/goods/GoodsDetail.vue')
        },
        {
          path: '/category/goods',
          component: () => import('@/views/category/GoodsCategory.vue')
        },
        {
          path: '/category/manage',
          component: () => import('@/views/category/CategoryManage.vue')
        },
        {
          path: '/gwc',
          component: () => import('@/views/gwc/MyCart.vue')
        },
        {
          path: '/orders',
          component: () => import('@/views/orders/MyOrders.vue')
        },
        {
          path: '/user/avatar',
          component: () => import('@/views/user/UserAvatar.vue')
        },
        {
          path: '/user/password',
          component: () => import('@/views/user/UserPassword.vue')
        },
        {
          path: '/user/profile',
          component: () => import('@/views/user/UserProfile.vue')
        },
        {
          path: '/user/address',
          component: () => import('@/views/user/UserAddress.vue')
        },
        {
          path: '/orders/detail/:id/:num',
          component: () => import('@/views/orders/OrdersDetail.vue')
        }
      ]
    }
  ]
})

// 登录访问拦截
router.beforeEach((to) => {
  const userStore = useUserStore()
  if (!userStore.token && to.path !== '/login') return '/login'
})
export default router
