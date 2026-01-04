# Rest

API_REST

API Connection and framework setup details for assignement
user_post url-https://petstore.swagger.io/v2/user

Request body

    curl -X 'POST' \

'https://petstore.swagger.io/v2/user' \
 -H 'accept: application/json' \
 -H 'Content-Type: application/json' \
 -d '{
"id": 0,
"username": "string",
"firstName": "string",
"lastName": "string",
"email": "string",
"password": "string",
"phone": "string",
"userStatus": 0
}'
Response body
Download
{
"code": 200,
"type": "unknown",
"message": "9223372036854743344"
}
Response headers
access-control-allow-headers: Content-Type,api_key,Authorization
access-control-allow-methods: GET,POST,DELETE,PUT
access-control-allow-origin: \*
content-type: application/json
date: Sat,03 Jan 2026 06:27:39 GMT
server: Jetty(9.2.9.v20150224)
Responses
