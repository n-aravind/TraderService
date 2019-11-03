package com.java.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.concurrent.Callable;

@Data
@AllArgsConstructor
public class AddShares implements Callable {

    TraderService traderService;
    String stockName;
    int numShares;
    int price;

    @Override
    public String call() throws Exception {
        int newNumShares = traderService.addShares(this.stockName,this.numShares,this.price);
        return "Added stock " + stockName + " , " + numShares + " shares at $" + price + " total shares " + newNumShares;
    }
}
