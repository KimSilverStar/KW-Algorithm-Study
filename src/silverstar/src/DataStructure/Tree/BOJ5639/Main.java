package DataStructure.Tree.BOJ5639;
import java.io.*;

/*
1. 풀이
 - 이진 탐색 트리
 - 전위 순회: Parent -> Left Child -> Right Child
 - 후위 순회: Left Child -> Right Child -> Parent
   => 재귀 함수
 - 입력 전위 순회에서 범위와 루트 노드를 정해서,
   루트 노드 값을 기준으로 Left Child, Right Child 찾음
   => Left Subtree, Right Subtree 에서 각각 다시 반복

2. 자료구조
 - int[]: 입력 전위 순회
   => 최대 노드 개수 10^5 개
   => int[10^5]: 4 x 10^5 byte = 0.4 MB

3. 시간 복잡도
 - 부모 노드 1개에 대해 재귀 호출 2번 수행
 => 전체 최대 노드 수 10^5에 대해 대략 재귀 호출 2번 수행
 => 총 시간 복잡도: 2 x 10^5 << 1억
*/

public class Main {
	static final int MAX_COUNT = 10000;
	static int[] preorder = new int[MAX_COUNT];		// 입력: 전위 순회
	static StringBuilder sb = new StringBuilder();

	static void postorder(int startIdx, int endIdx) {
		if (startIdx > endIdx)
			return;

		int parent = preorder[startIdx];

		int rightChildIdx;
		for (rightChildIdx = startIdx + 1; rightChildIdx <= endIdx; rightChildIdx++) {
			if (parent < preorder[rightChildIdx])		// Right Child 찾음
				break;
		}

		postorder(startIdx + 1, rightChildIdx - 1);		// Left Subtree
		postorder(rightChildIdx, endIdx);				// Right Subtree
		sb.append(parent).append("\n");					// Parent 출력
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		int idx = 0;
		while (true) {
			String input = br.readLine();
			if (input == null || input.equals(""))
				break;
			preorder[idx++] = Integer.parseInt(input);
		}

		postorder(0, idx - 1);

		System.out.println(sb);
	}
}
