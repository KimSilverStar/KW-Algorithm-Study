import sys
si = sys.stdin.readline
string = si().rstrip()
bomb = si().rstrip()

while bomb in string:
    string = string.replace(bomb, '')

if not string:
    print('FRULA')
else:
    print(string)
