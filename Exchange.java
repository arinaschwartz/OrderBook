/* CS121 A'11 Stock Exchange for a single stock 
 *
 * Main class for the exchange
 *
 * YOUR NAME
*/

import java.util.*;
import java.io.File;

//Arin Schwartz
public class Exchange {
    public static final char VENUE = 'I';
    public static final String TICKER = "AMGN";
    
    public Book sellBook;
    public Book buyBook;
    

    public Exchange() {
    	this.sellBook = new Book();
    	this.buyBook = new Book();
    }


    public void processOrder(Order o) {
    	if(o.type == Order.ADD){
    		if(o.book == Order.BUY){
    			processBuy(o); //Processes an add buy order
    			return;
    		}
    		else{
    			processSell(o); //Processes an add sell order
    			return;
    		}
    	}
    	else{
    		if(o.book == Order.BUY){ //Processes a cancel buy order
    			Order orderToCancel = buyBook.findOrder(o.oref);
    			if(orderToCancel == null){
    				outputCancelRecord(o, 0);
    				return;
    			}
    			int cancelledShares = buyBook.reduce(orderToCancel, o.shares);
    			outputCancelRecord(o, cancelledShares);
    			}
    		
    		else{ //Processes a cancel sell order
    			Order orderToCancel = sellBook.findOrder(o.oref);
    			if(orderToCancel == null){
    				outputCancelRecord(o, 0);
    				return;
    			}
    			int cancelledShares = sellBook.reduce(orderToCancel, o.shares);
    			outputCancelRecord(o, cancelledShares);
    		}
    	}
    }
    
    public void processBuy(Order o){
		Order bestMatch = sellBook.findMatching(o);
		if(bestMatch != null){
				
			int reducedShares = sellBook.reduce(bestMatch, o.shares);
			o.shares = o.shares - reducedShares;
			outputTradeRecord(bestMatch, reducedShares);
			if(o.shares != 0){
    			processBuy(o);
			}
		}
		else{
			buyBook.add(o);
			outputAddRecord(o, o.shares);
		}
	}
    
    public void processSell(Order o){
    	Order bestMatch = buyBook.findMatching(o);
    	if(bestMatch != null){
    		
    		int reducedShares = buyBook.reduce(bestMatch, o.shares);
    		o.shares = o.shares - reducedShares;
			outputTradeRecord(bestMatch, reducedShares);
    		
    		if(o.shares != 0){
    			processSell(o);
    		}
    	}
    	else{
    		sellBook.add(o);
    		outputAddRecord(o, o.shares);
    	}
    }

    /* run:
     *  read the orders from the specified file and process them 
     */
    public void run(String f) {
	int time = 0;

        Scanner scanner = null;

        // attempt to load file
        try {
            scanner = new Scanner(new File(f), "UTF-8");
        } catch (NullPointerException e) {
            System.out.print("Bad file name.");
            System.exit(0);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File " + f + " not found.");
            System.exit(0);
        }
	
        try {
            while (scanner.hasNextLine()) {
		Order o = new Order(scanner.nextLine(), time);
		if (!o.ticker.equals(TICKER) || (o.venue != VENUE)) {
		    System.out.printf("Skipping bad order at time %d: %s\n", time, o);
		} else {
		    processOrder(o);
		}
		time = time + 1;
            }
        } finally {
            // ensure the underlying stream is always closed
            scanner.close();
        }
    }


    /* outputAddRecord:
     *   output an add record for the specified number of shares 
     */
    public static void outputAddRecord(Order o, int numShares) {
	if (o == null) {
	    System.out.println("null order"); 
	} else {
	    System.out.println( o.venue + "," + o.ticker + "," + "A" + ","
				+ o.book + "," + numShares + ","
				+ o.price + "," + o.oref);
	}
    }

 
    /* outputCancelRecord:
     *   output an cancel record for the specified number of shares 
     */
    public static void outputCancelRecord(Order o, int numShares) {
	if (o == null) {
	    System.out.println("null order"); 
	} else {
	    System.out.println( o.venue + "," + o.ticker + "," + "C" + ","
				+ o.book + "," + numShares + ","
				+ o.price + "," + o.oref);
	}
    }


    /* outputTradeRecord:
     *   output an trade record for the specified number of shares 
     */
    public static void outputTradeRecord(Order o, int numShares) {
	if (o == null) {
	    System.out.println("null order"); 
	} else {
	    System.out.println(o.venue + "," + o.ticker + "," + "E" + ","
			       + o.book + "," + numShares + ","
			       + o.price + "," + o.oref);
	}
    }


    public static void main(String[] args) {
	String fileName = "";
	if (args.length == 1) {
	    fileName = args[0];
	} else {
	    System.out.println("usage: java Exchange <fileName>");
	    System.exit(0);
	}
	    
	Exchange e = new Exchange();
	e.run(fileName);
    }

}
