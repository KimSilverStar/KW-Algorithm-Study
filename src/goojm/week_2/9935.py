import sys
input = sys.stdin.readline

prob_str = input().rstrip()
bomb_str = input().rstrip()
except_case = "FRULA"

if len(prob_str) < len(bomb_str):
    print(prob_str)
else:
    prob_stack = []
    for p_char in prob_str:
        prob_stack.append(p_char)
        if p_char == bomb_str[-1] and ''.join(prob_stack[-len(bomb_str):]) == bomb_str:
            del prob_stack[-len(bomb_str):]

    if len(prob_stack) == 0:
        print(except_case)
    else:
        print(''.join(prob_stack))

# def div_cq(targetlst, bomb):
#     hapcount = 0
#     n_tlst = []
#     hap = ""
#     for t_ind in range(len(targetlst)):
#         target = targetlst[t_ind]
#         ddone = True
#         while ddone:
#             if len(target) == 0:
#                 ddone = False
#             for ind in range(len(target)):
#                 if ind+len(bomb) > len(target):
#                     ddone = False
#                     break
#                 if target[ind:ind+len(bomb)] == bomb:
#                     target = target[:ind]+target[ind+len(bomb):]
#                     break
#                 if ind+1 == len(target):
#                     ddone = False
#         if hapcount == 1 and len(target) != 0:
#             n_tlst.append(hap+target)
#             hap = ""
#             hapcount = 0
#         elif hapcount == 0 and len(target) != 0:
#             hap += target
#             hapcount = 1
#             if t_ind+1 == len(targetlst):
#                 n_tlst.append(hap)
#                 hap = ""
#     if hap != "":
#         n_tlst.append(hap)
#     return n_tlst


# prob_str = input().rstrip()
# bomb_str = input().rstrip()
# except_case = "FRULA"

# if len(prob_str) < len(bomb_str):
#     print(prob_str)
# else:
#     tlst = []
#     for s_ind in range(int(len(prob_str)/len(bomb_str))):
#         tlst.append(prob_str[s_ind*len(bomb_str):(s_ind+1)*len(bomb_str)])
#     if len(prob_str) % len(bomb_str) != 0:
#         tlst.append(prob_str[(s_ind+1)*len(bomb_str):])
#     print(tlst)
#     done = True
#     f_count = 0
#     while done:
#         tlst = div_cq(tlst, bomb_str)
#         if len(tlst) == 0 or (len(tlst) == 1 and f_count == 1):
#             done = False
#         if f_count == 0 and len(tlst) == 1:
#             f_count = 1

#     if len(tlst) == 0:
#         print(except_case)
#     else:
#         print(tlst[0])


# prob_str = "121212ababab"
# bomb_str = "12ab"
