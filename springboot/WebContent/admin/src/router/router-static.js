import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import news from '@/views/modules/news/list'
    import discussremenpaixing from '@/views/modules/discussremenpaixing/list'
    import remenpaixing from '@/views/modules/remenpaixing/list'
    import yonghu from '@/views/modules/yonghu/list'
    import chat from '@/views/modules/chat/list'
    import orders from '@/views/modules/orders/list'
    import storeup from '@/views/modules/storeup/list'
    import xiaoshuoleibie from '@/views/modules/xiaoshuoleibie/list'
    import config from '@/views/modules/config/list'
    import users from '@/views/modules/users/list'
    import discussxiaoshuoxinxi from '@/views/modules/discussxiaoshuoxinxi/list'
    import xiaoshuoxinxi from '@/views/modules/xiaoshuoxinxi/list'


//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
          ,{
	path: '/news',
        name: '图书资讯',
        component: news
      }
          ,{
	path: '/discussremenpaixing',
        name: '热门排行评论',
        component: discussremenpaixing
      }
          ,{
	path: '/remenpaixing',
        name: '热门排行',
        component: remenpaixing
      }
          ,{
	path: '/yonghu',
        name: '用户',
        component: yonghu
      }
          ,{
	path: '/chat',
        name: '在线客服',
        component: chat
      }
          ,{
        path: '/orders/:status',
        name: '订单信息管理',
        component: orders
      }
          ,{
	path: '/storeup',
        name: '我的收藏管理',
        component: storeup
      }
          ,{
	path: '/xiaoshuoleibie',
        name: '图书类别',
        component: xiaoshuoleibie
      }
          ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
          ,{
	path: '/users',
        name: '管理员',
        component: users
      }
          ,{
	path: '/discussxiaoshuoxinxi',
        name: '图书信息评论',
        component: discussxiaoshuoxinxi
      }
          ,{
	path: '/xiaoshuoxinxi',
        name: '图书信息',
        component: xiaoshuoxinxi
      }
        ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
