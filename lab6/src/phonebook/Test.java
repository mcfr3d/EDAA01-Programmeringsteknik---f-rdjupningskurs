package phonebook;

public class Test {

	public static void main(String[] args) {
		PhoneBook book = new PhoneBook();
		book.put("Fredrik", "12345");
		book.put("Fredrik", "55555");
		book.put("Fredrik", "4646464");
		book.put("Anders", "4646474");
		book.put("Anders", "12345");
		book.put("Rikard","16161616");
//		book.removeNumber("Fredrik", "12345");
		System.out.println(book.toString());
		
		for(String s: book.findNumber("yredrik")){
			System.out.println(s);
		}
		
		for(String s: book.findNames("12345")){
			System.out.println(s);
		}
		// TODO Auto-generated method stub
System.out.println();
System.out.println();

		for(String s:book.names()){
			System.out.println(s);
			
		}
		
		System.out.println("Storlek : " + book.size());
		
	}

}
