package io.girdharshubham.api;

import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.unmarshalling.Unmarshaller;
import io.girdharshubham.service.OllamaService;


public class RagEndpoint extends AllDirectives {
    final OllamaService os;

    public RagEndpoint(OllamaService os) {
        this.os = os;
    }

    private Route index() {
        return path("index", () -> post(() -> complete("Indexing")));
    }

    private Route chat() {
        return path("chat", () -> get(() -> entity(Unmarshaller.entityToString(), body -> {
                    try {
                        return completeOKWithFuture(os.getGeneration(body), Jackson.marshaller());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })

        ));
    }

    public Route api() {
        return concat(this.index(), this.chat());
    }

}
