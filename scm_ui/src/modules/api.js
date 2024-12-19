import axios from 'axios';
import { useAlertStore } from '@/stores/alert';
import { useAuthStore } from '@/stores/auth';
import { useCookies } from 'vue3-cookies';

const API_URL = process.env.VUE_APP_API_BASE_URL;

const {cookies} = useCookies();

const instance = axios.create(
    {
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json'
    }
})

const get = async(url, params = {}) => {
    const data = {
        ...params, 
        headers: {
          'Content-Type' : 'application/json',
          'Authorization': 'Bearer ' + cookies.get('scm-token')
        } 
      }
    try{
        const response = await instance.get(url, data);
        return response;
    }
    catch(error) {
        handleError(error);
    }
}

const post = async(url, body, params = {}) => {
    var data = {
        ...params, 
        headers: {
          'Content-Type' : 'application/json',
          'Authorization': 'Bearer ' + cookies.get('scm-token')
        } 
      }

      console.log('post authorization : ',data.headers)

    console.log('url : ',API_URL)
    console.log('direct url : ',process.env)
    try{
        const response = await instance.post(url, body, data);
        return response;
    }catch(error) {
        handleError(error);
    }
}

const handleError = (error) => {
    console.log("API error : ", error.response || error.message);
    throw error;
}

const api = {
    get,
    post
}

instance.interceptors.response.use(
    response => {
        return response
    },
    error => {
        // if(error.response == undefined && error.code == "ERR_NETWORK") {

        // }

        const alertStore = useAlertStore();
        const authStore = useAuthStore();

        console.log('error status : ',error.response)

        if(error.response && error.response.status == '401') {
            console.log('401 test!!!')

            authStore.logout();
        }
        else {
            let title = "다른 에러";
            let message = "다른 에러 메세지";

            alertStore.showAlert(title, message);
        }
    }
)

export default api;