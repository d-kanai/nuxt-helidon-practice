import { vi, expect, test, beforeAll, describe, afterEach } from "vitest";
import { mount } from "@vue/test-utils";
import flushPromises from "flush-promises";
import waitForExpect from "wait-for-expect";

import { localize, setLocale } from "@vee-validate/i18n";
import en from "@vee-validate/i18n/dist/locale/en.json";
import ja from "@vee-validate/i18n/dist/locale/ja.json";
import AllRules from "@vee-validate/rules";
import { defineRule, configure } from "vee-validate";

import LoginForm from "../login-form.vue";

describe("LoginForm.vue", () => {
  beforeAll(() => {
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

  afterEach(() => {
    vi.useRealTimers();
  });

  test("renders without errors", () => {
    const wrapper = mount(LoginForm);
    expect(wrapper.exists()).toBeTruthy();
  });

  test("emailが空の時に正しいエラーメッセージが表示される", async () => {
    const wrapper = mount(LoginForm);

    // フィールド要素とボタン要素を取得
    const emailField = wrapper.find('input[name="email"]');

    await emailField.trigger("focus");
    await emailField.trigger("blur");

    await flushPromises();
    await waitForExpect(() => {
      expect(wrapper.text()).toContain("emailは必須項目です"); // emailのエラーメッセージが表示されるはず
    });
  });

  test("passwordが空の時に正しいエラーメッセージが表示される", async () => {
    const wrapper = mount(LoginForm);

    // フィールド要素とボタン要素を取得
    const passwordField = wrapper.find('input[name="password"]');

    await passwordField.trigger("focus");
    await passwordField.trigger("blur");

    await flushPromises();
    await waitForExpect(() => {
      expect(wrapper.text()).toContain("passwordは必須項目です"); // emailのエラーメッセージが表示されるはず
    });
  });
});
