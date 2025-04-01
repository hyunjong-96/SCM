<template>
  <v-app-bar color="primary" app>
    <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    <v-toolbar-title>SCM</v-toolbar-title>
    <v-spacer></v-spacer>

    <template v-if="isDesktop">
      <v-btn icon="mdi-magnify" variant="text"></v-btn>
    </template>

    <v-btn icon="mdi-dots-vertical" variant="text"></v-btn>
  </v-app-bar>

  <v-navigation-drawer v-model="drawer" app temporary>
    <v-list>
      <v-list-item
        v-for="(item, index) in items"
        :key="index"
        :title="item.title"
        @click="navigateTo(item.path)"
        :v-if="item.path"
      ></v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useDisplay } from 'vuetify'
import { useRouter } from 'vue-router'

const drawer = ref(false)
const { mdAndUp } = useDisplay()
const router = useRouter()

const isDesktop = computed(() => mdAndUp.value)

const items = ref([
  { title: 'Home', path: '/' },
  { title: 'Repository', path: '/repo' },
  { title: 'Settings' }
])

const navigateTo = (path) => {
  if (path) {
    router.push(path)
    drawer.value = false
  }
}
</script>

<!-- <style scoped>
.v-app-bar {
  position: relative; /* 다른 컴포넌트 위에 떠있지 않도록 설정 */
  z-index: 1; /* 기본값 */
}
</style> -->