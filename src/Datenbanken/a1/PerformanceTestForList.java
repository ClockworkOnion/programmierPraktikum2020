package Datenbanken.a1;

import java.util.*;
import java.util.stream.StreamSupport;

public class PerformanceTestForList {

    List<Integer> ArrayList1 = new ArrayList<>();
    List<Integer> ArrayList2 = new ArrayList<>();
    DoubleLinkedList<Integer> DoubleLinkedList1 = new DoubleLinkedList<>();
    DoubleLinkedList<Integer> DoubleLinkedList2 = new DoubleLinkedList<>();


    public PerformanceTestForList() {
        long time = System.nanoTime();

        for (int i = 0; i < 100000; i++) {
            ArrayList1.add(i);
        }
        System.out.println("Adding to ArrayList:  " + (System.nanoTime() - time));

        time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            ArrayList2.add(1, i);
        }
        System.out.println("Adding to ArrayList1: " + (System.nanoTime() - time));


        time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            DoubleLinkedList2.addLast(i);
        }
        System.out.println("Adding to DoubleLinkedList2: " + (System.nanoTime() - time));

        time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            DoubleLinkedList1.addFirst(i);
        }
        System.out.println("Adding to DoubleLinkedList1: " + (System.nanoTime() - time));

        System.out.println("Elements added");


        // Getting :
        Random rand = new Random();

        time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int toGet = rand.nextInt(100000);
            int value = DoubleLinkedList1.get(toGet);
        }
        System.out.println("Getting from DoubleLinkedList1: " + (System.nanoTime() - time));

        time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int toGet = rand.nextInt(100000);
            int value = DoubleLinkedList2.get(toGet);
        }
        System.out.println("Getting from DoubleLinkedList2: " + (System.nanoTime() - time));

        time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int toGet = rand.nextInt(100000);
            int value = ArrayList1.get(toGet);
        }
        System.out.println("Getting from ArrayList1: " + (System.nanoTime() - time));

        time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int toGet = rand.nextInt(100000);
            int value = ArrayList2.get(toGet);
        }
        System.out.println("Getting from ArrayList2: " + (System.nanoTime() - time));


        Spliterator<Integer> spliterator = Spliterators.spliteratorUnknownSize(DoubleLinkedList1.iterator(), 0);
        StreamSupport.stream(spliterator, false).limit(100)
                .filter(x -> (x % 10 == 0)).forEach(System.out::println);

        /* Urspruengliche Implementierung
        Iterator<Integer> tempInt;
        tempInt = DoubleLinkedList1.iterator();
        for (int i = 0; i < 100; i++) {
            // System.out.println(tempInt.next());
            int newTemp = tempInt.next();
            if (newTemp % 10 == 0) {
                System.out.println(newTemp);
            }
        }

         */

    }
}
