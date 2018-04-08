## Server module ##

curl -X GET http://127.0.0.1:19999/v1/products/1
curl -X GET http://127.0.0.1:19999/v1/products/all/1
curl -X POST http://127.0.0.1:19999/v1/products?title=Bla
curl -X PUT -H "Content-Type: application/json" -d '{"id":1,"title":"Bla"}' http://127.0.0.1:19999/v1/products/
curl -X DELETE http://127.0.0.1:19999/v1/products/1
