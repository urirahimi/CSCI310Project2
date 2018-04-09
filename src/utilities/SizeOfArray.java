package utilities;

public class SizeOfArray {
	public int getArraySize() {
		int arr[] = new int[10];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = i;
		}
		return arr.length;
	}
}
