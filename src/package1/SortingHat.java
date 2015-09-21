package package1;

import java.util.Arrays;

/**
 * @author tobydobbs
 * For quick sorting
 */
public class SortingHat {

	int[] array = {4,3,2,1};
	
	public void quickSort(int lo, int hi) {
		if(lo < hi) {
			int p = partition(lo, hi);
			quickSort(lo, p-1);
			quickSort(p+1, hi);
		}
	}
	
	public int[] insertionSort(int[] array) {
		System.out.println(Arrays.toString(array));
		int l;
		int toCompare;
		for(int i = 1; i < (l = array.length); i++) {
			toCompare = array[i];
			for(int j = 0; j < l; j++) {
				if(toCompare < array[j]) {
					swap(array, i, j);
				}
			}
		}
		System.out.println(Arrays.toString(array));
		return array;
	}
	
	public int[] bubbleSort(int[] array) {
		System.out.println(Arrays.toString(array));
		boolean swapped = true;
		while(swapped == true) {
			swapped = false;
			for(int i = 0; i < array.length-1; i++) {
				System.out.println(array[i] + " " + array[i+1]);
				if(array[i] > array[i+1]) {
					swap(array, i, i+1);
					swapped = true;
				} 
			}
			System.out.println(swapped);
		}
		System.out.println(Arrays.toString(array));
		return array;
	}
	
	public int[] selectionSort(int[] array) {
		System.out.println(Arrays.toString(array));
		int min;
		for(int j = 0; j < array.length; j++) {
			min = j;
			for(int i = j+1; i < array.length; i++) {
				if(array[i] < array[min]) {
					min = i;
				}
			}
			swap(array, j, min);
		}
		System.out.println(Arrays.toString(array));
		return array;
	}
	
	public int partition(int lo, int hi) {
		int pivot = array[hi]; //the pivot will be the final element in the array
		int wall = lo;
		for(int curEl = lo; curEl < hi; curEl++) {
			if(array[curEl] < pivot) {
				swap(array, wall, curEl);
				//move the wall up one place to put the current element, which is smaller than the pivot, to its left
				wall++; 
			}
			System.out.println(Arrays.toString(array));
		}
		swap(array, wall, hi);
		System.out.println("The wall is in index: " + wall);
		return wall;
	}
	
	public void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		SortingHat jaded = new SortingHat();
		
		//QuickSort
		System.out.println("The original array: " + Arrays.toString(jaded.array));
		jaded.quickSort(0, jaded.array.length-1);
		System.out.println("The final array: " + Arrays.toString(jaded.array) + "\n");
		
		//InsertionSort
		System.out.println("Insertion Sort:");
		int[] array = {4,3,2,1};
		jaded.insertionSort(array);
		
		//BubbleSort
		System.out.println("\nBubble Sort:");
		int[] array2 = {4,3,2,1};
		jaded.bubbleSort(array2);
		
		//SelectionSort
		System.out.println("\nSelection Sort:");
		int[] array3 = {4,3,2,1};
		jaded.selectionSort(array3);
	}

}
