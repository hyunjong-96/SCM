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
    <v-main class="main-content">
      <router-view/>
      <SessionExpiredDialog/>
      <ErrorDialog/>
    </v-main>
  </v-app>
</template>

<!-- <style scoped>
.main-content {
  padding-top: 64px; /* 네비게이션 바의 높이만큼 */
  min-height: 100vh; /* 화면 전체 높이 유지 */
  background-color: #f8f9fa; /* 배경색 추가 (필요 시) */
}
</style> -->