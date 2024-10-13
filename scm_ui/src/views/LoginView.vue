<template>
    <LoginComponent/>
</template>

<script>
import {defineComponent} from 'vue';
import LoginComponent from '../components/LoginComponent.vue';

export default defineComponent({
    name: 'LoginView',
    components: {
        LoginComponent
    }
})

</script>