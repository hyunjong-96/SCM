import { defineStore } from "pinia"
import { ref } from "vue";

export const useAlertStore = defineStore('alert', () => {

    const isVisibleSessionExpired = ref(false);
    const isVisibleNormalError = ref(false);
    const message = ref('');
    const titleMessage = ref('');

    const showAlert = (title, msg, flag) => {
        if (!flag) {
            isVisibleNormalError.value = true;
        } else {
            isVisibleSessionExpired.value = true;
        }
        titleMessage.value = title;
        message.value = msg;
    }

    const hideAlert = () => {
        isVisibleSessionExpired.value = false;
        isVisibleNormalError.value = false;
    }

    return { isVisibleSessionExpired, isVisibleNormalError, titleMessage, message, showAlert, hideAlert };
})