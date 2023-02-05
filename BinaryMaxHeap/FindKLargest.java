package BinaryMaxHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Erin Parker and Riley Peters
 * @version 4/14/22
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		
		List<E> toReturn = new ArrayList<E>();
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<E>(items);
		
		for (int i = 0; i < items.size(); i++)  //Adds k items to the List
			toReturn.add(heap.extractMax());
		return toReturn;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		
		List<E> toReturn = new ArrayList<E>();
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<E>(items,cmp);
		
		for (int i = 0; i < items.size(); i++)  //Adds k items to the List
			toReturn.add(heap.extractMax());
		return toReturn;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		
		List<E> toReturn = new ArrayList<E>();
		
		Collections.sort(items);
		
		int index = items.size() - 1;
		int i = 0;
		while (i < k) {
			toReturn.add(items.remove(index));
			index--;
			i++;
		}
		return toReturn;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		
		List<E> toReturn = new ArrayList<E>();
		
		Collections.sort(items,cmp);
		
		int index = items.size() - 1;
		int i = 0;
		while (i < k) {
			toReturn.add(items.remove(index));
			index--;
			i++;
		}
		
		return toReturn;
	}
}