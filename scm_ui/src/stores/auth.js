import { defineStore } from 'pinia'
import { useCookies } from 'vue3-cookies'
import { useAlertStore } from '@/stores/alert'

export const useAuthStore = defineStore("auth", () => {

    const {cookies} = useCookies();
    const alertStore = useAlertStore();

    const logout = async() => {
        console.log('auth logout!')
        cookies.remove("scm-token");

        alertStore.showAlert("세션 만료", "다시 로그인 해주세요.", true);
    }

    return {logout}
})
