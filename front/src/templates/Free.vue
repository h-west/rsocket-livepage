<template>
    <div>
        <component :is="item.component" v-bind="item" v-for="(item,i) in cItems" :key="i"></component>
    </div>
</template>

<script>
export default {
    name: 'free',
    props: ['items'],
    computed: {
        cItems() {
            return this.items.map(item => {
                item.component= () => import(`./items/${item.name}.vue`)
                return item;
            })
        }
    }
}
</script>