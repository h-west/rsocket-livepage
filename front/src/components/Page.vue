<template>
  <v-app>
    <v-btn @click="send">OK</v-btn>
    <!-- <ul v-for="message in messages" :key="message">
        <li>{{message}}</li>
    </ul> -->
    <component :is="component" v-if="component" v-bind="template"></component>
  </v-app>  
</template>

<script>
import { Base64 } from 'js-base64';
import Vue from 'vue';

export default {
  name: 'dynamic-page',
  props: ['page-id'],
  created() {
    Vue.rsPageConnect(this.pageId,(result)=>{
      console.log(result)
      switch(result.cmd){
        case 'page':
          this.template = result.data;
          this.component = () => import(`../templates/${this.template.type}.vue`);
          break;
        case 'mq':
          break;
        default:
          break;
      }
    });
  },
  data: () => ({
    component: null,
    template: null
  }),
  methods:{
    send(){
      Vue.rsPageSend({test:'test'})
    }
  }
};
</script>