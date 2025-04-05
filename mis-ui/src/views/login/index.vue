<template>
  <div class="login-container">
    <el-form
      ref="loginFormRef"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      autocomplete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">MIS系统登录</h3>
      </div>

      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          placeholder="用户名"
          type="text"
          tabindex="1"
          autocomplete="on"
        >
          <template #prefix>
            <el-icon><User /></el-icon>
          </template>
        </el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          :type="passwordVisible ? 'text' : 'password'"
          placeholder="密码"
          tabindex="2"
          autocomplete="on"
          @keyup.enter="handleLogin"
        >
          <template #prefix>
            <el-icon><Lock /></el-icon>
          </template>
          <template #suffix>
            <el-icon
              class="cursor-pointer"
              @click="passwordVisible = !passwordVisible"
            >
              <View v-if="passwordVisible" />
              <Hide v-else />
            </el-icon>
          </template>
        </el-input>
        <div class="password-strength" v-if="loginForm.password">
          <el-progress
            :percentage="passwordStrength"
            :status="passwordStrengthStatus"
            :stroke-width="4"
          />
          <span class="strength-text">{{ passwordStrengthMessage }}</span>
        </div>
      </el-form-item>

      <el-form-item prop="captcha">
        <div class="captcha-container">
          <el-input
            v-model="loginForm.captcha"
            placeholder="验证码"
            style="width: 200px"
          />
          <img
            :src="captchaData.image"
            class="captcha-image"
            @click="refreshCaptcha"
            alt="验证码"
          />
        </div>
      </el-form-item>

      <el-form-item>
        <el-checkbox v-model="loginForm.remember">记住密码</el-checkbox>
        <el-button
          :loading="loading"
          type="primary"
          style="width: 100%; margin-bottom: 30px"
          @click="handleLogin"
        >
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { User, Lock, View, Hide } from '@element-plus/icons-vue'
import { useUserStore } from '@/stores/user'
import { useCaptcha } from '@/utils/captcha'
import { validatePasswordStrength } from '@/utils/crypto'
import type { FormInstance } from 'element-plus'

const router = useRouter()
const userStore = useUserStore()
const { captchaData, generateCaptcha, validateCaptcha } = useCaptcha()
const loginFormRef = ref<FormInstance>()
const loading = ref(false)
const passwordVisible = ref(false)

const loginForm = reactive({
  username: '',
  password: '',
  captcha: '',
  remember: false
})

const loginRules = {
  username: [{ required: true, trigger: 'blur', message: '请输入用户名' }],
  password: [{ required: true, trigger: 'blur', message: '请输入密码' }],
  captcha: [{ required: true, trigger: 'blur', message: '请输入验证码' }]
}

// 密码强度计算
const passwordStrength = computed(() => {
  if (!loginForm.password) return 0
  const result = validatePasswordStrength(loginForm.password)
  return result.valid ? 100 : 75
})

const passwordStrengthStatus = computed(() => {
  if (!loginForm.password) return ''
  const result = validatePasswordStrength(loginForm.password)
  return result.valid ? 'success' : 'warning'
})

const passwordStrengthMessage = computed(() => {
  if (!loginForm.password) return ''
  return validatePasswordStrength(loginForm.password).message
})

// 刷新验证码
const refreshCaptcha = () => {
  generateCaptcha()
}

const handleLogin = async () => {
  if (!loginFormRef.value) return
  
  try {
    await loginFormRef.value.validate()
    
    // 验证验证码
    if (!validateCaptcha(loginForm.captcha)) {
      ElMessage.error('验证码错误')
      refreshCaptcha()
      return
    }
    
    loading.value = true
    await userStore.loginAction(loginForm)
    ElMessage.success('登录成功')
    router.push('/')
  } catch (error: any) {
    ElMessage.error(error.message || '登录失败')
  } finally {
    loading.value = false
  }
}

// 页面加载时检查是否有记住的登录信息
onMounted(() => {
  const rememberedCredentials = userStore.getRememberedCredentials()
  if (rememberedCredentials) {
    loginForm.username = rememberedCredentials.username
    loginForm.password = rememberedCredentials.password
    loginForm.remember = true
  }
  // 生成初始验证码
  generateCaptcha()
})
</script>

<style lang="scss" scoped>
.login-container {
  min-height: 100vh;
  width: 100%;
  background-color: #2d3a4b;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;

    :deep(.el-form-item) {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }

    :deep(.el-input) {
      display: inline-block;
      height: 47px;
      width: 100%;

      input {
        background: transparent;
        border: 0;
        -webkit-appearance: none;
        border-radius: 0;
        padding: 12px 5px 12px 15px;
        color: #fff;
        height: 47px;
        caret-color: #fff;

        &:-webkit-autofill {
          box-shadow: 0 0 0 1000px #283443 inset !important;
          -webkit-text-fill-color: #fff !important;
        }
      }
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: #eee;
        margin: 0 auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .password-strength {
      margin-top: 8px;
      padding: 0 10px;

      .strength-text {
        display: block;
        margin-top: 4px;
        font-size: 12px;
        color: #909399;
      }
    }

    .captcha-container {
      display: flex;
      align-items: center;
      gap: 10px;

      .captcha-image {
        height: 40px;
        cursor: pointer;
        border-radius: 4px;
      }
    }
  }
}
</style> 