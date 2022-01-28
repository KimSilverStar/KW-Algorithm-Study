import sys
from itertools import combinations
input = sys.stdin.readline

i_formula = list(input().replace("\n", ""))

gwal_lst = []
gwal_tuple_lst = []

for i in range(len(i_formula)):
    character = i_formula[i]
    ch_ind = i
    if character == "(":
        gwal_lst.append(ch_ind)
    elif character == ")":
        gwal_tuple_lst.append([gwal_lst.pop(), ch_ind])

main_lst = []

for i in range(len(gwal_tuple_lst)):
    for case in list(combinations(gwal_tuple_lst, i+1)):
        new_formula = ""
        remove_lst = []
        for f, b in case:
            remove_lst.append(f)
            remove_lst.append(b)
        for chr in range(len(i_formula)):
            if chr in remove_lst:
                continue
            else:
                new_formula += i_formula[chr]
        if new_formula not in main_lst:
            main_lst.append(new_formula)

main_lst.sort()
for formula in main_lst:
    print(str(formula))
