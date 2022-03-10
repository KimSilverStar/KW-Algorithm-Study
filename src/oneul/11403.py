'''
백준 11403번 - 경로 찾기

입력
1. 정점의 개수 N (1 <= N <= 100)
2. N개의 줄에 그래프의 인접행렬

출력
1. N개의 줄에 정점 i에서 정점 j로 가는 경로가 있으면 1, 아니면 0 출력

알고리즘
- 플로이드와 유사한 방식 사용
- 최솟값을 구하는 대신 s->k 경로와 k->e 경로가 있으면 s->e 경로가
  있는 것으로 인접행렬 갱신

시간복잡도
- O(V^3)
'''
import sys


si = sys.stdin.readline
n = int(si().rstrip())

# 그래프 인접 행렬 채우기
adj_arr = []
for _ in range(n):
    adj_arr.append(list(map(int, si().rstrip().split())))

# 플로이드 알고리즘? 적용
# 시간복잡도 : O(N^3) == O(V^3)
for k in range(n):
    for s in range(n):
        for e in range(n):
            if adj_arr[s][k] + adj_arr[k][e] == 2:
                adj_arr[s][e] = 1

# 결과 출력
for i in range(n):
    for j in range(n):
        if adj_arr[i][j]:
            print(1, end=' ')
        else:
            print(0, end=' ')
    print()
