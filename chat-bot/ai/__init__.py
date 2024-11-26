from transformers import GPT2LMHeadModel, GPT2Tokenizer


def chat_with_bot():
    model = GPT2LMHeadModel.from_pretrained("gpt2", local_files_only=True)
    tokenizer = GPT2Tokenizer.from_pretrained("gpt2", local_files_only=True)

    while True:

        user_input = input("Sen: ")

        if user_input.lower() == "exit":
            print("Bot: I'm sorry, I'm afraid I can't do that.")
            break

        inputs = tokenizer.encode(user_input, return_tensors="pt")

        outputs = model.generate(
            inputs,
            max_length=100,
            num_return_sequences=1,
            no_repeat_ngram_size=2,
            top_p=0.92,
            temperature=0.8,
            do_sample=True
        )

        response = tokenizer.decode(outputs[0], skip_special_tokens=True)

        print(f"Bot: {response}")


if __name__ == '__main__':
    chat_with_bot()
