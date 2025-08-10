from transformers import pipeline

generator = pipeline("text-generation", model="gpt2")

result = generator(
    "Hello, I'm trying to learn how to use Hugging Face's Transformers library. Can you help me with that?"
)

print(result[0]['generated_text'])
