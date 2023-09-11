# Prepare
- docker-compose is ready to use
- Please set the CPU count of your container virtual machine to 4 or above, and the memory to 8 or above.
For example: `podman machine init --cpus 4 --memory 8096`
- you should create a new account on this site.https://container-registry.oracle.com/ and accept the oracle db enterprise licence.
And use that username and password to docker login container-registry.oracle.com, then you can pull the image of oracle db.

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

## Oracle DB
http://localhost:1521

## External Api
http://localhost:3001

# URLs of management website
## Redis Commander
http://localhost:7379

## Wiremock
http://localhost:3001/__admin/webapp