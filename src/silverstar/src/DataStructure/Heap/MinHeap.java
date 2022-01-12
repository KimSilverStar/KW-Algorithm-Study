package DataStructure.Heap;

/*
* 최소 힙
 - 루트 노드 arr[1]에 최소 값 위치 (arr[0]은 사용 X)
 - 부모 노드와 자식 노드의 index 관계
   => 부모 노드의 index = 자식 노드 index / 2
   => 왼쪽 자식 노드의 index = 부모 노드의 index * 2
   => 오른쪽 자식 노드의 index = 부모 노드의 index * 2 + 1

 1. void add(int item)
   1) 새로운 노드를 맨 뒤 arr[size+1]에 추가
   2) ReHeapification Upward
     - 부모 노드와 자식 노드를 비교해가면서 정렬
       => 부모 > 자식이면 교체

 2. int remove()
   1) 루트 노드를 삭제
   2) 빈 루트 노드를 맨 뒤 노드인 arr[size]로 채움
   3) ReHeapification Downward
     - 부모 노드와 자식 노드를 비교해가면서 정렬
       => 왼쪽 / 오른쪽 자식 노드 중, 더 작은 노드와 교체
*/

public class MinHeap {
	private int[] arr;
	private int size;

	public MinHeap(int length) {
		arr = new int[length];
	}

	public void add(int item) {
		arr[++size] = item;

		// Upward
		for (int i = size; i > 1; i /= 2) {
			if (arr[i / 2] > arr[i])		// 부모 > 자식
				swap(i / 2, i);
			else
				break;
		}
	}

	public int remove() {
		int root = arr[1];		// 기존 루트 노드 값
		arr[1] = arr[size--];	// 루트 노드를 맨 뒤 노드로 채움

		// Downward
		for (int i = 1; i * 2 <= size; ) {
			if (arr[i] < arr[i * 2] &&
					arr[i] < arr[i * 2 + 1])		// 부모 노드 < 왼쪽, 오른쪽 자식 노드
				break;
			else if (arr[i * 2] < arr[i* 2 + 1]) {	// 왼쪽 자식 노드 < 오른쪽 자식 노드
				swap(i, i * 2);
				i = i * 2;
			}
			else {		// 왼쪽 자식 노드 > 오른쪽 자식 노드
				swap(i, i * 2 + 1);
				i = i * 2 + 1;
			}
		}

		return root;
	}

	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }

	private void swap(int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
}
