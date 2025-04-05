import { useUserStore } from '@/stores/user'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const TIMEOUT = 30 * 60 * 1000 // 30分钟超时
const WARNING_TIME = 5 * 60 * 1000 // 5分钟警告时间

let timer: number | null = null
let warningTimer: number | null = null

export const autoLogout = {
  // 重置计时器
  reset() {
    if (timer) {
      window.clearTimeout(timer)
    }
    if (warningTimer) {
      window.clearTimeout(warningTimer)
    }

    // 设置警告计时器
    warningTimer = window.setTimeout(() => {
      ElMessage.warning('您的登录即将过期，请及时保存工作')
    }, TIMEOUT - WARNING_TIME)

    // 设置登出计时器
    timer = window.setTimeout(() => {
      this.logout()
    }, TIMEOUT)
  },

  // 清除计时器
  clear() {
    if (timer) {
      window.clearTimeout(timer)
      timer = null
    }
    if (warningTimer) {
      window.clearTimeout(warningTimer)
      warningTimer = null
    }
  },

  // 执行登出
  async logout() {
    const userStore = useUserStore()
    const router = useRouter()
    
    await userStore.logoutAction()
    ElMessage.warning('登录已过期，请重新登录')
    router.push('/login')
  }
} 