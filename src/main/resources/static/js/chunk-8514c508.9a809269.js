(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-8514c508","chunk-2d0c7d0f"],{"037b":function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"max-size-screen"},[i("Map",{attrs:{mapOptions:t.mapOptions}}),i("v-bottom-navigation",{staticClass:"bottom-over",attrs:{value:t.activeBtn,grow:"",color:"teal"}},[i("v-btn",[i("span",[t._v("Recents")]),i("v-icon",[t._v("mdi-history")])],1),i("v-btn",[i("span",[t._v("Favorites")]),i("v-icon",[t._v("mdi-heart")])],1),i("v-btn",[i("span",[t._v("Nearby")]),i("v-icon",[t._v("mdi-map-marker")])],1)],1)],1)},o=[],r=i("835e"),a=r["a"],s=(i("77d6"),i("2877")),l=i("6544"),p=i.n(l),h=(i("dd43"),i("fe6c")),c=i("58df");function u(t,e=[]){return Object(c["a"])(Object(h["b"])(["absolute","fixed"])).extend({name:"applicationable",props:{app:Boolean},computed:{applicationProperty(){return t}},watch:{app(t,e){e?this.removeApplication(!0):this.callUpdate()},applicationProperty(t,e){this.$vuetify.application.unregister(this._uid,e)}},activated(){this.callUpdate()},created(){for(let t=0,i=e.length;t<i;t++)this.$watch(e[t],this.callUpdate);this.callUpdate()},mounted(){this.callUpdate()},deactivated(){this.removeApplication()},destroyed(){this.removeApplication()},methods:{callUpdate(){this.app&&this.$vuetify.application.register(this._uid,this.applicationProperty,this.updateApplication())},removeApplication(t=!1){(t||this.app)&&this.$vuetify.application.unregister(this._uid,this.applicationProperty)},updateApplication:()=>0}})}var d=i("604c"),m=d["a"].extend({name:"button-group",provide(){return{btnToggle:this}},computed:{classes(){return d["a"].options.computed.classes.call(this)}},methods:{genData:d["a"].options.methods.genData}}),v=i("a9ad"),g=i("24b2"),f=i("a452");function y(t,e){const i=e.value,n=e.options||{passive:!0},o=e.arg?document.querySelector(e.arg):window;o&&(o.addEventListener("scroll",i,n),t._onScroll={callback:i,options:n,target:o})}function w(t){if(!t._onScroll)return;const{callback:e,options:i,target:n}=t._onScroll;n.removeEventListener("scroll",e,i),delete t._onScroll}const b={inserted:y,unbind:w};var S=i("d9bd"),T=i("a026"),O=T["default"].extend({name:"scrollable",directives:{Scroll:b},props:{scrollTarget:String,scrollThreshold:[String,Number]},data:()=>({currentScroll:0,currentThreshold:0,isActive:!1,isScrollingUp:!1,previousScroll:0,savedScroll:0,target:null}),computed:{canScroll(){return"undefined"!==typeof window},computedScrollThreshold(){return this.scrollThreshold?Number(this.scrollThreshold):300}},watch:{isScrollingUp(){this.savedScroll=this.savedScroll||this.currentScroll},isActive(){this.savedScroll=0}},mounted(){this.scrollTarget&&(this.target=document.querySelector(this.scrollTarget),this.target||Object(S["c"])(`Unable to locate element with identifier ${this.scrollTarget}`,this))},methods:{onScroll(){this.canScroll&&(this.previousScroll=this.currentScroll,this.currentScroll=this.target?this.target.scrollTop:window.pageYOffset,this.isScrollingUp=this.currentScroll<this.previousScroll,this.currentThreshold=Math.abs(this.currentScroll-this.computedScrollThreshold),this.$nextTick(()=>{Math.abs(this.currentScroll-this.savedScroll)>this.computedScrollThreshold&&this.thresholdMet()}))},thresholdMet(){}}}),C=i("7560"),M=i("f2e7"),L=Object(c["a"])(u("bottom",["height","inputValue"]),v["a"],g["a"],Object(M["b"])("inputValue"),f["a"],O,C["a"]).extend({name:"v-bottom-navigation",props:{activeClass:{type:String,default:"v-btn--active"},backgroundColor:String,grow:Boolean,height:{type:[Number,String],default:56},hideOnScroll:Boolean,horizontal:Boolean,inputValue:{type:Boolean,default:!0},mandatory:Boolean,shift:Boolean},data(){return{isActive:this.inputValue}},computed:{canScroll(){return O.options.computed.canScroll.call(this)&&(this.hideOnScroll||!this.inputValue)},classes(){return{"v-bottom-navigation--absolute":this.absolute,"v-bottom-navigation--grow":this.grow,"v-bottom-navigation--fixed":!this.absolute&&(this.app||this.fixed),"v-bottom-navigation--horizontal":this.horizontal,"v-bottom-navigation--shift":this.shift}},styles(){return{...this.measurableStyles,transform:this.isActive?"none":"translateY(100%)"}}},created(){this.$attrs.hasOwnProperty("active")&&Object(S["a"])("active.sync","value or v-model",this)},methods:{thresholdMet(){this.isActive=!this.isScrollingUp,this.$emit("update:input-value",this.isActive)},updateApplication(){return this.$el?this.$el.clientHeight:0},updateValue(t){this.$emit("change",t)}},render(t){const e=this.setBackgroundColor(this.backgroundColor,{staticClass:"v-bottom-navigation",class:this.classes,style:this.styles,props:{activeClass:this.activeClass,mandatory:Boolean(this.mandatory||void 0!==this.value),value:this.internalValue},on:{change:this.updateValue}});return this.canScroll&&(e.directives=e.directives||[],e.directives.push({arg:this.scrollTarget,name:"scroll",value:this.onScroll})),t(m,this.setTextColor(this.color,e),this.$slots.default)}}),A=i("8336"),B=i("132d"),P=Object(s["a"])(a,n,o,!1,null,"47dd75b4",null);e["default"]=P.exports;p()(P,{VBottomNavigation:L,VBtn:A["a"],VIcon:B["a"]})},"51da":function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{style:t.mapStyle,attrs:{id:"vue-naver-maps"}},[t._t("default")],2)},o=[],r={name:"Map",props:{width:Number,height:Number,mapOptions:{type:Object},initLayers:Array},computed:{mapStyle(){let t=this.width?this.width+"px":"100%",e=this.height?this.height+"px":"100%";return{width:t,height:e}}},data(){return{map:null,styleMapLoaded:!1}},watch:{mapOptions:{handler(t){this.map.setOptions(t)}}},methods:{addStyleLayer(t){return this.map.mapTypes.selectedMapType.addLayer(window.naver.maps.StyleMapLayerId[t]),this.map.refresh(),this},removeStyleLayer(t){return this.map.mapTypes.selectedMapType.removeLayer(window.naver.maps.StyleMapLayerId[t]),this.map.refresh(),this},removeLayer(t){return this.map.mapTypes.removeLayer(t),this},addPane(t,e){return this.map.addPane(t,e),this},destroy(){this.map.destory(),this.map=null,this.$destroy()},fitBounds(t,e){return this.map.fitBounds(t,e),this},morph(t,e,i){return e?i?this.map.morph(t,e,i):this.map.morph(t,e):this.map.morph(t),this},panTo(t,e){this.map.panTo(t,e)},panToBounds(t,e,i){this.map.panToBounds(t,e,i)},panBy(t,e){return this.map.panBy(new window.naver.maps.Point(t,e)),this},refresh(t=!1){return this.map.refresh(t),this},removePane(t){return this.map.removePane(t),this},updateBy(t,e){return this.map.updateBy(name),this},zoomBy(t,e,i=!1){return e?this.zoomBy(t,e,i):this.zoomBy(t),this},getBounds(){return this.map.getBounds()},getCenter(){return this.map.getCenterPoint()},getCenterPoint(){return this.map.getCenterPoint()},getElement(){return this.map.getElement()},getMapTypeId(){return this.map.getMapTypeId()},getOptions(t){return t?this.map.getOptions(t):this.map.getOptions()},getPanes(){return this.map.getPanes()},getPrimitiveProjection(){return this.map.getPrimitiveProjection()},getProjection(){return this.map.getProjection()},getSize(){return this.map.getSize()},getZoom(){return this.map.getZoom()},setCenter(t,e=0){return this.map.setCenter(isNaN(t)?t:new window.naver.maps.LatLng(t,e)),this},setCenterPoint(t){return this.map.setCenterPoint(t),this},setMapTypeId(t){return this.map.setMapTypeId(naver.maps.Position[t]),this},setOptions(t,e=null){if(!this.map)throw new Error("setOptions not be available before loaded.");return e?this.map.setOptions(t,e):this.map.setOptions(t),this},setSize(t){return this.map.setSize(t),this},setZoom(t,e=!1){return this.map.setZoom(t,e),this},loadNaverMapsComponents(){new Promise(t=>{const e={useStyleMap:!0,center:new window.naver.maps.LatLng(this.mapOptions.lat,this.mapOptions.lng),maxZoom:20,minZoom:0};if(this.useStyleMap){const i={BACKGROUND:"bg",BACKGROUND_DETAIL:"ol",BYCYCLE:"br",CADASTRAL:"lp",CTT:"ctt",HIKING_TRAIL:"ar",PANORAMA:"ps",POI_KOREAN:"lko",TRANSIT:"ts"},n=Object.keys(i);if(this.initLayers.map(t=>n.includes(t)).includes(!1))throw"[vue-naver-maps] Invalid StyledMap layer name in initLayers!";e.mapTypes=window.naver.maps.StyleMapTypeOption.getMapTypes({initLayers:this.initLayers.map(t=>i[t])}),e.mapTypeId=window.naver.maps.StyleMapTypeId.NORMAL,t(e)}else t(e)}).then(t=>{this.map=new window.naver.maps.Map("vue-naver-maps",Object.assign(t,this.mapOptions)),this.zoomControlOptions&&this.zoomControlOptions.position&&this.setOptions({zoomControlOptions:{position:naver.maps.Position[this.zoomControlOptions.position]}}),window.$naverMapsCallback.forEach(t=>t(this.map)),window.$naverMapsCallback=[],window.$naverMapsLoaded=!0,window.$naverMapsObject=this.map,this.$emit("load",this)}).catch(console.error)}},mounted(){if(!this.mapOptions.lat||!this.mapOptions.lng)throw new Error("mapOptions must be included lat and lng.");window.naver?this.loadNaverMapsComponents():document.getElementById("naver-map-load").onload=()=>{this.useStyleMap?document.querySelector('script[src="https://openapi.map.naver.com/openapi/v3/maps-stylemap.js"]').onload=()=>{setTimeout(()=>this.loadNaverMapsComponents(),100)}:window.naver.maps.onJSContentLoaded=this.loadNaverMapsComponents}}},a=r,s=i("2877"),l=Object(s["a"])(a,n,o,!1,null,null,null);e["default"]=l.exports},"76ef":function(t,e,i){},"77d6":function(t,e,i){"use strict";var n=i("76ef"),o=i.n(n);o.a},"835e":function(t,e,i){"use strict";(function(t){var n=i("51da");e["a"]={name:"HelloWorld",components:{Map:n["default"]},data(){return{activeBtn:1,info:!1,marker:null,count:1,map:null,mapOptions:{lat:37,lng:127,zoom:10,zoomControl:!0,zoomControlOptions:{position:"TOP_RIGHT"},mapTypeControl:!0}}},computed:{hello(){return`Hello, World! × ${this.count}`}},methods:{onLoad(t){},onWindowLoad(t){},onMarkerClicked(t){this.info=!this.info},onMarkerLoaded(t){this.marker=t.marker}},mounted(){setInterval(()=>this.count++,1e3)},created(){if(!t.browser)throw new Error("Sorry, this plugin is only available in browsers at now. If you are using Nuxt.js, turn off ssr for this plugin.");{window.$naverMapsCallback=[],window.$naverMapsLoaded=!1;const t="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=30ziljje7l",e=document.createElement("script");e&&(e.setAttribute("src",t),e.id="naver-map-load",e.setAttribute("async",""),e.setAttribute("defer",""),document.head.appendChild(e))}}}}).call(this,i("f28c"))},dd43:function(t,e,i){},f28c:function(t,e){var i,n,o=t.exports={};function r(){throw new Error("setTimeout has not been defined")}function a(){throw new Error("clearTimeout has not been defined")}function s(t){if(i===setTimeout)return setTimeout(t,0);if((i===r||!i)&&setTimeout)return i=setTimeout,setTimeout(t,0);try{return i(t,0)}catch(e){try{return i.call(null,t,0)}catch(e){return i.call(this,t,0)}}}function l(t){if(n===clearTimeout)return clearTimeout(t);if((n===a||!n)&&clearTimeout)return n=clearTimeout,clearTimeout(t);try{return n(t)}catch(e){try{return n.call(null,t)}catch(e){return n.call(this,t)}}}(function(){try{i="function"===typeof setTimeout?setTimeout:r}catch(t){i=r}try{n="function"===typeof clearTimeout?clearTimeout:a}catch(t){n=a}})();var p,h=[],c=!1,u=-1;function d(){c&&p&&(c=!1,p.length?h=p.concat(h):u=-1,h.length&&m())}function m(){if(!c){var t=s(d);c=!0;var e=h.length;while(e){p=h,h=[];while(++u<e)p&&p[u].run();u=-1,e=h.length}p=null,c=!1,l(t)}}function v(t,e){this.fun=t,this.array=e}function g(){}o.nextTick=function(t){var e=new Array(arguments.length-1);if(arguments.length>1)for(var i=1;i<arguments.length;i++)e[i-1]=arguments[i];h.push(new v(t,e)),1!==h.length||c||s(m)},v.prototype.run=function(){this.fun.apply(null,this.array)},o.title="browser",o.browser=!0,o.env={},o.argv=[],o.version="",o.versions={},o.on=g,o.addListener=g,o.once=g,o.off=g,o.removeListener=g,o.removeAllListeners=g,o.emit=g,o.prependListener=g,o.prependOnceListener=g,o.listeners=function(t){return[]},o.binding=function(t){throw new Error("process.binding is not supported")},o.cwd=function(){return"/"},o.chdir=function(t){throw new Error("process.chdir is not supported")},o.umask=function(){return 0}}}]);
//# sourceMappingURL=chunk-8514c508.9a809269.js.map