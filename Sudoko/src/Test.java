
public class Test {
	public static void main(String[] args) {
		SudukoSolver s = new SudukoSolver();
		s.setValue(0, 2, 8);
		s.setValue(0, 1, 8);
//		s.setValue(0, 5, 9);
//		s.setValue(0, 7, 6);
//		s.setValue(0, 8, 2);
//		s.setValue(1, 8, 5);
//
//		s.setValue(2, 2, 2);
//		s.setValue(2, 3, 5);
//		s.setValue(3, 3, 2);
//		s.setValue(3, 4, 1);
//		s.setValue(3, 7, 9);
//		s.setValue(4, 1, 5);
//		s.setValue(4, 6, 6);
//		s.setValue(5, 0, 6);
//		s.setValue(5, 7, 2);
//		s.setValue(5, 8, 8);
//		s.setValue(6, 0, 4);
//		s.setValue(6, 1, 1);
//		s.setValue(6, 3, 6);
//		s.setValue(6, 5, 8);
//		s.setValue(7, 0, 8);
//		s.setValue(7, 1, 6);
//		s.setValue(7, 4, 3);
//		s.setValue(7, 6, 1);
//		s.setValue(8, 6, 4);
//		s.setValue(2, 0, 1);
		s.solve();
		// for(int i = 0; i < 9; i++){
		// for (int j = 0; j< 9 ; j++){
		// System.out.println(s.getValue(i, j));
		// }

		System.out.println(s.toString());
	}
}
