package DataStructure.Tree.BOJ11725;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 트리의 자식 노드 개수에 제한이 없음
   (이진 트리처럼 left, right child 형식 X),
   노드의 연결 정보가 부모 - 자식 순서로 정해져서 입력되지 않음
   => 트리를 직접 구현 X, 그래프 탐색으로 해결

 - 트리 노드 연결 정보 저장
   => 인접 행렬 사용 시, 메모리 초과
   => 인접 리스트 사용

 - 루트 노드에서부터 BFS 탐색 시작
   1) 부모 노드 방문 처리
   2) 부모 노드에 연결된 노드들 중,
      아직 방문 안한 노드들에 대해 탐색 확장
     => 부모 노드와 연결된 노드들 중에서, 아직 방문 안한 노드가 자식 노드가 됨
     => 트리 탐색: 부모 노드가 자식 노드보다 항상 먼저 방문됨

2. 자료구조
 - Queue<Integer>, LinkedList<Integer>: BFS
 - List<Integer>[], ArrayList<Integer>[]: 인접 리스트
 - boolean[]: 부모 노드 방문 확인

3. 시간 복잡도
 - 루트 노드에서 시작하여 BFS 1번 수행
   => O(V + E)
   => V: n (최대 10^5)
*/

public class Main_BFS {
	static int n;				// 전체 노드 개수
	static int[] parents;		// 각 노드들의 부모 노드 번호 저장
	static Queue<Integer> queue = new LinkedList<>();
	static List<Integer>[] lists;			// 인접 리스트
	static boolean[] check;

	static void bfs() {
		while (!queue.isEmpty()) {
			int parentNode = queue.remove();
			List<Integer> list = lists[parentNode];		// 부모 노드와 연결된 노드들

			for (int node : list) {
				if (!check[node]) {			// 아직 방문 안한 노드 => 자식 노드
					parents[node] = parentNode;
					check[node] = true;
					queue.add(node);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		parents = new int[n + 1];
		check = new boolean[n + 1];
		lists = new ArrayList[n + 1];			// [1 ~ n]  사용
		for (int i = 1; i <= n; i++)
			lists[i] = new ArrayList<>();
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			lists[node1].add(node2);
			lists[node2].add(node1);
		}

		// 루트 노드에서부터 BFS 시작
		check[1] = true;
		queue.add(1);
		bfs();

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; i++)
			sb.append(parents[i]).append("\n");
		System.out.println(sb);
	}
}
