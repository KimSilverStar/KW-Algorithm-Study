'''
백준 1082번 - 방 번호

입력
1. N (1 <= N <= 10)
2. P_i 리스트 (1 <= P_i <= 50)
3. M (1 <= M <= 50)

출력
1. 최대 M원을 써서 만들 수 있는 가장 큰 방 번호

알고리즘 : DP + 그리디(Greedy)

시간복잡도 : O((N^3)M)
'''
import sys


si = sys.stdin.readline
N = int(si().rstrip())
prices = list(map(int, si().rstrip().split()))
M = int(si().rstrip())

# dp[i][m] : i까지의 숫자를 사용해서 m원의 비용내에서 만들 수 있는 가장 큰 방 번호(문자열)
dp = [['' for _ in range(M+1)] for _ in range(N)]

# dp 배열을 작은 수 부터 채워나감 - Bottom-Up
# 시간복잡도 : 대략 O(N*M+1*N*N) = O((N^3)M) => 10^4
for i in range(N):
    for m in range(M+1):
        
        dp_candi = '-1'
        # 현재 사용할 수 있는 수들을 모두 확인
        for current in range(i, -1, -1):
            # 가격 제한보다 현재 숫자 값이 더 싸면
            # 현재 숫자를 사용할 수 있음.
            if prices[current] <= m:
                # 현재 숫자를 사용했을 때 얻을 수 있는 최대값을 max_에 저장
                max_ = '-1'
                # 현재 숫자 하나를 사용하기 전에 만들었던 가장 큰 방 번호 값의
                # 가장 앞 자리에 현재 숫자 하나를 결합
                for k in range(i+1):
                    candi = str(current) + dp[k][m-prices[current]]

                    if int(candi) >= int(max_):
                        max_ = candi
                # 현재 숫자를 사용하지 않고 다른 숫자를 사용했을 때
                # 전체 값이 더 커질 수도 있으므로(자리수가 많아져서)
                # 모든 숫자를 사용해보기 위해 dp_candi에 최대값을 저장해둠.
                if int(dp_candi) < int(max_):
                    dp_candi = str(max_)
            # 현재 숫자를 사용할 수 없으면 다음 숫자 확인
            else:
                continue
        
        # 모든 숫자를 다 사용해봤을 때 그 중 최대값이 dp_candi에 들어있음.
        # -> dp 업데이트.
        if dp_candi != '-1':
            dp[i][m] = dp_candi

# N-1까지의 숫자를 사용해서 M원의 비용내에서 만들 수 있는 가장 큰 방 번호
print(int(dp[N-1][M]))
