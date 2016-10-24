package datastructs.trie;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a node in a Trie. A node contains a word and a {@link Map} of {@link Edge}s.
 * The map is keyed by each edge's character and the object stored is the edge itself.
 * 
 * @author Kevin
 *
 */
public class Node {
	String word;
	Map<String, Edge> edges = new TreeMap<String, Edge>();
	
	/**
	 * Creates a node representing the given word
	 * 
	 * @param word
	 */
	Node(String word) {
		if (word == null) 
			throw new IllegalArgumentException("word parameter cannot be null");
		
		this.word = word;
	}	
	
	void addEdge(Edge edge) {
		edges.put(edge.character, edge);
	}

	public String getWord() {
		return word;
	}

	public Map<String, Edge> getEdges() {
		return edges;
	}
}
