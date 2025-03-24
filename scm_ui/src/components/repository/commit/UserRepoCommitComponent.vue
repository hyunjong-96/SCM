<template>
    <VContainer>
        <VRow>
            <VCol>
                <h1>Repository Commit</h1>
            </VCol>
            <VCol class="commit-selecter">
                <VSelect
                label="User"
                :items="userList"
                @update:modelValue="userClick"
                return-object
                ></VSelect>
                <VSelect
                label="Repository"
                :items="userRepositoryList"
                @update:modelValue="repoClick"
                item-title="name"
                return-object
                ></VSelect>
                <VSelect
                label="Branch"
                :items="repoBranchList"
                @update:modelValue="branchClick"
                ></VSelect>
            </VCol>
        </VRow>
        <VRow>
            <VCol class="commit-button">
                <VBtn>Commit</VBtn>
            </VCol>
        </VRow>
        <VRow>
            <VCol>
                <VCard v-if="commitList.length == 0">
                    <VCardText>No Commit Found</VCardText>
                </VCard>

                
            </VCol>
        </VRow>
    </VContainer>
</template>

<script setup>
import {ref } from 'vue'
import api from '../../../modules/api'

const userList = ref(['hyunjong-96', 'tester1']);
const userRepositoryList = ref([]);
const repoBranchList = ref([]);
const commitList = ref([]);

const searchUserName = ref('')
const searchRepoName = ref('')
const searchBranchName = ref('')

const userClick = async(username) => {
    console.log('userClick : ',username)
    searchUserName.value = username

    callUserRepo(username);
}

const callUserRepo = async(username) => {
    const result = await api.get(`/repos/users/${username}`);

    console.log('callUserRepo : ',result)

    if(result) {
        userRepositoryList.value = result.data;   
    }
}

const repoClick = async(repo) => {
    console.log('repoClick : ',repo)
    searchRepoName.value = repo.name

    await callRepoBranch(repo.name);
}

const callRepoBranch = async(repo) => {
    console.log('callRepoBranch repo : ',repo)
    console.log('searchUserName : ',searchUserName.value)

    const result = await api.get(`/repos/${searchUserName.value}/${repo}/branches`);

    if(result) {
        repoBranchList.value = result.data;
    }

}

const branchClick = async(branch) => {
    searchBranchName.value = branch.name

    callRepoCommit(branch.name);
}

const callRepoCommit = async(branch) => {
    console.log('callRepoCommit : ',branch)

    const data = {
        params : {
            "branch" : branch
        }
    }

    const result = await api.get(`/commit/${searchUserName.value}/${searchRepoName.value}/commit`, data)

    if(result) {
        commitList.value = result.data;
    }
}

</script>

<style scoped>
.commit-selecter{
    display: flex;
    flex-direction: row;
}
.commit-button{
    display: flex;
    justify-content: end;
}
</style>