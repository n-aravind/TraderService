package com.java.assignment;

public class SharesException extends Exception {
    public SharesException(String stockName, int numShares) {
        super("Could not buy "+ numShares + " shares of " + stockName);
    }
}
