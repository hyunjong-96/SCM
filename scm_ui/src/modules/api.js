import axios from 'axios';
// import { handleError } from 'vue';

// const BASE_URL = "http://localhost:8080/api";

const instance = axios.create({
    // baseURL: BASE_URL,
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