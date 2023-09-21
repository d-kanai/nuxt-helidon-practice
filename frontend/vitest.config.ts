import { defineConfig } from "vitest/config";
import Vue from "@vitejs/plugin-vue";
import AutoImport from "unplugin-auto-import/vite";
import path from "path";

export default defineConfig({
  plugins: [
    Vue(),
    AutoImport({
      imports: ["vue"],
    }),
  ],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./"),
      "~": path.resolve(__dirname, "./"),
      "#imports": path.resolve(__dirname, "./.nuxt/imports.d.ts"),
    },
  },
  test: {
    globals: true,
    environment: "jsdom",
  },
});
