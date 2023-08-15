import { mount } from "@vue/test-utils";
import LoginForm from "../login-form.vue"; // パスは適切に修正してください

describe("LoginForm.vue", () => {
  it("renders without errors", () => {
    const wrapper = mount(LoginForm);
    expect(wrapper.exists()).toBeTruthy();
  });

  it("displays submitting text when form is submitting", async () => {
    const wrapper = mount(LoginForm);
    const button = wrapper.find("button");

    // ボタンをクリックしてsubmit処理を実行
    await button.trigger("click");

    // submitting状態のテキストが表示されているか確認
    expect(button.text()).toBe("is submitting...");

    // 2秒後の状態を確認するための待機
    await new Promise((resolve) => setTimeout(resolve, 2100));

    // submitting状態が終了した後のテキストを確認
    expect(button.text()).toBe("Submit");
  });
});
