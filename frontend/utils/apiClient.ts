import { RandomUsersRequest } from "types/RandomUserRequest";
import { RandomUsersResponse } from "types/RandomUserResponse";


export function getRandomUsers(request: RandomUsersRequest) {
  const response = useFetch<RandomUsersResponse>('https://random-data-api.com/api/users/random_user', {
    // リクエストのパラメータやヘッダーなど
    method: "GET",
    params: {
      size: request.size
    }
  });
  return response.data;
}
