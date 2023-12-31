import { CustomAxios } from "./axios-instance";

export default class User {

  /**
   * BFF の POST api/v1/user APIを呼び出すメソッド
   */
  async addUser(user: any): Promise<any> {
    const response = await CustomAxios.getInstance().post("/api/v1/users", user);
    return response.data;
  }
}