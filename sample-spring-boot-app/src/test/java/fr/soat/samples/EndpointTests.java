package fr.soat.samples;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author Patrick Allain - 3/6/19.
 */
@DisplayName("Endpoints")
class EndpointTests {

    @Nested
    @DisplayName("GET /")
    @AutoConfigureWebTestClient
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class RootEndpointTests {

        private final WebTestClient client;

        public RootEndpointTests(@Autowired WebTestClient client) {
            this.client = client;
        }

        @Test
        @DisplayName("Should return 'Hello World !' body with a 200 code")
        void shouldReturnHelloWorld() {
            client.get().uri("/")
                    .exchange()
                    .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                    .expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(String.class).isEqualTo("Hello World !");
        }
    }

    @Nested
    @DisplayName("GET /jenkinsfile")
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class JenkinsfileEndpointTests {

        private final WebTestClient client;

        public JenkinsfileEndpointTests(@Autowired WebTestClient client) {
            this.client = client;
        }

        @Test
        @DisplayName("Should return 'Hello Jenkinsfile !' body with a 200 code")
        void shouldReturnHelloWorld() {
            client.get().uri("/jenkinsfile")
                    .exchange()
                    .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                    .expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(String.class).isEqualTo("Hello Jenkinsfile !");
        }
    }

    @Nested
    @DisplayName("GET /user")
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class UserEndpointTests {

        private final WebTestClient client;

        public UserEndpointTests(@Autowired WebTestClient client) {
            this.client = client;
        }

        @Test
        @DisplayName("Should return a 400 when the query param 'name' is not provided")
        void shouldReturnBadRequestWhenNameIsMissing() {
            client.get().uri("/user")
                    .exchange()
                    .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                    .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                    .expectBody(String.class).isEqualTo("Parameter 'name' is missing");
        }

        @Test
        @DisplayName("Should return 'Hello World !' body with a 200 code")
        void shouldReturnHelloWorld() {
            client.get().uri("/user?name={name}", Maps.newHashMap("name", "Bob"))
                    .exchange()
                    .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                    .expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(String.class).isEqualTo("Hello Bob !");
        }

    }

}
