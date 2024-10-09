<template>
    <MainpageComponent/>
</template>

<script>
import { defineComponent } from 'vue';
import MainpageComponent from '../components/MainpageComponent.vue'

export default defineComponent({
    name: 'MainpageView',
    components: {
        MainpageComponent
    }
})
</script>