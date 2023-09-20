import axios from "axios";

const runtimeConfig = useRuntimeConfig();
export const axiosInstance = axios.create({
  baseURL: runtimeConfig.public.apiBase as string,
});
