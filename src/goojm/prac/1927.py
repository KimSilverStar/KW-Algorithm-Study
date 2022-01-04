from queue import PriorityQueue
from sys import stdin

cal_range = int(stdin.readline())
p_queue = PriorityQueue(maxsize=cal_range)

for _ in range(cal_range):
    c_num = int(stdin.readline())
    if c_num == 0:
        if p_queue.empty() == True:
            print("0")
        else:
            print(p_queue.get())
    else:
        p_queue.put(c_num)
