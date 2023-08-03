<template>
  <table class="info-table">
    <caption>Sample Data List</caption>
    <tr>
      <th>Fixed Info title</th>
      <td>{{ fixedInfo.title }}</td>
    </tr>
    <tr>
      <th>Fixed Info description</th>
      <td>{{ fixedInfo.description }}</td>
    </tr>
    <tr v-if="randomUsers">
      <th>User ID</th>
      <td>{{ randomUsers[0].id }}</td>
    </tr>
    <tr v-if="randomUsers">
      <th>Username</th>
      <td>{{ randomUsers[0].username }}</td>
    </tr>
    <!-- 他のAPIデータフィールドを表示したい場合に追加 -->
    <tr>
      <th>Store Text</th>
      <td>{{ storeData.text }}</td>
    </tr>
    <tr>
      <th>Store Count</th>
      <td>{{ storeData.count }}</td>
    </tr>
  </table>
</template>

<script setup lang="ts">
import { useSampleStore } from "~/store/sample";
import fixedInfo from "~/assets/fixed-info.js";
import { getRandomUsers } from "~/utils/apiClient";
import { RandomUsersRequest } from "types/RandomUserRequest";


// Piniaストアからデータを取得
const storeData = useSampleStore();

// APIからデータを取得
const request: RandomUsersRequest = {
  // リクエストのパラメータ
  size: 1,
};
const randomUsers = getRandomUsers(request);
</script>

<style scoped>
.info-table {
  border-collapse: collapse; /* セルのボーダーを隣接させる */
  width: 100%;
}

.info-table th,
.info-table td {
  border: 1px solid black; /* セルのボーダーを設定 */
  padding: 8px; /* 余白を追加 */
  text-align: left; /* テキストの配置を調整 */
}
</style>
