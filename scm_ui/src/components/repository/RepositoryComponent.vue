<template>
    <VCol class="repository-container">
        <VCard class="repository-card">
            <a :href="repository.htmlUrl" target="_blank" class="repository-link">
                <VCardTitle>{{repository.name}}</VCardTitle>
            </a>
            <VCardSubtitle>Owner: {{repository.owner}}</VCardSubtitle>
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
.repository-link:hover {
    text-decoration: underline;
    color: #26282a;
}
</style>