<template>
    <VContainer>
        <VCol>
            <VCard>
                <VCardTitle>[{{index}}] {{commit.commit.message}}</VCardTitle>
                <VCardSubtitle></VCardSubtitle>
            </VCard>
        </VCol>
    </VContainer>
</template>

<script setup>
// eslint-disable-next-line no-undef
defineProps({
    commit: {
        type: Object,
        required: true
    },
    index: {
        type: Number,
        required: true
    }
})

</script>