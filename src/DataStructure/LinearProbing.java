package DataStructure;
import java.util.HashMap;

/*
 * 2. Linear Probing 기법
 * - 폐쇄 해슁 또는 Close Hashing 기법 중 하나
 *   해쉬테이블 저장공간 안에서 충돌문제를 해결하는 기법
 * - 충돌이 일어나면, 해당 해쉬주소(address)의 다음주소(address)부터 맨 처음 나오는 빈공간에 저장하는 기법
 *   => 저장공간 활용도를 높이기 위한 기법
* */
public class LinearProbing {
    public Slot[] hashTable;

    public LinearProbing(Integer size) {
        this.hashTable = new Slot[size];
    }

    public class Slot {
        String key;
        String value;
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {  // 해당 주소에 값이 있으면
            if(this.hashTable[address].key == key){
                this.hashTable[address].value = value;
                return true;
            } else {
                Integer currAddress = address+1;
                while(this.hashTable[currAddress] != null){
                    if(this.hashTable[currAddress].key == key){
                        this.hashTable[currAddress].value = value;
                        return true;
                    } else {
                        currAddress++;
                        if(currAddress >= this.hashTable.length){
                            return false;
                        }
                    }
                }
                this.hashTable[currAddress] = new Slot(key, value);
                return true;
            }
        } else {
            this.hashTable[address] = new Slot(key, value);
        }
        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if (this.hashTable[address].key == key) {
                return this.hashTable[address].value;
            } else {
//                Integer currAddress = address + 1;    // 해당 address가 마지막 일 수 도 있으니
                Integer currAddress = address; // 수정
                while (this.hashTable[currAddress] != null) {
                    if (this.hashTable[currAddress].key == key) {
                        return this.hashTable[currAddress].value;
                    } else {
                        currAddress++;
                        if (currAddress >= this.hashTable.length) {
                            return null;
                        }
                    }
                }
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        LinearProbing mainObject = new LinearProbing(20);
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("fun-coding", "01033334444");
        mainObject.saveData("hello","00123451112");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        System.out.println(mainObject.getData("fun-coding"));
        System.out.println(mainObject.getData("Dave"));

        System.out.println("---------------------------------------------");

        // JAVA HashMap
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "사과");
        map1.put(2, "바나나");
        map1.put(3, "포도");

        System.out.println(map1.get(3));

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("DaveChoi","01032345232");
        map2.put("Dave","01042365632");
        map2.put("D.Choi","01035355112");
        System.out.println(map2.get("Dave"));
    }
}
