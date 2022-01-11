n, k = map(int, input().split())


table = [i+1 for i in range(n)]
josephus_perm = []

cur_idx = -1

while table:
    cur_idx += k

    if cur_idx >= len(table):
        cur_idx %= len(table)

    josephus_perm.append(table.pop(cur_idx))
    cur_idx -= 1


print('<', end='')
while len(josephus_perm) != 1:
    print(f'{josephus_perm.pop(0)}, ', end='')
print(f'{josephus_perm.pop(0)}>')
