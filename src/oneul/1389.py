# 백준 1389번 - 케빈 베이컨의 6단계 법칙
#
# 입력
# 1. N M
# 2. M개의 친구 관계
#
# 출력
# 1. 케빈 베이컨 수가 가장 작은 사람의 번호
#
# 시간 복잡도 : O(V^3)
from sre_constants import IN
import sys


si = sys.stdin.readline
n, m = map(int, si().rstrip().split())

INF = 200
# 케빈 베이컨 게임을 했을 때 단계 수를 저장할 배열
friend = [ [ INF for _ in range(n+1) ] for _ in range(n+1) ]

# 자기 자신과의 단계 수는 0
for i in range(1, n+1): friend[i][i] = 0

# 친구와의 단계 수는 1
for i in range(m):
    f1, f2 = map(int, si().rstrip().split())
    friend[f1][f2] = 1
    friend[f2][f1] = 1

# friend 배열 채우기
# 시간 복잡도 : O(V^3)
for k in range(1, n+1):
    for s in range(1, n+1):
        for e in range(1, n+1):
            # 친구를 거치는 것과 직접 가는 것 중 더 작은 값 저장
            friend[s][e] = min(friend[s][e], friend[s][k] + friend[k][e])

# 케빈 베이컨 수 구하기
kevin_bacon_num = INF
min_kbn_person = 0
for i in range(1, n+1):
    if kevin_bacon_num > sum(friend[i])-INF:
        kevin_bacon_num = sum(friend[i])-INF
        min_kbn_person = i

print(min_kbn_person)
