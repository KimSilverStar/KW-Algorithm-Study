import sys

input = sys.stdin.readline
i_num, t_num = map(int, input().split())
sspend = []


def bfs(i, t, count, ar):
    if i == t:
        ar.append(count)
    elif i > t:
        return
    else:
        bfs(i*2, t, count+1, ar)
        bfs(i*10+1, t, count+1, ar)


bfs(i_num, t_num, 1, sspend)
if len(sspend) == 0:
    print(-1)
else:
    sspend.sort(reverse=True)
    print(sspend[0])
