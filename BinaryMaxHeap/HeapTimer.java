package BinaryMaxHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HeapTimer {

	public static void main(String[] args) {
		int timesToLoop = 1000;
		int incr = 1000;
		Random rng = new Random();

		long startTime, midPointTime, stopTime;
		
		for (int probSize = 1000; probSize <= 10000; probSize += incr) {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 1; i <= probSize; i++)
				list.add(i);
			BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>(list);
			
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				//heap.add(rng.nextInt());
				//heap.extractMax();
				heap.peek();
			}
			
			midPointTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				//rng.nextInt();
				//heap.extractMax();
				//heap.add(rng.nextInt());
			}
			
			stopTime = System.nanoTime();
			
			double averageTime = ((midPointTime - startTime) - (stopTime - midPointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
		}

	}

}
