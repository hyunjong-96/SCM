<template>
    <v-card>
      <v-app-bar color="primary">
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>SCM</v-toolbar-title>
        <v-spacer></v-spacer>
  
        <template v-if="isDesktop">
          <v-btn icon="mdi-magnify" variant="text"></v-btn>
          <!-- <v-btn icon="mdi-filter" variant="text"></v-btn> -->
        </template>
  
        <v-btn icon="mdi-dots-vertical" variant="text"></v-btn>
      </v-app-bar>
  
      <v-navigation-drawer v-model="drawer" temporary>
        <v-list>
          <v-list-item v-for="(item, index) in items" :key="index" :title="item.title"
            @click="navigateTo(item.path)" :v-if="item.path"
          ></v-list-item>
        </v-list>
      </v-navigation-drawer>
    </v-card>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue'
  import { useDisplay } from 'vuetify'
  import {useRouter} from 'vue-router'
  
  const drawer = ref(false)
  const { mdAndUp } = useDisplay()
  const router = useRouter()
  
  const isDesktop = computed(() => mdAndUp.value)
  
  const items = ref([
    { title: 'Home', path: '/'},
    { title: 'Repository', path: '/repository'},
    { title: 'Settings' }
  ])

  const navigateTo = (path) => {
    if(path) {
        router.push(path);
        drawer.value = false
    }
  }
  </script>