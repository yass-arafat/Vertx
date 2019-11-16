package com.yassir

import io.vertx.core.Vertx

fun main(args: Array<String>) {

    val vertx = Vertx.vertx()
    vertx.deployVerticle(ApiVerticle::class.java.name)
}
