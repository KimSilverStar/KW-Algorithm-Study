package DataStructure.Stack.BOJ2800;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Set;
import java.util.TreeSet;

/*
1. 풀이
 1) Stack 으로 짝을 이루는 괄호 index (Pair) 저장
 2) 재귀 함수로 괄호 쌍을 제거 or 제거 안한 문자열 구하기
   - 종료 조건: 제거한 괄호 쌍 개수 == 전체 괄호 쌍 개수
     => Set 에 결과 수식 문자열 저장
   - 재귀 호출
     => 현재 괄호 쌍을 제거하고, 재귀 호출
     => 현재 괄호 쌍을 제거하지 않고, 재귀 호출
 !!! 괄호 쌍을 제거하지 않고 재귀 호출하는 케이스가 존재하여,
     괄호 쌍을 1개도 삭제하지 않아서 (결과 수식 == 입력 수식) 인 상황 발생
     => 모든 재귀 호출이 끝나고,
        결과 수식들이 저장된 Set 에서 입력 수식을 제거해줘야 함

2. 자료구조
 - String: 입력 수식
 - char[]: 입력 수식에서 괄호 쌍 제거 작업
 - List<Pair>, ArrayList<Pair>: 매칭한 괄호의 index 쌍들을 저장
 - Stack<Integer>: 괄호 쌍 매칭, Stack 에 여는 괄호 '('의 index 저장
 - Set<String>, TreeSet<String>: 결과 수식 저장

3. 시간 복잡도
 - 괄호 쌍 매칭: O(n)		(n: 입력 수식 문자열 길이)
   => 수식 길이 최대값 대입: 200
 - 재귀 함수: F(n) = F(2^n)	(n: 괄호 쌍 개수)
   => 괄호 쌍 개수 최대값 대입: 2^10
 => 총 시간 복잡도: 200 + 2^10 = 1,224 << 1억 (1초)
*/

class Pair {
	private int startIdx;
	private int endIdx;

	public Pair(int startIdx, int endIdx) {
		this.startIdx = startIdx;
		this.endIdx = endIdx;
	}

	public int getStartIdx() { return startIdx; }
	public int getEndIdx() { return endIdx; }
}

public class Main {
	static String input;		// 입력 수식
	static char[] expression;
	static List<Pair> pairs = new ArrayList<>();		// 괄호 index 쌍들 저장
	static Stack<Integer> stack = new Stack<>();		// 괄호 쌍 매칭
	static Set<String> set = new TreeSet<>();			// 결과 수식 저장 및 정렬

	/* 입력 수식에서 괄호 쌍 매칭 */
	static void matchBrackets() {
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			
			if (ch == '(')
				stack.push(i);		// 여는 괄호 '('의 index 를 Stack 에 push
			else if (ch == ')')
				pairs.add(new Pair(stack.pop(), i));
		}
	}

	/* 괄호 쌍의 번호를 인자로 받아서 재귀 호출 */
	static void solution(int pairIdx) {
		if (pairIdx == pairs.size()) {		// 결과 수식을 Set 에 추가
			String output = new String(expression);
			output = output.replaceAll(" ", "");	// 괄호 제거 처리한 공백을 제거
			set.add(output);
			return;
		}

		// 1) 해당 괄호 쌍 제거하고 재귀호출
		Pair pair = pairs.get(pairIdx);
		expression[pair.getStartIdx()] = ' ';
		expression[pair.getEndIdx()] = ' ';
		solution(pairIdx + 1);

		// 2) 해당 괄호 쌍 제거하지 않고 재귀호출
		expression[pair.getStartIdx()] = '(';
		expression[pair.getEndIdx()] = ')';
		solution(pairIdx + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringBuilder sb = new StringBuilder();

		input = br.readLine();
		expression = input.toCharArray();
//		expression = new char[input.length()];
//		for (int i = 0; i < input.length(); i++)
//			expression[i] = input.charAt(i);

		matchBrackets();
		solution(0);		// Init Call

		set.remove(input);		// 예외 처리: 괄호가 아예 제거 안된 수식은 제외

		// Set 에 저장된 결과 수식들 모두 출력
		for (String str : set)
			sb.append(str).append("\n");

		System.out.println(sb.toString());
	}
}
