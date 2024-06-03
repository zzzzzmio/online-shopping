import request from '@/utils/request'
//获取商品详情
export const goodsDetail = (id) => {
  return request.get(`/goods/get?id=${id}`)
}
//客户获取商品列表
export const customerList = ({ pageNum, pageSize, categoryId }) =>
  request.get('/goods/getlist', { params: { pageNum, pageSize, categoryId } })
//商家获取商品列表
export const sellerList = ({ pageNum, pageSize, categoryId }) =>
  request.get('/goods/seller', { params: { pageNum, pageSize, categoryId } })
export const updateGoods = (data) => request.put('/goods/update', data)
export const addGoods = (data) => request.post('/goods/add', data)
