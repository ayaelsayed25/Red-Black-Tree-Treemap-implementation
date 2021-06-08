package eg.edu.alexu.csd.filestructure.redblacktree;

import eg.edu.alexu.csd.filestructure.redblacktree.Trees.INode;
import eg.edu.alexu.csd.filestructure.redblacktree.Trees.RedBlackTree;
import org.junit.Assert;
import org.junit.Test;


import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RotationTest {

    @Test
    public void test() {

//        INode<Integer, Integer> root = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        root.setKey(20);
//        INode<Integer, Integer> node1 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node1.setKey(9);
//        INode<Integer, Integer> node2 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node2.setKey(6);
//        INode<Integer, Integer> node3 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node3.setKey(12);
//        INode<Integer, Integer> node4 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node4.setKey(1);
//        INode<Integer, Integer> node5 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node5.setKey(25);
//        INode<Integer, Integer> node6 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node6.setKey(31);
//        INode<Integer, Integer> node7 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node7.setKey(8);
//        INode<Integer, Integer> node8 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node8.setKey(10);
//        INode<Integer, Integer> node10 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node10.setKey(2);
//        INode<Integer, Integer> node11 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node11.setKey(5);
//        INode<Integer, Integer> node12 = new eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.Node();
//        node12.setKey(30);
//        root.setLeftChild(node11);
//        root.setRightChild(node12);
//        root.setParent(null);
//        node12.setLeftChild(node5);
//        node12.setRightChild(node6);
//        node12.setParent(root);
//        node11.setLeftChild(node10);
//        node11.setRightChild(node8);
//        node11.setParent(root);
//        node5.setLeftChild(null);
//        node5.setRightChild(null);
//        node5.setParent(node12);
//        node6.setLeftChild(null);
//        node6.setRightChild(null);
//        node6.setParent(node12);
//        node10.setLeftChild(node4);
//        node10.setRightChild(null);
//        node10.setParent(node11);
//        node4.setLeftChild(null);
//        node4.setRightChild(null);
//        node4.setParent(node10);
//        node8.setLeftChild(node7);
//        node8.setRightChild(node3);
//        node8.setParent(node11);
//        node3.setLeftChild(null);
//        node3.setRightChild(null);
//        node3.setParent(node8);
//        node7.setLeftChild(node2);
//        node7.setRightChild(node1);
//        node7.setParent(node8);
//        node2.setLeftChild(null);
//        node2.setRightChild(null);
//        node2.setParent(node7);
//        node1.setLeftChild(null);
//        node1.setRightChild(null);
//        node1.setParent(node7);

      RedBlackTree redBlackTree = new RedBlackTree();
//        tree.rotateLeft(node11);
//        assertEquals((int) root.getLeftChild().getKey(), 10);
//        assertEquals((int) root.getLeftChild().getLeftChild().getKey(), 5);
//        tree.rotateRight(node8);
//        assertEquals((int) root.getLeftChild().getKey(), 5);
//        assertEquals((int) root.getLeftChild().getLeftChild().getKey(), 2);

        redBlackTree.insert("Soso", "Toto");
        INode<String, String> root = redBlackTree.getRoot();
        Assert.assertEquals("Soso", root.getKey());
        Assert.assertEquals("Toto", root.getValue());


    }

    @Test
    public void test2(){
        RedBlackTree redBlackTree = new RedBlackTree();
        Random r = new Random();
        for(int i = 0; i < 100; i++) {
            int key = r.nextInt(100000);
            redBlackTree.insert(key, "toto" + key);
        }
        redBlackTree.insert(null, "soso");
        Assert.fail();
    }
}
