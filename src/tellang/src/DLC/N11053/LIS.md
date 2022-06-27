#DLC.N11053

* author : tellang
* date : 2022-06-27

---

매번 탐색을 해나갈 때 마다 `LIS`의 길이가 같다면  
이때 `마지막 값의 크기가 작은 것`의 정보를 유지하면서 탐색을 이어나가야 된다.

> 같은 크기의 증가수열에서 마지막 값 중 최솟값만 기억하면 문제를 풀어낼 최적화된 단서를 찾을 수 있다.

### Binary Search를 이용한 방법

0. 기준 배열과 `LIS` 배열을 준비하고, 첫 원소를 `LIS`배열에 넣어준다
1. 새로운 숫자 `num`이 현재 `memo[len]`의 값보다 크다면 새로운 `LIS`가 갱신된다.
   ```java
    if(num > lastNum)
        memo[++len] = num
    ```
2. 새로운 숫자 `num`이 수열의 최솟값과 최댓값의 사이에 있는 값이라면 **이진탐색을 통해 기존 값을 바꿔준다**.
    - 이진탐색을 통해 `memo[i-1] < num <= memo[i]`인 곳을 찾아 `memo[i] = num`을 갱신해준다.
   ```java
    else {
        int i = binarySearch(0, n, num);
        memo[i] = num;
    }
    ````   
3. 모든 탐색이 끝난 후 len이 해당 배열의 LIS 길이가 된다.