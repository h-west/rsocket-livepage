<template>
  <v-app>
    <component :is="component" v-if="component" v-bind="template" ref="template"></component>
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
    Vue.rsPageConnect(this.pageId,(result)=>{
      //console.log(result)
      switch(result.cmd){
        case 'page':
          this.template = result.data;
          this.component = () => import(`../templates/${this.template.type}.vue`);
          this.pageInit = true;
          break;
        case 'delivery':
          if(this.pageInit){
            const msg = JSON.parse(Base64.decode(result.data));
            Function("this.template."+msg.exec).call(this);
          }else{
            //다시받는처리
          }
          break;
        default:
          break;
      }
    });
  },
  data: () => ({
    component: null,
    template: null,
    pageInit: false
  })
};
</script>
