from collections import deque
import sys
input = sys.stdin.readline
d = deque()

iter_num = input()

for _ in range(int(iter_num)):
    command = input().split(" ")
    if len(command) == 2:
        if command[0] == "push_back":
            d.append(int(command[1]))
        elif command[0] == "push_front":
            d.appendleft(int(command[1]))
    else:
        if command[0] == "front\n":
            if len(d) == 0:
                print(-1)
            else:
                print(d[0])
        elif command[0] == "back\n":
            if len(d) == 0:
                print(-1)
            else:
                print(d[-1])
        elif command[0] == "size\n":
            print(len(d))
        elif command[0] == "empty\n":
            if len(d) == 0:
                print(1)
            else:
                print(0)
        elif command[0] == "pop_front\n":
            if len(d) == 0:
                print(-1)
            else:
                print(d[0])
                d.popleft()
        elif command[0] == "pop_back\n":
            if len(d) == 0:
                print(-1)
            else:
                print(d[-1])
                d.pop()
