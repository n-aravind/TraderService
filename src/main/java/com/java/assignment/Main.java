package com.java.assignment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    static final List<String> traderNames = Arrays.asList("Dave","John","David");
    static final String stockName = "IBM";

    public static void main(String[] args) {

        TraderService traderService = new TraderServiceImpl();

        List<Callable<String>> stockActions = new LinkedList<>();
        for(int i = 0; i < 10; i++){
            stockActions.add(new AddShares(traderService,stockName,(int) (Math.random() * 10) + 1,(int) (Math.random() * 10) + 1));
        }

        for(int i = 0; i < 10; i++){
            stockActions.add(new BuyShares(traderService,traderNames.get((int) (Math.random() * 2)),stockName,(int) (Math.random() * 10) + 1));
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = null;
        try {
            futures = executor.invokeAll(stockActions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        futures.forEach(t-> {
            try {
                System.out.println(t.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();

        traderService.getTrades().forEach(t -> System.out.println(t.toString()));

    }
}
