import request from '@/utils/request'
//生产订单
export const addOrders = (data) => request.post('/orders/add', data)
export const cusOrders = ({ pageNum, pageSize }) =>
  request.get('/orders/customer', { params: { pageNum, pageSize } })
export const updateOrders = (id, state) =>
  request.put(`/orders/state?id=${id}&&state=${state}`)
export const selOrders = ({ pageNum, pageSize }) =>
  request.get('/orders/seller', { params: { pageNum, pageSize } })
