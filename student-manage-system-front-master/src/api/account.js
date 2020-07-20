import request from '@/utils/request'

export function listAccount(){
  return request({
    url: '/account/list',
    method: 'post'
  })
}


export function addAccount(data){
  return request({
    url: '/account/add',
    method: 'post',
    data
  })
}

export function searchAccount(data){
  return request({
    url: '/account/search',
    method: 'post',
    data
  })
}

export function exportAccount(){
  return request({
    url: '/account/export',
    responseType: 'blob',
    method: 'post'
  })
}
