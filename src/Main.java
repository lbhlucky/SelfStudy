// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;
import java.io.*;
import java.util.*;
// 입력 받기 By 류호석
public class Main{
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
    
    static void input(){

    }
}
// import java.util.Scanner;
// public class Main {
//     public int factorial(int num) {
//         if (num <= 0) {
//             return 1;
//         }
//         return num * this.factorial(num - 1);
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();

//         Main test = new Main();
//         System.out.println(test.factorial(n));

//         sc.close();

//     }
// }
