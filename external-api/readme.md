# 起動方法
./start.sh instance_name port  
例：  
```
./start.sh 3001 3001
```
instance_name `3001`のフォルダが作成される。そこにマッピングファイルを配置する。

# 停止方法
./stop.sh instance_name  
例：  
```
./stop.sh 3001
```

# Docker
```
podman build -t wiremock-container .
podman run -p 3001:8080 wiremock-container
```
確認  
```
curl -i -X GET \
 'http://127.0.0.1:3001/api/v1/privacy?email=john@example.com'
```
