import Interfaces.INode;
import Interfaces.IRedBlackTree;

public class RedBlackTree implements IRedBlackTree {

    INode root = new Node();

    @Override
    public INode getRoot() {
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
    public Object search(Comparable key) {
        return null;
    }

    @Override
    public boolean contains(Comparable key) {
        return false;
    }

    @Override
    public void insert(Comparable key, Object value) {

    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }

    /**
     * Rotate subtree at a given node to the left
     * used in insertion and deletion
     *
     * @param rotateNode
     */
    public void rotateLeft(INode rotateNode) {
        INode node = rotateNode.getRightChild();         //Right Child of The rotate node
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
    public void rotateRight(INode rotateNode) {
        INode node = rotateNode.getLeftChild();         //Left Child of The rotate node
        rotateNode.setLeftChild(node.getRightChild());
        //Check Whether the left node of the rotate node has right child or not
        if (node.getRightChild() != null) {
            node.getRightChild().setParent(rotateNode);
        }
        node.setRightChild(rotateNode);
        setParentRotation(node,rotateNode);
    }
    private void setParentRotation(INode node,INode rotateNode){
        node.setParent(rotateNode.getParent());
        /* Check if the given node to be rotated has parent or not
         * if it has parent , then set the parent child to node
         * it checks whether the rotate node is right or left .
         */
        if (rotateNode.getParent() != null) {
            if (((Node) rotateNode.getParent()).isRightChild(rotateNode))
                rotateNode.getParent().setRightChild(node);
            else
                rotateNode.getParent().setLeftChild(node);
        } else
            this.root = node;
        rotateNode.setParent(node);
    }
}
