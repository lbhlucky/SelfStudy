package DataStructure;
/*
 * 트리(Tree) 구조
 * - 트리 : Node와 Branch를 이용해서, 사이클을 이루지 않도록 구성한 데이터 구조
 *   이진 트리(Birary Tree) 구조로 탐색(검색) 알고리즘 구현에 많이 사용됨
 *
 * ` Node : 트리에서 데이터를 저장하는 기본 요소 (데이터와 다른 연결된 노드에 대한 Branch 정보 포함)
 * ` Root Node : 트리 맨 위에 있는 노드
 * ` Level : 최상위 노드를 Level 0이라 했을 때, 하위 Branch로 연결된 노드의 긾이를 나타냄
 * ` Parent Node : 어떤 노드의 다음 레벨에 연결된 노드
 * ` Child Node : 어떤 노드의 상위 레벨에 연결된 노드
 * ` Leaf Node(Terminal Node) : Child Node 가 하나도 없는 노드
 * ` Sibling(Brother Node) : 동일한 Parent Node를 가진 노드
 * ` Depth : 트리에서 Node가 가질 수 있는 최대 Level
 *
 * 이진트리(Birary Tree) : 노드의 최대 Branch가 2인 트리
 * 이진탐색트리 : 이진 트리에 다음과 같은 추가적인 조건이 있는 트리
 * - 왼쪽 노드는 해당 노드보다 작은값, 오른쪽 노든느 해당 노드보다 큰값을 가지고 있음
 * */

public class Tree {
    // 전체 노드를 관리하는 객체 변수 선언
    Node head = null;
    public class Node{

        Node left;
        Node right;
        int value;

    // 생성자 선언
        public Node(int data){
            this.value = data;
            this.left = null;
            this.right = null;
        }
    }

    // 데이터를 넣는 메서드 추가
    public boolean insertNode(int data){
        // CASE1 : Node 가 하나도 없을 때
        if(this.head == null){
            this.head = new Node(data);
        } else {
          // CASE2 : Node가 하나 이상 들어가 있을 때
            Node findNode = this.head;
            while(true){
                // CASE2-1 : 현재 Node의 왼쪽에 Node가 들어가야 할 때
                if(data < findNode.value){
                    if(findNode.left != null){
                        findNode = findNode.left;
                    } else {
                        findNode.left = new Node(data);
                        break;
                    }
                } else {
                    // CASE2-2 : 현재 Node의 오른쪽에 Node가 들어가야 할 때
                    if(findNode.right != null){
                        findNode = findNode.right;
                    } else {
                        findNode.right = new Node(data);
                        break;
                    }
                }
            }
        }
        return true;
    }
    
