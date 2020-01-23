<template>
    <div>
        <ul v-for="(m,i) in chats" :key="i">
            <li>{{m}}</li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'item-chat',
    props: ['chats'],
}

/*  item object
{
    "type" : "Chat",
    "chats" : []
}
 */
</script>