package fr.soat.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> router() {
        return RouterFunctions.route()
                .GET("/", (r) -> ok().syncBody(("Hello World !")))
                .GET("/jenkinsfile", (r) -> ok().syncBody(("Hello Jenkinsfile !")))
                .GET("/user", (r) -> Mono.justOrEmpty(r.queryParam("name"))
                        .map(name -> String.format("Hello %s !", name))
                        .map(BodyInserters::fromObject)
                        .flatMap(i -> ok().body(i))
                        .switchIfEmpty(badRequest().syncBody("Parameter 'name' is missing"))
                )
                .build();
    }

}
