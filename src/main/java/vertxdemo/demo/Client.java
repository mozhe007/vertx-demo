package vertxdemo.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import org.springframework.http.HttpRequest;

public class Client extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runExample(Client.class);
    }

    @Override
    public void start() throws Exception {
        vertx.createHttpClient().getNow(8080, "localhost", "/", resp -> {
            System.out.println("Got response " + resp.statusCode());
            resp.bodyHandler(body -> {
                System.out.println("Got data " + body.toString("ISO-8859-1"));
            });

            resp.bodyHandler(new Handler<Buffer>() {
                @Override
                public void handle(Buffer buffer) {
                    System.out.println("Got data " + buffer.toString("ISO-8859-1"));
                }
            });
        });


    }


}
