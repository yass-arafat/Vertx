package com.yassir

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.codec.impl.BodyCodecImpl.json
import jdk.nashorn.internal.objects.annotations.Getter
import jdk.nashorn.internal.objects.annotations.Setter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ApiVerticle : AbstractVerticle() {
    companion object {

//        val log = loggerFor(javaClass)
    }


    override fun start(startFuture: Future<Void>?) {

        val mongoClient = MongoClient.createShared(
            vertx, JsonObject().put(
                "db_name",
                config().getString("db_name", "vertx")
            ).put(
                "connection_string",
                config().getString("connection_string", "mongodb://localhost:27017")
            )
        );

        val document = JsonObject()
            .put("username", "The Hobbit")
            .put("password", "123456")
            .put("email", "yassir@yopmail.com");

        mongoClient.save("users", document) { res ->
            if (res.succeeded()) {
                val id = res.result()
                println("Saved book with id $id")
            } else {
                res.cause().printStackTrace()
            }
        }

        val router = createRouter()
        val port = config().getInteger("http.port", 8080)

        vertx.createHttpServer()
            .requestHandler { router.accept(it) }
            .listen(port) { result ->
                if (result.succeeded()) {
                    System.out.println("Listening on port $port")
                    startFuture?.complete()
                } else {
                    startFuture?.fail(result.cause())
                }
            }
    }

    private fun createRouter() = Router.router(vertx).apply {
        get("/").handler(handlerRoot)
    }

    val handlerRoot = Handler<RoutingContext> { req ->
        req.response().end("Hello world!")
    }

    private fun showAllUsers() = Router.router(vertx).apply {
        get("/users").handler(getAllUsers)
    }

    val getAllUsers = Handler<RoutingContext> { req ->
        req.response().end("Hello world!")
    }
}