import sys
input = sys.stdin.readline

c_range = int(input())
for _ in range(c_range):
    gwal = list(input().replace("\n", ""))
    if len(gwal) % 2 != 0:
        print("NO")
    else:
        done = True
        gwal_stack = []
        b_count = 0
        while(done):
            if gwal.pop() == ")":
                gwal_stack.append(")")
            else:
                if len(gwal_stack) == 0:
                    b_count = 1
                    break
                else:
                    gwal_stack.pop()
            if len(gwal) == 0:
                done = False
        if len(gwal_stack) != 0 or b_count == 1:
            print("NO")
        else:
            print("YES")
