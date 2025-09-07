package io.girdharshubham.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexingService {

    public Map<String, String> load(String root) throws IOException {
        Path path = Path.of(root);

        try (Stream<Path> stream = Files.walk(path)) {
            return stream
                    .filter(p -> p.endsWith(".md"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toMap(
                            Path::toString,
                            p -> {
                                try {
                                    return Files.readString(p);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    ));
        }
    }
}
