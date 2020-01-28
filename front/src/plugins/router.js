import Vue from 'vue'
import Router from 'vue-router'
import Index from '../views/Index.vue'
import Page from '../views/Page.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', component: Index},
    {path: '/:pageId', component: Page, props: true}
  ]
})
