<template>
    <div class="max-size-screen">
        <Map :mapOptions="mapOptions"></Map>
        <v-bottom-navigation
            :value="activeBtn"
            grow
            color="teal"
            class="bottom-over"
        >
            <v-btn>
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
import Map from './navermaps/Map'

export default {
    name: 'HelloWorld',
    components: {Map},
    data() {
      return {
        activeBtn: 1,
        info: false,
        marker: null,
        count: 1,
        map: null,
        mapOptions: {
          lat: 37,
          lng: 127,
          zoom: 10,
          zoomControl: true,
          zoomControlOptions: {position: 'TOP_RIGHT'},
          mapTypeControl: true,
        },
      }
    },
    computed: {
      hello() {
        return `Hello, World! × ${this.count}`;
      }
    },
    methods: {
      onLoad(vue) {
      },
      onWindowLoad(that) {
      },
      onMarkerClicked(event) {
        this.info = !this.info;
      },
      onMarkerLoaded(vue) {
        this.marker = vue.marker;
      }
    },
    mounted() {
      setInterval(() => this.count++, 1000);
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