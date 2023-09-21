// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  runtimeConfig: {
    public: {
      apiBase: "http://localhost:8080",
    },
  },
  devtools: { enabled: true },
  modules: [
    // ...
    "@pinia/nuxt",
  ],
});
