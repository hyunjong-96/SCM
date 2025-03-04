<script setup>
import SessionExpiredDialog from './components/dialog/SessionExpiredDialog.vue'
import ErrorDialog from './components/dialog/ErrorDialog.vue'

</script>

<template>
  <v-app>
    <v-main>
      <router-view/>
      <SessionExpiredDialog/>
      <ErrorDialog/>
    </v-main>
  </v-app>
</template>