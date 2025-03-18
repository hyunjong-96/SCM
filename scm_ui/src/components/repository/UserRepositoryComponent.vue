<template>
    <VContainer>
        <VRow class="header-container">
            <VCol cols="6">
                <h1>User Repository</h1>
            </VCol>
            <VCol cols="3" class="select-container">
                <VSelect
                label="User"
                :items="['hyunjong-96', 'tester1']"
                @update:modelValue="userClick"
                density="compact"
            ></VSelect>
            </VCol>
        </VRow>
        <VRow class="body-container">
            <VCol>
                <VCard v-if="userRepositoryList.length == 0">
                    <VCardText>No Repository Found</VCardText>
                </VCard>

                <VRow v-else class="repository-list">
                    <div
                        v-for="repo in userRepositoryList"
                        :key="repo.id"
                    >
                        <RepositoryComponentVue :repository="repo"/>
                    </div>
                </VRow>
            </VCol>
        </VRow>
    </VContainer>
</template>

<script setup>
import {ref} from 'vue'
import api from '../../modules/api'
import RepositoryComponentVue from './RepositoryComponent.vue'

const userRepositoryList = ref([])

const userClick = (username) => {
    
    // {
    //     id;
    //     nodeId;
    //     name;
    //     fullName;
    //     isPrivate;
    //     htmlUrl;
    //     description;
    //     url;
    // }

    // userRepositoryList.value = [{id:1, name:'test_repository'}, {id: 2, name:'hyunjong-96-repository'}];

    callUserRepo(username);
}

const callUserRepo = async(username) => {
    const result = await api.get(`/repos/users/${username}`);

    console.log('callUserRepo : ', result.data);
    userRepositoryList.value = result.data;
}

</script>

<style scoped>
    .header-container{
        display: flex;
        justify-content: space-between;
        align-items: center; /* 수직 정렬 */
    }
    .select-container{
        display: flex;
        justify-content: flex-end;
        align-items: center; /* 수직 정렬 */
    }
    .repository-list{
        display:flex;
        flex-direction:column;
        gap:30px
    }
    .v-select{
        max-height: 30px;
    }
</style>