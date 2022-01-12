import sys, heapq

n = int(sys.stdin.readline().rstrip())
lectures = []
# 시작시간 오름차순으로 정렬되게 힙에 넣음.
for _ in range(n):
    data = list(map(int, sys.stdin.readline().rstrip().split()))
    heapq.heappush(lectures, (data[1], data[2]))


class_room = 0
lecture_now = []
# 가장 빨리 시작하는 강의 시작
lecture = tuple(reversed(heapq.heappop(lectures)))
# 현재 강의중인 강의실 목록에 끝나는 시간 오름차순으로 넣어줌
heapq.heappush(lecture_now, lecture)
class_room += 1

while lectures:
    lecture = tuple(reversed(heapq.heappop(lectures)))
    fast_end = heapq.heappop(lecture_now)

    # 강의시간이 겹치면 새 강의실 배정
    if lecture[1] < fast_end[0]:
        heapq.heappush(lecture_now, fast_end)
        heapq.heappush(lecture_now, lecture)
        class_room += 1
    else:
        # fast_end의 강의실에 lecture를 넣어줌.
        heapq.heappush(lecture_now, lecture)

print(class_room)
