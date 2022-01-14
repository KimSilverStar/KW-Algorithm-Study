import sys
from collections import deque

input = sys.stdin.readline
set_length = input()
ns = input().split()
num_set = deque()
for set in range(int(set_length)):
    num_set.append(int(ns[set]))
iter_num = int(input())

max_count = 0
asc_dec = 0
i_num = 0
setlst = []
for c in range(iter_num):
    asc_count, dec_count = map(int, input().split())
    setlst.append((asc_count, dec_count))
    if max_count < asc_count:
        max_count = asc_count
        i_num = c
        asc_dec = 1
    if max_count < dec_count:
        max_count = dec_count
        asc_dec = -1
        i_num = c

if asc_dec == 1:
    templist = []
    for _ in range(max_count):
        templist.append(num_set.popleft())
    templist.sort()
    for _ in range(max_count):
        num_set.appendleft(templist.pop())
    templist = []
    for _ in range(setlst[i_num][1]):
        templist.append(num_set.popleft())
    templist.sort(reverse=True)
    for _ in range(setlst[i_num][1]):
        num_set.appendleft(templist.pop())
else:
    templist = []
    for _ in range(max_count):
        templist.append(num_set.popleft())
    templist.sort(reverse=True)
    for _ in range(max_count):
        num_set.appendleft(templist.pop())


for c in range(i_num+1, iter_num):
    templist = []
    for _ in range(setlst[c][0]):
        templist.append(num_set.popleft())
    templist.sort()
    for _ in range(setlst[c][0]):
        num_set.appendleft(templist.pop())
    templist = []
    for _ in range(setlst[c][1]):
        templist.append(num_set.popleft())
    templist.sort(reverse=True)
    for _ in range(setlst[c][1]):
        num_set.appendleft(templist.pop())


for i in num_set:
    print(i, end=" ")
print()
