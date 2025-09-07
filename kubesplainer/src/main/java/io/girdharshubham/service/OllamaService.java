package io.girdharshubham.service;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.embeddings.OllamaEmbedRequestModel;
import io.github.ollama4j.models.embeddings.OllamaEmbedResponseModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class OllamaService implements GenerationService, EmbeddingService {
    private final OllamaAPI ollama;
    private final String embeddingModel = "nomic-embed-text:v1.5";
    private final String generationModel = "gemma3:270m";

    public OllamaService(OllamaAPI ollama) {
        this.ollama = ollama;
    }

    @Override
    public CompletionStage<String> getGeneration(String prompt) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ollama.generate(this.generationModel, prompt, null).getResponse();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletionStage<List<List<Double>>> getEmbedding(String text) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                OllamaEmbedRequestModel request = new OllamaEmbedRequestModel(this.embeddingModel, List.of(text));
                OllamaEmbedResponseModel response = ollama.embed(request);

                return response.getEmbeddings();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
