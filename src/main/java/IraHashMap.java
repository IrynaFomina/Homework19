import java.util.ArrayList;
import java.util.List;

public class IraHashMap<K, V> implements IHashStorage<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static int CAPACITY = DEFAULT_INITIAL_CAPACITY;
    private static final int MIN_DEEP = 2;
    private static int DEEP = 2;
    ArrayList<Node>[] table1 = new ArrayList[DEFAULT_INITIAL_CAPACITY];


    @Override
    public boolean add(K key, V value) {
        int hashCode = key.hashCode();
        int index = hashCode % 10;
        boolean flag;
        if (table1[index] == null) {
            table1[index] = new ArrayList<>(0);
            flag = table1[index].add(new Node(key, value));
        }
        else if (findNode(table1[index], key) != null) {
            findNode(table1[index], key).setValue(value);
            flag = true;
        }
        else {
            flag = table1[index].add(new Node(key, value));

        }

//        if (table.get(index).size() > DEEP * LOAD_FACTOR)
//        {
//
//        }
//        list.add(index, value);
        return flag;
    }

    @Override
    public V get(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % 10;
        Node node = null;
        V value = null;
        for (Node item : table1[index]) {
            node = item;
            if (node.getKey().equals(key)) {
                value = (V) node.getValue();
                break;
            }
        }
        return value;
    }

    private Node findNode(List<Node> bin, K key) {
        Node node = null;
        for (Node item : bin) {
            node = item;
            if (node.getKey().equals(key)) {
//                value = (V) node.getValue();
                break;
            }
        }
        return node;
    }


}
