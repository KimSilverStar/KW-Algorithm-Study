package DataStructure.Tree.BOJ1068;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 리스트 배열에 각 노드의 자식 노드들 저장
 1) 기존 트리의 Leaf 노드 개수 계산
 2) DFS 로 노드 삭제하면서, Leaf 노드 개수 차감
   - 삭제하려는 노드가 기존 Leaf 노드인 경우, Leaf 노드 개수 차감

 예외 처리 1) 루트 노드를 삭제하는 경우, 남은 Leaf 노드 개수는 0개
 예외 처리 2) 목표 노드 삭제 후, 목표 노드의 부모 노드가 Leaf 노드가 되는지 확인

2. 자료구조
 - List<Integer>[], ArrayList<Integer>[]: (인접) 리스트
   ex) lists[1]: 1번 노드의 자식 노드들 저장

3. 시간 복잡도
 - DFS 1번 수행
*/

public class Main_DFS {
	static int n;				// 노드 개수, 0 ~ n-1 번 노드
	static int deleteNode;		// 지울 노드
	static int leafCount;		// 출력: 남은 Leaf 노드 개수
	static int root;
	static List<Integer>[] lists;		// 인접 리스트

	/* deleteNode: 삭제할 노드 */
	static void dfs(int deleteNode) {
		List<Integer> list = lists[deleteNode];		// 삭제할 노드의 자식 노드들
		if (list.isEmpty())			// 삭제할 노드가 Leaf 노드인 경우
			leafCount--;

		for (int child : list)
			dfs(child);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		lists = new ArrayList[n];			// [0 ~ n-1] 사용
		for (int i = 0; i < n; i++)
			lists[i] = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if (parent == -1) {			// 루트 노드
				root = i;
				continue;
			}
			lists[parent].add(i);		// parent 노드의 자식 노드들 저장
		}
		deleteNode = Integer.parseInt(br.readLine());

		// 예외 처리 1) 루트 노드를 삭제하는 경우, 남은 Leaf 노드 개수는 0개
		if (deleteNode == root) {
			System.out.println(0);
			return;
		}

		// 기존 Leaf 노드 개수 계산
		for (int i = 0; i < n; i++) {
			if (lists[i].isEmpty())
				leafCount++;
		}

		int parentNode = -1;					// deleteNode 의 부모 노드
		for (int i = 0; i < n; i++) {
			for (int child : lists[i]) {
				if (child == deleteNode) {
					parentNode = i;
					break;
				}
			}
		}

		lists[parentNode].remove(Integer.valueOf(deleteNode));		// deleteNode 삭제
		dfs(deleteNode);

		// 예외 처리 2) 목표 노드 삭제 후, 목표 노드의 부모 노드가 Leaf 노드가 되는지 확인
		if (lists[parentNode].isEmpty())
			leafCount++;

		System.out.println(leafCount);
	}
}
