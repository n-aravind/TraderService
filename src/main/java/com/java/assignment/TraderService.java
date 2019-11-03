package com.java.assignment;

import java.util.List;

public interface TraderService {
    int addShares(String stockName, int numShares, int price);
    Trade buyShares(String traderName, String stockName, int numShares) throws SharesException;
    List<Trade> getTrades();
}
