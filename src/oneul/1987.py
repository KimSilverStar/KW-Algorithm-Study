import sys, time
from string import ascii_uppercase
si = sys.stdin.readline
r, c = map(int, si().rstrip().split())
grid = [list(si().rstrip()) for _ in range(r)]


visited = [[False for _ in range(c)] for _ in range(r)]
know = {}
for alpha in list(ascii_uppercase):
    know[alpha] = False
max_depth= 0

def in_range(x, y):
    global r, c
    return 0 <= x and x < r and 0 <= y and y < c

def can_go(x, y):
    global visited
    return in_range(x, y) and not know[grid[x][y]] and not visited[x][y]

def dfs(x, y, depth):
    global visited, know, max_depth
    visited[x][y] = True
    know[grid[x][y]] = True
    go_somewhere = False

    dxs, dys =[-1, 1, 0, 0], [0, 0, -1, 1]
    for dx, dy in zip(dxs, dys):
        new_x, new_y = x + dx, y + dy
        if can_go(new_x, new_y):
            go_somewhere = True
            dfs(new_x, new_y, depth + 1)
    
    know[grid[x][y]] = False
    visited[x][y] = False

    # 처음 왔던 곳인데 더이상 갈 곳이 없는 경우
    # (리프 노드인 경우?)
    if not go_somewhere and max_depth < depth:
        max_depth = depth

start = time.time()
dfs(0, 0, 1)
end = time.time()
print(f'{end-start:.5f}')
print(max_depth)
