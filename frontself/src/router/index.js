import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import Home from '@/components/Home'
import Register from '@/components/Register'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'home',
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
    }
  ]
})

this.$router.beforeEach((to, from, next) => {
  const publicPages = ['Login', 'Register']
  const authRequired = !publicPages.includes(to.name)
  const loggedIn = localStorage.getItem('user')

  if (authRequired && !loggedIn) {
    this.$router.push({ name: 'Login', query: {to: to.path} })
  } else {
    next()
  }
})
