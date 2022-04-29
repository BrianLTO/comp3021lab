package lab9;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) throws InterruptedException {
		new FindMax().printMax();
	}
	
	public class PrintMax implements Runnable {
		private volatile int start, end, max;
		
		public PrintMax (int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			max = findMax(start, end);
		}
		
		public int get() {
			return max;
		}
	}
	
	public void printMax() throws InterruptedException {
		// this is a single threaded version
		PrintMax PrintMax1 = new PrintMax(0,29);
		Thread thread1 = new Thread(PrintMax1);
		
		PrintMax PrintMax2 = new PrintMax(30,59);
		Thread thread2 = new Thread(PrintMax2);
		
		PrintMax PrintMax3 = new PrintMax(60,89);
		Thread thread3 = new Thread(PrintMax3);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread1.join();
		thread2.join();
		thread3.join();
		
		int max1 = PrintMax1.get();
		int max2 = PrintMax2.get();
		int max3 = PrintMax3.get();
		System.out.println("the max value is " + Math.max(max1, Math.max(max2, max3)));
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}
