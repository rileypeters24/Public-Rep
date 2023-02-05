package BinaryMaxHeap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A list of tests for the Binary Max Heap class
 * 
 * @author Riley Peters
 * @version 4/14/22
 *
 */
class BinaryMaxHeapTest {
	
	BinaryMaxHeap<Integer> heap,heapWithComparator,heapList,heapListWithComparator, empty;

	@BeforeEach
	void setUp() throws Exception {
		Comparator<Integer> cmp = (int1, int2) -> int2 - int1;
		List<Integer> list = new ArrayList<Integer>();
		list.add(50);
		list.add(17);
		list.add(36);
		list.add(49);
		list.add(50);
		list.add(4);
		list.add(28);
		list.add(11);
		heap = new BinaryMaxHeap<Integer>();
		heap.add(50);
		heap.add(17);
		heap.add(36);
		heap.add(49);
		heap.add(50);
		heap.add(4);
		heap.add(28);
		heap.add(11);
		heapWithComparator = new BinaryMaxHeap<Integer>(cmp);
		heapWithComparator.add(50);
		heapWithComparator.add(17);
		heapWithComparator.add(36);
		heapWithComparator.add(49);
		heapWithComparator.add(50);
		heapWithComparator.add(4);
		heapWithComparator.add(28);
		heapWithComparator.add(11);
		heapList = new BinaryMaxHeap<Integer>(list);
		heapListWithComparator = new BinaryMaxHeap<Integer>(list,cmp);
		empty =  new BinaryMaxHeap<Integer>();
	}

	//Add tests
	@Test
	void addNewMax() {
		heap.add(100);
		assertEquals(100, heap.peek());
		
		heapWithComparator.add(1);
		assertEquals(1, heapWithComparator.peek());
		
		heapList.add(100);
		assertEquals(100, heapList.peek());
		
		heapListWithComparator.add(1);
		assertEquals(1, heapListWithComparator.peek());
	}
	
	@Test
	void addNoChangeToMax() {
		heap.add(30);
		assertEquals(50, heap.peek());
		
		heapWithComparator.add(30);
		assertEquals(4, heapWithComparator.peek());
		
		heapList.add(30);
		assertEquals(50, heapList.peek());
		
		heapListWithComparator.add(30);
		assertEquals(4, heapListWithComparator.peek());
	}
	
	//Peek Tests
	@Test
	void peekEmpty() {
		assertThrows(NoSuchElementException.class, () -> {empty.peek();});
	}
	
	@Test
	void peekAfterRemove() {
		assertEquals(8, heap.size());
		heap.extractMax();
		assertEquals(50, heap.peek());
		assertEquals(7, heap.size());
		
		assertEquals(8, heapWithComparator.size());
		heapWithComparator.extractMax();
		assertEquals(11, heapWithComparator.peek());
		assertEquals(7, heapWithComparator.size());
		
		assertEquals(8, heapList.size());
		heapList.extractMax();
		assertEquals(50, heapList.peek());
		assertEquals(7, heapList.size());
		
		assertEquals(8, heapListWithComparator.size());
		heapListWithComparator.extractMax();
		assertEquals(11, heapListWithComparator.peek());
		assertEquals(7, heapListWithComparator.size());
	}
	
	@Test
	void peekAfterClear() {
		heap.clear();
		assertThrows(NoSuchElementException.class, () -> {heap.peek();});
		
		heapWithComparator.clear();
		assertThrows(NoSuchElementException.class, () -> {heapWithComparator.peek();});
		
		heapList.clear();
		assertThrows(NoSuchElementException.class, () -> {heapList.peek();});
		
		heapListWithComparator.clear();
		assertThrows(NoSuchElementException.class, () -> {heapListWithComparator.peek();});
	}

	//ExtractMax Tests
	@Test
	void extractEmpty() {
		assertThrows(NoSuchElementException.class, () -> {empty.peek();});
	}
	
