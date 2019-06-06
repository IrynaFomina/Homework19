import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        IraHashMap m = new IraHashMap<>();
        m.add("a",5);
        m.add("b",8);
        m.add("aa",2);
        m.add("bs",4);
        m.add("aa",10);

        m.add("ac",21);
        m.add("bc",22);
        m.add("aac",23);
        m.add("bsc",24);
        m.add("aac",25);

        m.add("cc",31);
        m.add("cc",32);
        m.add("cac",33);
        m.add("csc",34);
        m.add("cac",35);

        System.out.println(m.get("a"));
        System.out.println(m.get("b"));
        System.out.println(m.get("aa"));
        System.out.println(m.get("bs"));


        System.out.println(m.get("ac"));
        System.out.println(m.get("bc"));
        System.out.println(m.get("aac"));
        System.out.println(m.get("aac"));

        System.out.println(m.get("cc"));
        System.out.println(m.get("cac"));
        System.out.println(m.get("csc"));
        System.out.println(m.get("cac"));
    }
}
