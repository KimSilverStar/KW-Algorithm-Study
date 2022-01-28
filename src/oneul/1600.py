# 6퍼 메모리초과
# 아래 반례는 해결
# 반례
# 1
# 5 3
# 0 0 0 0 0
# 0 0 0 0 1
# 0 0 0 1 0
# 정답 : 4
# 출력 : -1
import sys
from collections import deque

si = sys.stdin.readline
k = int(si().rstrip())
w, h = map(int, si().rstrip().split())
grid = [list(map(int, si().rstrip().split())) for _ in range(h)]


answer = [[-1 for _ in range(w)] for _ in range(h)]


def in_range(x, y):
    return 0 <= x and x < h and 0 <= y and y < w

def is_min_step(x, y, step):
    return answer[x][y] == -1 or answer[x][y] > step

def can_go(x, y, visited):
    # 범위를 벗어나지 않았고 지금 경로에서 방문하지 않았을 때
    return in_range(x, y) and (x, y) not in visited and grid[x][y] != 1

def bfs():
    global answer, k
    start_x, start_y, start_step = 0, 0, 0
    q = deque()
    visited = set()

    visited.add((start_x, start_y))
    q.append((start_x, start_y, start_step, k, set(visited)))
    answer[start_x][start_y] = 0

    dxs_monkey, dys_monkey = [ -1, 1, 0, 0 ], [ 0, 0, -1, 1 ]
    dxs_horse = [ -1, -2, -2, -1, 1, 2, 2, 1 ]
    dys_horse = [ -2, -1, 1, 2, 2, 1, -1, -2 ]

    while q:
        x, y, step, rem_k, curr_visited = q.popleft()

        for dx, dy in zip(dxs_monkey, dys_monkey):
            new_x, new_y, next_step = x + dx, y + dy, step + 1
            if can_go(new_x, new_y, curr_visited):
                curr_visited.add((new_x, new_y))
                q.append((new_x, new_y, next_step, rem_k, set(curr_visited)))
                curr_visited.remove((new_x, new_y))
                if is_min_step(new_x, new_y, next_step):
                    answer[new_x][new_y] = next_step
        
        for dx, dy in zip(dxs_horse, dys_horse):
            new_x, new_y, next_step = x + dx, y + dy, step + 1
            if can_go(new_x, new_y, curr_visited) and rem_k:
                curr_visited.add((new_x, new_y))
                q.append((new_x, new_y, next_step, rem_k - 1, set(curr_visited)))
                curr_visited.remove((new_x, new_y))
                if is_min_step(new_x, new_y, next_step):
                    answer[new_x][new_y] = next_step


bfs()
print(answer[h-1][w-1])
