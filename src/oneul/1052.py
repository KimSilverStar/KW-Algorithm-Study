# 예제 다 되지만 시간초과
import sys, heapq


# 입력 받기
si = sys.stdin.readline
n, k = map(int, si().rstrip().split())


# priority q 생성
pq = [1 for _ in range(n)]
buy = 0
temp = []

# pq가 비지 않은 동안 반복
while pq:
    left_hand = heapq.heappop(pq)
    right_hand = 0

    if pq: # pq가 비지 않았을 때
        right_hand = heapq.heappop(pq)

    if left_hand == right_hand:
        heapq.heappush(pq, left_hand*2)
    else:
        temp.append(left_hand)
        if right_hand:
            heapq.heappush(pq, right_hand)
    
    if not pq: # pq가 비었을 때(최대 값까지 확인한 경우)
        if len(temp) > k: # 아직 옮길 수 없는 경우
            # 병 1개 구매
            buy += 1
            temp.append(1)

            # pq, temp 초기화
            heapq.heapify(temp)
            pq = temp
            temp = []
        else: # 옮길 수 있는 경우
            break

print(buy)
