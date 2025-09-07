package io.girdharshubham;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import io.girdharshubham.api.RagEndpoint;
import io.girdharshubham.service.OllamaService;
import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import scala.concurrent.ExecutionContextExecutor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, OllamaBaseException, InterruptedException {
        final ActorSystem system = ActorSystem.create("kubesplainer");
        final ExecutionContextExecutor dispatcher = system.dispatcher();
        final Http http = Http.get(system);


        OllamaAPI oa = new OllamaAPI();
        OllamaService os = new OllamaService(oa);

        http.newServerAt("localhost", 8080).bind(new RagEndpoint(os).api());
    }
}