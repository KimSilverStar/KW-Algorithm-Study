# idea from tellang
# https://github.com/KimSilverStar/KW-Algorithm-Study/blob/main/src/tellang/src/Week4/N1052/Main.java
import sys


# 입력 받기
si = sys.stdin.readline
n, k = map(int, si().rstrip().split())


buy = 0
bit_length = len(str(bin(n))[2:])

for i in range(bit_length-1, -1, -1):
    # bit 하나 구하기
    bit = (n >> i)

    # bit 가 1이면 물병 1개 존재
    if (bit & 1) == 1:
        k -= 1
    
    # 물병 k개를 다 채웠다면
    if not k:
        # 원래 비트랑 같으면 구매할 필요 없음.
        if bit << i == n:
            break
        
        # bit에 1을 더해서 원위치 시키면 물병을 최소로 구매 후
        # k개 이하로 물병을 남긴 것.
        # 여기에 n을 빼면 최소로 구매한 물병 개수가 됨.
        buy = (((bit + 1) << i) - n)
        break


print(buy)
