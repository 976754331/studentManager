import Vue from 'vue'
import Router from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(Router)


/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '控制面板',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '控制面板', icon: 'dashboard' }
    }]
  },

  {
    path: '/student',
    component: Layout,
    redirect: '/student/table',
    name: 'Example',
    meta: { title: '学生信息管理', icon: 'nested' },
    children: [
      {
        path: 'list',
        name: 'Table',
        component: () => import('@/views/student/list'),
        meta: { title: '查看学生信息', icon: 'table' }
      },
      {
        path: 'add',
        name: 'Tree',
        component: () => import('@/views/student/add'),
        meta: { title: '添加学生信息', icon: 'tree' }
      },
      {
        path: 'search',
        name: 'Tree',
        component: () => import('@/views/student/search'),
        meta: { title: '查询学生信息', icon: 'tree' }
      }
    ]
  },



    {
      path: '/course',
      component: Layout,
      redirect: '/course/manage',
      name: 'Example',
      meta: { title: '课程信息管理', icon: "form" },
      children: [
        {
          path: 'list',
          name: 'Table',
          component: () => import('@/views/course/list'),
          meta: { title: '查看课程信息', icon: 'table' }
        },
        {
          path: 'add',
          name: 'Tree',
          component: () => import('@/views/course/add'),
          meta: { title: '添加课程信息', icon: 'tree' }
        },
        {
          path: 'search',
          name: 'Tree',
          component: () => import('@/views/course/search'),
          meta: { title: '查询课程信息', icon: 'tree' }
        }
      ]
    },

      {
        path: '/account',
        component: Layout,
        redirect: '/account/manage',
        name: 'Example',
        meta: { title: '账单信息管理', icon: 'eye-open' },
        children: [
          {
            path: 'list',
            name: 'Table',
            component: () => import('@/views/account/list'),
            meta: { title: '查看账单信息', icon: 'table' }
          },
          {
            path: 'add',
            name: 'Tree',
            component: () => import('@/views/account/add'),
            meta: { title: '添加缴费信息', icon: 'tree' }
          },
          {
            path: 'search',
            name: 'Tree',
            component: () => import('@/views/account/search'),
            meta: { title: '查询账单信息', icon: 'tree' }
          }
        ]
      },

      {
          path: '/score',
          component: Layout,
          redirect: '/score/manage',
          name: 'Example',
          meta: { title: '成绩信息管理', icon: 'table' },
          children: [
            {
              path: 'list',
              name: 'Table',
              component: () => import('@/views/score/list'),
              meta: { title: '查看成绩信息', icon: 'table' }
            },
            {
              path: 'add',
              name: 'Tree',
              component: () => import('@/views/score/add'),
              meta: { title: '添加成绩信息', icon: 'tree' }
            },
            {
              path: 'search',
              name: 'Tree',
              component: () => import('@/views/score/search'),
              meta: { title: '查询成绩信息', icon: 'tree' }
            }
          ]
        },

{
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/manage',
    name: 'Example',
    meta: { title: '教师信息管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'Table',
        component: () => import('@/views/teacher/list'),
        meta: { title: '查看教师信息', icon: 'table' }
      },
      {
        path: 'add',
        name: 'Tree',
        component: () => import('@/views/teacher/add'),
        meta: { title: '添加教师信息', icon: 'tree' }
      },
      {
        path: 'search',
        name: 'Tree',
        component: () => import('@/views/teacher/search'),
        meta: { title: '查询教师信息', icon: 'tree' }
      }
    ]
  },

      {
              path: '/price',
              component: Layout,
              redirect: '/price/manage',
              name: 'Example',
              meta: { title: '价格信息管理', icon: 'example' },
              children: [
                {
                  path: 'list',
                  name: 'Table',
                  component: () => import('@/views/price/list'),
                  meta: { title: '查看价格列表', icon: 'table' }
                },
                {
                  path: 'add',
                  name: 'Tree',
                  component: () => import('@/views/price/add'),
                  meta: { title: '添加价格信息', icon: 'tree' }
                }
              ]
            },

  {
    path: '/clazz',
    component: Layout,
    redirect: '/class/table2',
    name: 'Example',
    meta: { title: '班级信息管理', icon: 'dashboard' },
    children: [
      {
        path: 'list',
        name: 'Table',
        component: () => import('@/views/clazz/list'),
        meta: { title: '查看班级信息', icon: 'table' }
      },
      {
        path: 'add',
        name: 'Tree',
        component: () => import('@/views/clazz/add'),
        meta: { title: '添加班级信息', icon: 'tree' }
      }
    ]
  },
  {
      path: '/form',
      component: Layout,
      redirect: '/user/',
      name: 'Example',
      meta: { title: '个人中心', icon: 'user' },
      children: [
        {
          path: 'index',
          name: 'Form',
          component: () => import('@/views/user/update'),
          meta: { title: '修改个人信息', icon: 'tree' }
        },
        {
          path: 'add',
          name: 'Tree',
          component: () => import('@/views/user/updatePassword'),
          meta: { title: '修改密码', icon: 'tree' }
        }
      ]
    },
  //
  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },
  //
  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
