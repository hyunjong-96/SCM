<script setup>
import SessionExpiredDialog from './components/dialog/SessionExpiredDialog.vue'
import ErrorDialog from './components/dialog/ErrorDialog.vue'
import HorizontalNavBar from './components/nav/HorizontalNavBar.vue'

import {useRoute} from 'vue-router'
import {computed} from 'vue'

const route = useRoute()

// 로그인 페이지에서는 네비게이션 바를 숨김
const hideNavBar = computed(() => route.path === '/login')

</script>

<template>
  <v-app>
    <template v-if="!hideNavBar">
      <HorizontalNavBar/>
    </template>
    <v-main>
      <router-view/>
      <SessionExpiredDialog/>
      <ErrorDialog/>
    </v-main>
  </v-app>
</template>