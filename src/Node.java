import Interfaces.INode;

public class Node<T extends Comparable<T>, V> implements INode {

        private T key;
        private V value;
        private INode<T,V> leftChild, rightChild,parent;
        private boolean color;

    @Override
    public void setParent(INode parent) {
        this.parent = parent;
    }

    @Override
    public INode getParent() {
        return this.parent;
    }

    @Override
    public void setLeftChild(INode leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public INode getLeftChild() {
        return this.leftChild;
    }

    @Override
    public void setRightChild(INode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public INode getRightChild() {
        return this.rightChild;
    }

    @Override
    public Comparable getKey() {
        return this.key;
    }

    @Override
    public void setKey(Comparable key) {
        this.key = (T) key;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (V) value;
    }

    @Override
    public boolean getColor() {
        return this.color;
    }

    @Override
    public void setColor(boolean color) {
        this.color = color;
    }

    @Override
    public boolean isNull() {
        return this.key == null;
    }
}