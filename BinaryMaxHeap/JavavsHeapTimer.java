package BinaryMaxHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JavavsHeapTimer {

	public static void main(String[] args) {
		FindKLargest find = new FindKLargest();
		
		int timesToLoop = 100;
		int incr = 1000;
		Random rng = new Random(5);

		long startTime, midPointTime, stopTime;
		
		for (int probSize = 1000; probSize <= 10000; probSize += incr) {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 1; i <= probSize; i++)
				list.add(i);
			
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				
				Collections.shuffle(list);
				find.findKLargestHeap(list, 5);
			}
			
			midPointTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				Collections.shuffle(list);
			}
			
			stopTime = System.nanoTime();
			
			double averageTime = ((midPointTime - startTime) - (stopTime - midPointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
		}
	}

}
