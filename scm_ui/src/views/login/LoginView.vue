<template>
    <LoginComponent/>
</template>

<script>
import {defineComponent} from 'vue';
import LoginComponent from '../../components/login/LoginComponent.vue';

export default defineComponent({
    name: 'LoginView',
    components: {
        LoginComponent
    }
})

</script>