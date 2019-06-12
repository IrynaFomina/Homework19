package org.hillel;

import org.hillel.hashmap.IraHashMap;

/**
 * This demo class for org.hillel.hashmap.IraHashMap
*/
public class Main {
    public static void main(String[] args) {
        long memory = (Runtime.getRuntime().freeMemory());
        IraHashMap m = new IraHashMap<>();
//        HashMap m = new HashMap<>();
        long t = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            try{
            m.add("key" + i,i);}
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("i = " + i);
                break;
            }
        }
        System.out.println("Add: " + (System.currentTimeMillis() - t));

        System.out.println((Runtime.getRuntime().freeMemory()-memory)/1048576);
        t = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            try{
                m.get("key" + i);
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("i = " + i);
                break;
            }
        }
        System.out.println("Get: "+(System.currentTimeMillis() - t));


//        m.add("a",null);
//        m.add(null,8);
//        m.add("bc",22);
//        m.add("aa",2);
//        m.add("bs",4);
//        m.add("aa",10);
//
//        m.add("ac",21);
//
//        m.add("aac",23);
//        m.add("bsc",24);
//        m.add("aac",25);
//
//        m.add("cc",31);
//        m.add("cc",32);
//        m.add("cac",33);
//        m.add("csc",34);
//        m.add("cac",35);
//
//        m.add("1",41);
//        m.add("2",42);
//        m.add("c3",43);
//        m.add("4",44);
//        m.add("5",45);
//
//        System.out.println(m.get("a"));
//        System.out.println(m.get(null));
//        System.out.println(m.get("aa"));
//        System.out.println(m.get("bs"));
//
//
//        System.out.println(m.get("ac"));
//        System.out.println(m.get("bc"));
//        System.out.println(m.get("aac"));
//        System.out.println(m.get("aac"));
//
//        System.out.println(m.get("cc"));
//        System.out.println(m.get("cac"));
//        System.out.println(m.get("csc"));
//        System.out.println(m.get("cac"));
    }
}
