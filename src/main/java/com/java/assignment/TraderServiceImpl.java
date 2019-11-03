package com.java.assignment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraderServiceImpl implements TraderService {

    private Map<String, Stock> stocks = new HashMap<>();
    private List<Trade> trades = new ArrayList<>();

    @Override
    public synchronized int addShares(String stockName, int numShares, int price) {
        try {
            Thread.sleep((int) (Math.random() * 500) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (stocks.containsKey(stockName)) {
            stocks.get(stockName).setNumShares(stocks.get(stockName).getNumShares() + numShares);
            stocks.get(stockName).setPrice(price);
        } else {
            Stock stock = new Stock(stockName, price, numShares);
            stocks.put(stockName, stock);
        }
        return stocks.get(stockName).getNumShares();
    }

    @Override
    public synchronized Trade buyShares(String traderName, String stockName, int numShares) throws SharesException {
        try {
            Thread.sleep((int) (Math.random() * 500) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (stocks.containsKey(stockName) && stocks.get(stockName).getNumShares() - numShares >= 0) {
            stocks.get(stockName).setNumShares(stocks.get(stockName).getNumShares() - numShares);
            Trade trade = new Trade(stockName, LocalDateTime.now(), numShares, stocks.get(stockName).getPrice());
            trades.add(trade);
            return trade;
        }
        throw new SharesException(stockName,numShares);
    }

    @Override
    public List<Trade> getTrades() {
        return trades;
    }
}
