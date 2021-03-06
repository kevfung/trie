package datastructs.trie;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class TestTrie {

	@Test
	public void test_walkTriePrint() {
		Trie trie = new Trie();
		
		trie.addWord("Apple");	
		trie.addWord("Application");
		trie.addWord("Abacus");
		trie.addWord("Absolute");
		trie.addWord("Blue");
		trie.addWord("Black");
		trie.addWord("Block");
		trie.walkTriePrint();
	}

	@Test
	public void test_walkTreeFind() {
		Trie trie = new Trie();
		
		trie.addWord("Absolute");
		trie.addWord("Abs");
		trie.addWord("Apple");	
		trie.addWord("Application");
		trie.addWord("Appendix");
		trie.addWord("Apparel");
		trie.addWord("Blast");
		trie.addWord("Black");		
		
		// Find the possible characters that come next when given "Ab"
		Set<String> nextChars = trie.findNextChar("Ab");
		assertTrue("Expected to find letter 's' as next char", nextChars.contains("s"));
		
		// Find the possible characters that come next when given "App".
		nextChars = trie.findNextChar("App");
		assertTrue("Expected to find letter 'l' as next char", nextChars.contains("l"));
		assertTrue("Expected to find letter 'e' as next char", nextChars.contains("l"));
		assertTrue("Expected to find letter 'a' as next char", nextChars.contains("l"));
	}
	
}
