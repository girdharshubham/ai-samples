package io.girdharshubham.kubesplainer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.girdharshubham.kubesplainer.service.IndexService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class IndexController {
    private final IndexService indexService;

    public IndexController(IndexService index) {
        this.indexService = index;

    }

    @PostMapping("/index")
    public Mono<String> indexUrl(@RequestBody String url) {
        return indexService.indexUrl(url)
                .onErrorReturn("Error indexing URL");
    }

}
