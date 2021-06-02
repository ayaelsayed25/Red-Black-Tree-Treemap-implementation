import Interfaces.INode;

public class Node<T extends Comparable<T>, V> implements INode {
    @Override
    public void setParent(INode parent) {

    }

    @Override
    public INode getParent() {
        return null;
    }

    @Override
    public void setLeftChild(INode leftChild) {

    }

    @Override
    public INode getLeftChild() {
        return null;
    }

    @Override
    public void setRightChild(INode rightChild) {

    }

    @Override
    public INode getRightChild() {
        return null;
    }

    @Override
    public Comparable<T> getKey() {
        return null;
    }

    @Override
    public void setKey(Comparable key) {

    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void setValue(Object value) {

    }

    @Override
    public boolean getColor() {
        return false;
    }

    @Override
    public void setColor(boolean color) {

    }

    @Override
    public boolean isNull() {
        return false;
    }


}
