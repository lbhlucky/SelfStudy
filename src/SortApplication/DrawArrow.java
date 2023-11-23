package SortApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class DrawArrow {
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

    // 화살표 그리기
    // 난이도 2
    // 점의 개수 N <= 5,000
    // 0 <= 점의 위치 <= 10^5 = 100,000
    // 1 <= 점의 색깔 <= N

    // 문제 파악하기 - 정답의 최대치
    // N = 5,000
    // 점 두개 => 2 * 10^5 만큼의 화살표길이
    // 색깔마다 이런 점들이 있다면?
    // => 5,000 / 2 쌍 만큼 만들 수 있다.
    // 즉, 모든 점마다 100,000 만큼의 길이를 갖는 화살표를 그리는 경우
    // 정답의 최대치는 : 5,000 * 100,000 = 5 * 10^8 (5억)
    // => Integer로 계산해도 충분

    // 접근 - 가장 쉬운 방법 O(N^2) - 시간 초과

    // 특성 : 각 원소마다, 가장 가까운 원소는 자신의 양 옆 중에 있다.
    // 접근 - 각 점마다, 자신과 가자 가까운 점을 빨리 찾기 O(N log N)
    // 1. 같은 색깔의 점들만 모아서 보자.
    //    > 색깔마다 ArrayList를 만들어주면, 총 배열의 크기는 O(N)이다.
    // 2. 모은 뒤에, 각 점마다 자신과 가장 가까운 것을 찾아야한다.
    // 3. 정렬의 특성을 이용하기 위해 점들의 위치를 오름차순 정렬한다. O(N log N)
    
    // 시간, 공간 복잡도 계산하기
    // 1. 색깔별로 모으기 : 공간 - ArrayList로 O(N)
    // 2. 배열 정렬 : O(N log N)
    // 3. 정답 계산 : 점 마다 좌우만 보니까 O(N)
    // 4. 복잡도 : 시간 - O(N log N)
    //             공간 - O(N)

    static int N;
    static ArrayList<Integer>[] a;

    static void input() {
        N = scan.nextInt();
        a = new ArrayList[N + 1];
        for (int color = 1; color <= N; color++) {
            a[color] = new ArrayList<Integer>();
        }
        for (int i = 1; i <= N; i++) {
            int coord, color;
            coord = scan.nextInt();
            color = scan.nextInt();
            //color 인 색깔의 점이 coord 에 놓여 있음
            a[color].add(coord);
        }
    }

    static int toLeft(int color, int idx) {        
        // 색깔이 color 인 점의 idx 번째에 있는 점이 왼쪽으로 화살표를 그린다면
        // 화살표의 길이를 return 하는 함수. 왼쪽에 점이 없다면 무한대를 return.
        if(idx == 0){   // 왼쪽에 더 이상 점이 없는 상태
            return Integer.MAX_VALUE;
        }
        return a[color].get(idx)-a[color].get(idx-1);

    }

    static int toRight(int color, int idx) {        
        // 색깔이 color 인 점의 idx 번째에 있는 점이 오른쪽으로 화살표를 그린다면
        // 화살표의 길이를 return 하는 함수. 오른쪽에 점이 없다면 무한대를 return.
        if(idx+1 == a[color].size()){   // 오른쪽에 더 이상 점이 없는 상태
            return Integer.MAX_VALUE;
        }
        return a[color].get(idx+1)-a[color].get(idx);
    }

    static void pro() {
        // 색깔별로 정렬하기
        for(int color = 1 ; color <= N ; color++){
            Collections.sort(a[color]);      
        }
        int ans = 0;
        for (int color = 1; color <= N; color++) {
            // 색깔 별로, 각 점마다 가장 가까운 점 찾아주기
            for(int i = 1 ; i <= a[color].size() ; i++){
                // int toLeft = 왼쪽 점 까지의 거리
                int left = toLeft(color, i);
                // int toLeft = 오른쪽 점 까지의 거리
                int right = toRight(color, i);

                ans += Math.min(left, right);
            }
        }
        // 정답 출력하기
        System.out.println("and : "+ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }


    
}
