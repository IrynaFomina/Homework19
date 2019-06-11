import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IraHashMap<K, V> implements IHashStorage<K, V> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final float LOAD_FACTOR;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private int CAPACITY = DEFAULT_INITIAL_CAPACITY;
    private static int DEEP = 2;
    private ArrayList<Node>[] table1 = new ArrayList[DEFAULT_INITIAL_CAPACITY];

    public IraHashMap() {
        CAPACITY = DEFAULT_INITIAL_CAPACITY;
        LOAD_FACTOR = DEFAULT_LOAD_FACTOR;
    }

    public IraHashMap(float LOAD_FACTOR, int CAPACITY) {
        if (CAPACITY < 0)
            throw new IllegalArgumentException("CAPACITY should be greater than 0.");
        if (LOAD_FACTOR < 1) {
            throw new IllegalArgumentException("LOAD_FACTOR should have value from 0 to 1");
        }
        this.CAPACITY = getValidCapacityValue(CAPACITY);
        this.LOAD_FACTOR = LOAD_FACTOR;
    }

    private static int getValidCapacityValue (int capacity){
        int i = 10;
        while (capacity > i){
            i=i*10;
        }
        return i;
    }

    @Override
    public boolean add(K key, V value) {
        return addNode(table1, key, value);
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


    private boolean addNode(List[] array, K key, V value) {
        int hashCode = key.hashCode();
        int index = hashCode % CAPACITY;
        boolean flag;

        if (array[index] == null) {
            array[index] = new ArrayList<>(DEEP);
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

    private void resizeTable() {

        CAPACITY = CAPACITY * 10;
        ArrayList<Node>[] tempList = new ArrayList[CAPACITY];

        for (List<Node> bin : table1) {
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
