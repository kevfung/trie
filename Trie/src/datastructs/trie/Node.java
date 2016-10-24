package datastructs.trie;

import java.util.Map;
import java.util.TreeMap;

public class Node {
	String word;
	Map<String, Edge> edges = new TreeMap<String, Edge>();
	
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
