import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login, logout, getUserInfo } from '@/api/user'
import { encrypt, decrypt } from '@/utils/crypto'
import { autoLogout } from '@/utils/autoLogout'

export const useUserStore = defineStore('user', () => {
  const token = ref('')
  const userInfo = ref<any>(null)
  const loginAttempts = ref(0)
  const lastLoginAttempt = ref(0)
  const MAX_LOGIN_ATTEMPTS = 5
  const LOCKOUT_TIME = 30 * 60 * 1000 // 30分钟锁定时间

  // 检查是否被锁定
  const isLocked = () => {
    if (loginAttempts.value >= MAX_LOGIN_ATTEMPTS) {
      const now = Date.now()
      if (now - lastLoginAttempt.value < LOCKOUT_TIME) {
        return true
      }
      // 重置尝试次数
      loginAttempts.value = 0
    }
    return false
  }

  // 登录
  const loginAction = async (loginForm: { username: string; password: string; remember: boolean }) => {
    if (isLocked()) {
      throw new Error('登录尝试次数过多，请稍后再试')
    }

    try {
      const res = await login(loginForm)
      token.value = res.data.token
      // 记住密码
      if (loginForm.remember) {
        const encryptedPassword = encrypt(loginForm.password)
        localStorage.setItem('rememberedUsername', loginForm.username)
        localStorage.setItem('rememberedPassword', encryptedPassword)
      } else {
        localStorage.removeItem('rememberedUsername')
        localStorage.removeItem('rememberedPassword')
      }
      // 重置登录尝试次数
      loginAttempts.value = 0
      // 启动自动登出计时器
      autoLogout.reset()
      return res
    } catch (error) {
      loginAttempts.value++
      lastLoginAttempt.value = Date.now()
      throw error
    }
  }

  // 登出
  const logoutAction = async () => {
    try {
      await logout()
      token.value = ''
      userInfo.value = null
      // 清除自动登出计时器
      autoLogout.clear()
    } catch (error) {
      console.error('Logout failed:', error)
    }
  }

  // 获取用户信息
  const getUserInfoAction = async () => {
    try {
      const res = await getUserInfo()
      userInfo.value = res.data
      return res
    } catch (error) {
      console.error('Get user info failed:', error)
      throw error
    }
  }

  // 获取记住的用户名和密码
  const getRememberedCredentials = () => {
    const username = localStorage.getItem('rememberedUsername')
    const encryptedPassword = localStorage.getItem('rememberedPassword')
    if (username && encryptedPassword) {
      return {
        username,
        password: decrypt(encryptedPassword)
      }
    }
    return null
  }

  return {
    token,
    userInfo,
    loginAction,
    logoutAction,
    getUserInfoAction,
    getRememberedCredentials
  }
}) 