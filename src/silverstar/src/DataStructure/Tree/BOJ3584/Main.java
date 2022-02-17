package DataStructure.Tree.BOJ3584;
import java.io.*;
import java.util.*;

/*
1. 풀이
 1) 루트 노드 찾기
   - 입력 트리가 "부모 노드 - 자식 노드" 형식으로 주어짐
   - 트리의 노드들을 인접 리스트에 저장하면서, 자식 노드들을 방문 처리
   - 입력 종료 후, 방문되지 않은 노드가 루트 노드

 2) 두 노드의 가장 가까운 공통 조상 노드 찾기
  *** 최소 공통 조상 (Lowest Common Ancestor, LCA) 알고리즘 ***
     1) dfs(int node, int d): DFS 로 각 노드의 depth, 2^0 번째 부모 (직계 부모) 저장
     2) setParent(): DP 로 각 노드의 2^i 번째 부모 저장
       - parent[node][i+1] == parent[ parent[node][i] ][i]
         => node 의 2^(i+1) 부모 == (node 의 2^i 번째 부모)의 2^i 번째 부모
         => 2^(i+1) = 2 x 2^i = 2^i + 2^i
     3) LCA(int n1, int n2)
       - 두 노드 n1, n2 중에서 depth 가 더 깊은 노드를 n2 로 고정
       - n2 를 n1 의 깊이와 동일하도록 맞춤
         => 깊이 동일하게 맞춘 후, 두 노드가 같으면 두 노드 자체가 LCA 가 됨
       - 두 노드의 부모 노드가 같아질 때까지, 번갈아가면서 2^k 칸 씩 부모 방향으로 올림
       !!! 노드를 부모 방향으로 올릴 때, 2^i 칸씩 이동
           => 가장 큰 보폭으로 이동
           ex) 13 칸 위로 올려야되는 경우: 8 -> 4 -> 2 -> 1 칸 씩 이동

2. 자료구조
 - List<Integer>[], ArrayList<Integer>[]: 인접 리스트
 - boolean[] check: 노드 방문 확인
   => 루트 노드 찾기, DFS 용도
 - int[] depth: 각 노드의 깊이
 - int[][] parent = new int[n][21];
   => Sparse Table (DP 배열): 각 노드의 2^i 번째 부모 저장
      ex) parent[node][2]: node 의 2^2 번째 부모
   => n: 트리의 노드 개수, 21: 깊이 21 이면 왠만한 트리 커버
   => 메모리: 4 x n x 21 byte
      n 최대값 대입: 840,000 byte = 0.84 MB

3. 시간 복잡도
 1) DFS 로 각 노드의 depth 저장: O(V + E) = O(n + n-1) ~= O(2n)
   - n 최대값 대입: 2 x 10^4
 2) DP 로 각 노드의 2^i 번째 부모 저장: O(Max Node Level x n)
   - n, MAX_LEVEL 최대값 대입: 20 x 10^4 = 2 x 10^5
 3) LCA 수행: O(log n)
   - n 최대값 대입: 대충 log 2^13 = 13	=> 무시 될 정도
 => 최소 공통 조상을 출력하는 1개의 Query (테스트 케이스)에 대해,
    시간 복잡도 = (2 x 10^4) + (2 x 10^5)
*/

public class Main {
	static int t;
	static int n;						// 트리의 노드 개수
	static int node1, node2;			// LCA 를 구할 두 노드

	static List<Integer>[] lists;		// 인접 리스트
	static boolean[] check;				// 루트 노드 찾는 용도, DFS 용도
	static int root;
	static int[] depth;					// 각 노드의 깊이
	static int[][] parent;				// 각 노드의 2^i 번째 부모 (Sparse Table, DP 배열)
	static final int MAX_LEVEL = 21;

	/* d: 노드의 depth, 각 노드의 깊이 저장, 2^0 번째 부모 저장 */
	static void dfs(int parentNode, int d) {
		check[parentNode] = true;
		depth[parentNode] = d;

		List<Integer> list = lists[parentNode];
		for (int node : list) {
			if (!check[node]) {					// 아직 방문 X => ParentNode 의 자식 노드들
				parent[node][0] = parentNode;	// node 의 2^0 번째 부모 (직계 부모) = parentNode
				dfs(node, d + 1);
			}
		}
	}

	/* 각 노드의 2^i 번째 부모 저장: parent[node][i] */
	static void setParent() {
		for (int i = 1; i < MAX_LEVEL; i++) {
			for (int node = 1; node <= n; node++)
				parent[node][i] = parent[ parent[node][i-1] ][i-1];
				// 2^(i+1) = 2^i + 2^i
		}
	}

	static int LCA(int n1, int n2) {
		// 1) 노드 n2 가 depth 더 깊은 노드가 되도록, 필요 시 swap
		if (depth[n1] > depth[n2]) {
			int temp = n1;
			n1 = n2;
			n2 = temp;
		}

		// 2) 두 노드의 깊이를 동일하게 맞춤 => n2 를 부모 방향으로 올림
		for (int i = MAX_LEVEL - 1; i >= 0; i--) {
			// 큰 거리의 부모부터 확인 => 2^20, 2^19, ..., 2^1, 2^0
			int jump = 1 << i;

			if (depth[n2] - depth[n1] >= jump)
				n2 = parent[n2][i];				// n2 의 2^i 번째 부모로 올라감
		}

		if (n1 == n2)			// 두 노드가 같은 경우 => 두 노드 자체가 LCA
			return n1;

		// 3) 두 노드를 부모가 같아질 때까지, 2^i 칸 씩 위로 이동
		for (int i = MAX_LEVEL - 1; i >= 0; i--) {
			// 두 노드의 부모가 같아질 때까지 반복
			if (parent[n1][i] == parent[n2][i])
				continue;

			// 두 노드의 2^i 번째 부모가 서로 다르면, 2^i 칸씩 위로 올림
			n1 = parent[n1][i];
			n2 = parent[n2][i];
		}

		return parent[n1][0];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			lists = new ArrayList[n + 1];			// [1 ~ n] 사용
			check = new boolean[n + 1];
			depth = new int[n + 1];
			parent = new int[n + 1][MAX_LEVEL];		// [1][0] ~ [n][20] 사용

			for (int i = 1; i <= n; i++)
				lists[i] = new ArrayList<>();

			for (int i = 0; i < n - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());

				check[child] = true;
				lists[parent].add(child);
				lists[child].add(parent);
			}

			st = new StringTokenizer(br.readLine());
			node1 = Integer.parseInt(st.nextToken());
			node2 = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= n; i++) {
				if (!check[i]) {		// 루트 노드 = 방문되지 않은 자식 노드
					root = i;
					break;
				}
			}

			check = new boolean[n + 1];
			dfs(root, 0);				// 각 노드의 깊이, 2^0 번째 부모 저장
			setParent();					// 각 노드의 2^i 번째 부모 저장
			sb.append(LCA(node1, node2))
					.append("\n");
		}

		System.out.println(sb);
	}
}
