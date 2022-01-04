import sys
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
        gwal_tuple_lst.append((gwal_lst.pop(), ch_ind))

main_lst = []

for i in range(2**len(gwal_tuple_lst)):
    if i == 0:
        continue
    t_ind = int(bin(i).replace('0b', ""))
    t_ind = format(t_ind, '0'+str(len(gwal_tuple_lst)))
    print(t_ind)
    remove_ind_lst = []
    for t in range(len(t_ind)):
        if t_ind[t] == '1':
            remove_ind_lst.append(gwal_tuple_lst[t][0])
            remove_ind_lst.append(gwal_tuple_lst[t][1])
    remove_ind_lst.sort()
    new_formula = ""
    for s in range(len(i_formula)):
        if s in remove_ind_lst:
            continue
        else:
            new_formula += i_formula[s]
    main_lst.append(new_formula)


for i in main_lst:
    print(i)
