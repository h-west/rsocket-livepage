<template>
    <div>
        <component :is='component' v-if="component"></component>
    </div>
</template>

<script>
import Vue from 'vue'
import Vuetify from 'vuetify'
Vue.use(Vuetify);

export default {
    name: 'vue-component',
    props: ['component-str'],
    computed: {
        component(){
            return Function('return ' + this.componentStr).call(this);
        }
    },
    methods: {
        handle(result) {  // mixin??
           // console.log(result);
        }
    }
}


/*  item object
{
    "type" : "VueComponent",
    "componentStr" : "{name:'aaaa',template:`<div><div v-if=\"show\">SHOW</div>\n<v-btn @click=\"ok\">show Message</v-btn></div>`,data:function(){return {show:false};},methods:{ok(){this.show=true}}}"
}
 */
</script>