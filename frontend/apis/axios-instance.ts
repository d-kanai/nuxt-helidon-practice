import axios from "axios";
import { useRuntimeConfig } from '#imports'

const runtimeConfig = useRuntimeConfig();
export const axiosInstance = axios.create({
  baseURL: runtimeConfig.public.apiBase as string,
});
