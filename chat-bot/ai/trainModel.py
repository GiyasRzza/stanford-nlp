import argparse

from transformers import GPTNeoForCausalLM, GPT2Tokenizer, Trainer, TrainingArguments
from datasets import Dataset

if __name__ == '__main__':
    parser = argparse.ArgumentParser()


    def tokenize_function(examples):
        return tokenizer(examples['text'], return_tensors="pt", padding=True, truncation=True)

model_name = "EleutherAI/gpt-neo-2.7B"
tokenizer = GPT2Tokenizer.from_pretrained("gpt2")

with open("E://djl-model//src//main//resources//model/train.txt", "r", encoding="utf-8") as file:
    texts = file.readlines()

train_dataset = Dataset.from_dict({"text": texts})

train_dataset = train_dataset.map(tokenize_function, batched=True)

model = GPTNeoForCausalLM.from_pretrained(model_name)

training_args = TrainingArguments(
    output_dir="./results",
    evaluation_strategy="epoch",
    learning_rate=5e-5,
    per_device_train_batch_size=1,
    num_train_epochs=3,
    weight_decay=0.01,
    save_steps=500,
    save_total_limit=1,
)

trainer = Trainer(
    model=model,
    args=training_args,
    train_dataset=train_dataset,
)

trainer.train()

model.save_pretrained("E://djl-model//src//main//resources//model")
tokenizer.save_pretrained("E://djl-model//src//main//resources//model")
