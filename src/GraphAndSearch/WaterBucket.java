package GraphAndSearch;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WaterBucket {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // 물통
    // 난이도 4 : 탐색 떠올리기 쉽지 않음
    // 1 <= 물통의 크기 A, B, C <= 200

    // Q. 물을 옮겨 넣을 수 있을 때, C에 남아 있는 물의 가능한 경우
    //    단, A는 비어 있어야한다.

    // 접근 - 문제의 탐색 영역
    // a, b, c를 각각 A, B, C 물통에 남아 있는 물의 양이라고 하자.
    // 한 가지 상태 => 하나의 (a, b, c) 페어로 표현이 된다.
    // => 최대 200 * 200 * 200 = 8 * 10^6 가지 상태가 존재한다.

    // 물을 붓는 행위 => (a, b, c) 상태가 (a', b', c')로 바뀐다
    // 그렇다면, 한가지 상태가 "정점"이고,
    // 물을 부어서 다른 상태로 이동하는 것을 "간선"으로 해보자

    // 접근 - 새로운 그래프 정의

    // 접근 - 탐색 시작

    // 시간, 공간 복잡도 계산하기
    // <격자형 그래프>
    // 정점 : O(8*10^6)
    // 간선 : O(8*10^6*6)
    // BFS이든 DFS 이든 시간 복잡도는 모두 O(8*10^6)

    static int[] Limit;
    static boolean[] possible;
    static boolean[][][] visit;

    static void input() {
        Limit = new int[3];
        for (int i=0;i<3;i++) Limit[i] = scan.nextInt();
        // 탐색 한적이 있나없냐 정보 저장
        visit = new boolean[205][205][205];
        // possible 정답에 가능한 값들
        possible = new boolean[205];
    }

    // 물통 탐색 시작~
    static void bfs(int x1, int x2, int x3) {
        Queue<State> Q = new LinkedList<>();
        visit[x1][x2][x3] = true;
        Q.add(new State(new int [] {x1, x2, x3}));

        // BFS 과정 시작
        while(!Q.isEmpty()){
            State st = Q.poll();
            if(st.X[0] == 0){
                possible[st.X[2]] = true;
            }
            for(int from = 0 ; from < 3 ; from++){
                for(int to = 0 ; to < 3 ; to++){
                    if(from == to){
                        continue;
                    }else{
                        State nxt = st.move(from, to, Limit);
                        if(!visit[nxt.X[0]][nxt.X[1]][nxt.X[2]]){
                        visit[nxt.X[0]][nxt.X[1]][nxt.X[2]] = true;
                        Q.add(nxt);
                        }
                    }

                }
            }
        }
    }

    static void pro() {
        bfs(0, 0, Limit[2]);
        // 정답 계산하기
        for(int i =0; i<= Limit[2] ; i++){
            if(possible[i]){
                sb.append(i).append(' ');
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
// 물통의 현재 상태와 물을 붓는 행위를 관리하는 클래스
class State{
    int[] X;
    State(int[] _X){
        X = new int[3];
        for (int i=0;i<3;i++) X[i] = _X[i];
    }

    State move(int from,int to,int[] Limit){
        // from 물통에서 to 물통으로 물을 옮긴다.
        int[] nX = new int[]{X[0], X[1], X[2]};

        if(X[from]+X[to] >= Limit[to]){
            nX[from] -= Limit[to] - X[to];
            nX[to] = Limit[to];
        }else{
            nX[to] += nX[from];
            nX[from] = 0;
        }

        return new State(nX);
    }
};