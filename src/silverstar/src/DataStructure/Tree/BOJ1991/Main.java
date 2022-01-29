package DataStructure.Tree.BOJ1991;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 노드 연결 정보를 인접 리스트에 저장
 - 재귀 함수로 각 트리 순회 구현
   1) 전위 순회 (Preorder): Parent -> Left Child -> Right Child
   2) 중위 순회 (Inorder): Left Child -> Parent -> Right Child
   3) 후위 순회 (Postorder): Left Child -> Right Child -> Parent

2. 자료구조
 - List<Character>[], ArrayList<Character>[]: 인접 리스트
   => lists[0]: 루트 노드 'A' 의 자식 노드들 저장

3. 시간 복잡도
 - 트리 순회 1번: 각 부모 노드에서 재귀 호출 2번 수행
   => 대충 2 x n 번 재귀 호출 수행
 => 총 시간 복잡도 = 트리 순회 3번 = 3 x (2 x n) = 6 x n
 => n 최대값 대입: 6 x 26 = 156
*/

public class Main {
	static int n;						// 이진 트리의 노드 개수
	static List<Character>[] lists;		// 인접 리스트
	static StringBuilder sb = new StringBuilder();

	static void preorder(char parent) {
		List<Character> list = lists[parent - 'A'];
		char leftChild = list.get(0);
		char rightChild = list.get(1);

		// Parent -> Left Child -> Right Child
		sb.append(parent);
		if (leftChild != '.')
			preorder(leftChild);
		if (rightChild != '.')
			preorder(rightChild);
	}

	static void inorder(char parent) {
		List<Character> list = lists[parent - 'A'];
		char leftChild = list.get(0);
		char rightChild = list.get(1);

		// Left Child -> Parent -> Right Child
		if (leftChild != '.')
			inorder(leftChild);
		sb.append(parent);
		if (rightChild != '.')
			inorder(rightChild);
	}

	static void postorder(char parent) {
		List<Character> list = lists[parent - 'A'];
		char leftChild = list.get(0);
		char rightChild = list.get(1);

		// Left Child -> Right Child -> Parent
		if (leftChild != '.')
			postorder(leftChild);
		if (rightChild != '.')
			postorder(rightChild);
		sb.append(parent);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		lists = new ArrayList[n];
		for (int i = 0; i < n; i++)
			lists[i] = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char leftChild = st.nextToken().charAt(0);
			char rightChild = st.nextToken().charAt(0);

			lists[parent - 'A'].add(leftChild);
			lists[parent - 'A'].add(rightChild);
		}

		preorder('A');
		sb.append("\n");

		inorder('A');
		sb.append("\n");

		postorder('A');
		sb.append("\n");

		System.out.println(sb);
	}
}
