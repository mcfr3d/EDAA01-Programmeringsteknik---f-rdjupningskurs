package queue;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FifoQueue<String> myStringQueue= new FifoQueue<String>();
		myStringQueue.offer("First");
		myStringQueue.offer("Second");
		myStringQueue.offer("Third");
//		String s = myStringQueue.testlast();
//		System.out.println(s);

	}

}
