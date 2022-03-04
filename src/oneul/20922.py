# 백준 20922번 - 겹치는 건 싫어
import sys
from collections import deque


si = sys.stdin.readline
n, k = map(int, si().rstrip().split())
seq = list(map(int, si().rstrip().split()))

max_len = 0
# 투 포인터 선언
start = end = 0

pointer_dict = {}

while end < n:
    if seq[end] not in pointer_dict:
        pointer_dict[seq[end]] = deque([end])
        max_len = max(end - start + 1, max_len)
        end += 1
    else:
        if len(pointer_dict[seq[end]]) == k:
            pointer_dict[seq[start]].popleft()
            start += 1
            continue
        else:
            pointer_dict[seq[end]].append(end)
            max_len = max(end - start + 1, max_len)
            end += 1

print(max_len)
