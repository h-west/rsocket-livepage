<template>
    <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <div :id='playerDivId'></div>
        <v-btn color="primary" dark v-on="on">change video</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">change video</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field v-model="formVideoId"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="changeVideoId">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import Vue from 'vue'
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
        },
        changeVideoId(){
            Vue.rsPageSend('template',{exec:"items["+this.$vnode.key+"].videoId='"+this.formVideoId+"'"});
            this.dialog=false;
        }
    },
    data: () => ({
        dialog: false,
        formVideoId: ''
    })
}


/*  item object
{
    "type" : "YoutubePlayer",
    "videoId" : "o_UUYwymh30"
}
 */
</script>