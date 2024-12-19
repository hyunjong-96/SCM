<script setup>
import { useAlertStore } from '@/stores/alert'
import { useRouter } from 'vue-router'
import { computed, onMounted } from 'vue';

const alertStore = useAlertStore();
const router = useRouter();

const isVisible = computed(() => alertStore.isVisibleSessionExpired);
const titleMessage = computed(() => alertStore.titleMessage);
const message = computed(() => alertStore.message);
const hideAlert = () => {
    alertStore.hideAlert();
    router.push('/login');
}

onMounted(() => {
    console.log('alert dialog : ',alertStore)
})

</script>

<!-- <template>
    <div style="position: fixed; z-index:999; top:100px; left:50%; transform: translate(-50%, -50%);">
    <div
    :model-value="isVisible"
      class="d-flex justify-center"
      :close-on-content-click=false
      @update:model-value="value => { if (!value) hideAlert() }"
    >
        <div class="dialog-container">
            <div>{{ titleMessage }}</div>
            <div>{{ message }}</div>
        </div>
    </div>
</div>
</template> -->

<style scoped>
.dialog-container{
    background-color: red;
    width: 500px;
    height: auto;
}
</style>

<template>
    <!-- <div style="position: fixed; z-index:999; top:100px; left:50%; transform: translate(-50%, -50%);"> -->
      <VDialog
        :model-value="isVisible"
        class="d-flex justify-center"
        :close-on-content-click=false
        @update:model-value="value => { if (!value) hideAlert() }"
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
              @click:close="hideAlert"
              >
              {{ message }}
            </VAlert>
          </VCol>
        </VRow>
      </VDialog>
    <!-- </div> -->
  </template>