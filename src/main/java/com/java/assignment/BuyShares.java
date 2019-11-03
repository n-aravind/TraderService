package com.java.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Callable;

@Data
@AllArgsConstructor
public class BuyShares implements Callable {
    TraderService traderService;
    String traderName;
    String stockName;
    int numShares;

    @Override
    public String call() throws Exception {
        try {
            Trade trade = traderService.buyShares(traderName, stockName, numShares);
            return traderName + " bought "+stockName+", "+ numShares + " shares at $" + trade.getPrice()+ " at " + trade.getPurchasedAt();
        }
        catch (SharesException e){
            return e.getMessage();
        }
    }
}
