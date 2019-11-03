package com.java.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stock {
    private String tradingName;
    private int price;
    private int numShares;
}