    // 이진 탐색 트리
    // 탐색 메서드 생성
    public Node search(int data){
        // Case 1 : Node 가 하나도 없을 때
        if(this.head == null){
            return null;
        } else {
        // Case 2 : Node 가 하나 이상 있을 때
            Node findNode = this.head;
            while(findNode != null){
                if(findNode.value == data){
                    return findNode;
                }else if(data < findNode.value){
                    findNode = findNode.left;
                } else {
                    findNode = findNode.right;
                }
            }
            return null;
        }
    } 
    // 삭제 메서드 생성
    public boolean delete(int value){
        // 1. 삭제할 Node 탐색
        // - 삭제할 Node가 없는 경우도 처리해야 함
        //   >> 삭제할 Node 가없는 경우 false 리턴, 메서드 종료
        boolean searched = false;
        
        Node currParentNode = this.head;
        Node currNode = this.head;

        // 예외케이스(코너케이스)1 : Node 가 하나도 없을 때
        if(this.head == null){
            return false;
        } else {
            // 예외케이스(코너케이스)2 : Node 가 단지 하나만 있고, 해당 Node가 삭제할 Node 일 때
            if(this.head.value == value && this.head.left == null && this.head.right == null){
                this.head = null;
                return true;
            }

             while(currNode != null){
                 if(currNode.value == value){
                    searched = true;
                    break;
                } else if(value < currNode.value){
                    currParentNode = currNode;
                    currNode = currNode.left;
                } else {
                    currParentNode = currNode;
                    currNode = currNode.right;
                }
            }
            if(searched == false){
                return false;

            }
        }
        // 여기까지 실행되면
        // CurrNode 에는 해당 데이터를 가지고 있는 Node,
        // CurrParentNode에는 해당 데이터를 가지고 있는 Node의 부모 Node

        // Case 1 : 삭제할 Node가 Leaf Node인 경우
        if(currNode.left == null && currNode.right == null){
            if(value < currParentNode.value){
                currParentNode.left = null;
                currNode = null;
            } else {
                currParentNode.right = null;
                currNode = null;
            }
            return true;
        // Case 2-1 : 삭제할 Node가 Child Node를 한 개 가지고 있을 경우(왼쪽에 Child Node 있을 때)
        } else if (currNode.left != null && currNode.right == null) {
            if(value < currParentNode.value){
                currParentNode.left = currNode.left;
                currNode = null;
            } else {
                currParentNode.right = currNode.left;
                currNode = null;
            }
            return true;
        // Case 2-2 : 삭제할 Node가 Child Node를 한 개 가지고 있을 경우(오른쪽에 Child Node 있을 때)
        } else if (currNode.left == null && currNode.right != null) {
            if(value < currParentNode.value){
                currParentNode.left = currNode.right;
                currNode = null;
            } else {
                currParentNode.right = currNode.right;
                currNode = null;
            }
            return true;
        // Case 3 : 삭제할 Node가 Child Node를 두 개 가지고 있을 경우
        // Case 3-1 : 삭제할 Node 가 Child Node 를 두 개 가지고 있고, (삭제할 Node 가 부모 Node 의 왼쪽에 있을 때)
        } else {
            // 삭제할 Node 가 부모 Node의 왼쪽에 있을 때
            if(value < currParentNode.value){
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;

                while(changeNode.left != null){
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }
                // 여기까지 실행되면, changeNode 에는 삭제할 Node의 오른쪽 Node 중에서
                // 가장 작은 값은 값을 가진 Node 가 들어있음

                if(changeNode.right != null){
                // Case 3-1-1 : chageNode의 오른쪽 Child Node 가 있을 때
                    changeParentNode.left = changeNode.right;
                } else {
                // Case 3-1-2 : chageNode의 Child Node가 없을 때
                    changeParentNode.left = null;
                }
                // currParentNode 의 왼쪽 Child Node에, 삭제할 Node 의 오른쪽 자식 중
                // 가장 작은 값을 가진 ChangeNode를 연결
                currParentNode.left = changeNode;

                // parentNode 의 왼쪽 Child Node 가 현재, changeNode 이고,
                // changeNode의 왼쪽/오른쪽 Child Node를 모두, 삭제할 currNode의
                // 기존 왼쪽/오른쪽 Node로 변경
                changeNode.right = currNode.right;
                changeNode.left = currNode.left;

                currNode = null;
            // Case3-2: 삭제할 Node 가 Child Node 를 두 개 가지고 있고, (삭제할 Node 가 부모 Node 의 오른쪽에 있을 때)
            } else {
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;

                while(changeNode.left != null){
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }
                // 여기까지 실행되면, changeNode 에는 삭제할 Node의 오른쪽 Node 중에서
                // 가장 작은 값은 값을 가진 Node 가 들어있음

                if (changeNode.right != null) {
                    // Case 3-2-1: changeNode 의 오른쪽 Child Node 가 있을 때
                    changeParentNode.left = changeNode.right;
                } else {
                    // Case 3-2-2: changeNode 의 Child Node 가 없을 때
                    changeParentNode.left = null;
                }

                // currParentNode 의 오른쪽 Child Node 에, 삭제할 Node 의 오른쪽 자식중,
                // 가장 작은 값을 가진 changeNode 를 연결
                currParentNode.right = changeNode;

                // parentNode 의 왼쪽 Child Node 가 현재, changeNode 이고,
                // changeNode 의 왼쪽/오른쪽 Child Node 를 모두, 삭제할 currNode 의
                // 기존 왼쪽/오른쪽 Node 로 변경
                changeNode.right = currNode.right;
                changeNode.left = currNode.left;

                currNode= null;
            }

            return true;

        }

    }


    public static void main(String[] args) {
        Tree myTree = new Tree();
        myTree.insertNode(2);
        myTree.insertNode(3);
        myTree.insertNode(1);
        myTree.insertNode(4);
        System.out.println(myTree.insertNode(6));

        Node testNode = myTree.search(3);
        System.out.println(testNode.value);
        System.out.println(testNode.right.value);

        System.out.println("------------------------------------------");

        // Case3-1: 삭제할 Node가 Child Node를 두 개 가지고 있을 경우
        Tree myTree1 = new Tree();
        myTree1.insertNode(10);
        myTree1.insertNode(15);
        myTree1.insertNode(13);
        myTree1.insertNode(11);
        myTree1.insertNode(14);
        myTree1.insertNode(18);
        myTree1.insertNode(16);
        myTree1.insertNode(19);
        myTree1.insertNode(17);
        myTree1.insertNode(7);
        myTree1.insertNode(8);
        myTree1.insertNode(6);
        System.out.println("15삭제 : "+myTree1.delete(15));
        System.out.println("HEAD: " + myTree1.head.value);
        System.out.println("HEAD LEFT: " + myTree1.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree1.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree1.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree1.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree1.head.right.left.value);
        System.out.println("HEAD RIGHT RIGHT: " + myTree1.head.right.right.value);

        System.out.println("HEAD RIGHT RIGHT LEFT: " + myTree1.head.right.right.left.value);
        System.out.println("HEAD RIGHT RIGHT RIGHT: " + myTree1.head.right.right.right.value);
    }
}
