# 그래프와 탐색(Graph & Search)

## 그래프
   - 자료 구조로써 정점(Vertex)와 간선(Edge) 로 구성
     간선은 무방향/방향 + 가중치(weight)

### 점점의 차수(Degree)와 성질
   - deg(x) := 정점 x의 차수(degree), 정점 x에 연결된 간선의 수
   - ∑deg(x) = 모든 정점의 차수의 합 = 간선의 개수의 2배!
   위 식을 알아야 그래프와 관련된 알고리즘의 시간복잡도 계산시 유용

### 그래프를 저장하는 방법
1. 인접 행렬(Adjacency Matrix)
   - int[][] adj = int new[v][v];
   - O(V^2) 만큼의 공간 필요
   - A 에서 B로 이동 가능? 가중치 얼마?
     => O(1)
   - 정점 A에서 갈 수 있는 정점들은?
     => O(V)
   만약, V = 10만, E = 50만 이라면
   V^2 = 100억 = 10G!!!!!!!!!!
※ 구현하기는 쉽지만 메모리 이슈가 있음!!

2. 인접 리스트(Adjacency List)
   - ArrayList<ArrayList<Integer>> adj;
   - O(E) 만큼의 공간 필요
   - A 에서 B로 이동 가능? 가중치 얼마?
     => O(min(deg(A),deg(B)))
   - 정점 A에서 갈 수 있는 정점들은?
     => O(deg(A))
  
   만약, V = 10만, E = 50만 이라면
   5 * 10^5 = 500K

### 그래프 문제의 핵심!
   - 정점(Vertex) & 간선(Edge)에 대한 정확한 정의
   - 간선 저장 방식을 확인하기

## 탐색 
### 그래프에서의 탐색이란?
   - 탐색 : 시작점에서 간선을 0개 이상 사용해서 갈 수 있는 정점들은 무엇인가?

1. 깊이 우선 탐색(Depth First Search, DFS)
![img_1.png](img_1.png)

2. 너비 우선 탐색(Breadth First Search, BFS)
![img_2.png](img_2.png)