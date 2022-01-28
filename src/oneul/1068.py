# 1068번: 트리 (https://www.acmicpc.net/problem/1068)
# 
# 입력
# 1. 트리의 노드의 개수 N (N <= 50)
# 2. 각 노드의 부모 list
# 3. 지울 노드의 번호
# 
# 출력
# 리프 노드의 개수
import sys
si = sys.stdin.readline
n = int(si().rstrip())
parents = list(map(int, si().rstrip().split()))
delete_node = int(si().strip())


root = -1
adj_list = [[] for _ in range(n)]
leaf_node = 0
visited = [False for _ in range(n)]
# 부모 정보를 이용해서 그래프의 인접 리스트 생성
# 시간복잡도 : O(N)
for idx, parent in enumerate(parents):
    if parent == -1:
        root = idx
    elif parent != delete_node and idx != delete_node:
        adj_list[parent].append(idx)
        adj_list[idx].append(parent)


# 깊이 우선 탐색 
# 시간복잡도 : O(N)
def dfs(node):
    global adj_list, leaf_node
    
    # root node를 지우면 그래프가 없으니 탐색할 필요 없음.
    if delete_node == root:
        return

    # 리프 노드인지 판단
    is_leaf = True
    # 연결된 노드 중 방문을 안 한 노드가 하나도 없으면 leaf node
    # 시간복잡도 : O(N)
    for adj_node in adj_list[node]:
        if not visited[adj_node]:
            is_leaf = False
    
    # 자식이 있는 root node는 leaf node가 아님
    if node == root and adj_list[root]:
        is_leaf = False

    # leaf node라면 leaf_node의 개수 1 증가
    if is_leaf:
        leaf_node += 1

    # 연결된 노드 중 방문을 안 한 곳이 있다면 방문(dfs)
    # 시간 복잡도 : O(N)
    visited[node] = True
    for adj_node in adj_list[node]:
        if not visited[adj_node]:
            dfs(adj_node)


dfs(root)
print(leaf_node)
