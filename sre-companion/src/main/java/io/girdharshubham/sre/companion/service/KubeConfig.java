package io.girdharshubham.sre.companion.service;

import io.girdharshubham.sre.companion.configuration.KubernetesClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class KubeConfig {
    private static final Logger logger = LoggerFactory.getLogger(Pods.class);
    private final KubernetesClientConfiguration config;

    public KubeConfig(KubernetesClientConfiguration config) {
        this.config = config;
    }

    @Tool(description = "Get the current Kubernetes context")
    public String currentContext() {
        try {
            var currentContext = config.getConfig().getCurrentContext();
            return "Current Kubernetes context: " + currentContext;
        } catch (Exception e) {
            logger.error("Error fetching current context: {}", e.getMessage());
            return "Error fetching current context: " + e.getMessage();
        }
    }
}
