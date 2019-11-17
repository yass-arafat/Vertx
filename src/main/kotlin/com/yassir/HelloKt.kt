package com.yassir

import io.netty.util.internal.logging.InternalLoggerFactory
import io.netty.util.internal.logging.Slf4JLoggerFactory
import io.vertx.core.Vertx
import io.vertx.core.logging.LoggerFactory
import io.vertx.core.logging.SLF4JLogDelegateFactory
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.mongo.impl.MongoClientImpl


fun main(args: Array<String>) {
//    setupLogger()
    val vertx = Vertx.vertx()
    vertx.deployVerticle(ApiVerticle::class.java.name)
}


private fun setupLogger() = InternalLoggerFactory.setDefaultFactory(Slf4JLoggerFactory.INSTANCE)
    .also {
        System.setProperty(
            LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME,
            SLF4JLogDelegateFactory::class.qualifiedName
        )
    }
