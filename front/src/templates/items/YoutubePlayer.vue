<template>
    <div>
        <div :id='playerDivId'></div>
    </div>
</template>

<script>
import YouTubePlayer from 'youtube-player';

export default {
    name: 'item-youtube-player',
    props: ['video-id'],
    mounted() {
        this.load(this.videoId);
    },
    computed: {
        player() {
            return YouTubePlayer(this.playerDivId);
        },
        playerDivId() {
            return 'player-yt-'+this._uid;
        }
    },
    watch: {
        videoId(id) {
            this.load(id);
        }
    },
    methods: {
        load(id) {
            this.player.loadVideoById(this.videoId);
            this.player.playVideo();
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