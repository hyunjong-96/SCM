<template>
    <VContainer>
        <VRow>
            <VCol>
                <h1>Repository Commit</h1>
            </VCol>
            <VCol>
                <VSelect
                label="User"
                :items="userList"
                @update:modelValue="userClick"
                ></VSelect>
                <VSelect
                label="Repository"
                :items="userRepositoryList"
                @update:modelValue="repoClick"
                ></VSelect>
                <VSelect
                label="Branch"
                :items="repoBranchList"
                @update:modelValue="branchClick"
                ></VSelect>
            </VCol>
        </VRow>
        <VRow>
            <VBtn>Commit</VBtn>
        </VRow>
        <VRow>
            <VCol>
                <VCard v-if="commitList.length == 0">
                    <VCardText>No Commit FOund</VCardText>
                </VCard>

                <VRow v-else>
                    <!-- <div
                        v-for="commit in commitList"
                        ::key="commit.id"
                    >
                        commit.name
                    </div> -->
                </VRow>
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
    searchUserName.value = username

    callUserRepo(username);
}

const callUserRepo = async(username) => {
    const result = await api.get(`/repos/users/${username}`);

    if(result) {
        userRepositoryList.value = result.data;   
    }
}

const repoClick = async(repo) => {
    searchRepoName.value = repo.name

    await callRepoBranch(repo.name);
}

const callRepoBranch = async(repo) => {
    console.log('callRepoBranch repo : ',repo)
    // const result = await api.get(`/repos/${searchUserName}/${searchRepoName}/branches`);

    repoBranchList.value = [];

}

const branchClick = async(branch) => {
    searchBranchName.value = branch.name

    callRepoCommit(branch.name);
}

const callRepoCommit = async(branch) => {
    console.log('callRepoCommit : ',branch)

    commitList.value = [];
}

</script>