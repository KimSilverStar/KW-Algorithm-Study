t_num, k_num = map(int, input().split())

idx_lst = []
for i in range(t_num):
    idx_lst.append(i+1)
idx = 0
print_lst = []
while(len(idx_lst) > 0):
    idx = (idx+k_num-1) % len(idx_lst)
    print_lst.append(idx_lst.pop(idx))
print(str(print_lst).replace("[", "<").replace("]", ">"))
