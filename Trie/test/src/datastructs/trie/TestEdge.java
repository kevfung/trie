package datastructs.trie;

import org.junit.Test;

public class TestEdge {

	@Test(expected=IllegalArgumentException.class)
	public void test_EdgeConstructor_NullParent() {
		new Edge(null, new Node("child"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_EdgeConstructor_NullChild() {
		new Edge(new Node("parent"), null);
	}	
}
