# 基本となるイメージを選択。ここではOpenJDK 11を使用
FROM openjdk:11-jre-slim

# WireMockのバージョンを指定
ARG WIREMOCK_VERSION=2.27.2

# WireMockのスタンドアロンJARをダウンロード
ADD https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/${WIREMOCK_VERSION}/wiremock-standalone-${WIREMOCK_VERSION}.jar /var/wiremock/wiremock-standalone.jar

# ローカルのマッピングファイルをコンテナにコピー
COPY wiremock/3001/__files/ /var/wiremock/__files/
COPY wiremock/3001/mappings/ /var/wiremock/mappings/

# ポート8080を公開
EXPOSE 8080

# コンテナが起動したときにWireMockを起動
ENTRYPOINT ["java", "-jar", "/var/wiremock/wiremock-standalone.jar", "--port", "8080", "--root-dir", "/var/wiremock"]
