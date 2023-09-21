import axios from "axios";
import { useRuntimeConfig } from "#imports";

export class CustomAxios {
  static axiosInstance;
  static getInstance() {
    if (!CustomAxios.axiosInstance) {
      const runtimeConfig = useRuntimeConfig();
      CustomAxios.axiosInstance = axios.create({
        baseURL: runtimeConfig.public.apiBase as string,
      });
    }
    return CustomAxios.axiosInstance;
  }
}
