<template>
    <v-card>
        <v-list
        subheader
        two-line
        flat
        >
        <v-subheader>TODO LIST</v-subheader>

        <div v-for="(todo, i) in items" :key="i">
            <v-list-item>
                <v-list-item-action>
                    <v-checkbox
                        v-model="todo.done"
                        color="primary"
                    ></v-checkbox>
                </v-list-item-action>

                <v-list-item-content>
                    <v-list-item-title :class="{done: todo.done}" class="title">{{todo.title | capitalize}}</v-list-item-title>
                    <v-list-item-subtitle>...</v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-icon>
                    <v-btn icon ripple color="red"  v-if="todo.done" @click="removeTodo(i)">
                        <v-icon>mdi-window-close</v-icon>
                    </v-btn>
                </v-list-item-icon>
            </v-list-item>
        </div>

        </v-list>
    </v-card>
</template>

<script>
export default {
    name: 'Todo',
    props: ['items','msg'],
    methods: {
        removeTodo(index) {
            this.items.splice(index, 1);
        },
    },
    filters: {
        capitalize: function (value) {
            if (!value) return ''
            value = value.toString()
            return value.charAt(0).toUpperCase() + value.slice(1)
        }
    }
}
</script>

<style scoped>
.done {
  text-decoration: line-through;
}
</style>