package io.girdharshubham.sre.companion.service;

import io.girdharshubham.sre.companion.configuration.KubernetesClientConfiguration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Pods {
    private static final Logger logger = LoggerFactory.getLogger(Pods.class);
    private final KubernetesClientConfiguration config;

    public Pods(KubernetesClientConfiguration config) {
        this.config = config;
    }

    @Tool(description = "Get all pods for a kubernetes context")
    public String getPodNames(String context, String namespace) {
        try {
            var client = config.getApiClient(Optional.ofNullable(context));
            var v1 = new CoreV1Api(client);
            var pods = v1.listNamespacedPod(namespace);

            return pods.execute()
                    .getItems()
                    .stream()
                    .map(x -> x.getMetadata().getName())
                    .collect(Collectors.joining(", "));
        } catch (Exception e) {
            logger.error("Error fetching pods: {}", e.getMessage());
            return "Error fetching pods: " + e.getMessage();
        }
    }
}

