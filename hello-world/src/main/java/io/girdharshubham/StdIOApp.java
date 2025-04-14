package io.girdharshubham;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Hello world!
 */

@SpringBootApplication
public class StdIOApp {
    public static void main(String[] args) {
        SpringApplication.run(StdIOApp.class);
    }

    @Bean
    public List<ToolCallback> tools(MessageService messageService) {
        return List.of(
                ToolCallbacks.from(messageService)
        );
    }

    record Message(String message) {
    }

    @Service
    static class MessageService {
        @Tool(name = "get_message", description = "Hello World or custom message")
        public Message getMessage(String message) {
            if (message.isEmpty()) {
                return new Message("Hello World!");
            }
            return new Message(String.format("Message: %s", message));
        }
    }
}
