import request from '@/utils/request'

export function listCourse(){
  return request({
    url: '/score/list',
    method: 'post'
  })
}


export function addCourse(data){
  return request({
    url: '/score/add',
    method: 'post',
    data
  })
}

export function searchCourse(data){
  return request({
    url: '/score/search',
    method: 'post',
    data
  })
}
