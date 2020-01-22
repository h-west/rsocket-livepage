<template>
    <div>
        <component :is="item.component" v-bind="item" v-for="(item,i) in cItems" :key="i" ref="items"></component>
    </div>
</template>

<script>
export default {
    name: 'free',
    props: ['items'],
    computed: {
        cItems() {
            return this.items.map(item => {
                item.component= () => import(`./items/${item.type}.vue`)
                return item;
            })
        }
    },
    methods: {
        handle(result) {  // mixin??
            //console.log(result);
            this.$refs.items.forEach(i=>{
                i.handle(result);
            })
        }
    }
}
</script>