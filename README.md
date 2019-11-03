# TraderService

> IBM has traders on Wall Street floor that acquire the rights to buy x number of shares of a stock. When a trader acquires the rights the trader creates/updates a stock information. Financial planners and individual investors can buy stock if there are shares available.

Create a new maven project in IntelliJ

Stock class

```
String tradingName
int currentPrice
int numShares
```

Trader class
```
String name
LocalDateTime purchasedAt
int numShares
int price
```
TraderService interface
```
public interface TraderService {
      int addShares(String stockName, int numShares, int price);
      Trade buyShares(String traderName, String stockName, int numShares) throws SharesException;
      List<Trade> getTrades();
}
```

TraderServiceImpl class

Implements the TraderService interface. It should use the following instance variables.

```
private Map stocks = new HashMap<>();
private List trades = new ArrayList<>();
```
The key to the stocks map should be the trading name of the stock.

TraderService.addShares method

- synchronized to avoid other methods manipulating the same stock
- Thread.sleep for 0 to 500 milliseconds
- creates the stock if not already created
- increments the number of available shares
- sets the stock price

TraderService.buyShares method

- synchronized to avoid other methods manipulating the same stock
- Thread.sleep for 0 to 500 milliseconds
- decrement the number of available shares
- creates a Trade and adds the Trade to a list
- once the number of shares have been decremented and before the Trade is created allow calls to the other methods through
- throws SharesException if the stock cannot be found or if there are not enough shares to sell
- returns the Trade

TraderService.getTrades method

- is not synchronized because it's ok if the current trade is not yet in the list
- returns the trades

**AddShares class**

Create an AddShares Callable class that has the following variables
```
TraderService traderService
String stockName
int numShares
int price
```
This class calls the traderService.addShares method. 

It should return a String something like
> "Added stock IBM, 2 shares at $41 total shares 125"

**BuyShares class**

Create an BuyShares Callable class that has the following variables
```
TraderService traderService
String traderName
String stockName
int numShares
```
This class calls the traderService.buyShares method. This class should return a String something like
> "Dave bought IBM, 9 shares at $91 at 2019-09-29T15:33:58.457"

or

> Could not buy 20 shares of IBM

**main method**

Write a multi-threaded test that does the following
- Only trades in IBM stock (i.e. private static final String)
- Create a static final List variable of 3 strings which will be used for the trader names. Initialize the static variables but DO NOT initialize them in main method (i.e. initialize them before main runs).
- Creates a List of Callable
          `List<Callable<String>> stockActions = new LinkedList<>();`
- In a for loop add 10 AddShares classes to the List. Randomize the number of shares and price.
- In a for loop add 10 BuyShares classes to the List. Randomize the number of shares and trader name.
- Create an ExecutorService with a fixed thread pool of 5 threads and invoke all the Callables

       
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = executor.invokeAll(stockActions);
        
- Loop through the Futures printing out the the results the get method
- Shutdown the executor
- Print the list of trades
