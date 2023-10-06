// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  runtimeConfig: {
    public: {
      apiBase: process.env.API_BASE,
    },
  },
  devtools: { enabled: true },
  modules: [
    // ...
    "@pinia/nuxt",
  ],
});
