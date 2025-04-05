import CryptoJS from 'crypto-js'

const SECRET_KEY = 'mis-secret-key-2024'

// 加密
export const encrypt = (text: string): string => {
  return CryptoJS.AES.encrypt(text, SECRET_KEY).toString()
}

// 解密
export const decrypt = (ciphertext: string): string => {
  const bytes = CryptoJS.AES.decrypt(ciphertext, SECRET_KEY)
  return bytes.toString(CryptoJS.enc.Utf8)
}

// 生成随机盐值
export const generateSalt = (): string => {
  return CryptoJS.lib.WordArray.random(16).toString()
}

// 使用盐值加密密码
export const hashPassword = (password: string, salt: string): string => {
  return CryptoJS.PBKDF2(password, salt, {
    keySize: 512 / 32,
    iterations: 10000
  }).toString()
}

// 验证密码强度
export const validatePasswordStrength = (password: string): { valid: boolean; message: string } => {
  const minLength = 8
  const hasUpperCase = /[A-Z]/.test(password)
  const hasLowerCase = /[a-z]/.test(password)
  const hasNumbers = /\d/.test(password)
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password)

  if (password.length < minLength) {
    return { valid: false, message: '密码长度至少为8位' }
  }
  if (!hasUpperCase || !hasLowerCase) {
    return { valid: false, message: '密码必须包含大小写字母' }
  }
  if (!hasNumbers) {
    return { valid: false, message: '密码必须包含数字' }
  }
  if (!hasSpecialChar) {
    return { valid: false, message: '密码必须包含特殊字符' }
  }

  return { valid: true, message: '密码强度符合要求' }
} 