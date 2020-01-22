<template>
    <div>
        <component :is="youtube-player" :video-id="videoId"></component>
        ----- chat ------
    </div>
</template>

<script>
import YouTubePlayer from './items/YoutubePlayer.vue';

export default {
    name: 'live1',
    props: ['video-id'],
    components:{
        'youtube-player': YouTubePlayer
    },
    methods: {
        handle(result) {  // mixin??
            console.log(result);
        }
    }
}
</script>