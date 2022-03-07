# 백준 1753번 - 최단경로(https://www.acmicpc.net/problem/1753)
#
# 입력
# 1. V E
# 2. K
# 3. u, v, e -> E개
#
# 출력
# 1. 모든 정점까지의 최단 경로값
#
# 사용 알고리즘 : 다익스트라 알고리즘
# 전체 시간 복잡도 : O((V + E)logV)
import sys, heapq


si = sys.stdin.readline
v, e = map(int, si().rstrip().split())
k = int(si().rstrip())

adj_dict = {}
for i in range(v+1):
    adj_dict[i] = []

for _ in range(e):
    s, e, w = map(int, si().rstrip().split())
    adj_dict[s].append((w, e))

INF = 200000
answer = [ INF for _ in range(v+1) ]
answer[k] = 0

heap = []
heapq.heappush(heap, (0, k))

while heap:
    # 시간 복잡도 : O(VlogV)
    # 모든 노드를 힙에서 꺼냄.
    cur_w, cur_v = heapq.heappop(heap)

    # 이미 최소값이 갱신되었으면 다시 볼 필요 없음
    if cur_w != answer[cur_v]: continue

    # 시간 복잡도 : O(E)
    # 연결된 노드들에서 갱신할 수 있는 최단 경로 찾기
    for w, v in adj_dict[cur_v]:
        cost = cur_w + w
        
        # 시간 복잡도 : O(logV)
        if cost < answer[v]:
            answer[v] = cost
            heapq.heappush(heap, (cost, v))

for i, cost in enumerate(answer):
    if not i: continue

    if cost == INF:
        print('INF')
    else:
        print(cost)
