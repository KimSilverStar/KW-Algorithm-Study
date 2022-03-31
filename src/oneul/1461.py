'''
백준 1461번 - 도서관

입력
1. N M (1 <= N, M <= 50)
2. N개의 책 위치 (절대값이 10,000보다 작거나 같은 정수)

출력
1. 최소 걸음 수

알고리즘 : 그리디(Greedy)
1) 0 기준 왼쪽과 오른쪽 중 거리가 짧은 곳 부터 가져다 놓음.
-> 거리가 가장 먼 곳까지 갔다가 돌아오는 경우의 수를 없앨 수 있음.
2) 한 번에 갖다 놓을 수 있는 최대한 먼 거리로 갖다 놓음.
-> 돌아오는 경로가 길어지는 경우의 수를 없앨 수 있음.

시간복잡도 : O(N)
'''
import sys


si = sys.stdin.readline
n, m = map(int, si().rstrip().split())
books = list(map(int, si().rstrip().split()))
# 좌표로 표현하기 위해 정렬
# 시간복잡도 : O(N)
books.sort()

zero_idx = walk = 0
# 0이 있는 위치 찾기
# 책을 가져와야 하므로 0이 있는 위치를 기준으로 움직여야 함.
while zero_idx < n and books[zero_idx] < 0:
    zero_idx += 1

# 책이 1권일 경우 1번만에 제자리에 둘 수 있음.
if len(books) == 1:
    print(1)
    exit()

# 왼쪽의 최대 거리가 더 가까운 경우 왼쪽부터 감.
if abs(books[0]) < abs(books[-1]):
    # 0번으로 돌아오는 거리를 줄이기 위해 가장 먼 곳부터 갖다 놓음
    current = 0
    # 책이 있는 0번을 기준으로 왼쪽은 음수
    while current < zero_idx:
        walk += (books[current] * -1 * 2)
        # 최대한 0번에 가까이 감(가장 많게 가져와서 가장 많이 갖다놓음)
        current += m
# 오른쪽의 최대 거리가 더 가까운 경우 오른쪽부터 감.
else:
    current = n-1
    # 책이 있는 0번을 기준으로 오른쪽은 양수
    while current >= zero_idx:
        walk += (books[current] * 2)
        current -= m

# 반대쪽 마저 갖다놓기
if abs(books[0]) < abs(books[-1]):
    current = n-1
    
    # 가장 먼 곳은 두고 돌아올 필요가 없음.(2배 x)
    walk += abs(books[current])
    current -= m
    
    while current >= zero_idx:
        walk += (abs(books[current]) * 2)
        current -= m
else:
    current = 0

    walk += abs(books[current])
    current += m

    while current < zero_idx:
        walk += (abs(books[current]) * 2)
        current += m

print(walk)
