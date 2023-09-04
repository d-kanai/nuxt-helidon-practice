import { expect, test, describe, beforeEach } from "vitest";
import { mount } from "@vue/test-utils";
import flushPromises from "flush-promises";
import waitForExpect from "wait-for-expect";
import MockAdapter from "axios-mock-adapter";
import { axiosInstance } from "../../apis/axios-instance";
import UserPage from "../user.vue";

const axiosMock = new MockAdapter(axiosInstance);

describe("user.vue", () => {
  beforeEach(() => {
    axiosMock.reset();
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
    axiosMock.onPost("/api/v1/users").reply(200, {
      status: "success",
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
      expect(axiosMock.history.post.length).toBe(1);
      expect(axiosMock.history.post[0].url).toBe("/api/v1/users");
      expect(JSON.parse(axiosMock.history.post[0].data)).toEqual({
        name: "jiadong.chen",
        age: 39,
      });
    });
  });
});
