import axios, { AxiosResponse, AxiosError } from 'axios';

const instance = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 5000,
  headers: {
    "Content-Type": "application/json",
  },
});

interface ApiResponse<T> {
  data: T;
}

export const get = async <T>(url: string, params?: any): Promise<T> => {
  try {
    const response: AxiosResponse<ApiResponse<T>> = await instance.get(url, { params });
    return response.data.data;
  } catch (error) {
    // Handle error here (e.g., show a notification, log the error)
    throw (error as AxiosError).response?.data || error;
  }
};

export const post = async <T>(url: string, data?: any): Promise<T> => {
  try {
    const response: AxiosResponse<ApiResponse<T>> = await instance.post(url, { data });
    return response.data.data;
  } catch (error) {
    //Handle error here
    throw (error as AxiosError).response?.data || error;
  }
};