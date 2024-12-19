import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import MainpageView from '../views/MainpageView.vue'
import LoginView from '../views/LoginView.vue'
import RedirectComponent from '../components/auth/RedirectComponent.vue'
import LoginView from '../views/login/LoginView.vue'

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/dashboard',
    name: 'MainpageView',
    component: MainpageView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/auth/login',
    component: RedirectComponent
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
