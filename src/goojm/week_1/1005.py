# 1005 ACM Craft
import sys
from collections import deque

input = sys.stdin.readline
prob_num = int(input())


for _ in range(prob_num):
    build_num, rule_num = map(int, input().split())
    btime_lst = [0]+list(map(int, input().split()))

    rules_lst = [[] for _ in range(build_num+1)]
    brank_lst = [0 for _ in range(build_num+1)]
    Target_DP = [0 for _ in range(build_num+1)]

    update_dq = deque()

    for _ in range(rule_num):
        f_num, b_num = map(int, input().split())
        rules_lst[f_num].append(b_num)
        brank_lst[b_num] += 1

    for rank_num in range(1, build_num+1):
        if brank_lst[rank_num] == 0:
            update_dq.append(rank_num)
            Target_DP[rank_num] = btime_lst[rank_num]

    while update_dq:
        update_num = update_dq.popleft()
        for r_num in rules_lst[update_num]:
            brank_lst[r_num] -= 1
            Target_DP[r_num] = max(
                btime_lst[r_num]+Target_DP[update_num], Target_DP[r_num])
            if brank_lst[r_num] == 0:
                update_dq.append(r_num)

    target_bnum = int(input())
    print(Target_DP[target_bnum])
