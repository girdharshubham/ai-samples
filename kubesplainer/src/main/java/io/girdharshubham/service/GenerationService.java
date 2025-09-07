package io.girdharshubham.service;

import java.util.concurrent.CompletionStage;

public interface GenerationService {
    CompletionStage<String> getGeneration(String prompt);
}
