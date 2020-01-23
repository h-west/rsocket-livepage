<template>
  <v-app>
    <v-btn @click="send">OK</v-btn>
    <component :is="component" v-if="component" v-bind="template" ref="template"></component>
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
  }),
  methods:{
    send(){
      Vue.rsPageSend({exec:"items[0].videoId='Az41nxCqMus'"});
      Vue.rsPageSend({exec:"items[2].url='https://monthly.chosun.com/upload/1612/1612_332.jpg'"});
      Vue.rsPageSend({exec:"items[3].chats.push('안녕하세요')"});
      Vue.rsPageSend({exec:"items[4].chart.chartData.datasets[0].data[0]+=1"});
    }
  }
};
</script>