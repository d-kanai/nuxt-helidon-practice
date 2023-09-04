# prepare
docker-composeが使える状態

# create local environment
```
cd infra/local
docker-compose up --build -d
```
# stop local environment
```
docker-compose down
```

# 各種UI
## Nuxt画面
http://localhost:3000
## Redis管理画面
http://localhost:7379
## PostgreSQL管理画面
http://localhost:5050  
username: admin@example.com  
password:admin
## Wiremock管理画面
http://localhost:3001/__admin/webapp