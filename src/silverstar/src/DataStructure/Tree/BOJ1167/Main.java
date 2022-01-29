package DataStructure.Tree.BOJ1167;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 간선에 가중치(노드 간의 거리가 존재)가 있는 트리에서,
   가장 거리가 먼 두 노드 구하기
   => 트리에서 임의의 노드로부터 가장 먼 노드는,
      트리에서 가장 먼 두 노드 중 하나의 노드 !!

 1) 트리에서 임의의 노드로부터 가장 먼 노드 v1 을 DFS 로 구하기
 2) 찾은 노드 v1 으로부터 가장 먼 노드 v2 를 DFS 로 구하기
 => v1, v2 의 거리 구하기

2. 자료구조
 - List<Pair>[], ArrayList<Pair>[]: 인접 리스트
   => Pair: (노드 번호, 거리) 저장
   => lists[1]: 1번 노드와 연결된 노드들의 (노드 번호, 거리) 쌍들 저장
 - boolean[]: 방문 확인

3. 시간 복잡도
 - DFS 총 2번 수행
*/

class Pair {
	public int node;
	public int distance;

	public Pair(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
}

public class Main {
	static int v;					// 트리의 정점 개수
	static int maxR = 0;			// 출력: 트리의 최대 지름

	static int vertex;				// 거리가 가장 먼 2개의 노드 중, 첫 번째 노드
	static List<Pair>[] lists;		// 인접 리스트
	static boolean[] check;

	static void dfs(int parent, int totalDistance) {
		check[parent] = true;

		List<Pair> list = lists[parent];			// parent 노드와 연결된 노드들
		for (Pair p : list) {
			if (!check[p.node])
				dfs(p.node, totalDistance + p.distance);
		}

		// 재귀 종료 조건: Leaf 노드까지 내려간 경우 (연결된 노드가 없는 경우)
		if (maxR < totalDistance) {
			maxR = totalDistance;
			vertex = parent;				// v1 저장
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		v = Integer.parseInt(br.readLine());
		lists = new ArrayList[v + 1];			// [1 ~ v] 사용
		for (int i = 1; i <= v; i++)
			lists[i] = new ArrayList<>();

		for (int i = 1; i <= v; i++) {
			st = new StringTokenizer(br.readLine());

			int startNode = Integer.parseInt(st.nextToken());
			while (true) {
				int destNode = Integer.parseInt(st.nextToken());
				if (destNode == -1)
					break;
				int distance = Integer.parseInt(st.nextToken());

				lists[startNode].add(new Pair(destNode, distance));
				lists[destNode].add(new Pair(startNode, distance));
			}
		}

		// 1) 임의의 노드 (1번 노드)로부터 가장 먼 노드 v1 (vertex) 찾음
		check = new boolean[v + 1];
		dfs(1, 0);

		// 2) 노드 v1 (vertex) 으로부터 가장 먼 노드 v2 찾기 => 트리의 지름 계산
		check = new boolean[v + 1];
		dfs(vertex, 0);

		System.out.println(maxR);
	}
}
