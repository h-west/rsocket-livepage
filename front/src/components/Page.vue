<template>
  <v-app>
    <v-btn @click="send">OK</v-btn>
    <ul v-for="message in messages" :key="message">
        <li>{{message}}</li>
    </ul>
    <component :is="template" v-if="template" v-bind="templateData"></component>
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
      //this.messages.push(data);

      // data = {
      //   type:'Live1',
      //   videoId:'Fht6hmUGRfU'
      // }

      let template = {
        type:'Free1',
        items:[
          {type:'YoutubePlayer',videoId:'Fht6hmUGRfU'},
          {type:'YoutubePlayer',videoId:'mLE7zCi-UTg'}
        ]
      }
console.log('@')
      this.template = () => import(`../templates/${template.type}.vue`);
      this.templateData = template;
     // let c = JSON.parse(Base64.decode(data.body));
     // console.log(c);
     // this.component = () => import('../templates/Live1.vue');

    });
    
  },
  data: () => ({
    template: null,
    templateData: null,
    messages: []
  }),
  methods:{
    send(){
      Vue.rsPageSend({test:'test'})
    }
  }
};
</script>