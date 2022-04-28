package lab9;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a multi-threaded version.

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 *
 *
 */
public class FindMax_multi extends Thread {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int max = -1;
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	int start = 0;
	int end = 0;
	
	public FindMax_multi(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public static void main(String[] args) {
		System.out.println("This is multi thread findMax");
		long startTime = System.nanoTime();
		new FindMax_multi(0,0).printMax();
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Time taken = "+totalTime+" nanoseconds");
	}

	public void printMax() {
		// this is a multi-threaded version
		FindMax_multi t1 = new FindMax_multi(0,array.length/3-1);
		FindMax_multi t2 = new FindMax_multi(array.length/3,array.length*2/3-1);
		FindMax_multi t3 = new FindMax_multi(array.length*2/3,array.length-1);
		
		t1.start();
		t2.start();
		t3.start();

		while(t1.isAlive() && t2.isAlive() && t3.isAlive()) {
		}
		System.out.println("the max value is " + max);
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
	
	public void run() {
		int temp = findMax(start,end);
		if (temp > max)
			max = temp;
	}
}
