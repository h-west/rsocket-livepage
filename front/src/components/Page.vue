<template>
  <v-app>
    <v-btn @click="send">OK</v-btn>
    <component :is="component" v-if="component" v-bind="componentData"></component>
  </v-app>  
</template>

<script>
import { Base64 } from 'js-base64';
import Vue from 'vue';

export default {
  name: 'dynamic-page',
  props: ['page-id'],
  created() {
    Vue.rsPageConnect(this.pageId,(data)=>{
      console.log(data);

      let c = JSON.parse(Base64.decode(data.body));
      console.log(c);
      this.component = () => import('../templates/Live1.vue');

    });
    
  },
  data: () => ({
    component: null,
    componentData: {videoId:'Fht6hmUGRfU'},
  }),
  methods:{
    send(){
      Vue.rsPageSend({test:'test'})
    }
  }
};
</script>