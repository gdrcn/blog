package com.rdc.bean;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.assertSame;

public class UserBeanTest {

//    @Test
//    public void test(){
//        String contents = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
//        List<String> words = Arrays.asList(contents.split("[\\p{L}]+"));
//        int count = 0;
//        for(String w : words){
//            if(w.length() > 12)
//                count++;
//        }
//    }

//    @Test
//    public void test2(){
//        Stream<Double> randoms = Stream.generate(Math :: random).limit(100);
//        System.out.println(randoms);
//        for(Double double1 : randoms){
//            System.out.println(double1);
//        }
//    }

    @Test
    public void test2() {
        List a = new ArrayList();
        List b = new LinkedList();
        Map c = new HashMap<>();
        Map d = new TreeMap<>();
        System.out.println((null != null) ? 0 : 1);
//        int sum = widgets.stream()
//                .filter(w -> w.getColor() == RED)
//                .mapToInt(w -> w.getWeight())
//                .sum();
//        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
//        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
//        System.out.println(concat);
//        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
//        System.out.println(minValue);
//        concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
//        System.out.println(concat);
        Stream.iterate(0, n -> n + 3).limit(1000).forEach(x -> System.out.print(x + " "));
    }

    @Test
    public void test3() {
        String name = "abner chai";
        //String name = null;
        assert (name == null) : "变量name为空null";
//        System.out.println(name);
    }

    @Test
    public void test4() {
        int i = 0;
        String greetings[] = {" Hello world !", " Hello World !! ",
                " HELLO WORLD !!!"};
        while (i < 5) {
            try {
                i++;
            } finally {
                System.out.println("--------------------------");
            }
//            assertEquals(60, result, 0);
        }
    }

    @Test
    public void test5() {
        Object a = 3;
        Object b = 3;
        assertSame("不相同", a, b);
    }

    @Test
    public void test7() {
        String a = "123";
        String b = "12";
        assertSame("1", a, b);
    }

//    @Ignore
//    @Test(timeout = 30)
//    public void test8(){
//        for(int i = 0; i < 10000; i++){
//            System.out.println(i);
//        }
//    }

    List<String> values;

    @Before
    public void setUpList() {
        values = new ArrayList<String>();
        values.add("x");
        values.add("y");
        values.add("z");
    }

    @Test
    public void test10() {

    }

}