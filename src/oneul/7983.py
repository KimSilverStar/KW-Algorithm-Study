'''
백준 7983번 - 내일 할거야

입력
1. 과제의 개수 n (1 <= n <= 10^6)
2. n개의 줄에 d일간 해야하고 t일까지 해야하는 과제 정보 d t (1 <= d, t <= 10^9)

출력
1. 내일(1일)부터 연속으로 최대 며칠 동안 놀 수 있는 일 수

알고리즘 : 그리디(Greedy)

시간복잡도 : O(nlogn)
'''
import sys, heapq


si = sys.stdin.readline
n = int(si().rstrip())

heap = []

# n개의 줄을 돌면서 heap에 넣음
# 시간복잡도 : O(nlogn)
for _ in range(n):
    d, t = map(int, si().rstrip().split())
    # 과제 마감 기한이 느린 순(t가 큰 순)으로 정렬
    heapq.heappush(heap, (-t, d, t))

# heap에서 하나 꺼내서 변수 초기화
_, d, t = heapq.heappop(heap)
longest_free = 0
free_days = 0
last_hw = t - d + 1

# heap이 빌 때 까지 꺼냄
# 시간복잡도 : O(nlogn)
while heap:
    _, d, t = heapq.heappop(heap)

    # 마지막으로 과제한 날보다 지금 꺼낸 마감일이 나중이면
    # 마지막 과제날보다 하루 전부터 세도록 함
    if last_hw <= t:
        t = last_hw - 1
    
    # 현재 과제를 하는 날을 계산해서
    # 마지막 과제한 날을 업데이트
    last_hw = t - d + 1

# 가장 최근에 과제한 날 하루 전부터
# 1일 까지가 내일부터 가장 오래 쉬는 기간
longest_free = last_hw - 1

print(longest_free)
