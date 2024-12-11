import { defineStore } from 'pinia'
import { useCookies } from 'vue3-cookies'
import { useAlertStore } from '@/stores/alert'

export const useAuthStore = defineStore("auth", () => {
    // actions: {
    //     async logout() {
    //         const { cookies } = useCookies();

    //         cookies.set("scm-token", null);

    //         useAlertStore();
    //     }
    // }
    const {cookies} = useCookies();
    const alertStore = useAlertStore();

    const logout = async() => {
        console.log('logout!')
        cookies.set("scm-token", null);

        alertStore.showAlert("세션 만료", "다시 로그인 해주세요.", "session");
    }

    return {logout}
})
