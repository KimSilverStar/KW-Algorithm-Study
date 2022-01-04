import sys
input = sys.stdin.readline

c_range = int(input())
m_stack = []

for _ in range(c_range):
    command = input().split()
    if command[0] == 'push':
        m_stack.append(int(command[1]))
    elif command[0] == 'top':
        if len(m_stack) == 0:
            print("-1")
        else:
            print(m_stack[-1])
    elif command[0] == 'size':
        print(len(m_stack))
    elif command[0] == 'empty':
        if len(m_stack) == 0:
            print("1")
        else:
            print("0")
    elif command[0] == 'pop':
        if len(m_stack) == 0:
            print("-1")
        else:
            print(m_stack.pop())
