package com.TodoLists.Application.Data.Model;

import java.time.LocalDate;
import java.util.*;

public class Curiosity1 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        LocalDate startDate = today.minusYears(5);
        while (!startDate.isAfter(today)){
            System.out.println("The month is "+startDate.getMonth());
            System.out.println("The year is "+ startDate.getYear());
            System.out.println("The day is "+startDate.getDayOfWeek());
            startDate = startDate.plusDays(1);
        }

        ////        Object[][] o = {{"hello", "does it"}, {1, 4}, {0.1}};
////        System.out.println(Arrays.toString(Arrays.stream(o).flatMap(Arrays::stream).toArray()));
//        // omo make i try
//        List<Integer> total = List.of(40, 29, 26, 24);

//        System.out.println("Position after performing position algorithm "+Arrays.toString(pos));
    }

    private static int[] numberDoubler(int[] numbers) {
        ArrayList<Integer> res = new ArrayList<>(Arrays.stream(numbers).boxed().toList());
        Arrays.stream(numbers).forEach(x->res.add(x*2));
        return res.stream().mapToInt(x->x).toArray();
    }





    private static Object[] evenAndOddReplace(List<Integer> numbers) {
        int[] num = numbers.stream().mapToInt(x -> numbers.get(numbers.indexOf(x))).toArray();
        Object[] finalFalsify = new Object[numbers.size()];
        Arrays.stream(num).filter(x->x%2==0).mapToObj(x-> finalFalsify[numbers.indexOf(x)] = true).toArray();
        Arrays.stream(num).filter(x->x%2!=0).mapToObj(x-> finalFalsify[numbers.indexOf(x)] = false).toArray();
        return finalFalsify;
    }



   public static boolean[] lol(int[] anotherone){
        boolean[] res = new boolean[anotherone.length];
        for (int i = 0; i < anotherone.length; i++) {
            if (anotherone[(i)] %2 ==0) res[i] = true;
            else if (anotherone[i] %2 !=0)    res[i] = false;

        }
      return res;
    }
    
    public static List<Boolean> anotherOne(List<Integer> numbers){
        List<Boolean> j = new ArrayList<>();
        numbers.stream().forEach(x->{
            if (x%2==0) j.add(true);
            else j.add(false);
        });
        return j;
    }

    public static void passwordGuesser(){
        System.out.println(UUID.randomUUID().toString());
    }
}

