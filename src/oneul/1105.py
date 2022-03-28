# 1105번 - 팔
# 
# 입력
# 1. L R
# 
# 출력
# L<=x<=R인 x 중 8의 개수가 가장 적은 숫자의 8의 개수
# 
# 알고리즘 : 그리디(Greedy)
# 
# 시간 복잡도: worst O(10)

import sys
from collections import deque


si = sys.stdin.readline
l, r = si().rstrip().split()
l_dq = deque(list(l))
r_dq = deque(list(r))
eights = 0

# l의 모든 자리를 볼 때까지 반복
while l_dq:
    l_num = l_dq.popleft()
    r_num = r_dq.popleft()

    # l과 r이 다른 숫자가 나오면 종료
    # -> 높은 자리부터 보기 때문에 숫자가 달라진 순간
    #    그 자리부터는 8이 없을 수 있음.
    if l_num != r_num:
        break
    # l과 r의 숫자가 모두 8인 경우 8이 존재할 수 밖에 없으므로
    # 8의 개수를 증가
    elif r_num == '8':
        eights += 1

# 왼쪽 자리수가 오른쪽 자리수보다 작은 경우
# l r 사이값에 8이 없는 경우가 무조건 존재
if not l_dq and r_dq:
    eights = 0

print(eights)
