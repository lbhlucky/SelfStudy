import java.util.ArrayList;
/*
* 스택(Stack)
* - 데이터를 제한적으로 접근할 수 있는 구조
*   - 한쪽 끝에서만 자료를 넣거나 뺄 수 있는 구조
* - 가장 나중에 쌓은 데이터를 가장 먼저 빼낼 수 있는 구조
*   LIFO(Last-In, First-Out)
*
* push() : 데이터에 스택 쌓기
* pop() : 데이터를 스택에서 꺼내기
* 
* JAVA에서의 스택 자료 구조 사용학
*  - 자료구조와 알고리즘은 주요 개념을 이해하고, 알고리즘은 변수, 조건, 반복문으로
*    직접 구현할 수 있어야한다.
* 
* JAVA Stack 클래스
* - push(아이템) 메서드 : 아이템을 스택에 추가
* - pop() 메서드 : 스택에 마지막에 넣은 아이템을 리턴하고 해당 아이템 삭제
* */
public class Stack<T> {
    private ArrayList<T> stack = new ArrayList<T>();

    public void push(T item){
        stack.add(item);
    }

    public T pop(){
        if(stack.isEmpty()){
            return null;
        }
        return stack.remove(stack.size() -1);
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }
}
