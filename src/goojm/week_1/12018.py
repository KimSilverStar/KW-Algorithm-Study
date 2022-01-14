from queue import PriorityQueue
import heapq
import sys

input = sys.stdin.readline

c_num, my_point = map(int, input().split())

point_pq = []
for _ in range(c_num):
    reg_num, maximum_num = map(int, input().split())
    point_lst = list(map(int, input().split()))
    heapq.heapify(point_lst)
    if reg_num < maximum_num:
        heapq.heappush(point_pq, 1)
    else:
        target = 1
        for _ in range(reg_num-maximum_num+1):
            target = heapq.heappop(point_lst)
        heapq.heappush(point_pq, target)


c_count = 0
while point_pq:
    my_point -= heapq.heappop(point_pq)
    if my_point >= 0:
        c_count += 1
    else:
        break
print(c_count)
