import axios from 'axios'

const axiosSettings = axios.create({
  baseURL: 'https://memento-back.herokuapp.com',
  timeout: 30000,
  headers: {
    'Host': '*'
  },
})

export default axiosSettings
