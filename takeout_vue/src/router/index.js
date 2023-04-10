import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/user/index',
    component: () => import('../views/user/Index.vue'),
    children: [ //二级路由
      {
        path: '/home_user',
        component: () => import('../views/user/Home.vue')
      },
      {
        path: '/my_user',
        component: () => import('../views/user/My.vue')
      },
      {
        path: '/order',
        component: () => import('../views/user/Order.vue')
      },
      {
        path: '/search',
        name: 'search',
        component: () => import('../views/user/Search.vue'),
        meta: {
          keepAlive: true
        }
      },
      {
        path: '/toMessage',
        name: 'message',
        component: () => import('../views/user/Message.vue')
      },
      {
        path: '/address',
        name: 'address',
        component: () => import('../views/user/Address.vue')
      },
      {
        path: '/addressEdit',
        name: 'addressEdit',
        component: () => import('../views/user/AddressEdit.vue')
      },
      {
        path: '/addressAdd',
        name: 'addressAdd',
        component: () => import('../views/user/AddressAdd.vue')
      },
      {
        path: '/shop',
        name: 'shop',
        component: () => import('../views/user/Shop.vue')
      },
      {
        path: '/pay',
        name: 'pay',
        component: () => import('../views/user/Pay.vue')
      },
      {
        path: '/paySuccess',
        name: 'paySuccess',
        component: () => import('../views/user/PaySuccess.vue')
      },
      {
        path: '/orderInfo_user',
        name: 'orderInfo_user',
        component: () => import('../views/user/OrderInfo.vue')
      },

    ]
  }
];



const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

// router.beforeEach((to, from, next) => {
//   // document.title = `${to.meta.title} | 饱了吗外卖`;
//   document.title = `饱了吗外卖`;
//   console.log(to.path)
//   const role = localStorage.getItem('ms_username');
//   if (to.path !== '/login') {
//     next('/login');
//   } else if (to.meta.permission) {
//     // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
//     role === 'admin' ?
//       next() :
//       next('/403');
//   } else {
//     next();
//   }
// });

export default router
