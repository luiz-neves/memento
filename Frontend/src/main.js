import Vue from 'vue'
import Router from 'vue-router'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import Contact from './components/Contact'
Vue.config.productionTip = false

const router = new Router({
  routes: [
    {
      path: '/contacts',
      component: Contact
    }
  ]
})

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')

Vue.use(Router)
