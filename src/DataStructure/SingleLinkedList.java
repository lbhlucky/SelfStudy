package DataStructure;
/*
 * 링크드 리스트 (LinkedList)
 * - 연결리스트라고도 함
 * - 떨어진 곳에 존재하는 데이터를 연결해서 관리하는 데이터 구조
 *
 * 노드 : 데이터 저장 단위 (데이터, 포인터값)으로 구성
 * 포인터 : 각 노드 안에서, 다음이나 이전의 노드와 연결정보를 가지고 있는 공간
 * */

import java.util.LinkedList;

public class SingleLinkedList<T> {
    // 최초로 head 선언
    public Node<T> head = null;

    // 내부 클래스 선언
    public class Node<T> {
        T data;
        Node<T> next = null;

        // 생성자 생성
        public Node(T data){
            this.data = data;
        }
    }
    
    // 노드를 추가하는 메서드 생성
    public void addNode(T data){
        // 첫노드(head)일때
        if(head == null){
            head = new Node<T>(data);
        } else {    // head 가 아니라면
            Node<T> node = this.head;
            while(node.next != null) {  // 다음 노드가 있을떄
                node = node.next;
            }   // 여기 next = null 이다

            // next 는 다음 데이터의 주소를 저장하기 위함
            node.next = new Node<T>(data);
        }
    }
    
    // 노드의 모든 데이터 출력
    public void printAll(){
        if(head != null){
            // 노드는 현재 head 부터 시작
            Node<T> node = this.head;
            // head data 출력
            System.out.println(node.data);
            
            // head의 next 가 null 이 아니면
            // next 주소로 이동 후 출력
            while(node.next != null){
                node = node.next;
                System.out.println(node.data);
            }
        }
    }

    // 해당 데이터를 가진 노드를 찾는 메서드
    public Node<T> search(T data){
        if(this.head == null){  // head 가 null 이면
            // 이 링크드 리스트에 데이터는 없다.
            return null;
        } else {    // head 가 null 아니면
            Node<T> node = this.head;
            // head 부터 순차적 순회
            while(node != null){
                if(node.data == data) { // 해당  노드의 데이터가 내가 찾는 데이터라면
                    // 해당 노드 리턴
                    return node;
                } else { // 해당 노드의 데이터가 내가 찾는 데이터가 아니면
                    // 해당 노드의 다음 노드로 지정
                    node = node.next;;
                }
            }
            // 전체 노드를 찾아봤지만 해당 데이터 없다.
            return null;
        }
    }

    // 링크드리스트의 복잡한 기능1
    // 링크드리스트 데이터 사이에 데이터 추가
    public void addNodeInside(T data, T isData) {
        // 내가 찾고 있는 데이터의 노드
        Node<T> searchedNode = this.search(isData);

        // 해당 노드의 값이 없으면
        if(searchedNode == null){
            // 해당 노드에 데이터를 추가한다.
            this.addNode(data);
        } else { // 해당 노드의 값이 있으면
            // 해당 노드의 다음 노드 주소값 선언
            Node<T> nextNode = searchedNode.next;
            // 기존 노드의 다음 노드 주소 값에 새로운 노드 지정            
            searchedNode.next = new Node<T>(data);
            // 새로만들어진 노드의 next에 기존 노드의 다음 주소값 선언
            searchedNode.next.next = nextNode;
        }
    }

    // 링크드리스트의 복잡한 기능 2
    // 링크드리스트의 특정노드 삭제
    public boolean delNode(T isData) {
        if(this.head == null){  // head 가 null면
            // 데이터가 없다.
            // 삭제 실패
            return false;
        } else {    // head 가 null 아니면
            // 데이터가 있다
            Node<T> node = this.head;
            // head 부터 순차적 순회
            
            // 삭제해야할 데이터가 head면
            if(node.data == isData){
                // 해당 노드를 다음 노드의 주소값으로 연결
                // => 해당 노드는 삭제되고 다음 노드가 head 가된다.
                this.head = this.head.next;
                // 삭제 성공
                return true;
            } else {    // 삭제해야할 데이터 head 가 아니면
                while(node.next != null) {  // 다음 노드가 있는 동안
                    if(node.next.data == isData) {  // 다음 노드의 데이터가 삭제해야 할 데이터라면
                        // 다음 노드의 주소값 을 다다음 노드의 주소값으로 변경
                        // => 해당 노드 값은 삭제된다
                        node.next = node.next.next;
                        // 삭제 성공
                        return true;
                    }
                    // 다음 노드의 데이터가 삭제해야할 노드 가 아니라면
                    // 해당 노드 = 다음 노드로 지정
                    node = node.next;
                }
                // 삭제해야할 데이터가 없다.
                return false;
            }
        }
    }
    public static void main(String[] args) {
        SingleLinkedList<Integer> MyLinkedList = new SingleLinkedList<Integer>();
        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);

        MyLinkedList.printAll();
        System.out.println("------------------------------------------");

        // 새로운 링크드리스트 MyLinkedList1 생성
        SingleLinkedList<Integer> MyLinkedList1 = new SingleLinkedList<Integer>();

        // 새로운 링크드리스트에 1, 2, 3 데이터 추가
        MyLinkedList1.addNode(1);
        MyLinkedList1.addNode(2);
        MyLinkedList1.addNode(3);

        // 추가된 데이터 확인
        MyLinkedList1.printAll();
        System.out.println("------------------------------------------");

        // 데이터 1 뒤에 데이터 5 추가
        MyLinkedList1.addNodeInside(5,1);
        // 추가된 데이터 확인
        MyLinkedList1.printAll();
        System.out.println("------------------------------------------");

        // 데이터 3 뒤에 6 추가
        MyLinkedList1.addNodeInside(6,3);
        // 추가된 데이터 확인
        MyLinkedList1.printAll();
        System.out.println("------------------------------------------");

        // 없는 데이터를 찾도록 홰서 , 맨 마지막에 데이터 추가
        MyLinkedList1.addNodeInside(7,10);
        // 추가된 데이터 확인
        MyLinkedList1.printAll();
        System.out.println("------------------------------------------");

        // 테스트1 5개의 노드 생성
        SingleLinkedList<Integer> MyLinkedList2 = new SingleLinkedList<Integer>();
        MyLinkedList2.addNode(1);
        MyLinkedList2.addNode(2);
        MyLinkedList2.addNode(3);
        MyLinkedList2.addNode(4);
        MyLinkedList2.addNode(5);
        MyLinkedList2.printAll();
        System.out.println("------------------------------------------");

        // 테스트2 중간 노드(3) 삭제
        System.out.println("삭제 결과 : "+MyLinkedList2.delNode(3));
        MyLinkedList2.printAll();
        System.out.println("------------------------------------------");

        // 테스트3 맨 앞의 노드(헤드) 삭제
        System.out.println("삭제 결과 : "+MyLinkedList2.delNode(1));
        MyLinkedList2.printAll();
        System.out.println("------------------------------------------");

        // 테스트4 맨 마지막 노드 삭제
        System.out.println("삭제 결과 : "+MyLinkedList2.delNode(5));
        MyLinkedList2.printAll();
        System.out.println("------------------------------------------");

        // 테스트 5 없는 노드 삭제 시도
        System.out.println("삭제 결과 : "+MyLinkedList2.delNode(20));
        MyLinkedList2.printAll();
        System.out.println("------------------------------------------");
    }
}