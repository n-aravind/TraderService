package com.java.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Trade {

    private String name;
    private LocalDateTime purchasedAt;
    private int numShares;
    private int price;

}
