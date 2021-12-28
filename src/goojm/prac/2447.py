import sys

i_num = int(sys.stdin.readline())


def print_star_1(num):
    for w in range(num):
        for h in range(num):
            print_star(num, h, w, h, w)


def print_star(num, h, w, rh, rw):
    if num/3 < (w+1) <= num/3*2 and num/3 < (h+1) <= num/3*2:
        return
    else:
        if num == 3:
            g[rw][rh] = "*"
        else:
            return print_star(num/3, h % (num/3), w % (num/3), rh, rw)


g = [[' ' for _ in range(i_num)] for _ in range(i_num)]
print_star_1(i_num)
for w in range(i_num):
    for h in range(i_num):
        print(g[h][w], end='')
    print()
