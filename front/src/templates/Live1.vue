<template>
    <div>
        <div id='player-1'></div>
    </div>
</template>

<script>
import YouTubePlayer from 'youtube-player';

export default {
    name: 'live1',
    props: ['video-id'],
    created(){
        console.log(this.videoId)
    },
    mounted(){
        let player1 = YouTubePlayer('player-1', {
            videoId: this.videoId
        });
        player1
        // Play video is a Promise.
        // 'playVideo' is queued and will execute as soon as player is ready.
        .playVideo()
        .then(function () {
            console.log('Starting to play player1. It will take some time to buffer video before it starts playing.');
        });
    }
}
</script>