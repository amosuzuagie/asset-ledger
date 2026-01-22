import axios from "axios";

export const http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
    }
});

export const setAuthToken = (token: string | null) => {
    if (token) {
        http.defaults.headers.common.Authorization = `Bearer ${token}`;
    } else {
        delete http.defaults.headers.common.Authorization;
    }
};