<template>
    <div>
        <component :is="item.component" v-bind="item" v-for="(item,i) in cItems" :key="i"></component>
    </div>
</template>

<script>
import YP from './items/YoutubePlayer.vue'
export default {
    name: 'live1',
    props: ['items'],
    computed: {
        cItems() {
            return this.items.map(item => {
                item.component= () => import(`./items/${item.type}.vue`)
                return item;
            })
        }
    }
}
</script>