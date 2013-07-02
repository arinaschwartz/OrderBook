public class Test {
	//Arin Schwartz
	//Frist, I will test the book class and its functions
	
	
	
	
	public static void main(String[] args){
		Order a = new Order("I,AMGN,A,S,100,550000,1000", 0);
		Order b = new Order("I,AMGN,A,S,20,540000,2000", 1);
		Order c = new Order("I,AMGN,A,S,10,550000,1010", 2);
		Order q = new Order("I,AMGN,A,S,10,570000,1030", 3);
		Order r = new Order("I,AMGN,A,S,10,550000,1050", 4);
		
		Order d = new Order("I,AMGN,A,B,70,530000,1070", 3);
		Order e = new Order("I,AMGN,A,B,20,535000,1020", 4);
		Order f = new Order("I,AMGN,A,B,100,550000,1040", 5);
		
		Book buyBook = new Book();
		Book sellBook = new Book();
		
		sellBook.add(a);
		sellBook.add(b);
		sellBook.add(c);
		sellBook.add(q);
		sellBook.add(r);
		
		buyBook.add(d);
		buyBook.add(e);
		buyBook.add(f);
		
		sellBook.print();
		buyBook.print();
		
		//Testing of ADD function and auxilaries complete, all cases accounted for, every combination traced out.
		//Moving to find best match function
		
		System.out.println(sellBook.findMatching(d));
		System.out.println(sellBook.findMatching(e));
		System.out.println(sellBook.findMatching(f));
		
		System.out.println(buyBook.findMatching(a));
		System.out.println(buyBook.findMatching(b));
		System.out.println(buyBook.findMatching(q));
		
		//Testing of findMatching function and auxiliaries complete.
		//Moving to reduce function'
		
		
		System.out.println(sellBook.reduce(a, 30));
		System.out.println(sellBook.reduce(b, 60));
		
		System.out.println(buyBook.reduce(d, 30));
		System.out.println(buyBook.reduce(e, 60));
		System.out.println(buyBook.reduce(f, 900));
		
		sellBook.print();
		buyBook.print();
		
		//Reduce function tested, both its reducing and removing cases.
		
		/*Now on to the exchange function. I will describe my testing process in prose
		 * here, as there are built in methods for testing this.
		 * 
		 * UPDATE: Have tested Exchange class on inputs and outputs on handout.
		 * Little glitch in processing cancel orders caused a bit of a mess, but
		 * that diff function came in very much handy, as well as the description
		 * of the process in the handout.
		 * 
		 * Now will test on large input list, size 1000: Failure.
		 * Something to do with outputting of strange buy records very late into the program...
		 * FOUND ONE BUG: My reduce function did not remove the booked order in the case where
		 * the shares to cancel exactly equaled the shares in the booked order. needed a <= sign.
		 * 
		 * Still more problems, however. They all begin at line 856.
		 * I'm going to make a TestExchange class to work this out.
		 * Well, that didn't help but I FOUND THE PROBLEM, had to do with multiple
		 * booked orders of the same price messing up the add order. On to the next problem...
		 * 
		 * Last one was easy. Wasn't outputting a cancel record at all when the order to cancel
		 * simply did not exist at all. Changed that.
		 * 
		 * I think I'm done. Jesus.
		 */
		
	}
}