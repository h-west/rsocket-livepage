<template>
    <div class="text-center">
      <v-bottom-sheet v-model="menu">
        <template v-slot:activator="{ on }">
          <v-btn fab dark class="float-button-menu">
            <v-icon v-on="on">mdi-menu</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-subheader>
                <v-btn text icon outlined>
                    <v-icon>mdi-account-circle</v-icon>
                </v-btn>
                <v-btn text icon outlined>
                    <v-icon>mdi-login</v-icon>
                </v-btn>
          </v-subheader>
          <v-list-item
            v-for="tile in tiles"
            :key="tile.title"
            @click="sheet = false"
          >
            <v-list-item-avatar>
              <v-avatar size="32px" tile>
                <img
                  :src="`https://cdn.vuetifyjs.com/images/bottom-sheets/${tile.img}`"
                  :alt="tile.title"
                >
              </v-avatar>
            </v-list-item-avatar>
            <v-list-item-title>{{ tile.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-bottom-sheet>
    </div>
</template>

<script>
export default {
    data: () => ({
        menu: false,
        tiles: [
        { img: 'keep.png', title: '탬플릿 등록/ 변경' },
        { img: 'hangouts.png', title: '탬플릿 삭제' },
        { img: 'messenger.png', title: 'MY 활동' },
        { img: 'google.png', title: '즐겨찾기' },
        { img: 'inbox.png', title: '랜덤페이지' },
        { img: 'inbox.png', title: '페이지 이동' },
        ]
    })
}
</script>

<style scoped>
.float-button-menu {
  bottom: 0;
  right: 0;
  position: fixed;
  margin: 0 16px 72px 0;
}
</style>