import Interfaces.INode;
import Interfaces.IRedBlackTree;

import java.security.Key;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T,V> {

    INode<T,V> root = new Node<>();

    @Override
    public INode<T,V> getRoot() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public V search(T key) {
        return null;
    }

    @Override
    public boolean contains(T key) {
        return false;
    }

    @Override
    public void insert(T key, V value) {

    }

    @Override
    public boolean delete(T key) {
        return delete( Find(root,key));

    }
    private boolean delete(INode<T,V> deletedNode){
        if(deletedNode == null)
            return false;
        //TODO if parent is null (root case) //1
        if(deletedNode.getLeftChild() == null && deletedNode.getRightChild() == null){
            if(deletedNode.getColor()){
                if(((Node<T,V>)deletedNode).isChildLeft())
                    deletedNode.getParent().setLeftChild(null);
                else
                    deletedNode.getParent().setRightChild(null);
            }
            else deletedNode = doubleBlack(deletedNode);
        }
        else if(deletedNode.getLeftChild() == null){
            if(!deletedNode.getColor())
                deletedNode.getRightChild().setColor(false);
            deletedNode.getParent().setRightChild(deletedNode.getRightChild());
        }
        else if(deletedNode.getRightChild() == null){
            if(!deletedNode.getColor())
                deletedNode.getLeftChild().setColor(false);
            deletedNode.getParent().setLeftChild(deletedNode.getLeftChild());
        }
        else {
            INode<T,V> predecessor = findMin(deletedNode.getRightChild());
            deletedNode.setValue(predecessor.getValue());
            delete(predecessor);
        }
        return true;
    }

    /**
     * Rotate subtree at a given node to the left
     * used in insertion and deletion
     *
     * @param rotateNode
     */
    public void rotateLeft(INode<T,V> rotateNode) {
        INode<T,V> node = rotateNode.getRightChild();         //Right Child of The rotate node
        rotateNode.setRightChild(node.getLeftChild());
        //Check Whether the right node of the rotate node has left child or not
        if (node.getLeftChild() != null) {
            node.getLeftChild().setParent(rotateNode);
        }
        node.setLeftChild(rotateNode);
        setParentRotation(node,rotateNode);
    }


    /**
     * Rotate subtree at a given node to the right
     * used in insertion and deletion
     *
     * @param rotateNode
     */
    public void rotateRight(INode<T,V> rotateNode) {
        INode<T,V> node = rotateNode.getLeftChild();         //Left Child of The rotate node
        rotateNode.setLeftChild(node.getRightChild());
        //Check Whether the left node of the rotate node has right child or not
        if (node.getRightChild() != null) {
            node.getRightChild().setParent(rotateNode);
        }
        node.setRightChild(rotateNode);
        setParentRotation(node,rotateNode);
    }
    private void setParentRotation(INode<T,V> node,INode<T,V> rotateNode){
        node.setParent(rotateNode.getParent());
        /* Check if the given node to be rotated has parent or not
         * if it has parent , then set the parent child to node
         * it checks whether the rotate node is right or left .
         */
        if (rotateNode.getParent() != null) {
            if (((Node<T,V>) rotateNode.getParent()).isRightChild(rotateNode))
                rotateNode.getParent().setRightChild(node);
            else
                rotateNode.getParent().setLeftChild(node);
        } else
            this.root = node;
        rotateNode.setParent(node);
    }

    private INode<T,V> Find(INode<T,V> root,T key){
        if(root == null)
            return null;
        if(key.compareTo(root.getKey()) < 0)
             return Find(root.getLeftChild(),key);
        else if(key.compareTo(root.getKey()) > 0)
            return Find(root.getRightChild(),key);
        return root;
    }
    private INode<T,V> findMin(INode<T,V> node){
        //TODO implement find min //1
        return null;
    }
    private INode<T,V> doubleBlack(INode<T,V> node) {
        //TODO implement find black
        return null;
        // if red sibling {rotate} //2
        //if black sibling + no red children  {recolor and recurse} //2
        // if black sibling + at least one red child{near & far > rotations} //1
        // sarah 1
        // aya 2
    }


}
