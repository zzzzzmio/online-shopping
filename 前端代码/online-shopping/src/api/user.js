import request from '@/utils/request'
//注册
export const userRegist = (registerData) => {
  //借助于UrlSearchParams完成传递
  const params = new URLSearchParams()
  for (let key in registerData) {
    params.append(key, registerData[key])
  }
  return request.post('/user/register', params)
}
//登录
export const userLogin = (loginData) => {
  //借助于UrlSearchParams完成传递
  const params = new URLSearchParams()
  for (let key in loginData) {
    params.append(key, loginData[key])
  }
  return request.post('/user/login', params)
}
//获取个人信息
export const userInfo = () => request.get('/user/userinfo')
export const userAvatar = (avatar) =>
  request.put(`/user/avatar?avatar=${avatar}`)
export const userPwd = (data) => {
  const params = new URLSearchParams()
  for (let key in data) {
    params.append(key, data[key])
  }
  return request.patch('/user/password', params)
}
export const userUpdate = (data) => request.put('/user/update', data)
export const userAddress = (address) =>
  request.put(`/user/address?address=${address}`)
export const getAddress = () => request.get('/user/getaddress')
