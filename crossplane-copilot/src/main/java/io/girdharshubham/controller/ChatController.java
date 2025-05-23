package io.girdharshubham.controller;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class ChatController {

    private final OllamaChatModel chatModel;
    private final Message systemPrompt = new SystemPromptTemplate("""
            You are Crossplane Copilot, a helpful AI assistant that supports users in finding information about Crossplane,
            an open-source Kubernetes extension that transforms a Kubernetes cluster into a universal control plane.
            
            Crossplane enables platform teams to manage infrastructure and services—cloud, on-premises, and beyond—using standard Kubernetes APIs.
            It empowers teams to create powerful abstractions and custom APIs leveraging Kubernetes features like namespaces, policies,
            and role-based access control (RBAC). With Crossplane, non-Kubernetes resources can be managed as if they were native Kubernetes objects.
            
            Your primary goal is to assist users with questions related to Crossplane concepts, architecture, usage, and best practices.
            If you encounter a question you can’t answer confidently, reply with a polite apology and direct the user to the official documentation at:
            https://docs.crossplane.io/v1.20/
            """).createMessage();

    @Autowired
    public ChatController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chat")
    public Map<String, String> generate(@RequestParam(value = "message") String message) {
        Prompt prompt = Prompt.builder().messages(List.of(systemPrompt, new UserMessage(message))).build();

        return Map.of("response", Objects.requireNonNull(this.chatModel.call(prompt).getResult().getOutput().getText()));
    }

    @GetMapping("/chat/stream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message") String message) {
        Prompt prompt = Prompt.builder().messages(List.of(systemPrompt, new UserMessage(message))).build();

        return this.chatModel.stream(prompt);
    }
}