package nz.co.craigfox;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class StockProfitTest {
    private StockProfit stockProfit;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setup(){
        this.stockProfit = new StockProfit();
    }

    @Test
    public void testEmptyArray(){
        int[] stockPrices = {};

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Price array must not be empty");
        stockProfit.findBestProfit(stockPrices);
    }

    @Test
    public void testOneItem(){
        int[] stockPrices = {1};
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Price array must have at least 2 items");
        stockProfit.findBestProfit(stockPrices);
    }

    @Test
    public void testPricesStandard(){
        int[] stockPrices = {10, 7, 5, 11, 8, 9};
        int profit = stockProfit.findBestProfit(stockPrices);
        assertTrue("Profit should be 6, not " + profit, profit == 6);
    }

    @Test
    public void testKeyPricesInFirstHalf(){
        int[] stockPrices = {5, 7, 11, 9, 8, 9, 6, 7};
        int profit = stockProfit.findBestProfit(stockPrices);
        assertTrue("Profit should be 6, not " + profit, profit == 6);
    }

    @Test
    public void testKeyPricesInSecondHalf(){
        int[] stockPrices = {6, 7, 10, 9, 8, 4, 6, 11, 5};
        int profit = stockProfit.findBestProfit(stockPrices);
        assertTrue("Profit should be 7, not " + profit, profit == 7);
    }

    @Test
    public void testDuplicateMinMaxPrices(){
        int[] stockPrices = {2, 0, 16, 0, 12, 5, 9, 18, 18, 9, 10};
        int profit = stockProfit.findBestProfit(stockPrices);
        assertTrue("Profit should be 18, not " + profit, profit == 18);
    }

    @Test
    public void testAllPricesDescending(){
        int[] stockPrices = {20, 18, 17, 15, 12, 10, 7, 5, 4, 2, 0};
        int profit = stockProfit.findBestProfit(stockPrices);
        assertTrue("Profit should be -1, not " + profit, profit == -1);
    }

    @Test
    public void testDescendingWithZeroDiff(){
        int[] stockPrices = {20, 18, 17, 15, 12, 10, 7, 6, 6, 2, 0};
        int profit = stockProfit.findBestProfit(stockPrices);
        assertTrue("Profit should be 0, not " + profit, profit == 0);
    }
    @Test
    public void testDescendingWithDiffOne(){
        int[] stockPrices = {20, 18, 17, 15, 12, 10, 7, 6, 7, 2, 0};
        int profit = stockProfit.findBestProfit(stockPrices);
        assertTrue("Profit should be 1, not " + profit, profit == 1);
    }

    @Test
    public void testArray100Elements(){
        int[] stockPrices = {32, 3,	31,	62,	11, 25,	82,	37,	63,	22, 27,	86,	100, 62, 79,
        50,	47,	44,	43,	85, 38,	91,	86,	92,	13, 14,	10,	61,	46,	37, 11,	90,	21,	31,	53,
        79,	11,	5,	90,	2, 89,	87,	97,	74,	100, 20, 51, 29, 31, 45, 87,82,	91,	21,	68,
        69,	77,	96,	84,	88, 44,	44,	4,	79,	56, 50,	60,	100,27,	41, 5,	15,	99,	19,	37,
        23,	59,	93,	50,	94, 83,	6,	72,	60,	93, 93,	40,	81,	44,	43, 28,	52,	75,	53,	9,
        78,	16,	86,	83,	88};
        int profit = stockProfit.findBestProfit(stockPrices);
        assertTrue("Profit should be 98, not " + profit, profit == 98);
    }



}
