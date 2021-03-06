# 광운대학교 알고리즘 스터디

<br/>
<br/>

## 😎 일주일에 12문제 풀기 도전!
1. 일주일에 인당 3문제 씩 해당 주차의 유형 내에서 문제 제출

-   너무 쉬운 Bronze 문제는 제출 금지
-   수학, 기하학 같은 문제는 제출 금지

2. 12문제를 완벽하게 풀지 못할 수 있지만, 최대한 노력할 것
3. 개인이 제출한 문제는 코드 리뷰 때 설명 가능할 정도로 숙지할 것

<br/>
<br/>

## 👨‍💻 주차 별 문제 유형
1. 1주차 : 스택/큐, 덱, 힙, 우선순위 큐

2. 2주차 : 문자열, 브루트포스, DFS, BFS

3. 3주차 : DFS, BFS, 트리

4. 4주차 : 트리, 비트 마스킹, 그리디, 분할 정복

5. 5주차 : 구현, 시뮬레이션 + 자유주제

6. 6주차 : 투 포인터, DP, HashMap

7. 7주차 : 다익스트라, 벨만 포드, 플로이드-와샬, 문자열

<br/>
<br/>

## 👨‍🏫 알고리즘 풀이 진행 방식
-   백준 solved.ac에서 문제 선정
-   주차 별로 정해진 유형에서 인당 각 4문제씩 제출
    <br/>
    => 주차당 총 12문제를 각자 풀이
-   본인이 제출한 문제는 완벽히 숙지
-   언어는 자유롭게 사용하나, 다른 언어 사용자에게 로직을 설명할 수 있어야 함
-   ⚠️ 본인이 제출한 문제는 필히 SOL을 제시해야 하며, 이외의 문제는 풀어보고 질문하기

<br/>
<br/>

## ✅ 참고 사이트
-   [백준](https://www.acmicpc.net/)
-   [solved.ac](https://solved.ac/)
-   [프로그래머스](https://programmers.co.kr/learn/challenges)

<br/>
<br/>

## 👨‍🎓 팀원 소개
#### [Eun-Sung Kim](https://github.com/KimSilverStar)

-   Blog: https://velog.io/@silver_star
-   Email: dmstjd365@naver.com

<br/>

#### [Seung-Soo Shin](https://github.com/GoojungMyeon)

-   Blog:
-   Email: tmd0707@naver.com

<br/>

#### [Jeongsang Im](https://github.com/Oneul1213)

-   Blog: https://velog.io/@oneul1213
-   Email: toda1213@naver.com

<br/>

#### [Taeeon Park](https://github.com/)

-   Blog: https://velog.io/@tellang
-   Email: 

<br/>
<br/>

## 🚩 진행 순서
1. 입력을 보고 시간 복잡도 확인

-   e^8 = 1초 (0이 8개)
-   입력 값 범위
    -   ~ 100: 완탐 가능
    -   ~ 400: O(N^3) 가능 (플로이드 등)
    -   ~ 10,000: O(N^2)도 불가능

2. 40분 정도 로직 생각, 반례 있는지 확인
3. 코드 작성
4. 디버깅

<br/>
<br/>

## ❗ 런타임 에러 발생하는 경우
1. 배열에 할당된 크기를 넘어서 접근했을 때
2. 전역 배열의 크기가 메모리 제한을 초과할 때
3. 지역 배열의 크기가 스택 크기 제한을 넘어갈 때
4. 0으로 나눌 떄
5. 라이브러리에서 예외를 발생시켰을 때
6. 재귀 호출이 너무 깊어질 때
7. 이미 해제된 메모리를 또 참조할 때
8. 프로그램(main 함수)이 0이 아닌 수를 반환했을 때
9. 반환형이 void가 아닌 함수에서 아무런 값을 반환하지 않았을 때

<br/>
<br/>

## ❌ "틀렸습니다" 발생하는 경우
1. 특정 테스트 케이스 확인하기
2. 예외처리
