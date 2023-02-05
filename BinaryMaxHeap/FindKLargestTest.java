package BinaryMaxHeap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A collection of tests for the FindKLargest class
 * 
 * @author Riley Peters
 * @version 4/14/22
 */
class FindKLargestTest {
	
	FindKLargest test;
	Comparator<Integer> cmp;
	List<Integer> listToAdd, listToCompareMax, listToCompareMin;
	
	@BeforeEach
	void setUp() throws Exception {
		test = new FindKLargest();
		cmp = (int1, int2) -> int2 - int1;
		listToAdd = new ArrayList<Integer>();
		listToAdd.add(50);
		listToAdd.add(17);
		listToAdd.add(36);
		listToAdd.add(49);
		listToAdd.add(50);
		listToAdd.add(4);
		listToAdd.add(28);
		listToAdd.add(11);
		
		listToCompareMax = new ArrayList<Integer>();
		listToCompareMax.add(50);
		listToCompareMax.add(50);
		listToCompareMax.add(49);
		listToCompareMax.add(36);
		listToCompareMax.add(28);
		listToCompareMax.add(17);
		listToCompareMax.add(11);
		listToCompareMax.add(4);
		
		listToCompareMin = new ArrayList<Integer>();
		listToCompareMin.add(4);
		listToCompareMin.add(11);
		listToCompareMin.add(17);
		listToCompareMin.add(28);
		listToCompareMin.add(36);
		listToCompareMin.add(49);
		listToCompareMin.add(50);
		listToCompareMin.add(50);
	}

	//findKLargestHeap Test
	@Test
	void findKLargestHeap() {
		assertEquals(listToCompareMax, test.findKLargestHeap(listToAdd, 8));
	}
	
	@Test
	void findKLargestHeapExcpetion() {
		assertThrows(IllegalArgumentException.class, () -> {test.findKLargestHeap(listToAdd, -2);});
		assertThrows(IllegalArgumentException.class, () -> {test.findKLargestHeap(listToAdd, 9);});
	}

	//findKLargestHeapComp Tests
	@Test
	void findKLargestHeapCmp() {
		assertEquals(listToCompareMin, test.findKLargestHeap(listToAdd, 8, cmp));
	}
	
	@Test
	void findKLargestHeapCmpExcpetion() {
		assertThrows(IllegalArgumentException.class, () -> {test.findKLargestHeap(listToAdd, -2, cmp);});
		assertThrows(IllegalArgumentException.class, () -> {test.findKLargestHeap(listToAdd, 9, cmp);});
	}
	
	//findKLargestSort Tests
	@Test
	void findKLargestSort() {
		assertEquals(listToCompareMax, test.findKLargestSort(listToAdd, 8));
	}
	
	@Test
	void findKLargestSortExcpetion() {
		assertThrows(IllegalArgumentException.class, () -> {test.findKLargestHeap(listToAdd, -2);});
		assertThrows(IllegalArgumentException.class, () -> {test.findKLargestHeap(listToAdd, 9);});
	}
	
	//findKLargestSortCmp Tests
	@Test
	void findKLargestSortCmp() {
		assertEquals(listToCompareMin, test.findKLargestSort(listToAdd, 8, cmp));
	}
	
	@Test
	void findKLargestSortCmpException() {
		assertThrows(IllegalArgumentException.class, () -> {test.findKLargestHeap(listToAdd, -2, cmp);});
		assertThrows(IllegalArgumentException.class, () -> {test.findKLargestHeap(listToAdd, 9, cmp);});
	}
}
