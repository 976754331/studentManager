import request from '@/utils/request'

export function listCourse(){
  return request({
    url: '/course/list',
    method: 'post'
  })
}


export function addCourse(data){
  return request({
    url: '/course/add',
    method: 'post',
    data
  })
}

export function searchCourse(data){
  return request({
    url: '/course/search',
    method: 'post',
    data
  })
}
