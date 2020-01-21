<template>
    <div>
        <component :is='component' v-if="component"></component>
    </div>
</template>

<script>

export default {
    name: 'vue-component',
    props: ['component-str'],
    // created(vm) {
    //     vm.$children.push(Function('return ' + this.componentStr).call(this));
    // }
    data(){
        return {
            component: null
        }
    },
    computed: {
        componentObj(){
            return Function('return ' + this.componentStr).call(this);
        }
    },
    mounted(){
        this.component = this.componentObj;
    }
    // mounted(){
    //     let player = YouTubePlayer(this.videoId, {
    //         videoId: this.videoId
    //     });
    //     player
    //     // Play video is a Promise.
    //     // 'playVideo' is queued and will execute as soon as player is ready.
    //     .playVideo()
    //     .then(function () {
    //         console.log('Starting to play player1. It will take some time to buffer video before it starts playing.');
    //     });
    // }
}
</script>