import request from '@/utils/request'
//全部
export const getCategory = () => request.get('/category/getlist')
//分页获取
export const getList = ({ pageNum, pageSize }) =>
  request.get('/category/get', { params: { pageNum, pageSize } })
export const getInfo = (id) => request.get(`/category/getname?id=${id}`)
export const updateCategory = (categoryName, id) =>
  request.put(`/category/update?categoryName=${categoryName}&&id=${id}`)
export const addCategory = (data) => request.post('/category/add', data)
