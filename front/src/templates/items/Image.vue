<template>
    <div>
        <v-img :src="url"></v-img>
    </div>
</template>

<script>
export default {
    name: 'item-image',
    props: ['url']
}

/*  item object
{
    "type" : "Image",
    "url" : "https://i.ytimg.com/vi/s4gBChg6AII/maxresdefault.jpg"
}
 */
</script>