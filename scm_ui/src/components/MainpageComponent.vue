<template>
    <v-container>
        <!-- <v-row class="text-center">
            <h1 class="display-2 font-weight-bold mb-3">
                Welcome to the Vuetify 3 Beta
            </h1>
        </v-row> -->
        <h1 >
                Hello custom login
            </h1>

            <VBtn @click="callTest">
                button
            </VBtn>
    </v-container>
</template>

<script setup>

// export default {
//     name: 'MainpageComponent',
//     data: () => {

//     }
// }

import api from '../modules/api'
import {useCookies} from 'vue3-cookies'

const {cookies} = useCookies();

const accessToken = cookies.get('scm-token')

console.log('accessToken : ',accessToken);

const callTest = () => {
    api.get('/merge-request')
}

</script>