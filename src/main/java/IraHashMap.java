import java.util.ArrayList;
import java.util.List;

public class IraHashMap<K, V> implements IHashStorage<K, V> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final int MAX_CAPACITY = 1000000000;
    private static int DEEP = 2;
    private final float load_factor;
    private int capacity = DEFAULT_INITIAL_CAPACITY;
    private ArrayList<Node>[] table1 = new ArrayList[DEFAULT_INITIAL_CAPACITY];

    public IraHashMap() {
        capacity = DEFAULT_INITIAL_CAPACITY;
        load_factor = DEFAULT_LOAD_FACTOR;
    }

    public IraHashMap(float load_factor, int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("capacity should be greater than 0.");
        if (load_factor < 1) {
            throw new IllegalArgumentException("load_factor should have value from 0 to 1");
        }
        this.capacity = getValidCapacityValue(capacity);
        this.load_factor = load_factor;
    }

    private static int getValidCapacityValue(int capacity) {
        int i = 10;
        while (capacity > i) {
            i = i * 10;
        }
        return i;
    }

    @Override
    public boolean add(K key, V value) {
        return addNode(table1, key, value);
    }

    @Override
    public V get(K key) {
        int hashCode;
        int index;
        V value = null;
        if (key != null) {
            hashCode = key.hashCode();
            index = hashCode % capacity;
        } else {
            index = 0;
        }

        for (Node item : table1[index]) {
            if (item.getKey() == key) {
                value = (V) item.getValue();
                break;
            }
        }
        return value;
    }

    private boolean addNode(List[] array, K key, V value) {
        int hashCode;
        int index;
        if (key != null) {
            hashCode = Math.abs(key.hashCode());
            index = hashCode % capacity;
        } else {
            index = 0;
        }

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
        if (array[index].size() > DEEP * load_factor) {
            resizeTable();
        }
        return flag;
    }

    private void resizeTable() {
        if (capacity != MAX_CAPACITY) {
            capacity = capacity * 10;
            ArrayList<Node>[] tempList = new ArrayList[capacity * 10];

            for (List<Node> bin : table1) {
                if (bin != null) {
                    bin.forEach(node -> addNode(tempList, (K) node.getKey(), (V) node.getValue()));
                }
            }
            table1 = tempList;
        }
        else
            System.out.println("Capacity has max value");
    }

    private Node findNode(List<Node> bin, K key) {
        for (Node item : bin) {
            if (item.getKey() == key) {
                return item;
            }
        }
        return null;
    }
}
