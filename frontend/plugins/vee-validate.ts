import { localize, setLocale } from "@vee-validate/i18n";
import en from "@vee-validate/i18n/dist/locale/en.json";
import ja from "@vee-validate/i18n/dist/locale/ja.json";
import AllRules from "@vee-validate/rules";
import { defineRule, configure } from "vee-validate";
import { defineNuxtPlugin } from "#app";

export default defineNuxtPlugin((_nuxtApp) => {
  configure({
    generateMessage: localize({
      en,
      ja,
    }),
  });

  Object.keys(AllRules).forEach((rule) => {
    defineRule(rule, AllRules[rule]); // 全ルールを使えるようにする
  });

  defineRule("minLength", (value, [limit]) => {
    // The field is empty so it should pass
    if (!value || !value.length) {
      return true;
    }
    if (value.length < limit) {
      return `${limit}文字以上にしてください`;
    }
    return true;
  });

  defineRule("maxLength", (value, [limit]) => {
    // The field is empty so it should pass
    if (!value || !value.length) {
      return true;
    }
    if (value.length > limit) {
      return `${limit}文字以下にしてください`;
    }
    return true;
  });

  setLocale("ja");
});
