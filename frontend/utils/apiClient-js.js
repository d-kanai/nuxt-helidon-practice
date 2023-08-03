export function getRandomUsersJs(request) {
  const response = useFetch(
    "https://random-data-api.com/api/users/random_user",
    {
      // リクエストのパラメータやヘッダーなど
      method: "GET",
      params: {
        size: request.size,
      },
    }
  );
  const randomUsers = response.data.value;
  return randomUsers;
}
