# 예제 다 맞게 나오는데 시작하자 마자 틀렸습니다..
import sys
from collections import deque


si = sys.stdin.readline
gears = {}
for i in range(1, 5):
    gears[i] = list(si().rstrip())
k = int(si().rstrip())
turns_q = deque()
for _ in range(k):
    turns_q.append(tuple(map(int, si().rstrip().split())))


def turn(turns):
    global gears
    pivot, rotation = turns
    # 톱니가 어느 방향으로 회전해야 할 지
    rot_list = [3, 0, 0, 0, 0]
    rot_list[pivot] = rotation

    # 기준 톱니의 왼쪽 톱니들 회전 여부 결정
    for i in range(pivot, 1, -1):
        if gears[i][6] != gears[i-1][2]:
            rotation *= -1
            rot_list[i-1] = rotation
        else:
            break

    # 기준 톱니의 오른쪽 톱니들 회전 여부 결정
    for i in range(pivot, 4):
        if gears[i][2] != gears[i+1][6]:
            rotation *= -1
            rot_list[i+1] = rotation
        else:
            break
    
    # 모든 톱니 회전
    for i in range(1, 5):
        if rot_list[i] == 1:
            gears[i] = [gears[i][7]] + gears[i][:7]
        elif rot_list[i] == -1:
            gears[i] = gears[i][1:] + [gears[i][0]]
    

def get_score():
    global gears
    score = 0
    for i in range(1, 5):
        if gears[i][0] == '1':
            score += pow(2, i-1)
    
    return score


while turns_q:
    turn(turns_q.popleft())

print(get_score())
