import { ref } from 'vue'

export interface CaptchaData {
  code: string
  image: string
}

export const useCaptcha = () => {
  const captchaData = ref<CaptchaData>({
    code: '',
    image: ''
  })

  // 生成验证码
  const generateCaptcha = () => {
    const canvas = document.createElement('canvas')
    const ctx = canvas.getContext('2d')
    if (!ctx) return

    // 设置画布大小
    canvas.width = 120
    canvas.height = 40

    // 生成随机验证码
    const code = Math.random().toString(36).substring(2, 8).toUpperCase()
    captchaData.value.code = code

    // 绘制背景
    ctx.fillStyle = '#f0f0f0'
    ctx.fillRect(0, 0, canvas.width, canvas.height)

    // 绘制干扰线
    for (let i = 0; i < 5; i++) {
      ctx.beginPath()
      ctx.moveTo(Math.random() * canvas.width, Math.random() * canvas.height)
      ctx.lineTo(Math.random() * canvas.width, Math.random() * canvas.height)
      ctx.strokeStyle = `rgb(${Math.random() * 255},${Math.random() * 255},${Math.random() * 255})`
      ctx.stroke()
    }

    // 绘制干扰点
    for (let i = 0; i < 50; i++) {
      ctx.beginPath()
      ctx.arc(Math.random() * canvas.width, Math.random() * canvas.height, 1, 0, 2 * Math.PI)
      ctx.fillStyle = `rgb(${Math.random() * 255},${Math.random() * 255},${Math.random() * 255})`
      ctx.fill()
    }

    // 绘制验证码文字
    ctx.font = 'bold 24px Arial'
    ctx.textBaseline = 'middle'
    ctx.textAlign = 'center'
    ctx.fillStyle = '#333'
    ctx.fillText(code, canvas.width / 2, canvas.height / 2)

    // 转换为图片
    captchaData.value.image = canvas.toDataURL('image/png')
  }

  // 验证验证码
  const validateCaptcha = (inputCode: string): boolean => {
    return inputCode.toUpperCase() === captchaData.value.code
  }

  return {
    captchaData,
    generateCaptcha,
    validateCaptcha
  }
} 