import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import vueCookies from "vue3-cookies"
import { loadFonts } from './plugins/webfontloader'
import { createPinia } from 'pinia'

loadFonts()

const pinia = createPinia();

createApp(App)
  .use(router)
  .use(vuetify)
  .use(axios)
  .use(vueCookies)
  .use(pinia)
  .mount('#app')
