import { defineStore } from "pinia"
import { ref } from "vue";

export const useAlertStore = defineStore('alert', () => {

    const isVisibleSessionExpired = ref(false);
    const isVisibleNormalError = ref(false);
    const message = ref('');
    const titleMessage = ref('');

    const showAlert = (title, msg, flag) => {
        console.log('alert showAlert title :',title, ' msg : ',msg, ' flag : ',flag)
        if (!flag) {
            isVisibleNormalError.value = true;
        } else {
            isVisibleSessionExpired.value = true;
        }
        titleMessage.value = title;
        message.value = msg;

        console.log('isVisibleNormalError:', isVisibleNormalError.value);
        console.log('isVisibleSessionExpired:', isVisibleSessionExpired.value);
    }

    const hideAlert = () => {
        isVisibleSessionExpired.value = false;
        isVisibleNormalError.value = false;
    }

    return { isVisibleSessionExpired, isVisibleNormalError, titleMessage, message, showAlert, hideAlert };
})