package vertxdemo.demo;

import io.vertx.core.Vertx;

public class HelloWorld {
    public static void main(String[] args) {
        //Vertx.vertx().createHttpServer().requestHandler( req -> req.response().end("Hello World!")).listen(8080);
        Vertx.vertx().createHttpServer().requestHandler( req -> req.response().end("Hello World!")).listen(8080);
    }
}
