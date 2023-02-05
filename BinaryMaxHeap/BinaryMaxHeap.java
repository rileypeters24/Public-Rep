package BinaryMaxHeap;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class that represents a Binary Max Heap with add, extract max, and peek methods
 * 
 * @author Riley Peters
 * @version 4/14/22
 * @param <E>
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E>{
	
	private E[] array;
	private Comparator<? super E> cmp;
	private int size;
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		array = (E[]) new Object[20];
		size = 0;
		cmp = null;
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		array = (E[]) new Object[20];
		size = 0;
		this.cmp = cmp;
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list) {
		array = (E[]) new Object[20000];
		for (int i = 1; i <= list.size(); i++)
			array[i] = list.get(i-1);
		size = list.size();
		buildHeap();
		cmp = null;
	}

	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		array = (E[]) new Object[20];
		for (int i = 1; i <= list.size(); i++)
			array[i] = list.get(i-1);
		size = list.size();
		this.cmp = cmp;
		buildHeap();
	}
	
	
	/**
	 * Adds the given item to this priority queue.
	 * 
	 * @param item
	 */
	public void add(E item) {
		if (isEmpty()) {
			array[1] = item;
			size++;
		}
		else {
			percolateUp(item);
			size++;
		}
	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();
		return array[1];
	}

	/**
	 * Returns and removes the maximum item this priority queue.
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extractMax() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();
		else {
			E store = array[1];  //Temp variable to hold the max
			array[1] = array[size];
			size--;
			percolateDown(1);
			return store;
		}
	}

	/**
	 * Returns the number of items in this priority queue.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Empties this priority queue of items.
	 */
	public void clear() {
		size = 0;
	}

	/** 
	 * Creates and returns an array of the items in this priority queue,
	 * in the same order they appear in the backing array.
	 */
	public Object[] toArray() {
		Object[] toReturn = new Object[size];
		for (int i = 1; i <= size; i++)
			toReturn[i - 1] = array[i];
		return toReturn;
	}

	/**
	 * Traverses up the heap, looking for the correct place to put the item
	 * 
	 * @param item
	 */
	private void percolateUp(E item) {
		int index = size + 1;
		if (index >= array.length - 1)
			resize();
		
		array[index] = item;
		while (index != 1 && innerCompare(array[index/2], item) < 0) {  //Checks which parents are smaller than the item and swaps if they are
			array[index] = array[index/2];
			array[index/2] = item;
			index = index/2;
		}
	}
	
	/**
	 * Doubles the size of the backing array
	 */
	private void resize() {
		@SuppressWarnings("unchecked")
		E[] doubleSize = (E[]) new Object[array.length*2];
		for (int i = 1; i < array.length; i++)
			doubleSize[i] = array[i];
		array = doubleSize;
	}

	/**
	 * Traverses down the heap, looking for the correct place to put the item
	 * 
	 * @param index - Where to begin traversing
	 */
	private void percolateDown(int index) {
		E store = array[index];
		while (index*2  <= size ) {
			if (index*2+1 > size) {  //Checks the value for only Left child
				if (innerCompare(store,array[index*2]) < 0){  
					array[index] = array[index*2];
					array[index*2] = store;
					index = index*2;
					continue;
				}
				else 
				break;
			}
			if (innerCompare(store,array[index*2]) < 0 && innerCompare(store,array[index*2+1]) < 0) {  //Both children are greater than the child
				if (innerCompare(array[index*2],array[index*2+1]) < 0) {  //Right child is greater than the Left
					array[index] = array[index*2+1];
					array[index*2+1] = store;
					index = index*2+1;
					continue;
				} else {  //Left child is greater than the Right
					array[index] = array[index*2];
					array[index*2] = store;
					index = index*2;
					continue;
				}
			}
			if (innerCompare(store,array[index*2]) < 0 && innerCompare(store,array[index*2+1]) >= 0) { //Left child is greater than the item
				array[index] = array[index*2];
				array[index*2] = store;
				index = index*2;
				continue;
			}
			
			if (innerCompare(store,array[index*2]) >= 0 && innerCompare(store,array[index*2+1]) < 0) {  //Right child is greater than the child 
				array[index] = array[index*2+1];
				array[index*2+1] = store;
				index = index*2+1;
				continue;
			}
			
			if (array[index*2] == null && array[index*2-1] == null)  //No children to compare to
				return;
			break;
		}	
	}
	
	/**
	 * Repeatedly percolates down on the backing array to sort
	 */
	private void buildHeap() {
		int index = size / 2;
		while (index >= 1) {
			percolateDown(index);
			index--;
		}
	}
	
	/**
	 * Uses either Comparable or Comparator and returns the value
	 * 
	 * @param item1
	 * @param item2
	 * @return an int value
	 */
	@SuppressWarnings("unchecked")
	private int innerCompare(E item1, E item2) {
		if (cmp == null) {
			return ((Comparable<? super E>)item1).compareTo(item2);
		}
		
		else return cmp.compare(item1, item2);
	}
}
