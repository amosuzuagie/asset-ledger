import axios from "axios";

export const http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
    }
});

http.interceptors.request.use((config) => {
    const stored = localStorage.getItem('auth');
    if (stored) {
        const { token } = JSON.parse(stored)
        config.headers.Authorization = `Bearer ${token}`
    }
    return config;
})

export const setAuthToken = (token: string | null) => {
    if (token) {
        http.defaults.headers.common.Authorization = `Bearer ${token}`;
    } else {
        delete http.defaults.headers.common.Authorization;
    }
};