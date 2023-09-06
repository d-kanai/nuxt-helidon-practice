# Prepare
docker-compose is ready to use

# Usage
## create local environment
```
cd infra/local
docker-compose up --build -d
```
## stop local environment
```
docker-compose down
```

# URLs of services
## UI(Nuxt)
http://localhost:3000

## BFF
http://localhost:8080

## Redis
http://localhost:6379

## Core Api
http://localhost:8081

## Postgresql
http://localhost:5432

## External Api
http://localhost:3001

# URLs of management website
## Redis Commander
http://localhost:7379
## pgAdmin(PostgreSQL Tools)
http://localhost:5050  
username: admin@example.com  
password:admin
## Wiremock
http://localhost:3001/__admin/webapp