# 1374 강의실
import sys
from collections import deque
input = sys.stdin.readline

class_num = int(input())

max_num = 0
dqlst = []
for _ in range(class_num):
    class_ind, class_s, class_f = map(int, input().split())
    if len(dqlst) == 0:
        fdeq = deque()
        fdeq.append(class_s)
        fdeq.append(class_f)
        dqlst.append(fdeq)
    for deq in dqlst:

    if class_f > max_num:
        max_num = class_f
