import Vue from 'vue'
import VueRouter from 'vue-router'
import Najcitanije from '../views/Najcitanije.vue'
import SingleNews from '../views/SingleNews'
import TagNews from '../views/TagNews.vue'
import Categories from '../views/Categories.vue'
import News from '../views/News.vue'
import Users from '../views/Users.vue'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/najcitanije',
    name: 'Najcitanije',
    component: Najcitanije
  },
  {
    path: '/singleNews/:id',
    name: 'SingleNews',
    component: SingleNews
  },
  {
    path: '/tagNews/:id',
    name: 'TagNews',
    component: TagNews
  },
  {
    path: '/categories',
    name: 'Categories',
    component: Categories
  },
  {
    path: '/news',
    name: 'News',
    component: News
  },
  {
    path: '/users',
    name: 'Users',
    component: Users
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router;
