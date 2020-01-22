<template>
    <div>
        <div :id='videoId'></div>
    </div>
</template>

<script>
import YouTubePlayer from 'youtube-player';

export default {
    name: 'youtube-player',
    props: ['video-id'],
    created(){
        console.log(this.videoId)
    },
    mounted(){
        let player = YouTubePlayer(this.videoId, {
            videoId: this.videoId
        });
        player
        // Play video is a Promise.
        // 'playVideo' is queued and will execute as soon as player is ready.
        .playVideo()
        .then(function () {
            console.log('Starting to play player1. It will take some time to buffer video before it starts playing.');
        });
    },
    methods: {
        handle(result) {  // mixin??
            //console.log(result);
        }
    }
}


/*  item object
{
    "type" : "YoutubePlayer",
    "videoId" : "o_UUYwymh30"
}
 */
</script>