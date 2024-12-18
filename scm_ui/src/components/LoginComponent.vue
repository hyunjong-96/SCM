<script setup>
import api from '../modules/api.js'
import { ref } from 'vue';
import { useRouter } from 'vue-router'
import { useCookies } from 'vue3-cookies';

const router = useRouter()
const { cookies } = useCookies();
// const visible = ref(false)
const account = ref("")
const password = ref("")

const login = async () => {

  const data = {
    "email" : account.value,
    "password" : password.value
  }

  const result = await api.post('/auth/login', data);

  if(result) {
    let redirectUrl = result.data.redirectUrl;

    const url = new URL(redirectUrl);

    const urlParams = url.searchParams;

    const token = urlParams.get("scm-token");

    // if(!token) {
    //   console.log("token 없음!!!");
    //   return;
    // }

    cookies.set("scm-token", token);

    router.push("/dashboard")
  }
  else {
    router.push("/login")
  }
}
</script>

<template>
  <div style="display: flex; justify-content: center; align-content: center;">
    <div class="form-container">
      <div class="item-container">
        <input class="text-container" v-model="account" autofocus placeholder="Account"/>
        <input class="text-container" v-model="password" placeholder="Password"/>
        <span style="margin-top: 5px; margin-bottom: 5px;"></span>
        <button class="button-container" @click="login">login</button>
      </div>
    </div>
  </div>
</template>

<!-- <template>
  <div>
    <v-img class="mx-auto my-6" max-width="228"
      src="https://cdn.vuetifyjs.com/docs/images/logos/vuetify-logo-v3-slim-text-light.svg"></v-img>

    <v-card class="mx-auto pa-12 pb-8" v-model="" elevation="8" max-width="448" rounded="lg">
      <div class="text-subtitle-1 text-medium-emphasis">Account</div>

      <v-text-field density="compact" placeholder="ID" prepend-inner-icon="mdi-email-outline"variant="outlined"></v-text-field>

      <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
        Password

        <a
            class="text-caption text-decoration-none text-blue"
            href="#"
            rel="noopener noreferrer"
            target="_blank"
          >
            Forgot login password?</a>
      </div>

      <v-text-field :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'" :type="visible ? 'text' : 'password'"
        density="compact" placeholder="PASSWORD" prepend-inner-icon="mdi-lock-outline" variant="outlined"
        @click:append-inner="visible = !visible"></v-text-field>

      <v-btn class="mb-8" color="blue" size="large" variant="tonal" block @click=login>
        Log In
      </v-btn>

      <v-card-text class="text-center">
        <a class="text-blue text-decoration-none" href="#" rel="noopener noreferrer" target="_blank">
          Sign up now <v-icon icon="mdi-chevron-right"></v-icon>
        </a>
      </v-card-text>
    </v-card>
  </div>
</template> -->

<style scoped>
  .form-container{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border: 2px solid black;
    border-radius: 10px;
    width: 500px;
    height: 500px;
  }
  .item-container{
    display: flex;
    flex-direction: column;
    width: 70%;
    gap: 10px;
  }
  .text-container{
    width: 100%;
    height: auto;
    border: 1px solid black;
    border-radius: 10px;
    font-size: 16px;
    padding-left: 10px;
    box-sizing: border-box;
    resize: none;
  }
  .button-container{
    width: 100%;
    border: 1px solid black;
    border-radius: 10px;
  }
</style>