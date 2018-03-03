package set;

public class RemoveDuplicates {
	

	public static int[] uniqueElements(int [] ints){
		MaxSet<Integer> mx = new MaxSet<Integer>();
		for(int i: ints){
			mx.add(i);
		}
		int[] elements = new int[mx.size()];
		for(int i = mx.size()-1; i>=0; i--){
			elements[i]= mx.getMax();
			mx.remove(mx.getMax());
		}
		return elements;
	}
	
	public static void main(String [] args){
		 int[] g = {7,5,3,5,2,2,7};
		 int[] r = RemoveDuplicates.uniqueElements(g);
		 for(int i: r){
		 System.out.println(i);
		 }
		
	}
}