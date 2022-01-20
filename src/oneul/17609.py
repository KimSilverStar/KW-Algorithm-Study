import sys
si = sys.stdin.readline
t = int(si().rstrip())


def is_pallindrome(string):
    left, right = 0, len(string)-1

    while left < right:
        if string[left] != string[right]:
            return False
        left += 1
        right -= 1
    
    return True


for _ in range(t):
    string = si().rstrip()
    
    # 그냥 회문인지
    if is_pallindrome(string):
        print(0)
        continue
    # 앞에거 하나 빼고 회문인지(유사회문)
    elif is_pallindrome(string[1:]):
        print(1)
        continue
    # 뒤에거 하나 빼고 회문인지(유사회문)
    elif is_pallindrome(string[:-1]):
        print(1)
        continue

    # 유사회문인지 판단
    left, right = 0, len(string)-1
    judge_pal = 0
    while left < right:
        if string[left] != string[right]:
            judge_pal += 1

            if string[left] == string[right - 1]:
                right -= 1
            elif string[left + 1] == string[right]:
                left += 1
            else:
                judge_pal += 1

        if judge_pal >= 2:  # 2를 넘으면 일반 문자열
            judge_pal = 2
            break
        
        left += 1
        right -= 1
    print(judge_pal)