	@Test
	void extractThree() {
		assertEquals(50, heap.extractMax());
		assertEquals(50, heap.extractMax());
		assertEquals(49, heap.extractMax());
		
		assertEquals(4, heapWithComparator.extractMax());
		assertEquals(11, heapWithComparator.extractMax());
		assertEquals(17, heapWithComparator.extractMax());
		
		assertEquals(50, heapList.extractMax());
		assertEquals(50, heapList.extractMax());
		assertEquals(49, heapList.extractMax());
		
		assertEquals(4, heapListWithComparator.extractMax());
		assertEquals(11, heapListWithComparator.extractMax());
		assertEquals(17, heapListWithComparator.extractMax());
	}
	
	@Test
	void extractClear() {
		heap.clear();
		assertThrows(NoSuchElementException.class, () -> {heap.extractMax();});
		
		heapWithComparator.clear();
		assertThrows(NoSuchElementException.class, () -> {heap.extractMax();});
		
		heapList.clear();
		assertThrows(NoSuchElementException.class, () -> {heap.extractMax();});
		
		heapListWithComparator.clear();
		assertThrows(NoSuchElementException.class, () -> {heap.extractMax();});
	}
	
	//Size Tests
	@Test
	void sizeEmpty() {
		assertEquals(0, empty.size());
	}
	
	@Test
	void sizeAfterExtractMax() {
		assertEquals(8, heap.size());
		heap.extractMax();
		assertEquals(7, heap.size());
		heap.extractMax();
		assertEquals(6, heap.size());
		
		assertEquals(8, heapWithComparator.size());
		heapWithComparator.extractMax();
		assertEquals(7, heapWithComparator.size());
		heapWithComparator.extractMax();
		assertEquals(6, heapWithComparator.size());
		
		assertEquals(8, heapList.size());
		heapList.extractMax();
		assertEquals(7, heapList.size());
		heapList.extractMax();
		assertEquals(6, heapList.size());
		
		assertEquals(8, heapListWithComparator.size());
		heapListWithComparator.extractMax();
		assertEquals(7, heapListWithComparator.size());
		heapListWithComparator.extractMax();
		assertEquals(6, heapListWithComparator.size());
	}
	
	@Test
	void sizeAfterClear() {
		assertEquals(8, heap.size());
		heap.clear();
		assertEquals(0, heap.size());
		
		assertEquals(8, heapWithComparator.size());
		heapWithComparator.clear();
		assertEquals(0, heapWithComparator.size());
		
		assertEquals(8, heapList.size());
		heapList.clear();
		assertEquals(0, heapList.size());
		
		assertEquals(8, heapListWithComparator.size());
		heapListWithComparator.clear();
		assertEquals(0, heapListWithComparator.size());
	}
	
	//IsEmpty Tests
	@Test
	void isEmptyTrue() {
		assertTrue(empty.isEmpty());
	}
	
	@Test
	void isEmptyFalse() {
		assertFalse(heap.isEmpty());
		assertFalse(heapWithComparator.isEmpty());
		assertFalse(heapList.isEmpty());
		assertFalse(heapListWithComparator.isEmpty());
	}
	
	@Test
	void isEmptyAfterClear() {
		heap.clear();
		assertTrue(heap.isEmpty());

		heapWithComparator.clear();
		assertTrue(heapWithComparator.isEmpty());
		
		heapList.clear();
		assertTrue(heapList.isEmpty());
		
		heapListWithComparator.clear();
		assertTrue(heapListWithComparator.isEmpty());
	}
	
	//Clear Tests
	@Test
	void clearTest() {
		assertFalse(heap.isEmpty());
		heap.clear();
		assertTrue(heap.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {heap.peek();});

		assertFalse(heapWithComparator.isEmpty());
		heapWithComparator.clear();
		assertTrue(heapWithComparator.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {heapWithComparator.peek();});
		
		assertFalse(heapList.isEmpty());
		heapList.clear();
		assertTrue(heapList.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {heapList.peek();});
		
		assertFalse(heapListWithComparator.isEmpty());
		heapListWithComparator.clear();
		assertTrue(heapListWithComparator.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {heapListWithComparator.peek();});
	}
}
