import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        IraHashMap m = new IraHashMap<>();
        m.add("a",5);
        m.add("b",8);
        m.add("aa",2);
        m.add("bs",4);
        m.add("aa",10);

        System.out.println(m.get("a"));
        System.out.println(m.get("b"));
        System.out.println(m.get("aa"));
        System.out.println(m.get("bs"));
    }
}
