package DataStructure.Tree.BOJ5639;
import java.io.*;

/*
1. 풀이
 - 이진 탐색 트리 구현, 직접 노드를 추가해나감
 - 후위 순회: Left Child -> Right Child -> Parent
   => 재귀 함수

2. 자료구조
 - BinarySearchTree: 이진 탐색 트리 구현

3. 시간 복잡도
 1) 이진 탐색 트리 저장
   - BST 의 탐색 / 삽입 시간 복잡도: O(H)	(H: BST 의 높이)
   - BST 가 완전 이진 트리 (균형이 완벽하게 잡힌 이진 트리)인 경우,
     O(H) = O(log2 n)	(n: 노드 개수)
   - Worst) BST 가 균형이 잡히지 않은 경우 (BST 가 왼쪽 or 오른쪽으로만 길게 뻗은 경우),
     O(H) => H 최대값으로 노드의 개수 10^5 개 대입
     	  => BST 저장 시간 복잡도: 10^5
 2) 후위 순회 출력
   - 부모 노드 1개에 대해 재귀 호출 2번 수행
   => 전체 최대 노드 수 10^5에 대해 대략 재귀 호출 2번 수행
   => 후위 순회 출력 시간 복잡도: 2 x 10^5

 => 전체 시간 복잡도 = BST 저장 후, 후위 순회 출력
    = 10^5 + (2 x 10^5) = 3 x 10^5 << 1억 (1초)
*/

public class Main_Dev_BST {
	static final int MAX_COUNT = 10000;
	static int[] preorder = new int[MAX_COUNT];		// 입력: 전위 순회
	static BinarySearchTree bst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		int root = Integer.parseInt(br.readLine());
		bst = new BinarySearchTree(new Node(root));

		while (true) {
			String input = br.readLine();
			if (input == null || input.equals(""))
				break;

			bst.insert(Integer.parseInt(input));
		}

		bst.postorder(bst.root);
		System.out.println(bst.sb);
	}
}
