# stock.profit

#####This was written using Java 8 and the Gradle build tool

The algorithm used in **findBestProfit** works like this.

The initial price array is divided into a first and second half. The minimum value in the first half is found, and the maximum value in the second half. These are the initial min/max values.

All prices from index 0 up to the min value(inclusive) in the first half are discarded, The same is done for the prices
in the second half, from the max value's position(inclusive) up to the last element.

The remaining prices in the first half are checked to see whether a larger value than the current maximum exists. The remaining prices in the second half are checked to see whether a smaller value than the current minimum exists. The minimum and/or maximum values are replaced if needed, then the minimum is subtracted from the maximum to arrive at the final profit.

This works for all tests presented, unless the final profit is negative, which happens if the numbers in the price list are continuously descending. (i.e you're trying to find the smallest loss.). If the profit would be negative, the list is iterated over in order to find the smallest difference between adjacent values. This result will be the highest 'profit', and is the one returned.

(_I wondered whether this particular case should be accounted for, but decided it was easy enough to include_.)

Two enums were included in order to reduce the number of similar functions that would be written.

**Reducer** has the values MIN and MAX, and is used in the _getPrice_ method for returning the minimum or maximum price.

**Side** has the values LEFT and RIGHT, and is used in the dropPrices function. The LEFT value instructs the function to 
remove prices from the left side of the list, and the RIGHT value instructs it to remove prices from the right.

The printed output at the end of the findBestProfit method lists the locations of the prices, to show that the buy
price occurred before the sell price. This location value is one-based (_first=1, second=2, etc_)