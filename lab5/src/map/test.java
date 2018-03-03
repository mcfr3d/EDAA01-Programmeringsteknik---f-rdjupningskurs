package map;

public class test {
	
	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> hash = new SimpleHashMap<Integer, Integer>();

		for (int i = 0; i < 15; i++) {
			hash.put(i, i);
		}

		System.out.println(hash.show());

	}

}
