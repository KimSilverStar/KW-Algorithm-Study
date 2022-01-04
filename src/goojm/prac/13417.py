q_range = int(input())

for _ in range(q_range):
    w_length = int(input())
    word_lst = input().split(" ")
    output = []
    for word in word_lst:
        if len(output) == 0:
            output.append(word)
        elif ord(output[0]) >= ord(word):
            output.insert(0, word)
        else:
            output.append(word)
    for i in output:
        print(i, end="")
    print()
