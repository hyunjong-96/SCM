import axios from 'axios';
// import { handleError } from 'vue';

const API_URL = process.env.VUE_APP_API_BASE_URL;

const instance = axios.create(
    {
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json'
    }
})

const get = async(url, params = {}) => {
    try{
        const response = await instance.get(url, {params});
        return response;
    }
    catch(error) {
        handleError(error);
    }
}

const post = async(url, data = {}) => {
    console.log('url : ',API_URL)
    console.log('direct url : ',process.env)
    try{
        const response = await instance.post(url, data);
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

export default api;