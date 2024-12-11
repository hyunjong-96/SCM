<script setup>
import AlertDialog from './components/dialog/AlertDialog.vue'

</script>

<template>
  <v-app>
    <v-main>
      <router-view/>
      <AlertDialog/>
    </v-main>
  </v-app>
</template>