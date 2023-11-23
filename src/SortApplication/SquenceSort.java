package SortApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SquenceSort implements Comparable<SquenceSort>{
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

    // 수열 정렬
    // 난이도 2
    // N <= 50
    
    // 문제 파악하기 - 정답의 최대치
    // 출력 : 0 ~ N-1
    // N <= 50
    // Int 범위면 충분

    // 접근 - 가장 쉬운 방법 O(N^2)
    // > 각 원소마다 어디로 갈지 일일이 찾는다.
    // >> 정렬된 A 배열의 원소 하나를 선택하여 B 배열을 순회하며 위치를 찾는다
    // >>> A 원소 하나마다 B 원소 하나하나를 비교해야 한다

    // 접근 - O(N log N)

    // 시간, 공간 복잡도 계산하기
    // 배열 정열  : O(N log N)
    // P 배열 구하기 : 순서대로 채우면 O(N)
    // 복잡도 : 시간 - O (N log N)
    //          공간 - O(N) 
    
    static int N;
    static int[] P;
    static SquenceSort[] B;

    static void input(){
        N = scan.nextInt();
        B = new SquenceSort[N];
        P = new int[N];

        for(int i = 0; i < N ; i++){
            B[i] = new SquenceSort();
            // SqenceSort의 정의에 맞게 B[i]에 값을 넣어주기
            B[i].num = scan.nextInt();
            B[i].idx = i;
        }
        
    }

    static void pro(){
        // B 배열 정렬하기 
        Arrays.sort(B);

        // B 배열의 값을 이용해서 P 배열 채우기
        for(int b_idx = 0 ; b_idx < N ; b_idx++){
            P[B[b_idx].idx] = b_idx;
        }

        // P 배열 출력하기
        for(int i = 0 ; i < N ; i++){
            sb.append(P[i]).append(' ');
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    int num, idx;

    @Override
    public int compareTo(SquenceSort other){
        // 정렬 조건에 맞게 정렬하기
        // 1. num의 비내림차순
        // 2. num이 같으면 idx 오름차순
        if(num != other.num){
            return num - other.num;
        }
        return idx-other.idx;
    }


}