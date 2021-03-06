package nz.co.craigfox;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

enum Reducer {MIN, MAX};
enum Side {LEFT, RIGHT};

public class StockProfit {
    private PrintWriter pw = new PrintWriter(System.out, true);


    /**
     *
     * @param prices
     * @param reducer
     * @return miniumum or maximum price within a list, depending on Reducer value
     *
     */
    private Integer getPrice(List<Integer> prices, Reducer reducer){
        Integer price;
        switch(reducer){
            case MIN:
                price = prices
                        .stream()
                        .mapToInt(n -> n)
                        .min().orElseThrow(NoSuchElementException::new);
                break;
            case MAX:
                price = prices
                        .stream()
                        .mapToInt(n -> n)
                        .max().orElseThrow(NoSuchElementException::new);
                break;
            default:
                price = 0;
        }

        return price;
    }

    /**
     *
     * @param prices
     * @param priceIndex
     * @param side
     * @return Returns all prices that may hold a larger minimum or maximum value than the initial one determined
     */
    private List<Integer> dropPrices(List<Integer> prices, int priceIndex, Side side){
       int fromIndex = 0, toIndex = prices.size();

       if(side == Side.LEFT && priceIndex == prices.size()-1){
           return Collections.EMPTY_LIST;
       } else if (side == Side.RIGHT && priceIndex == 0){
           return Collections.EMPTY_LIST;
       }

       switch(side){
           case LEFT: {
               fromIndex = priceIndex+1;
               break;
           }
           case RIGHT: {
               toIndex = priceIndex-1;
               break;
           }
       }
       List<Integer> newList = new ArrayList<>(prices.subList(fromIndex, toIndex));
       return newList;
    }

    public int findBestProfit(int[] prices){
        int result = 0;
        if(prices.length == 0){
            throw new IllegalArgumentException("Price array must not be empty");
        } else if (prices.length == 1){
            throw new IllegalArgumentException("Price array must have at least 2 items");
        }

        List<Integer> priceList = Arrays.stream(prices).boxed().collect(Collectors.toList());
        int midIndex = (priceList.size() / 2) -1;
        List<Integer> priceListFirstHalf = new ArrayList<>(priceList.subList(0, midIndex+1));
        List<Integer> priceListSecondHalf = new ArrayList<>(priceList.subList(midIndex+1, priceList.size()));

        Integer buyPrice = getPrice(priceListFirstHalf, Reducer.MIN);
        Integer sellPrice = getPrice(priceListSecondHalf, Reducer.MAX);

        int buyPriceIndex = priceListFirstHalf.indexOf(buyPrice);
        int sellPriceIndex = priceListSecondHalf.indexOf(sellPrice);

        priceListFirstHalf = dropPrices(priceListFirstHalf, buyPriceIndex, Side.LEFT);
        if(!priceListFirstHalf.isEmpty()){
            int maxFirstHalfPrice = getPrice(priceListFirstHalf, Reducer.MAX);
            sellPrice = (maxFirstHalfPrice > sellPrice) ? maxFirstHalfPrice : sellPrice;
        }

        priceListSecondHalf = dropPrices(priceListSecondHalf, sellPriceIndex, Side.RIGHT);
        if(!priceListSecondHalf.isEmpty()){
            int minSecondHalfPrice = getPrice(priceListSecondHalf, Reducer.MIN);
            buyPrice = (minSecondHalfPrice < buyPrice) ? minSecondHalfPrice : buyPrice;
        }

        buyPriceIndex = priceList.indexOf(buyPrice);
        sellPriceIndex = priceList.lastIndexOf(sellPrice);
        result = sellPrice - buyPrice;

        /*Handle for case where all prices are descending */
        if(result < 0){
            int loss = result;
            for(int i=0; i<priceList.size()-1; i++){
                int diff = priceList.get(i+1) - priceList.get(i);
                if(diff > loss){
                    buyPrice = priceList.get(i);
                    sellPrice = priceList.get(i+1);
                    buyPriceIndex = i;
                    sellPriceIndex = i+1;
                    loss = diff;

                }
                loss = (diff > loss) ? diff: loss;
            }
            result = loss;
        }
        pw.printf("Buy price is %s at location %s\n", buyPrice, buyPriceIndex + 1);
        pw.printf("Sell price is %s at location %s\n", sellPrice, sellPriceIndex + 1);
        pw.printf("Profit is %s\n", result);

        return result;
    }

    public static void main(String[] args){

    }
}
