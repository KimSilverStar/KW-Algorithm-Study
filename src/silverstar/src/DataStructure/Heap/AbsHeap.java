package DataStructure.Heap;

/*
* 절댓값 힙
 - 루트 노드 arr[1]에 최소 절댓값 값 위치 (arr[0]은 사용 X)
 - 부모 노드와 자식 노드의 index 관계
   => 부모 노드의 index = 자식 노드 index / 2
   => 왼쪽 자식 노드의 index = 부모 노드의 index * 2
   => 오른쪽 자식 노드의 index = 부모 노드의 index * 2 + 1

 1. void add(int item)
   1) 새로운 노드를 맨 뒤 arr[size+1]에 추가
   2) ReHeapification Upward
     - 부모 노드와 자식 노드를 비교해가면서 정렬
       => |부모| > |자식| 이면, 교체
       => |부모| == |자식| 이면, 더 작은 값이 부모가 됨

 2. int remove()
   1) 루트 노드를 삭제
   2) 빈 루트 노드를 맨 뒤 노드인 arr[size]로 채움
   3) ReHeapification Downward
     - 부모 노드와 자식 노드를 비교해가면서 정렬
       => 왼쪽 / 오른쪽 자식 노드 중, 절댓값이 더 작은 노드와 교체
       => |왼쪽 자식| == |오른쪽 자식| 이면, 값이 더 작은 것 고려
*/

public class AbsHeap {
	private int[] arr;
	private int size;

	public AbsHeap(int length) {
		arr = new int[length];
	}

	public void add(int item) {
		arr[++size] = item;

		// Upward
		for (int i = size; i > 1; i /= 2) {
			int absParent = Math.abs(arr[i / 2]);
			int absChild = Math.abs(arr[i]);

			if (absParent > absChild)			// |부모| > |자식|
				swap(i / 2, i);
			else if (absParent == absChild) {	// |부모| == |자식|
				if (arr[i / 2] > arr[i])		// 부모 > 자식
					swap(i / 2, i);
			}
			else
				break;
		}
	}

	public int remove() {
		int root = arr[1];		// 기존 루트 노드 (최소 절댓값)
		arr[1] = arr[size--];	// 빈 루트 노드를 맨 뒤의 노드로 채움

		// Downward
		for (int i = 1; i * 2 <= size; ) {
			int absParent = Math.abs(arr[i]);
			int absLeftChild = Math.abs(arr[i * 2]);
			int absRightChild = Math.abs(arr[i * 2 + 1]);

			// |부모| < |자식| 또는
			// |부모| == |자식| && 부모 < 자식
			boolean isLeftSorted = (absParent < absLeftChild) ||
					(absParent == absLeftChild && arr[i] < arr[i * 2]);
			boolean isRightSorted = (absParent < absRightChild) ||
					(absParent == absRightChild && arr[i] < arr[i * 2 + 1]);

			if (isLeftSorted && isRightSorted)
				break;
			else if (absLeftChild < absRightChild) {	// 절댓값이 더 작은 자식과 부모 교체
				swap(i, i * 2);
				i = i * 2;
			}
			else if (absLeftChild > absRightChild) {	// 절댓값이 더 작은 자식과 부모 교체
				swap(i, i * 2 + 1);
				i = i * 2 + 1;
			}
			else {		// |왼쪽 자식| == |오른쪽 자식| => 값이 더 작은 자식과 부모 교체
				if (arr[i * 2] < arr[i * 2 + 1]) {
					swap(i, i * 2);
					i = i * 2;
				}
				else {
					swap(i, i * 2 + 1);
					i = i * 2 + 1;
				}
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
