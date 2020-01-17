<template>
    <div>
        dpage.vue
        <v-btn @click="send">OK</v-btn>
    </div>
</template>

<script>
import {RSocketClient, JsonSerializer, IdentitySerializer} from 'rsocket-core';
import RSocketWebSocketClient from 'rsocket-websocket-client';
import { Base64 } from 'js-base64';

// Create an instance of a client
const client = new RSocketClient({
    serializers: {
      data: JsonSerializer,
      metadata: IdentitySerializer
    },
  setup: {
    // ms btw sending keepalive to server
    keepAlive: 60000, 
    // ms timeout if no keepalive response
    lifetime: 180000, 
    // format of `data`
    dataMimeType: 'application/json', 
    // format of `metadata`
    metadataMimeType: 'message/x.rsocket.routing.v0',
  },
  transport: new RSocketWebSocketClient({url: 'ws://localhost:8080/ws'}),
});



export default {
  name: 'dynamic-page',
  props: ['page-id'],
  created() {
    client.connect().subscribe({
    onComplete: socket => {
      // socket provides the rsocket interactions fire/forget, request/response,
      // request/stream, etc as well as methods to close the socket.
      this.socket = socket;
      socket.requestStream({
        data: {
          'page': location.pathname.split('/')[(location.pathname.split('/')).length-1]
        },
        metadata: String.fromCharCode('data.get'.length) + 'data.get',
      }).subscribe({
        onComplete: () => console.log('complete'),
        onError: error => {
          console.log(error);
          addErrorMessage("Connection has been closed due to ", error);
        },
        onNext: payload => {
          //console.log(payload.data);
          this.handle(payload.data);
        },
        onSubscribe: subscription => {
          subscription.request(2147483647);
        },
      });
    },
    onError: error => {
      console.log(error);
      addErrorMessage("Connection has been refused due to ", error);
    },
    onSubscribe: cancel => {
      /* call cancel() to abort */
    }
  });
  },
  data: () => ({
    client: null,
    socket: null,
    component: null,
  }),
  methods:{
    handle(data){
      console.dir(data);
      
      let c = JSON.parse(Base64.decode(data.body));
      console.log(c);
      this.component = c;
    },
    send(){
      if(this.socket!==null){
        this.socket.fireAndForget({
            data: {test:'testdata',lang:'한글'},
            metadata: String.fromCharCode('data.set'.length) + 'data.set',
          });
      }
    }
  }
};
</script>