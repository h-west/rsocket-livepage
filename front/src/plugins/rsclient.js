import { IdentitySerializer, JsonSerializer, RSocketClient } from 'rsocket-core';
import RSocketWebSocketClient from 'rsocket-websocket-client';

let installed = false;
let client = null;
let connectedSocket = null;
let savedPageId = null;
export default {
    install: function(Vue, options) {
        if (installed) return
        installed = true
        
        client = new RSocketClient({
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
          transport: new RSocketWebSocketClient({url: options.url}), //'ws://localhost:8080/ws'
        })

        Vue.rsPageConnect = function(pageId,handle){
          if(savedPageId) return
          savedPageId = pageId;
          client.connect().subscribe({
            onComplete: socket => {
              connectedSocket = socket;
              socket.requestStream({
                data: {
                  page: pageId
                },
                metadata: String.fromCharCode('data.page'.length) + 'data.page',
              }).subscribe({
                onComplete: () => console.log('complete'),
                onError: error => {
                  console.log(error);
                  addErrorMessage("Connection has been closed due to ", error);
                },
                onNext: payload => {
                  //console.log(payload.data);
                  handle(payload.data);
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
        }

        Vue.rsPageSend = function(message){
          if(!savedPageId) return
          connectedSocket.fireAndForget({
            data: {page:savedPageId,message:message},
            metadata: String.fromCharCode('data.page.set'.length) + 'data.page.set',
          });
        }

    }
}