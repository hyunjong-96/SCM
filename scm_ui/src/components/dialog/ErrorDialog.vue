<template>
    <VDialog
        :model-value="isVisible"
        class="d-flex justify-center"
        :close-on-content-click=false
        @update:model-value="value => { if (!value) alertStore.hideAlert }"
        transition="slide-y-transition"
        persistent
        >
        <VRow class="d-flex justify-center">
          <VCol cols="auto">
            <VAlert 
              :title="titleMessage"
              color="red" 
              width="500px"
              closable
              height="100px"
              @click:close="alertStore.hideAlert"
              >
              {{ message }}
            </VAlert>
          </VCol>
        </VRow>
      </VDialog>
  </template>
  
  <script setup>
  import { useAlertStore } from '@/stores/alert';
  import { computed } from 'vue';
  
  const alertStore = useAlertStore();
  
  // 반응형 상태 감지
  const isVisible = computed(() => alertStore.isVisibleNormalError);
  const titleMessage = computed(() => alertStore.titleMessage)
  const message = computed(() => alertStore.message)
  </script>
  
  <!-- <style scoped>
  .alert-popup {
    position: fixed;
    top: 20px;
    left: 50%;
    transform: translateX(-50%);
    background: rgba(0, 0, 0, 0.7);
    color: white;
    padding: 20px;
    border-radius: 10px;
    z-index: 1000;
  }
  .alert-box {
    text-align: center;
  }
  </style> -->
