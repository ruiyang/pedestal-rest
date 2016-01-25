# pedestal-rest
A project build on clojure ecosystem. The aim is to compose different libraries to build a single page app backend template.

## To DO
- [x] Json request/response (with pedestal json-response)
- [x] Authentication (with buddy-auth)
- [ ] Authorization
- [x] DB access
- [x] Data migration
- [ ] Logging

## Getting Started

1. Start the application: `lein run-dev` \*
2. Go to [localhost:8080](http://localhost:8080/) to see: `Hello World!`
3. Endpoint without authentication
```shell
curl -X GET -H 'Content-Type: application/json" }' http://localhost:8080/about
returns:
{"message":"pedestal-rest: a single page app backend."}
```
4. Endpoint to login /login
```shell
curl -X POST -H 'Content-Type: application/json" }' -d '{"username": "admin", "password": "admin"}' http://localhost:8080/login
```
returns
```json
{"status":200,"body":{"token": <encryped jwe token>}}
```
5. Endpoint protected by authentication
```shell
curl -X GET -H 'Content-Type: application/json" }' -H 'Authorization: Token <token from step.4>' http://localhost:8080/user/1/info
```
returns
```json
{
    "id": 1,
    "first_name": "first3",
    "last_name": "last3",
    "email": "admin",
    "pass": "pass"
}
```
```shell
curl -X GET -H 'Content-Type: application/json" }' -H 'Authorization: Token <token from step.4>' http://localhost:8080/user/2/info
```
returns
```json
{
    "status": 401,
    "body": {
        "message": "Unauthorized resource access"
    }
}
```
