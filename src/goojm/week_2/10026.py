import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

prob_n = int(input().rstrip())

imap = []
rg_imap = []
rgbmap = []
rmap = []

for _ in range(prob_n):
    nline = list(input().rstrip())
    imap.append(nline)
    cn_line = []
    for value in nline:
        if value == "G":
            cn_line.append("R")
        else:
            cn_line.append(value)
    rg_imap.append(cn_line)
    zline = []
    zline_2 = []
    for _ in range(prob_n):
        zline.append(0)
        zline_2.append(0)
    rgbmap.append(zline)
    rmap.append(zline_2)


def flague(array, h, w, Boundary, tmap):
    if h-1 >= 0 and tmap[h][w] == tmap[h-1][w] and array[h-1][w] == 0:
        array[h-1][w] = array[h][w]
        flague(array, h-1, w, Boundary, tmap)
    if h+1 < Boundary and tmap[h][w] == tmap[h+1][w] and array[h+1][w] == 0:
        array[h+1][w] = array[h][w]
        flague(array, h+1, w, Boundary, tmap)
    if w-1 >= 0 and tmap[h][w] == tmap[h][w-1] and array[h][w-1] == 0:
        array[h][w-1] = array[h][w]
        flague(array, h, w-1, Boundary, tmap)
    if w+1 < Boundary and tmap[h][w] == tmap[h][w+1] and array[h][w+1] == 0:
        array[h][w+1] = array[h][w]
        flague(array, h, w+1, Boundary, tmap)
    # tx = [0, 0, -1, 1]
    # ty = [-1, 1, 0, 0]
    # array[h][w] = 0
    # target = tmap[h][w]
    # pq = [[h, w]]
    # while pq:
    #     bx, by = pq[0][0], pq[0][1]
    #     del pq[0]
    #     for i in range(4):
    #         x = bx+tx[i]
    #         y = by+ty[i]
    #         if 0 <= x < Boundary and 0 <= y < Boundary and tmap[bx][by] == target:
    #             array[x][y] = 1
    #             pq.append([x, y])


icount = 0
rcount = 0

for r in range(prob_n):
    for c in range(prob_n):
        if rgbmap[r][c] == 0:
            icount += 1
            rgbmap[r][c] = 1
            flague(rgbmap, r, c, prob_n, imap)
        if rmap[r][c] == 0:
            rcount += 1
            rmap[r][c] = 1
            flague(rmap, r, c, prob_n, rg_imap)

print(icount, rcount)
