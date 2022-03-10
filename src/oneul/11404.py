'''
백준 11404 - 플로이드

입력
1. n
2. m
3. m개의 줄에 출발도시, 도착도시, 비용

출력
1. n개의 줄에 i 도시에서 j 도시로 가는 최소 비용

알고리즘
- 모든 도시에서 모든 도시로 가는 비용을 구해야 하므로
  플로이드 알고리즘 적용

시간복잡도
- O(V^3)
'''
import sys


si = sys.stdin.readline
n = int(si().rstrip())
m = int(si().rstrip())

# 최소 비용 배열 
INF = sys.maxsize
min_costs = [ [ INF for _ in range(n+1) ] for _ in range(n+1) ]

for _ in range(m):
    s, e, c = map(int, si().rstrip().split())
    # s에서 e로 가는 노선은 하나가 아닐 수 있음
    min_costs[s][e] = min(c, min_costs[s][e])

# 자기 자신으로 가는 비용은 0
for i in range(n+1):
    min_costs[i][i] = 0

# 플로이드 알고리즘 적용
# 시간복잡도 : O(n^3) == O(V^3)
for k in range(1, n+1):
    for s in range(1, n+1):
        for e in range(1, n+1):
            min_costs[s][e] = min(min_costs[s][e], min_costs[s][k] + min_costs[k][e])

# 결과 출력
for i in range(1, n+1):
    for j in range(1, n+1):
        if min_costs[i][j] == INF:
            print(0, end=' ')
        else:
            print(min_costs[i][j], end=' ')
    print()
