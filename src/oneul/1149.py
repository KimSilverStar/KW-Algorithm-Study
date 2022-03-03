# 백준 1149번 - RGB거리
import sys

sys.setrecursionlimit(10**6)
si = sys.stdin.readline
n = int(si().rstrip())
costs = []
for _ in range(n):
    costs.append(list(map(int, si().rstrip().split())))

# dp[i][j] : i번째 까지의 집을 칠 할 때 최소 비용. i번째 집은 j 색으로 칠함.
dp = [[2000, 2000, 2000] for _ in range(n)]

# dp 초기값 설정
for i in range(3):
    dp[0][i] = costs[0][i]


# house 까지의 집을 칠하는 최소 비용을 반환하는 함수. 
# house는 color로 칠함.
# dp[house][color]를 채움.
def paint_house(house, color):
    if house == 0:
        return dp[0][color]

    # 계산하지 않은 경우 계산
    if dp[house][color] == 2000:
        candi_costs = []
        for i in range(3):
            if i == color: continue
            candi_costs.append(paint_house(house-1, i))
        dp[house][color] = min(candi_costs) + costs[house][color]
    
    return dp[house][color]

for i in range(3):
    paint_house(n-1, i)

print(min(dp[n-1][0], dp[n-1][1], dp[n-1][2]))
