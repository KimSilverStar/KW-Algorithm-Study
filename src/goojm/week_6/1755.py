import sys
input = sys.stdin.readline

above_num, below_num = map(int, input().split())
numlst = {
    "0": "zero",
    "1": "one",
    "2": "two",
    "3": "three",
    "4": "four",
    "5": "five",
    "6": "six",
    "7": "seven",
    "8": "eight",
    "9": "nine",
}

ndict_lst = []
for i in range(above_num, below_num+1):
    nstring = ""
    for nstr in str(i):
        nstring += numlst[nstr]
    ndict_lst.append({"rn": i, "nstring": nstring})

sorted_ndlst = sorted(ndict_lst, key=lambda ndict_lst: (ndict_lst['nstring']))


count = 0
for i in sorted_ndlst:
    print(i['rn'], end=' ')
    count += 1
    if count % 10 == 0:
        print()
