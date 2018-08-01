package com.rdc.bean;

import org.junit.Test;

import java.util.stream.Stream;

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
}