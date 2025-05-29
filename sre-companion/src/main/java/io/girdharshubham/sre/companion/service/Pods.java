package io.girdharshubham.sre.companion.service;

import io.girdharshubham.sre.companion.configuration.KubernetesClientConfiguration;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class Pods {
    private final KubernetesClientConfiguration configuration;

    public Pods(KubernetesClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Tool(description = "Get the list of pods in the kubernetes cluster")
    public String getPods(
            @ToolParam(description = "The Kubernetes context to use") String context,
            @ToolParam(description = "The Kubernetes namespace to use") String namespace,
            @ToolParam(description = "The label selector to filter pods by") String labelSelector
    ) throws IOException, ApiException {
        ApiClient client = configuration.getApiClient(Optional.ofNullable(context));
        CoreV1Api v1 = new CoreV1Api(client);

        return v1
                .listNamespacedPod(namespace)
                .labelSelector(labelSelector)
                .execute()
                .getItems()
                .stream()
                .map(pod -> Objects.requireNonNull(pod.getMetadata()).getName())
                .reduce((acc, name) -> acc + ", " + name)
                .orElse("No pods found");
    }
}
