import request from '@/utils/request'

export function listPrice(){
  return request({
    url: '/price/list',
    method: 'get'
  })
}

export function listForPrice(){
  return request({
    url: '/price/listForPrice',
    method: 'get'
  })
}


export function addPrice(data){
  return request({
    url: '/price/add',
    method: 'post',
    data
  })
}


