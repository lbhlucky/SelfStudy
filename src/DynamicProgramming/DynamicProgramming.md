<h1>동적 프로그래밍(Dynamic Programming)</h1>

-- 문제의 크기를 변화하면서 정답을 계산하는데,             
  작은 문제의 결과를 이용해서 큰 문제의 정답을 빠르게 계산하는 알고리즘

<h5> < 동적 프로그래밍 시나리오 ></h5>

1. 문제가 원하는 정답을 찾기 위해 가장 먼저 완전 탐색(Brute-Force) 접근을 시도해본다.
2. 근데, 완전 탐색 과정에서 탐색하게 되는 경우가 지나치게 많아서 도저히 안 될 것 같다.
3. 이럴 때, 모든 경우를 빠르게 탐색하는 방법으로 Dynamic Programming 접근을 시도해볼 수 있다.
=> 규격화된 문제 풀이 순서를 외워서 훈련해야 한다.

<h3> < 동적 프로그래밍 푸는 순서 ></h3>

1. 풀고 싶은 가짜 문제 정의   
   예시)  
   i. Dy[i] := 1 ~ i 번 원소에 대해서 조건을 만족하는 경우의 수   
   ii. Dy[i][j] := 1 ~ i 번 원소에 대해서 조건을 만족하는 최댓값   
   iii. Dy[i][j] := 수열 A[1 ... i]와 수열 B[1 ... j]에 대해서 무언가를 계산한 값 
2. 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    예시)   
    진짜 문제    
    => 수열 A[1...N]에서 조건을 만족하는 부분 수열의 개수   
    가짜 문제   
    Dy[i] : 수열 A[1 ... i]에서 조건을 만족하는 부분 수열의 개수<br>   
가짜 문제를 푼다면 Dy[1], Dy[2], ..., Dy[N]을 모두 계산했을 것이니까,   
Dy[N]에 적혀있는 값이 곧 진짜 문제가 원하는 값이다.
3. 초기값은 어떻게 되는가?   
   => 가장 작은 문제 해결하기
4. 점화식 구해내기   
   => 3 에서 계산한 것을 기반으로, 점점 더 큰 문제들을 해결하면서   
      Dy 배열을 가득 계산하는 과정
5. 진짜 문제 정답 출력하기   
   => 1 ~ 4 가 성공적으로 끝난다면 Dy 배열을 이용하여 진짜 문제 해결하기
