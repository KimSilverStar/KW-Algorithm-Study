import sys
from collections import deque

si = sys.stdin.readline
n = int(si().rstrip())
area = [list(map(int, si().rstrip().split())) for _ in range(n)]


def can_go(x, y, rainfall, visited):
    return 0 <= x and x < n and 0 <= y and y < n and not visited[x][y] and area[x][y] > rainfall


dxs, dys = [-1, 1, 0, 0], [0, 0, -1, 1]
max_safe_zone = 0

max_height = max(map(max, area))
# 강수량 0 부터 max_height 까지 모두 확인
for rainfall in range(max_height):
    q = deque()
    visited = [[False for _ in range(n)] for _ in range(n)]

    safe_zone = 0
    # (방문하지 않은 )모든 칸에 대해
    for i in range(n):
        for j in range(n):
            # safe_zone이 있다면 safe_zone + 1
            if can_go(i, j, rainfall, visited):
                safe_zone += 1
                
                # 연결된 safe_zone을 표시하기 위한 BFS
                q.append((i, j))
                visited[i][j] = True
                
                while q:
                    x, y = q.popleft()

                    for dx, dy in zip(dxs, dys):
                        new_x, new_y = x + dx, y + dy
                        if can_go(new_x, new_y, rainfall, visited):
                            q.append((new_x, new_y))
                            visited[new_x][new_y] = True
                
    if safe_zone > max_safe_zone:
        max_safe_zone = safe_zone

print(max_safe_zone)
