import { expect, test, describe } from "vitest";
import { mount } from "@vue/test-utils";
import flushPromises from "flush-promises";
import waitForExpect from "wait-for-expect";

import LoginForm from "../login-form.vue";

describe("LoginForm.vue", () => {
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
      expect(wrapper.text()).toContain("Email is required"); // emailのエラーメッセージが表示されるはず
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
      expect(wrapper.text()).toContain("Password is required"); // emailのエラーメッセージが表示されるはず
    });
  });
});
