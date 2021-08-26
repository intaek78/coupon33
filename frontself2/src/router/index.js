import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import Coupon from '@/components/Coupon'
import Member from '@/components/Member'
import Cancellation from '@/components/Cancellation'
import Mypage from '@/components/Mypage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/helloworld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/coupons',
      name: 'coupon',
      component: Coupon,
      props: true
    },
    {
      path: '/members',
      name: 'member',
      component: Member,
      props: true
    },
    {
      path: '/cancellations',
      name: 'cancellation',
      component: Cancellation,
      props: true
    },
    {
      path: '/mypages',
      name: 'mypage',
      component: Mypage,
      props: true
    }
  ]
})
