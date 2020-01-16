import Vue from 'vue';
import Index from './Index.vue';
import router from './plugins/router';
import store from './plugins/store';
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  store,
  render: h => h(Index)
}).$mount('#app')
