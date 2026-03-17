/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

//package com.mycompany.collectiontypecomparisondemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author IlyaJava
 */
public class CollectionTypeComparisonDemo {
    public static void testCollection(List<Integer> list, String listType) {
    System.out.println("\n========Тестирование: " + listType + " ==========");
    
    // Последовательная обработка
    long startSeq = System.currentTimeMillis();
    Integer sumSeq = list.stream()
            .reduce(0, Integer::sum);
    long endSeq = System.currentTimeMillis();
    
    // Параллельная обработка
    long startPar = System.currentTimeMillis();
    Integer sumPar = list.parallelStream()
            .reduce(0, Integer::sum);
    long endPar = System.currentTimeMillis();
    
    System.out.println("Последовательно: " + (endSeq - startSeq) + " мс");
    System.out.println("Параллельно: " + (endPar - startPar) + " мс");
    System.out.println("Результат суммы: " + sumPar + " (проверка: " + sumSeq + ")");
}
    public static void main(String[] args) {
        System.out.println("Hello World!");
        int size = 10_000_000;
        List<Integer>baseList= IntStream.rangeClosed(1, size)
                .boxed()
                .collect(Collectors.toList());
        // ArrayList
        List<Integer>arrayList=new ArrayList<>(baseList);
        testCollection(arrayList, "ArrayList");
        //LinkedList
        List<Integer>linkedList=new LinkedList<>(baseList);
        testCollection(linkedList,  "LinkedList");
        // TreeSet
        Set<Integer>treeSet=new TreeSet<>(baseList);
        List<Integer>treeList= new ArrayList<>(treeSet);
        testCollection(treeList,"TreeSet(через List)");
        
    }
}
