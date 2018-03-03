package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import set.MaxSet;
import set.RemoveDuplicates;

public class TestRemoveDuplicates {
	private int[] s;


	@Before
	public void setUp() throws Exception {

		int[] fiveEle = {1,2,2,4,5};
		int[] tenEle = {1,2,3,3,5,6,6,8,9,11};
		int[] allSame = {5,5,5,5,5,5};

	}

	@After
	public void tearDown() throws Exception {

		int[] fiveEle = null;
		int[] tenEle = null;
		int[] allSame = null;
	}

	@Test
	public void sameElements(){
		int[] s = {5};
		int[] allSame = {5,5,5,5,5,5};
		int[] test =RemoveDuplicates.uniqueElements(allSame);
		assertTrue("är det sant",(s.length==test.length));   
		assertEquals("Något gick fel",s[0] ,test[0] );
	}
	
	
	
	
	@Test
	public void varyElements(){
		int[] tenEle = {11,9,8,8,7,6,6,5,4,3};
		int[] s= {3,4,5,6,7,8,9,11};
		int[] test =RemoveDuplicates.uniqueElements(tenEle);
		assertTrue("är det sant",(s.length==test.length));
		for(int i=0;i<s.length;i++){
		assertEquals("Något gick fel",s[i] ,test[i] );
	}
	}
	
	@Test
	public void difElements(){
		int[] difEle = {3,4,5,6,7,8,9,11};
		int[] s= {3,4,5,6,7,8,9,11};
		int[] test =RemoveDuplicates.uniqueElements(difEle);
		assertTrue("är det sant",(s.length==test.length));
		for(int i=0;i<s.length;i++){
		assertEquals("Något gick fel",s[i] ,test[i] );
	}
	}
	
}