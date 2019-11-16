package com.yassir

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class ApiVerticle: AbstractVerticle(){
//    companion object {
//        val log = loggerFor(javaClass)
//    }

    override fun start(startFuture: Future<Void>?) {
        val router = createRouter()
        val port = config().getInteger("http.port", 8080)

        vertx.createHttpServer()
            .requestHandler { router.accept(it) }
            .listen(port, { result ->
                if (result.succeeded()) {
//                    log.info("Listening on port $port")
                    startFuture?.complete()
                } else {
                    startFuture?.fail(result.cause())
                }
            })
    }

    private fun createRouter() = Router.router(vertx).apply {
        get("/").handler(handlerRoot)
    }

    val handlerRoot = Handler<RoutingContext> { req ->
        req.response().end("Hello world!")
    }
}