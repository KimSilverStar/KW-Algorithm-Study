'''
백준 20115번 - 에너지 드링크

입력
1. N (2<= N <= 10^5)
2. N개의 에너지 드링크 양 (1 이상 10^9 이하)

출력
1. 만들 수 있는 최대 에너지  드링크양

알고리즘 : 그리디(Greedy)
- 가장 적게 든 에너지 드링크를 가장 많이 든 에너지 드링크에 부음.
-> 흘리는 양을 줄이는 효과.

시간복잡도 : O(N)
'''
import sys
from collections import deque


si = sys.stdin.readline
n = int(si().rstrip())
drinks = list(map(int, si().rstrip().split()))
# 음료 양에 따른 정렬
# 시간복잡도 : O(NlogN)
drinks.sort()
drink_dq = deque(drinks)

# 음료양이 최대인 것에 따름.
base = drink_dq.pop()
# 음료가 적은 것부터 따름.
# 시간복잡도 : O(N)
while drink_dq:
    pour = drink_dq.popleft()
    base += pour / 2

print(base)
