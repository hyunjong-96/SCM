<template>
  <HelloWorld />
</template>

<script setup>
import { useCookies } from "vue3-cookies";
import { useRouter } from "vue-router"
import HelloWorld from '../components/HelloWorld.vue';

// vue3-cookies의 훅 사용
const { cookies } = useCookies();
const router = useRouter();

//cookie 가져오기
const token = cookies.get('scm-token')

console.log('test : ',cookies)
console.log('token : ',token)

//
if(token) {
  router.push("/dashboard")
}
else {
  router.push("/login")
}

</script>
