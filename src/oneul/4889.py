import sys
si = sys.stdin.readline

idx = 1
while True:
    stack = []
    string = list(si().rstrip())

    # 종료조건
    if '-' in string:
        break
    
    opr = 0
    while string:
        brace = string.pop(0)
        # 여는 괄호인 경우 스택에 넣어줌
        if brace == '{':
            stack.append('{')
        # 닫는 괄호인 경우
        elif brace == '}':
            # 스택이 비었다면(왼쪽에 여는 괄호가 없었다면)
            # 여는 괄호로 바꾸는 연산 수행
            if len(stack) == 0:
                stack.append('{')
                opr += 1
            # 여는 괄호가 있었다면 짝이 맞도록 스택에서 빼줌
            else:
                stack.pop()
    
    # 문자열을 다 돌았는데 스택이 비지 않은 경우
    # (여는 괄호가 남은 경우) 
    # 둘 씩 짝지어서 하나를 닫는 괄호로 바꿔줌
    while len(stack) != 0:
        stack.pop()
        stack.pop()
        opr += 1
    
    print(f"{idx}. {opr}")
    idx += 1
