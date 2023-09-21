// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  runtimeConfig: {
    public: {
      apiBase: "",
    },
  },
  devtools: { enabled: true },
  modules: [
    // ...
    "@pinia/nuxt",
  ],
});
