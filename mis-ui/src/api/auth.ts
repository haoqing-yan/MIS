import request from '@/utils/request'

export interface LoginForm {
  username: string
  password: string
}

export interface LoginResult {
  token: string
  userInfo: {
    id: number
    username: string
    nickname: string
    avatar: string
  }
}

export function login(data: LoginForm) {
  return request<LoginResult>({
    url: '/auth/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}

export function getUserInfo() {
  return request<LoginResult['userInfo']>({
    url: '/auth/info',
    method: 'get'
  })
} 