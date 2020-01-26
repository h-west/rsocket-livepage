import Vue from 'vue';
import Main from './Main.vue';
import router from './plugins/router';
import rsclient from './plugins/rsclient';
import store from './plugins/store';
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false
Vue.use(rsclient,{url:'ws://52.78.139.246:8080/ws'})

new Vue({
  router,
  vuetify,
  store,
  render: h => h(Main)
}).$mount('#app')
