package DataStructure.Tree.BOJ9934;
import java.io.*;
import java.util.*;

/*
- 완전 이진 트리: depth k 이면, 총 2^k - 1 개 노드
- 방문 순서: left child -> parent -> right child
  => 중위 순회 (Inorder Traversal)
- 중위 순회의 순서가 주어질 때, 트리를 출력 (depth 별로 출력)
*/

/*
1. 아이디어
 - 중위 순회 순서에서 루트 노드: 중간에 방문
   1) 루트 노드
     - 총 노드 개수 (2^k - 1) / 2 번째에 방문한 노드가 루트 노드
     - inorder[(2^k - 1) / 2] 가 tree[1] 이 됨
   2) 찾은 부모 노드를 기준으로, left / right subtree 각각에서 부모 노드찾음
     - 찾은 부모 노드가 [i]이면
       => left subtree 에서의 부모 노드는 [0] ~ [i-1] 의 중간 index
       => right subtree 에서의 부모 노드는 [i+1] ~ [끝] 의 중간 index
       !!! 이분 탐색 (Binary Search) 하듯이 중간 index (부모) 기준으로 2개 subtree
     => recursive(startIdx, endIdx) 로 중위 순회에서 부모 index 찾음
     => 재귀 종료 조건: Leaf 노드까지 내려간 경우
 - 트리에서 부모 index 가 [i] 이면		(루트 노드는 [1]에서 시작)
   => left child: [i * 2], right child: [i * 2 + 1]

2. 자료구조
 - int[]: 입력 값, 중위 순회 순서 저장
   => [0 ~ ] 사용
 - Queue<Pair>, LinkedList<Pair>: 트리 Level 단위로 inorder[]에서 탐색할 Pair (startIdx, endIdx) 저장

3. 시간 복잡도
*/

class Pair {
	public int startIdx, endIdx;

	public Pair(int startIdx, int endIdx) {
		this.startIdx = startIdx;
		this.endIdx = endIdx;
	}
}

public class Main_Queue {
	static int k;				// 완전 이진 트리의 depth
	static int nodeCount;		// 총 노드 개수: 2^k - 1
	static int[] inorder;		// 입력: Inorder Traversal (중위 순회) 방문 순서
	static Queue<Pair> queue = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();		// 출력 값 저장

	static void solution() {
		while (!queue.isEmpty()) {
			// 현재 트리 Level 의 노드 개수
			int currentNodeCount = queue.size();
			for (int i = 0; i < currentNodeCount; i++) {
				Pair p = queue.remove();

				// 주어진 범위 start ~ end 에서, 중위 순회 inorder[] 의 부모 노드를 찾음
				int parentIdx = (p.startIdx + p.endIdx) / 2;
				sb.append(inorder[parentIdx]).append(" ");

				if (p.startIdx != p.endIdx) {
					queue.add(new Pair(p.startIdx, parentIdx - 1));
					queue.add(new Pair(parentIdx + 1, p.endIdx));
				}
			}

			sb.append("\n");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		nodeCount = (int)Math.pow(2, k) - 1;
		inorder = new int[nodeCount];			// [0 ~ 노드 개수 - 1] 사용
		for (int i = 0; i < inorder.length; i++)
			inorder[i] = Integer.parseInt(st.nextToken());

		queue.add(new Pair(0, nodeCount - 1));
		solution();

		System.out.println(sb);
	}
}
