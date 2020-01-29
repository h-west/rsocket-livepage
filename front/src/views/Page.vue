<template>
  <v-app>
    <component :is="component" v-if="component" v-bind="template" :msg="msg" ref="template"></component>
    <Menu></Menu>
  </v-app>  
</template>

<script>
import { Base64 } from 'js-base64';
import Vue from 'vue';
import Menu from '../components/Menu.vue'

export default {
  components: {Menu},
  name: 'dynamic-page',
  props: ['page-id'],
  created() {
    Vue.rsPageConnect(this.pageId,this.handle);
  },
  data: () => ({
    component: null,
    template: null,
    pageInit: false,
    msg: null,
  }),
  methods: {
    handle(result) {
      console.log(result)
      switch(result.cmd){
        case 'page':
          this.template = result.data;
          this.component = () => import(`../templates/${this.template.type}.vue`);
          this.pageInit = true;
          break;
        case 'delivery':
          if(this.pageInit){
            const delivery = JSON.parse(Base64.decode(result.data));
            this.handle(delivery);
          }else{
            //다시받는처리
          }
          break;
        case 'template':
          Function("this.template."+result.data.exec).call(this);
          break;
        default:
          break;
      }
    }
  }
};
</script>
