# 2174번: 로봇 시뮬레이션 (https://www.acmicpc.net/problem/2174)
# 
# 입력
# 1. 격자판 가로길이 A, 격자판 세로 길이 B
# 2. 로봇 개수 N, 명령 개수 M
# 3. N개의 로봇 위치와 방향 (x, y, 방향)
# 4. M개의 명령 정보 (명령할 로봇 번호, 명령 종류(L,R,F), 명령 반복 회수)
# 
# 가능한 출력
# 1. OK : 충돌 없이 로봇 시뮬레이션이 끝난 경우
# 2. Robot X crahses into the wall : X번 로봇이 벽에 충돌하는 경우
# 3. Robot X crashes into robot Y : X번 로봇이 움직이다가 Y번 로봇에 충돌하는 경우
import sys
from collections import deque


si = sys.stdin.readline
a, b = map(int, si().rstrip().split())
n, m = map(int, si().rstrip().split())

board = [[(0, '0') for _ in range(a+1)] for _ in range(b+1)]
robot_info = {}
for i in range(n):
    x, y, direction = si().rstrip().split()
    board[int(y)][int(x)] = (i+1, direction)
    robot_info[i+1] = (int(x), int(y), direction)

commands_q = deque()
for _ in range(m):
    commands_q.append(si().rstrip().split())


def get_dxdy(robot_dir):
    if robot_dir == 'E':
        return (1, 0)
    elif robot_dir == 'W':
        return (-1, 0)
    elif robot_dir == 'S':
        return (0, -1)
    elif robot_dir == 'N':
        return (0, 1)


def change_direction(current, direction, cnt):
    result = current
    for _ in range(cnt):
        if direction == 'L':

            if result == 'E':
                result = 'N'
            elif result == 'W':
                result = 'S'
            elif result == 'S':
                result = 'E'
            elif result == 'N':
                result = 'W'

        elif direction == 'R':

            if result == 'E':
                result = 'S'
            elif result == 'W':
                result = 'N'
            elif result == 'S':
                result = 'W'
            elif result == 'N':
                result = 'E'
    
    return result


def in_range(x, y):
    global a, b
    return 0 < x and x <= a and 0 < y and y <= b


def is_crashed(command):
    global a, b, robot_info, board
    # command == [로봇 번호, 방향, 횟수]
    robot_num = int(command[0])
    direction = command[1]
    cnt = int(command[2])

    # 이동 전 위치 기억
    past_x = robot_info[robot_num][0]
    past_y = robot_info[robot_num][1]

    # 명령이 직진인 경우
    if direction == 'F':
        dx, dy = get_dxdy(robot_info[robot_num][2])
        for i in range(1, cnt+1):
            # 한 칸씩 이동
            moved_x = robot_info[robot_num][0] + (dx*i)
            moved_y = robot_info[robot_num][1] + (dy*i)
            # 움직인 곳에 로봇이 있다면
            if in_range(moved_x, moved_y) and board[moved_y][moved_x][1] != '0':
                # print(f'Robot {robot_num} crashes into robot {board[moved_y][moved_x][0]}')
                # return True
                return f'Robot {robot_num} crashes into robot {board[moved_y][moved_x][0]}'
        
        # 벽과 충돌했는지 확인
        moved_x = robot_info[robot_num][0] + (dx*cnt)
        moved_y = robot_info[robot_num][1] + (dy*cnt)
        if not in_range(moved_x, moved_y):
            # print(f'Robot {robot_num} crashes into the wall')
            # return True
            return f'Robot {robot_num} crashes into the wall'
        
        # 충돌이 없었다면 robot_info, board 갱신
        robot_direction = robot_info[robot_num][2]
        robot_info[robot_num] = (moved_x, moved_y, robot_direction)

        board[past_y][past_x] = (0, '0')
        board[moved_y][moved_x] = (robot_num, robot_direction)

    # 명령이 직진이 아닌 경우(L 또는 R)
    else:
        current_direction = robot_info[robot_num][2]
        changed_direction = change_direction(current_direction, direction, cnt)
        x, y, _ = robot_info[robot_num]

        # robot_info 갱신
        robot_info[robot_num] = (x, y, changed_direction)

        # board 갱신
        board[y][x] = (robot_num, changed_direction)

    # return False
    return 'OK'


while commands_q:
    print_str = is_crashed(commands_q.popleft())
    if print_str != 'OK':
        break

print(print_str)
