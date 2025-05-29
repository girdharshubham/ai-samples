package io.girdharshubham.sre.companion;

import io.girdharshubham.sre.companion.service.Pods;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SreCompanionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SreCompanionApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider tools(Pods pods) {
        List<ToolCallback> tools = List.of(
                ToolCallbacks.from(pods)
        );
        return ToolCallbackProvider.from(tools);
    }

}
