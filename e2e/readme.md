# Separate folders for test cases by feature perspective
![](asset/readme.md_2023-09-06-11-42-31.png)

# Usage
**please install node 18 and docker-compose first**
```
npm ci
npm run test
```

# One-stop test cases
add-user.spec.ts

# Container startup is implemented using testcontainer
containers.ts

I use set gloabl-setup.ts and global-teardown.ts into 
playwright.config.ts to start and stop the container environment before and after playwright starts.

# Use TypeORM to interact with Database
DbDataSource.ts

# User WiremockRestClient to interact with ExternalApi
ExternalApiMock.ts