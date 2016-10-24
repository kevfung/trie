package datastructs.trie;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class TestNode {

	@Test(expected=IllegalArgumentException.class)
	public void test_NodeConstructor() {
		new Node(null);
	}
	
	@Test
	public void test_AddEdge() {
		// parent = A and child = A; should not be allowed
		try {
			Node parent = new Node("A");
			parent.addEdge(new Edge(parent, new Node("A")));
			fail("Expected exception when addEdge() called where parent and child node have same length");
		} 
		catch (IllegalArgumentException ex) {			
		}
		
		// parent = Aa and child = A; should not be allowed
		try {
			Node parent = new Node("Aa");
			parent.addEdge(new Edge(parent, new Node("A")));
			fail("Expected exception when addEdge() called where parent node has a bigger length than the child");
		} 
		catch (IllegalArgumentException ex) {			
		}
		
		// parent = A and child = Aa; should be allowed
		try {
			Node parent = new Node("A");
			
			parent.addEdge(new Edge(parent, new Node("Aa")));		
		} 
		catch (IllegalArgumentException ex) {			
			fail("Do not expect exception for parent = A and child = Aa");
		}
		
		
	}
	
	@Test
	public void test_NodeEdgesStoredInOrder() {
		Node parent = new Node("A");
		
		// add edges out of alphabetical order to test if the edges will be stored in order
		parent.addEdge(new Edge(parent, new Node("Aa")));
		parent.addEdge(new Edge(parent, new Node("Az")));
		parent.addEdge(new Edge(parent, new Node("Ac")));
		parent.addEdge(new Edge(parent, new Node("Ae")));
		parent.addEdge(new Edge(parent, new Node("Ab")));
		parent.addEdge(new Edge(parent, new Node("Af")));	
		
		Map<String, Edge> edgeMap = parent.getEdges();
		Set<Entry<String, Edge>> edgeSet = edgeMap.entrySet();
		String parentEdgeChar= null;
		for(Entry<String, Edge> edgeEntry : edgeSet) {
			if (parentEdgeChar == null) {
				parentEdgeChar = edgeEntry.getKey();
				continue;
			} 
			else {
				String thisEdgeChar = edgeEntry.getKey();
				assertTrue("Found an edge that is not in order: parent=" + parentEdgeChar + " child=" + thisEdgeChar, parentEdgeChar.compareTo(thisEdgeChar) < 0);
			}
		}
	}

}
