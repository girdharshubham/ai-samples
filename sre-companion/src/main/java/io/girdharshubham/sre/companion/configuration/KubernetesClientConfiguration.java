package io.girdharshubham.sre.companion.configuration;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class KubernetesClientConfiguration {
    private final KubeConfig config;

    public KubernetesClientConfiguration() throws FileNotFoundException {
        String kubeConfigPath = System.getProperty("user.home") + "/.kube/config";
        config = KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath));
    }

    public ApiClient getApiClient(Optional<String> context) throws IOException {
        context.ifPresentOrElse(config::setContext, () -> config.setContext("default"));
        return ClientBuilder.kubeconfig(config).build();
    }

    public KubeConfig getConfig() {
        return config;
    }
}
