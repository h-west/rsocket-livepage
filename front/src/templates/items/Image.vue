<template>
    <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <!--<v-btn color="primary" dark v-on="on">Open Dialog</v-btn> -->
        <v-img :src="url" v-on="on"></v-img>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">update url</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="Legal first name*" v-model="formUrl"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="changeUrl">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import Vue from 'vue'

export default {
    name: 'item-image',
    props: ['url'],
    data: () => ({
      dialog: false,
      formUrl: ''
    }),
    methods: {
        changeUrl() {
            Vue.rsPageSend('template',{exec:"items["+this.$vnode.key+"].url='"+this.formUrl+"'"});
            this.dialog=false;
        }
    }
}

/*  item object
{
    "type" : "Image",
    "url" : "https://i.ytimg.com/vi/s4gBChg6AII/maxresdefault.jpg"
}
 */
</script>