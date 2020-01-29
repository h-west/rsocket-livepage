<template>
    <div class="max-size-screen">
        <Map :mapOptions="mapOptions" @load="onLoad"></Map>
        <v-bottom-navigation
            :value="activeBtn"
            grow
            color="teal"
            class="bottom-over"
        >
            <v-btn @click.stop.prevent="send">
            <span>Recents</span>
            <v-icon>mdi-history</v-icon>
            </v-btn>

            <v-btn>
            <span>Favorites</span>
            <v-icon>mdi-heart</v-icon>
            </v-btn>

            <v-btn>
            <span>Nearby</span>
            <v-icon>mdi-map-marker</v-icon>
            </v-btn>
        </v-bottom-navigation>
    </div>
</template>

<script>
import Map from './items/Map'
import Vue from 'vue'

export default {
    components: {Map},
    props: ['features','add'],
    watch: {
        add(feature) {
            this.$refs.map.addFeature(feature);
        }
    },
    data() {
      return {
        activeBtn: 1,
        info: false,
        marker: null,
        count: 1,
        map: null,
        mapOptions: {
          lat: 37.5666103,
          lng: 126.9783882,
          zoom: 10,
          //zoomControl: true,
          //zoomControlOptions: {position: 'TOP_RIGHT'},
          //mapTypeControl: true,
          scaleControl: false,
        },
      }
    },
    computed: {
      hello() {
        return `Hello, World! × ${this.count}`;
      }
    },
    methods: {
      onLoad(map) {
          this.map = map;
        //   console.log(this.features);
        //   console.log(map);
          if(this.features){
              map.data.addGeoJson(this.features);
              map.data.setStyle(function(feature) {
                var color = 'red';
    
                if (feature.getProperty('isColorful')) {
                    color = feature.getProperty('color');
                }
    
                return {
                    fillColor: color,
                    strokeColor: color,
                    strokeWeight: 2,
                    icon: null
                };
            });
          }
        // 기타 이벤트
      },
      onWindowLoad(that) {
      },
      onMarkerClicked(event) {
        this.info = !this.info;
      },
      onMarkerLoaded(vue) {
        this.marker = vue.marker;
      },
      send(){
        //   Vue.rsPageSend()
          
        this.map.data.addGeoJson({
            type: "Feature",
            geometry: {
                "type": "Point",
                "coordinates": [126.9783882, 37.5666103]
                ,mantle_properties:{
                    icon:'https://navermaps.github.io/maps.js.ncp/docs/img/example/sally.png'
                }
            },
            properties: {
                name: "Dinagat Islands"
                
            }
          },true);


        //   var markerOptions = {
        //     position: new naver.maps.LatLng(37.3849483, 127.1229117).destinationPoint(90, 15),
        //     map: this.map,
        //     icon: {
        //         url: 'https://navermaps.github.io/maps.js.ncp/docs/img/example/sally.png',
        //         size: new naver.maps.Size(50, 52),
        //         origin: new naver.maps.Point(0, 0),
        //         anchor: new naver.maps.Point(25, 26)
        //     }
        // };
        // new naver.maps.Marker(markerOptions);

      }
    },
    mounted() {
    },
    created() {
        if (process.browser) {
            window.$naverMapsCallback = [];
            window.$naverMapsLoaded = false;
            const URL = 'https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=30ziljje7l';
            const script = document.createElement('script');
            if (script) {
                script.setAttribute('src', URL);
                script.id = 'naver-map-load';
                script.setAttribute('async', '');
                script.setAttribute('defer', '');
                document.head.appendChild(script);
            }
            // Vue.component('naver-maps', Map);
            // Vue.component('naver-info-window', InfoWindow);
            // Vue.component('naver-marker', Marker);
            // Vue.component('naver-circle', NaverCircle);
            // Vue.component('naver-ellipse', NaverEllipse);
            // Vue.component('naver-rectangle', NaverRectangle);
            // Vue.component('naver-ground-overlay', NaverGroundOverlay);
            // Vue.component('naver-polygon', NaverPolygon);
            // Vue.component('naver-polyline', NaverPolyline);
            } else throw new Error('Sorry, this plugin is only available in browsers at now. If you are using Nuxt.js, turn off ssr for this plugin.');
    }
}
</script>

<style scoped>
.max-size-screen{
    height: 100%;
    width: 100%;
    margin: 0px;
}
.info-window-container {
    padding: 10px;
    width: 300px;
    height: 100px;
}
.bottom-over{
    position: fixed;
    bottom: 0;
    z-index: 100;
}
</style>