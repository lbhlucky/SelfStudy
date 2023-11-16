import java.util.Scanner;

public class Test {
    public int factorial(int num) {
        if (num <= 0) {
            return 1;
        }
        return num * this.factorial(num - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Test test = new Test();
        System.out.println(test.factorial(n));

    }
}
