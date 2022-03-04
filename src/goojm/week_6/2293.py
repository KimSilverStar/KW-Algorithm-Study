import sys
input = sys.stdin.readline

c_coin, t_coin = map(int, input().split())
lst_coin = []

for _ in range(c_coin):
    lst_coin.append(int(input()))

dp = []
for i in range(t_coin+1):
    dp.append(0)
dp[0] = 1

for i in range(c_coin):
    for j in range(lst_coin[i], t_coin+1):
        dp[j] = dp[j] + dp[j-lst_coin[i]]

print(dp[-1])
