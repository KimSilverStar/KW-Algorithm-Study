package DataStructure.Tree.BOJ9934;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - 입력: 트리의 중위 순회 (Inorder Traversal) 순서
 - 완전 이진 트리의 depth = k 일 때,
   전체 노드 개수 = 2^k - 1
 - 중위 순회에서 루트(부모) 노드는 중간에 방문
   => 부모 노드 = subtree 에서 중간 index

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
 - int[] inorder: 입력 값, 트리의 중위 순회 순서
 - int[] tree: 출력 값, 트리 (Level Order Traversal)

3. 시간 복잡도
 - 1개 부모 노드에서 재귀 호출 2번 수행
   => 전체 (2^k - 1)개 노드에서, 대충 총 2(2^k - 1) 번 재귀 호출 발생
   => k 최대값 대입: 2(2^10 - 1) = 2(1,024 - 1) = 2,046 << 1억 (1초)
*/

public class Main_Recursive1 {
	static int k;				// 완전 이진 트리의 depth
	static int[] inorder;		// 입력 값, 트리의 중위 순회 순서
	static int nodeCount;		// 전체 노드 개수 = 2^k - 1
	static int[] tree;			// 출력 값, tree[1]: 루트 노드

	static void recursive(int startIdx, int endIdx, int treeIdx) {
		int parentIdx = (startIdx + endIdx) / 2;
		tree[treeIdx] = inorder[parentIdx];

		// 재귀 종료 조건: Leaf 노드까지 내려간 경우
		if (startIdx == endIdx)
			return;

		recursive(startIdx, parentIdx - 1, treeIdx * 2);		// Left Subtree
		recursive(parentIdx + 1, endIdx, treeIdx * 2 + 1);	// Right Subtree
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		nodeCount = (int)Math.pow(2, k) - 1;
		inorder = new int[nodeCount];		// [0] ~ [nodeCount - 1]
		tree = new int[nodeCount + 1];		// 루트 노드 [1] ~ [nodeCount]
		for (int i = 0; i < nodeCount; i++)
			inorder[i] = Integer.parseInt(st.nextToken());

		recursive(0, nodeCount - 1, 1);		// 루트 노드 tree[1] 부터 시작

		// 트리의 Depth (Level) 단위로 개행하여 출력
		StringBuilder sb = new StringBuilder();
		for (int level = 1; level <= k; level++) {
			int currentNode = (int)Math.pow(2, level) - 1;		// 현재 level 까지 전체 노드 개수
			int prevNode = (int)Math.pow(2, level - 1) - 1;		// 이전 level 까지 전체 노드 개수

			for (int i = prevNode + 1; i <= currentNode; i++)
				sb.append(tree[i]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
