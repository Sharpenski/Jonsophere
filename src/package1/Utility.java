package package1;

import java.util.ArrayList;
import java.util.Arrays;

public class Utility {
	
	public int[] reverse(int[] list) {
		for(int i=0; i < div(list.length, 2); i++) {
			list = swap(i,list.length-1-i,list);
		}
		return list;
	}
	
	public int div(double a, double b) {
		return floor(a/b);
	}
	
	public int floor(double a) {
		char[] numbers = Double.toString(a).toCharArray();
		int i = 0;
		String beforeDec = ""; 
		while(numbers[i] != '.') {
			beforeDec = beforeDec + Character.toString(numbers[i]);
			i++;
		}
		return Integer.parseInt(beforeDec);
	}
	
	public int[] swap(int a, int b, int[] array) {
		int temp = array[b];
		array[b] = array[a];
		array[a] = temp;
		return array;
	}
	
	public ArrayList<Integer> sieve(int a, int[] array) {
		ArrayList<Integer> sieved = new ArrayList<Integer>();
		for(int item : array) {
			if(item != a){
				sieved.add(item);
			}
		}
		return sieved;
	}
	
	public boolean contains(char[] array, char item) {
		for(char i : array) {
			if(i == item) {
				return true;
			}
		}
		return false;
	}
	
	public int[] insertionSort(int[] array) {
		for(int i = 0; i < array.length - 1; i++) {
			for(int j = i + 1; j < array.length; j++){
				if(array[i] > array[j]) {
					swap(i, j, array);
				}
			}
		}
		return array;
	}
	
	public static void main(String[] args) {
		Utility ut = new Utility();
		int[] array = {0,1,2,3,4,5,4,7,8,9};
		System.out.println("Reversed: " + Arrays.toString(ut.reverse(array)));
		System.out.println("Filtered by 2: " + ut.sieve(2, array));
		System.out.println("Sorted (insertion): " + Arrays.toString(ut.insertionSort(array)));
	}
	
}
