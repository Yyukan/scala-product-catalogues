# Product Catalogues Service #

This is a resume portfolio project to demonstrate the case of building micro-services with Scala and Akka

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

The project is multi module where each module represents one layer of the service

* common - contains common shareable code
* persistence - code to persist/retrieve product catalog
* server - REST API
* core - main application logic and orchestration