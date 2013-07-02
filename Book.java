/* CS121 A'11 Stock Exchange for a single stock 
 *
 * Class for representing books
 *
 * Arin Schwartz
*/

//Arin Schwartz
import java.util.ArrayList;

//Arin Schwartz
public class Book
{
	public ArrayList<Order> x;
	
	public Book()
    {
    	this.x = new ArrayList<Order>();
    	
    }
    
	public void add(Order o)
	{
		if(x.size() == 0)
		{
			x.add(o);
			return;
		}
		if(o.book == Order.BUY)
		{
			addBuy(o);
			return;
		}
		if(o.book == Order.SELL)
		{
			addSell(o);
			return;
		}
	}
	
	private void addBuy(Order o)
	{
		for(int i = 0; i < x.size(); i++){
			
			if(o.price == x.get(i).price){
					
				if(i == x.size() - 1){
					x.add(o);
					return;
				}
				if(x.get(i+1).price == x.get(i).price){
					continue;
				}
				else{
					x.add(i+1, o);
					return;
				}
			}
			
			if(o.price > x.get(i).price){
				x.add(i, o);
				return;
			}
			if(i == x.size() - 1){
				x.add(o);
				return;
			}
		}
	}
	
	private void addSell(Order o)
	{
		for(int i = 0; i < x.size(); i++)
		{
			if(o.price == x.get(i).price){
				
				if(i == x.size() - 1){
					x.add(o);
					return;
				}
				if(x.get(i+1).price == x.get(i).price){
					continue;
				}
				else{
					x.add(i+1, o);
					return;
				}
			}
			if(o.price < x.get(i).price){
				x.add(i, o);
				return;
			}
			if(i == x.size() - 1){
				x.add(o);
				return;
			}
		}
	}
	
	public int reduce(Order o, int numShares)
	{
		if(o.shares <= numShares)
		{
			numShares = o.shares;
			o.shares = 0;
			x.remove(o);
			return numShares;
		}
		o.shares = o.shares - numShares;
		return numShares;
	}
	
	public Order findMatching(Order o)
	{
		if(o.book == Order.BUY)
		{
			return findMatchingSell(o);
		}
		if(o.book == Order.SELL)
		{
			return findMatchingBuy(o);
		}
		else{
			return null;
		}
		

	}
	
	/*We have these two because there are slightly different procedures
	 * for finding the best match, depending on if the order is a buy or a sell.
	 * This one finds the best matching sell for a buy order by checking if
	 * prices in the booked orders are LESS THAN or equal to the bid (buy) price.
	 */
	private Order findMatchingSell(Order o)
	{
		Order bestMatch = null;

		if(x.size() == 0){
			return bestMatch;
		}
		
		for(int i = 0; i < x.size(); i++){
			
			if(x.get(i).price <= o.price){
				bestMatch = x.get(i);
				return bestMatch;
			}
		}
		return bestMatch;
	}
	 /* This one finds the best matching buy for a sell order by checking if
	 * prices in the booked orders are GREATER THAN or equal to the ask(sell) price.
	 */
	private Order findMatchingBuy(Order o)
	{
		Order bestMatch = null;
		
		if(x.size() == 0){
			return bestMatch;
		}
		
		for(int i = 0; i < x.size(); i++){
			
			if(x.get(i).price >= o.price){
				bestMatch = x.get(i);
				return bestMatch;
			}
		}
		return bestMatch;
	}
	
	public Order findOrder(long oref){//Finds an order from a given reference number
		for(int i = 0; i < x.size(); i++){
			if(oref == x.get(i).oref){
				return x.get(i);
			}
		}
		return null;
	}
	
	public void print(){  //Method written for easy testing
		for(int i = 0; i < x.size(); i++){
			System.out.println(x.get(i));
		}

	}

}