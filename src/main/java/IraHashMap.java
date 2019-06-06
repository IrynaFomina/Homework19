import java.util.ArrayList;
import java.util.List;

public class IraHashMap<K, V> implements IHashStorage<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static int CAPACITY = DEFAULT_INITIAL_CAPACITY;
    private static int DEEP = 2;
    private ArrayList<Node>[] table1 = new ArrayList[DEFAULT_INITIAL_CAPACITY];

    public IraHashMap() {
        CAPACITY = DEFAULT_INITIAL_CAPACITY;
    }

    @Override
    public boolean add(K key, V value) {
        return addNode(table1,key,value);
    }

    @Override
    public V get(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % CAPACITY;
        V value = null;
        for (Node item : table1[index]) {
            if (item.getKey().equals(key)) {
                value = (V) item.getValue();
                break;
            }
        }
        return value;
    }


    private boolean addNode(List[] array,K key, V value) {
        int hashCode = key.hashCode();
        int index = hashCode % CAPACITY;
        boolean flag;

        if (array[index] == null) {
            array[index] = new ArrayList<>(0);
            flag = array[index].add(new Node(key, value));
        } else if (findNode(array[index], key) != null) {
            findNode(array[index], key).setValue(value);
            flag = true;
        } else {
            flag = array[index].add(new Node(key, value));
        }

//        Check a bin size
        if (array[index].size() > DEEP * LOAD_FACTOR) {
            resizeTable();
        }
        return flag;
    }

    private void resizeTable(){

            CAPACITY = CAPACITY * 10;
            ArrayList<Node>[] tempList = new ArrayList[CAPACITY];

            for (List<Node> bin: table1) {
                if (bin != null) {
                    bin.forEach(node -> addNode(tempList, (K) node.getKey(), (V) node.getValue()));
                }
            }
            table1 = tempList;
    }

    private Node findNode(List<Node> bin, K key) {
        for (Node item : bin) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }
}
