```
/*
Project: kubesplainer - A Retrieval-Augmented Generation (RAG) application to explain Kubernetes YAML resources.

Goal:
Build a Java + Spring Boot app that allows users to send Kubernetes YAML files or inline text via REST API,
uses OpenAI's embedding endpoint to create vector representations of document chunks,
stores and retrieves vectors from a vector DB (e.g., Qdrant or Weaviate),
and finally calls the OpenAI Chat Completion API to generate explanations grounded in real Kubernetes context.

Architecture:
1. /api/explain accepts YAML input and user questions
2. Chunk the YAML into logical pieces
3. Call OpenAI's Embedding API (text-embedding-3-small)
4. Store vectors in a vector DB like Qdrant (via REST API)
5. Embed user query and perform vector search to retrieve relevant context
6. Compose prompt with query + retrieved context
7. Call OpenAI Chat Completion API (gpt-4-turbo)
8. Return explanation to user

Tech stack:
- Java 17+
- Spring Boot (Web, REST)
- Jackson (JSON)
- OkHttp or WebClient (for OpenAI + Vector DB calls)
- Lombok (for boilerplate)
- YAML parsing (SnakeYAML or similar)
- Maven or Gradle

Next step:
Start with a working /api/explain endpoint that:
- Accepts a POST request with raw YAML and a question
- Returns a placeholder response

You can scaffold out:
- controller/ExplainController.java
- service/EmbeddingService.java (to handle OpenAI requests)
- service/VectorDBService.java (to store/retrieve from vector DB)
- service/CompletionService.java (to call Chat Completion API)

Configure OpenAI API key and base URL in application.properties:
openai.api.key=sk-...
openai.api.url=https://api.openai.com/v1

This prompt should guide GitHub Copilot to complete methods, suggest OpenAI payloads,
handle chunking logic, and integrate YAML + vector search functionality.
*/

```