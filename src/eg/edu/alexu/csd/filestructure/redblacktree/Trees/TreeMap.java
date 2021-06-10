package eg.edu.alexu.csd.filestructure.redblacktree.Trees;

import javax.management.RuntimeErrorException;
import java.util.*;

public class TreeMap<T extends Comparable<T>,V> implements ITreeMap<T,V>{
    private final IRedBlackTree<T,V> root = new RedBlackTree<>();


    @Override
    public Map.Entry<T, V> ceilingEntry(T key) {
        if (key == null) {
            throw new RuntimeErrorException(new Error("key can't be null"));
        }

        INode<T, V> current = root.getRoot();
        while (!current.isNull()) {
            if (key.compareTo(current.getKey()) < 0) {
                if (!current.getLeftChild().isNull())
                    current = current.getLeftChild();
                else
                    return new MapEntry<>(current.getKey(), current.getValue());
            } else if (key.compareTo(current.getKey()) > 0) {
                if (current.getRightChild() != null) {
                    current = current.getRightChild();
                } else {
                    INode<T, V> parent = current.getParent();
                    INode<T, V> child = current;
                    while (parent != null && child == parent.getRightChild()) {
                        child = parent;
                        parent = parent.getParent();
                    }
                    if (parent != null)
                        new MapEntry<>(parent.getKey(), parent.getValue());
                    return null;
                }
            } else
                return new MapEntry<>(current.getKey(), current.getValue());
        }
        return null;
    }

    @Override
    public T ceilingKey(T key) {
        return ceilingEntry(key).getKey();
    }

    @Override
    public void clear() {
        this.root.clear();
    }

    @Override
    public boolean containsKey(T key) {
        if (key == null){
            throw new RuntimeErrorException(new Error("Can't contain null value"));
        }
        return root.contains(key);
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null) {
            throw new RuntimeErrorException(new Error("Can't contain null value"));
        } else {
            return this.values().contains(value);
        }
    }

    @Override
    public Set<Map.Entry<T, V>> entrySet() {
        return root.getEntries(root.getRoot());
    }

    @Override
    public Map.Entry<T, V> firstEntry() {
        return firstEntry(root.getRoot());
    }
    private Map.Entry<T,V> firstEntry(INode<T,V> node){
        if (root.getRoot() != null) {
            INode<T, V> min = root.findMin(node);
            if (min == null) return null;
            return new MapEntry<>(min.getKey(), min.getValue());
        }
        return null;
    }

    @Override
    public T firstKey() {
        if (root.getRoot() != null) {
            INode<T, V> min = root.findMin(root.getRoot());
            if (min == null)
                return null;
            return min.getKey();
        }
        return null;
    }

    /**
     * Returns a key-value mapping associated with the greatest key less than or equal to the given key, or null if there is no such key.
     */

    @Override
    public Map.Entry<T, V> floorEntry(T key) {
        if (key == null) {
            throw new RuntimeErrorException(new Error("key can't be null"));
        }

        INode<T, V> current = root.getRoot();
        while (!current.isNull()) {
            if (key.compareTo(current.getKey()) > 0) {
                if (!current.getRightChild().isNull())
                    current = current.getRightChild();
                else
                    return new MapEntry<>(current.getKey(), current.getValue());
            } else if (key.compareTo(current.getKey()) < 0) {
                if (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                } else {
                    INode<T, V> parent = current.getParent();
                    INode<T, V> child = current;
                    while (parent != null && child == parent.getLeftChild()) {
                        child = parent;
                        parent = parent.getParent();
                    }
                    if (parent != null)
                        new MapEntry<>(parent.getKey(), parent.getValue());
                    return null;
                }
            } else
                return new MapEntry<>(current.getKey(), current.getValue());
        }
        return null;
    }

    @Override
    public T floorKey(T key) {
        return floorEntry(key).getKey();
    }

    @Override
    public V get(T key) {
        if (key == null){
            throw new RuntimeErrorException(new Error("Can't get null key"));
        }
        return this.root.search(key);
    }
    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
        if(toKey == null)
            throw new RuntimeErrorException(new Error("Key can't be Null"));
        return headMap(toKey,false);
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
        if(toKey == null)
            throw new RuntimeErrorException(new Error("Key can't be Null"));
        ArrayList<Map.Entry<T,V>> entries = new ArrayList<>();
        return lessThanKey(toKey,root.getRoot(),entries,inclusive);

    }
    private void addAll(INode<T,V> node,ArrayList<Map.Entry<T,V>> head) {
        if (node.isNull()) return;
        addAll(node.getLeftChild(),head);
        head.add(new MapEntry<>(node.getKey(),node.getValue()));
        addAll(node.getRightChild(),head);
    }

    private ArrayList<Map.Entry<T, V>> lessThanKey(T Key, INode<T,V> node,ArrayList<Map.Entry<T,V>> head,boolean inclusive){
        if (node.isNull()) return head;
        if ((inclusive && node.getKey().compareTo(Key)<=0)||(!inclusive && node.getKey().compareTo(Key)<0)) {
            addAll(node.getLeftChild(),head);
            head.add(new MapEntry<>(node.getKey(),node.getValue()));
            lessThanKey(Key, node.getRightChild(),head,inclusive);
        } else {
            lessThanKey(Key, node.getLeftChild(),head,inclusive);
        }
        return head;
    }

    @Override
    public Set<T> keySet() {
        TreeSet<T> keys = new TreeSet<>();
        Set<Map.Entry<T,V>> entries = root.getEntries(root.getRoot());
        for(Map.Entry<T,V> entry : entries)
            keys.add(entry.getKey());
        return keys;
    }

    @Override
    public Map.Entry<T, V> lastEntry() {
        return lastEntry(root.getRoot());
    }

    public Map.Entry<T, V> lastEntry(INode<T,V> node) {
        if (root.getRoot() != null){
        INode<T,V> max = root.findMax(node);
        if(max == null) return null;
        return new MapEntry<>(max.getKey(),max.getValue());
        }
        return null;
    }

    @Override
    public T lastKey() {
        if (root.getRoot() != null) {
            INode<T, V> max = root.findMax(root.getRoot());
            if (max == null)
                return null;
            return max.getKey();
        }
        return null;
    }

    @Override
    public Map.Entry<T, V> pollFirstEntry() {
        Map.Entry<T, V> first = firstEntry();
        if (first != null) {
            remove(first.getKey());
            return first;
        }
        return null;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        Map.Entry<T, V> last = lastEntry();
        if (last != null) {
            remove(last.getKey());
            return last;
        }
        return null;
    }

    @Override
    public void put(T key, V value) {
        if (key == null){
            throw new RuntimeErrorException(new Error("Can't put null key"));
        }
        root.insert(key,value);
    }

    @Override
    public void putAll(Map<T, V> map) {
        if(map == null)
            throw new RuntimeErrorException(new Error("The map to copy from is null"));
        for (Map.Entry<T, V> e : map.entrySet()){
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public boolean remove(T key) {
        return root.delete(key);
    }

    @Override
    public int size() {
        return root.getSize();
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        Set<Map.Entry<T,V>> entries = root.getEntries(root.getRoot());
        for(Map.Entry<T,V> entry : entries)
            values.add(entry.getValue());
        return values;
    }
}
