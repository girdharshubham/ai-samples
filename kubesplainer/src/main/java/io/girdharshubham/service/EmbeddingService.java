package io.girdharshubham.service;

import io.github.ollama4j.exceptions.OllamaBaseException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletionStage;

public interface EmbeddingService {
    CompletionStage<List<List<Double>>> getEmbedding(String text) throws IOException, OllamaBaseException, InterruptedException;
}
