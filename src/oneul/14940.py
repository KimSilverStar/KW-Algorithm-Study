import sys
from collections import deque
si = sys.stdin.readline

n, m = map(int, si().rstrip().split())
mmap = [list(map(int, si().rstrip().split())) for _ in range(n)]
answer = [[-1 for _ in range(m)] for _ in range(n)]

# answer에서 0인 부분 0으로 초기화
def answer_init():
    global answer
    for i in range(n):
        for j in range(m):
            if mmap[i][j] == 0:
                answer[i][j] = 0

# 목표지점(2인 점) 반환
def find_target():
    global mmap
    for i in range(n):
        for j in range(m):
            if mmap[i][j] == 2:
                return i, j

def in_range(x, y):
    global n, m
    return 0 <= x and x < n and 0 <= y and y < m

# 범위를 안 벗어나고 갈 수 있는 곳(mmap->1) 중 아직 안 간 곳(answer->-1)인지
def can_go(x, y):
    global mmap
    return in_range(x, y) and mmap[x][y] == 1 and answer[x][y] == -1

def bfs(start_x, start_y):
    global mmap, answer
    q = deque()
    dxs, dys = [-1, 1, 0, 0], [0, 0, -1, 1]

    answer[start_x][start_y] = 0
    q.append((start_x, start_y, 0))

    while q:
        x, y, step = q.popleft()

        for dx, dy in zip(dxs, dys):
            new_x, new_y = x + dx, y + dy
            if can_go(new_x, new_y):
                answer[new_x][new_y] = step + 1
                q.append((new_x, new_y, step + 1))


answer_init()
target_x, target_y = find_target()
bfs(target_x, target_y)
for i in range(n):
    for j in range(m):
        print(answer[i][j], end=' ')
    print()
