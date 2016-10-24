package datastructs.trie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Trie {
	Map<String, Node> rootNodes = new HashMap<>(26);
	
	public void addWord(String word) {
		if (word == null || word.length() == 0) 
			return;
		
		String firstChar = word.charAt(0) + "";	
		Node rootNode = getRootNode(firstChar);
		
		try {
			String restOfWord = word.substring(1);
			addWordHelper(rootNode, restOfWord);
		}
		catch (IndexOutOfBoundsException ex) {
			return;
		}			
		
		return;
	}
	
	/**
	 * Recursive method to continue creating nodes
	 * and edges for the given word.
	 * 
	 * @param parent
	 * @param word
	 */
	private void addWordHelper(Node parent, String word) {
		if (parent == null || word == null || word.length() == 0) 
			return;
		
		Node child = null;
		
		String firstChar = word.charAt(0) + "";
		
		// Try to find an existing edge that already represents
		// the first character in the word.
		if( parent.getEdges() != null) {
			Map<String, Edge> edges = parent.edges;
			if (edges.containsKey(firstChar)) {
				Edge edge = edges.get(firstChar);
				child = edge.getChild();				
			}
		}

		// If we couldn't find an existing child from the edges
		// of the parent node...
		if (child == null) {
			child = new Node(parent.word + firstChar);
			Edge newEdge = new Edge(parent, child);
			parent.addEdge(newEdge);
		}
				
		// Recursively call this method with the child node
		// and the rest of the word (minus the first character)
		try {			
			String restOfWord = word.substring(1);
			addWordHelper(child, restOfWord);
		} 
		catch (IndexOutOfBoundsException ex) {
			return;
		}
	}
	
	/**
	 * Gets the root node that represents this character.
	 * If it cannot find it, it creates a new node and
	 * adds it to the map of root nodes.
	 * 
	 * @param firstChar
	 * @return {@link Node}
	 */
	private Node getRootNode(String firstChar) {	
		if (rootNodes.containsKey(firstChar)) {
			return rootNodes.get(firstChar);
		} else {
			Node rootNode = new Node(firstChar);
			rootNodes.put(firstChar, rootNode);
			
			return rootNode;
		}
	}
	
	public void walkTrie() {
		Set<String> keys = rootNodes.keySet();
		for (String key : keys) {
			Node rootNode = rootNodes.get(key);
			
			System.out.println(rootNode.word);			
			walkTriePrint(rootNode, 1);
			System.out.println("");
		}
	}
	
	private void walkTriePrint(Node node, int depth) {	
		if (node ==  null)
			return;		
		
		if (node.getEdges() == null || node.getEdges().size() == 0)
			System.out.println(">" + new String(new char[depth]).replace("\0", "-") + node.word);
		
		Map<String, Edge> edgesMap = node.getEdges();	
		Collection<Edge> edges = edgesMap.values();	
		for (Edge edge : edges) {
			System.out.println("|" + new String(new char[depth]).replace("\0", "-") + edge.character);
			Node child = edge.getChild();
			walkTriePrint(child, depth + 1);
		}
	}
	
	/**
	 * Given a word, this returns a Set of all possible
	 * next characters based on this  Trie
	 * 
	 * @param word to find possible next characters for
	 * @return Set<String> of all possible next characters
	 */
	public Set<String> findNextChar(String word) {
		if (word == null || word.length() == 0)
			return null;
				
		String firstChar = word.charAt(0) + "";
		
		if (rootNodes.containsKey(firstChar)) {
			Node rootNode = rootNodes.get(firstChar);
			
			try {
				return walkTrieFind(rootNode, word.substring(1));
			}
			// If the word only has one character, then we'll
			// get an exception for the substring call. This
			// means all edges from this root node are possible
			// next characters
			catch (IndexOutOfBoundsException ex) {
				return rootNode.edges.keySet();
			}
		}
		
		return null;
	}
	
	private Set<String> walkTrieFind(Node node, String word) {
		if (node ==  null)
			return null;			
		
		String firstChar = word.charAt(0) + "";
		
		Map<String, Edge> edgesMap = node.getEdges();			
		if (edgesMap.containsKey(firstChar)) {
			Node child = edgesMap.get(firstChar).getChild();
			
			try {				
				String restOfString = word.substring(1);				
				return walkTrieFind(child, restOfString);
			}
			// We are at the last character of the original word
			// this means all edges of the child node are possible 
			// solutions to return
			catch (IndexOutOfBoundsException ex) {
				return child.edges.keySet();
			}
		}
		
		return new TreeSet<String>();		
	}
	
}
