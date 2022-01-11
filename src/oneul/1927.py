import heapq, sys

n = int(sys.stdin.readline().rstrip())

pq = []
for _ in range(n):
    op = int(sys.stdin.readline().rstrip())
    if op == 0:
        if len(pq) == 0:
            print(0)
        else:
            print(heapq.heappop(pq))
    else:
        heapq.heappush(pq, op)
