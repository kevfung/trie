package datastructs.trie;

/**
 * Represents an edge in a Trie. It connects a parent {@link Node} to a child {@link Node}.
 * It also contains a single character that is appended to the parent node's word
 * to get the word found in the child node.
 * 
 * <br/><br/>
 * Example:
 * 		Apple (parent) --s-->  Apples (child)
 * <br/>
 *      where --s--> represents the edge
 * 
 * @author Kevin
 *
 */
public class Edge {
	String character;
	Node parent;
	Node child;	
	
	/**
	 * Creates an edge between a parent {@link Node} and a child {@link Node}.
	 * 
	 * @param parent
	 * @param child
	 */
	Edge(Node parent, Node child) {			
		if (parent == null)
			throw new IllegalArgumentException("parent node parameter cannot be null");
		
		if (parent.word == null) 
			throw new IllegalArgumentException("parent node parameter's word cannot be null");

		if (child == null)
			throw new IllegalArgumentException("child node parameter cannot be null");
		
		if (child.word == null) 
			throw new IllegalArgumentException("child node parameter's word cannot be null");
			
		if ((child.word.length() - parent.word.length()) != 1)
			throw new IllegalArgumentException("child node's word should differ from the parent node's word by one character. "
					+ "Parent word = " + parent.word
					+ " Child word = " + child.word);
		
		// Get the last character in the child word, which would be the character of this edge.
		this.character = child.word.substring(parent.word.length());		
		this.parent = parent;		
		this.child = child;
	}		
	
	public String getCharacter() {
		return character;
	}

	public Node getParent() {
		return parent;
	}

	public Node getChild() {
		return child;
	}
}
