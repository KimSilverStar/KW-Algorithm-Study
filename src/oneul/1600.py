# 1600번: 트리 (https://www.acmicpc.net/problem/1600)
# 
# 입력
# 1. 말 처럼 움직일 수 있는 횟수 k (0 <= k <= 30)
# 2. 가로길이 w, 세로길이 h (1 <= w,h <= 200)
# 3. 격자판 정보
# 
# 출력
# 도착점까지 가는 동작수의 최솟값
import sys
from collections import deque

si = sys.stdin.readline
k = int(si().rstrip())
w, h = map(int, si().rstrip().split())
grid = [list(map(int, si().rstrip().split())) for _ in range(h)]


answer = [[[-1 for _ in range(k + 1)] for _ in range(w)] for _ in range(h)]


def in_range(x, y):
    return 0 <= x and x < h and 0 <= y and y < w

def can_go(x, y, use_k):
    # 범위를 벗어나지 않았고 k가 같을 때 방문하지 않았다면(최소로 온 것이라면)
    return in_range(x, y) and answer[x][y][use_k] == -1 and grid[x][y] != 1

# 시작점에서 갈 수 있는 곳까지 최대한 감.
# 시간복잡도 : O(WH)
def bfs():
    global answer, k
    start_x, start_y, start_step, use_k = 0, 0, 0, 0
    q = deque()

    q.append((start_x, start_y, start_step, use_k))
    answer[start_x][start_y][use_k] = 0

    dxs_monkey, dys_monkey = [ -1, 1, 0, 0 ], [ 0, 0, -1, 1 ]
    dxs_horse = [ -1, -2, -2, -1, 1, 2, 2, 1 ]
    dys_horse = [ -2, -1, 1, 2, 2, 1, -1, -2 ]

    while q:
        x, y, step, use_k = q.popleft()

        # 원숭이의 이동 방법으로 이동
        # 시간복잡도 : O(WH)
        for dx, dy in zip(dxs_monkey, dys_monkey):
            new_x, new_y, next_step = x + dx, y + dy, step + 1
            if can_go(new_x, new_y, use_k):
                q.append((new_x, new_y, next_step, use_k))
                answer[new_x][new_y][use_k] = next_step
        
        # 말의 이동 방법으로 k번 이동
        # 시간복잡도 : O(WH)
        for dx, dy in zip(dxs_horse, dys_horse):
            new_x, new_y, next_step = x + dx, y + dy, step + 1
            if (k - use_k) and can_go(new_x, new_y, use_k + 1):
                q.append((new_x, new_y, next_step, use_k + 1))
                answer[new_x][new_y][use_k + 1] = next_step


bfs()
min_step = 40001
# 모든 경로 중 최소값을 출력
# 시간복잡도 : O(K)
for i in range(k + 1):
    if answer[h-1][w-1][i] != -1 and min_step > answer[h-1][w-1][i]:
        min_step = answer[h-1][w-1][i]
if min_step == 40001:
    min_step = -1
print(min_step)
