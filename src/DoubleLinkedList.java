/*
* 더블링크드리스트
* - 이중 연결 리스트
* 장점 : 양방향으로 연결되어 있어 노드 탐색이 양쪽으로 모두 가능
* */
public class DoubleLinkedList<T> {
    // 변수 선언
    public Node<T> head = null;
    public Node<T> tail = null;
    
    // 내부 클래스 선언
    public class Node<T> {
        T data;
        Node<T> prev = null;
        Node<T> next = null;
        
        // 생성자 생성
        public Node(T data){
            this.data = data;
        }
    }
    
    // 데이터 추가 메서드
    public void addNode(T data){
        if(this.head == null){  // head 가 null이면 데이터가 없다.
            // 해당 head 에 새로운 노드 생성
            this.head = new Node<T>(data);
            // 해당 tail에 해당 head 추가
            this.tail = this.head;
        } else {
            // 현재 노드는 head
            Node<T> node = this.head;            
            while(node.next != null){ // 현재 노드에 다음 주소값이 있으면
                // 현재 노드에 다음 주소값 지정
                node = node.next;
            }
            // 다음 주소값에 새로운 노드 생성
            node.next = new Node<T>(data);
            // 다음 주소 값의 이전 주소값은 현재 노드
            node.next.prev = node;
            // 현재 주소 값의 다음 주소값은 다음 노드
            this.tail = node.next;
        }
    }
    
    // 전체 데이터 출력 메서드
    public void printAll(){
        if(this.head != null){  // 현재 헤드가 null 이 아니면(데이터가 있으면)
            // 노드는 헤드
            Node<T> node = this.head;
            // 헤드 출력
            System.out.println(node.data);
            while(node.next != null){   // 현재 노드의 다음 주소값이 있으면
                // 현재 노드에 다음 주소값 지정
                node = node.next;
                // 해당 노드의 데이터 출력
                System.out.println(node.data);
            }
        }
    }

    // 맨처음부터 검색하는 메서드
    public T searchFromHead(T isData){
        if(this.head == null){  // 해당 노드의 head 가 없으면
            // 데이터가 없다
            return null;
        }else{
            // 해당 데이터의 head 를 노드로 지정
            Node<T> node = this.head;            
            while(node != null){ // 노드가 있을 경우
                // 현재 노드의 데이터가 찾는 데이터라면
                if(node.data == isData){
                    // 현재 데이터 리턴
                    return node.data;
                }else {
                    // 현재 노드를 다음 주소값으로 지정
                    node = node.next;
                }
            }
            // 노드가 없다
            // 찾는 데이터가 없다
            return null;
        }
    }
    
    // 맨마지막부터 검색하는 메서드
    public T searchFromTail(T isData){
        // 찾는 노드의 head가 없다
        if(this.head == null){ 
            // 데이터가 없다
            return null;
        } else {
            // 찾는데이터의 tail을 현재 노드로 지정
            Node<T> node = this.tail;
            while(node != null){    // 노드가 있는경우
                if(node.data == isData){    // 현재 노드의 데이터가 찾는 데이터 라면
                    // 현재 노드의 데이터 리턴
                    return node.data;
                }else{  
                    // 현재 노드를 이전 주소값으로 지정
                    node = node.prev;
                }
            }
            // 찾는 데이터가 없다.
            return null;
        }
    }

    // 데이터를 임의의 노드 앞에 노드를 추가하는 메서드
   public boolean insertToFront(T existedData, T addData){
        if(this.head == null){
            this.head = new Node<T>(addData);
            this.tail = this.head;
            return true;
        }else if(this.head.data == existedData) {
            Node<T> newHead = new Node<T>(addData);
            newHead.next = this.head;
            this.head = newHead;
            return true;
        } else {
            Node<T> node = this.head;
            while(node != null){
                if(node.data == existedData){
                    Node<T> nodePrev = node.prev;

                    nodePrev.next = new Node<T>(addData);
                    nodePrev.next.next = node;

                    nodePrev.next.prev = nodePrev;
                    node.prev = nodePrev.next;
                    return true;
                } else {
                    node = node.next;
                }
            }
            return false;
        }
   }

    public static void main(String[] args) {
        // 더블 링크드리스트 생성
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<Integer>();
        // 데이터 추가
        doubleLinkedList.addNode(1);
        doubleLinkedList.addNode(3);
        doubleLinkedList.addNode(5);
        doubleLinkedList.addNode(7);
        doubleLinkedList.addNode(9);
        // 전체 데이터 출력
        doubleLinkedList.printAll();
        // head에서 부터 검색하는 메서드 활용
        // 찾는 데이터 있을 때
        System.out.println(doubleLinkedList.searchFromHead(3));
        // 찾는 데이터 없을 때
        System.out.println(doubleLinkedList.searchFromHead(2));
        // tail에서 부터 검색하는 메서드 활용
        // 찾는 데이터 있을 때
        System.out.println(doubleLinkedList.searchFromTail(5));
        // 찾는 데이터 없을 때
        System.out.println(doubleLinkedList.searchFromTail(6));

        System.out.println("----------------------------------------------------------");

        // 기존 데이터 2 앞에 새로운 데이터 3 추가
        System.out.println(doubleLinkedList.insertToFront(3,2));
        // 전체 데이터 출력
        doubleLinkedList.printAll();

        System.out.println("----------------------------------------------------------");

        // 기존에 없는 데이터 10 에 새로운 데이터 23 추가
        System.out.println(doubleLinkedList.insertToFront(10,23));
        // 전체 데이터 출력
        doubleLinkedList.printAll();
    }
}
