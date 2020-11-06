import Vue from 'vue'
import Router from 'vue-router'
import VueYouTubeEmbed from 'vue-youtube-embed'

import App from './App.vue'
import vuetify from './plugins/vuetify'
import Contact from './components/Contact'
import Agenda from './components/Agenda'
import Memorial from './components/Memorial'
import Call from './components/Call'
Vue.config.productionTip = false

const router = new Router({
  mode: 'history',
  baseUrl: '',
  routes: [
    {
      name: 'Contact',
      path: '/contact',
      component: Contact
    },
    {
      name: 'Agenda',
      path: '/agenda',
      component: Agenda
    },
    {
      name: 'Call',
      path: '/call',
      component: Call
    },
    {
      name: 'Memorial',
      path: '/',
      component: Memorial
    },
  ]
})

Vue.use(VueYouTubeEmbed)
Vue.use(Router)

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
