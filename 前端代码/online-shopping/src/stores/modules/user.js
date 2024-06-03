import { defineStore } from 'pinia'
import { ref } from 'vue'
import { userInfo, getAddress } from '@/api/user'

// 用户模块
export const useUserStore = defineStore(
  'big-user',
  () => {
    const token = ref('') // 定义 token
    const user = ref({})
    const address = ref('')
    const setToken = (t) => (token.value = t) // 设置 token
    const getUser = async () => {
      const res = await userInfo()
      user.value = res.data.data
    }
    const setUserInfo = (object) => {
      user.value = object
    }
    const removeToken = () => (token.value = '')
    const removeUserInfo = () => (user.value = {})
    const Address = async () => {
      const res = await getAddress()
      address.value = res.data.data
    }
    return {
      token,
      setToken,
      setUserInfo,
      removeToken,
      removeUserInfo,
      getUser,
      user,
      address,
      Address
    }
  },
  {
    persist: true // 持久化
  }
)
