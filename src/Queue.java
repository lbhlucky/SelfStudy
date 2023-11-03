import java.util.ArrayList;

public class Queue<T> {
    private ArrayList<T> queue = new ArrayList<T>();

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {

        /*
         * 큐(Queue)
         * - 줄을 서는 행위와 유사
         * - 가장 먼저 넣은 데이터를 가장 먼저 꺼낼 수 있는 구조
         * - FIFO(First-In,First-Out) 또는 LILO(Last-In,Last-Out) 방식으로 스택과 꺼네는 순서가 반대
         *
         * Enqueue : 큐에 데이터를 넣는 기능
         * Dequeue : 큐에서 데이터를 꺼내는 기능
         *
         * JAVA 에서는 기본적으로 java.util 패키지에 Queue 클래스 제공
         *  - Enqueue에 해당하는 기능으로 Queue 클래스에서는 add(value) 또는 offer(value) 메서드 제공
         *  - Dequeue에 해당하는 기능으로 Queue 클레스에서는 poll() 또는 remove() 메서드 제공
         *  - Queue 클래스에 데이터 생성을 위해서는 LinkedList 클래스를 사용해야함
         *
         * 멀티 테스킹을 위한 프로세스 스케쥴링 방식을 구현하기 위해 많이 사용됨
         * */
        Queue<Integer> mq = new Queue<Integer>();
        mq.enqueue(1);
        mq.enqueue(2);
        mq.enqueue(3);

        System.out.println(mq.dequeue());
        System.out.println(mq.dequeue());
        System.out.println(mq.dequeue());
    }

}

