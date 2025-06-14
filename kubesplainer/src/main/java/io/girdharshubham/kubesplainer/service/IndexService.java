package io.girdharshubham.kubesplainer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class IndexService {
    public Mono<String> indexUrl(String url) {
        return WebClient.create(url)
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }

}
