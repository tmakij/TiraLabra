package com.mycompany.tiralabra_maven;

import java.io.Serializable;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class NodeTest {
    
    private static final char TEST_SYMBOL = 'P';
    private static final int TEST_WEIGHT = 23;
    private Node node;
    
    @Before
    public void setUp() {
        node = new Node(TEST_SYMBOL, TEST_WEIGHT, null, null);
    }
    
    @Test
    public void testGetSymbol() {
        assertEquals(node.getSymbol(), TEST_SYMBOL);
    }
    
    @Test
    public void testIsLeafWhenIsLeaf() {
        assertTrue(node.isLeaf());
    }
    
    @Test
    public void testGetLeftWhenNull() {
        assertNull(node.getLeft());
    }
    
    @Test
    public void testGetRightWhenNull() {
        assertNull(node.getRight());
    }
    
    @Test
    public void testGetWeight() {
        assertEquals(node.getWeight(), TEST_WEIGHT);
    }
    
    @Test
    public void testIsLeafWhenNotLeaf() {
        Node parentNode = new Node(TEST_SYMBOL, TEST_WEIGHT, node, node);
        assertFalse(parentNode.isLeaf());
    }
    
    @Test
    public void testIsLeafWhenNotLeafLeftNull() {
        Node parentNode = new Node(TEST_SYMBOL, TEST_WEIGHT, null, node);
        assertFalse(parentNode.isLeaf());
    }
    
    @Test
    public void testIsLeafWhenNotLeafRightNull() {
        Node parentNode = new Node(TEST_SYMBOL, TEST_WEIGHT, node, null);
        assertFalse(parentNode.isLeaf());
    }
    
    @Test
    public void testGetLeftWhenNotNull() {
        Node parentNode = new Node(TEST_SYMBOL, TEST_WEIGHT, node, null);
        assertEquals(node, parentNode.getLeft());
    }
    
    @Test
    public void testGetRightWhenNotNull() {
        Node parentNode = new Node(TEST_SYMBOL, TEST_WEIGHT, null, node);
        assertEquals(node, parentNode.getRight());
    }
    
    @Test
    public void testGetWeightWhenNotLeaf() {
        final int rightWeigth = 945;
        Node rightSide = new Node('3', rightWeigth, null, null);
        Node parentNode = new Node(TEST_SYMBOL, TEST_WEIGHT, node, rightSide);
        assertEquals(rightWeigth + TEST_WEIGHT, parentNode.getWeight());
    }
    
    @Test
    public void testCompareWhenLess() {
        Node anotherNode = new Node(TEST_SYMBOL, TEST_WEIGHT - 1, null, null);
        assertTrue(anotherNode.compareTo(node) < 0);
    }
    
    @Test
    public void testCompareWhenMore() {
        Node anotherNode = new Node(TEST_SYMBOL, TEST_WEIGHT + 1, null, null);
        assertTrue(anotherNode.compareTo(node) > 0);
    }
    
    @Test
    public void testCompareWhenEquals() {
        Node anotherNode = new Node(TEST_SYMBOL, TEST_WEIGHT, null, null);
        assertEquals(0, anotherNode.compareTo(node));
    }
    
    @Test
    public void testSerializable() {
        assertTrue(node instanceof Serializable);
    }
}
