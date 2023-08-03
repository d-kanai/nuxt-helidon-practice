import { defineStore } from "pinia";

export const useSampleStore = defineStore({
  id: "sample",
  state: () => ({
    text: "Sample text from store",
    count: 0,
  }),
});
