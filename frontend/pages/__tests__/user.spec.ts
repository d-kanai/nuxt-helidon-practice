import { expect, test, describe, beforeEach } from "vitest";
import { mount } from "@vue/test-utils";
import flushPromises from "flush-promises";
import waitForExpect from "wait-for-expect";
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

import UserPage from "../user.vue";

describe("user.vue", () => {
  let mock;
  beforeEach(() => {
    mock = new MockAdapter(axios);
  });
  test("renders without errors", () => {
    const wrapper = mount(UserPage);
    expect(wrapper.exists()).toBeTruthy();
  });

  test("Nameが空の時に正しいエラーメッセージが表示される", async () => {
    const wrapper = mount(UserPage);

    // フィールド要素とボタン要素を取得
    const emailField = wrapper.find('input[name="name"]');

    await emailField.trigger("focus");
    await emailField.trigger("blur");

    await flushPromises();
    await waitForExpect(() => {
      expect(wrapper.text()).toContain("Name is required"); // emailのエラーメッセージが表示されるはず
    });
  });

  test("passwordが空の時に正しいエラーメッセージが表示される", async () => {
    const wrapper = mount(UserPage);

    // フィールド要素とボタン要素を取得
    const passwordField = wrapper.find('input[name="age"]');

    await passwordField.trigger("focus");
    await passwordField.trigger("blur");

    await flushPromises();
    await waitForExpect(() => {
      expect(wrapper.text()).toContain("Age is required"); // emailのエラーメッセージが表示されるはず
    });
  });

  test("ユーザ登録できること", async () => {
    // Arrange
    mock.onPost('http://localhost:8080/api/v1/user').reply(200, {
      status: 'success'
    });

    // Act
    const wrapper = mount(UserPage);
    const nameField = wrapper.find('input[name="name"]');
    const ageField = wrapper.find('input[name="age"]');

    await nameField.setValue("jiadong.chen");
    await ageField.setValue(39);
    await wrapper.find("form").trigger("submit");

    // Wait for promises and axios calls to resolve
    await flushPromises();
    await waitForExpect(() => {
      // Expect axios POST to have been made with correct data
      expect(mock.history.post.length).toBe(1);
      expect(mock.history.post[0].url).toBe('http://localhost:8080/api/v1/user');
      expect(JSON.parse(mock.history.post[0].data)).toEqual({
        name: "jiadong.chen",
        age: 39
      });
    });
  });
});
