import request from '@/utils/request'
export const addCarts = (data) => request.post('/gwc/add', data)
export const getCarts = ({ pageNum, pageSize }) =>
  request.get('gwc/get', { params: { pageNum, pageSize } })
export const updateCarts = (data) => request.put('/gwc/update', data)
export const deleteCarts = (id) => request.delete(`/gwc/delete?id=${id}`)
