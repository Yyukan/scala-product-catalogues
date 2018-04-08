# Product Catalogues Service #

This is a resume portfolio project to demonstrate the case of building micro-services with Scala and Akka

[![Build Status](https://travis-ci.org/Yyukan/scala-product-catalogues.svg?branch=master)](https://travis-ci.org/Yyukan/scala-product-catalogues)

## Technologies are used ##

* [Scala](https://scala-lang.org)
* [Cats](https://github.com/typelevel/cats)
* [Akka Http](https://doc.akka.io/docs/akka-http/current/scala/http/)
* [Akka Streams](https://doc.akka.io/docs/akka/current/scala/stream/)
* [Swagger](https://swagger.io/)
* [Slick](http://slick.lightbend.com/)
* [Postgres DB](https://www.postgresql.org/)
* [SBT](http://www.scala-sbt.org/)

## Project structure ##

The project has multi module structure where each module represents one layer of the service

* common - contains common shareable code
* persistence - code to persist/retrieve product catalog
* server - REST API
* core - main application logic and orchestration

## Architecture ##

<!-- language: lang-none -->
    ------------         -----------------         ------ 
    | REST API | <-----> | Remote Actors | <-----> | DB |          
    ------------         -----------------         ------ 

## Swagger ##

`http://127.0.0.1:19999/swagger`

## How to run ##

First node (127.0.0.1:2551)

`sbt -Dpcs.server.host=127.0.0.1 -Dpcs.server.port=19999 "runMain pcs.Server" `

Second node 

`sbt -Dpcs.server.host=127.0.0.1 -Dpcs.server.port=20000 -DHOST=127.0.0.1 -DPORT=2552 "runMain pcs.Server"`