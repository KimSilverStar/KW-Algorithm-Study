import sys
sys.setrecursionlimit(10**6)
si = sys.stdin.readline
r, c = map(int, si().rstrip().split())
grid = [list(si().rstrip()) for _ in range(r)]


know = set()
max_depth= 0

# def in_range(x, y):
#     return 0 <= x and x < r and 0 <= y and y < c

# def can_go(x, y):
#     global know
#     return in_range(x, y) and grid[x][y] not in know

def dfs(x, y, depth):
    global know, max_depth
    if max_depth < depth:
        max_depth = depth

    dxs, dys =[-1, 1, 0, 0], [0, 0, -1, 1]
    for i in range(4):
        new_x, new_y = x + dxs[i], y + dys[i]
        # if can_go(new_x, new_y):
        if 0 <= new_x and new_x < r and 0 <= new_y and new_y < c and grid[new_x][new_y] not in know:
            know.add(grid[new_x][new_y])
            dfs(new_x, new_y, depth + 1)
            know.remove(grid[new_x][new_y])

know.add(grid[0][0])
dfs(0, 0, 1)
print(max_depth)
