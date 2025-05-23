package io.girdharshubham.controller;

import java.util.Map;

import org.apache.catalina.User;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final OllamaChatModel chatModel;

    @Autowired
    public ChatController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chat")
    public Map<String, String> generate(@RequestParam(value = "message") String message) {
        return Map.of("response", this.chatModel.call(message));
    }

    @GetMapping("/chat/stream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message") String message) {
        var um = UserMessage.builder().text(message).build();
        var prompt = Prompt.builder().messages(um).build();
        return this.chatModel.stream(prompt);
    }
}