
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import MemberManager from "./components/MemberManager"

import CouponManager from "./components/CouponManager"
import CancellationManager from "./components/CancellationManager"


import Mypage from "./components/Mypage"
import NotificationManager from "./components/NotificationManager"

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/members',
                name: 'MemberManager',
                component: MemberManager
            },

            {
                path: '/coupons',
                name: 'CouponManager',
                component: CouponManager
            },
            {
                path: '/cancellations',
                name: 'CancellationManager',
                component: CancellationManager
            },


            {
                path: '/mypages',
                name: 'Mypage',
                component: Mypage
            },
            {
                path: '/notifications',
                name: 'NotificationManager',
                component: NotificationManager
            },
            {
                path: '/',
                name: 'MemberManager',
                component: MemberManager
            }



    ]
})
