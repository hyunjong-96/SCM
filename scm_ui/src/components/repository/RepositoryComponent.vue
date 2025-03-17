<template>
    <VCol class="repository-container">
        <VCard class="repository-card">
            <VcardTitle>{{repository.id}}</VcardTitle>
            <VCardSubtitle>Owner: {{repository.name}}</VCardSubtitle>
        </VCard>
    </VCol>
</template>

<script setup>

// eslint-disable-next-line no-undef
defineProps({
    repository: {
        type: Object,
        required: true
    }
})

</script>

<style scoped>
.repository-container{
    
}
.repository-card {
    padding: 2%;
    border: 1px solid #ddd;
    border-radius: 8px;
}
</style>