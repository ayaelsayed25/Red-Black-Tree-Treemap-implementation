package eg.edu.alexu.csd.filestructure.redblacktree.Trees;

import javax.management.RuntimeErrorException;
import java.util.*;

public class TreeMap<T extends Comparable<T>,V> implements ITreeMap<T,V>{
    private int size =0;
    private final RedBlackTree<T,V> root = new RedBlackTree<>();

    @Override
    public Map.Entry<T, V> ceilingEntry(T key) {
        if (key == null){
            throw new RuntimeErrorException(new Error("key can't be null"));
        }
        if (root.contains(key)){
            return new MapEntry<>(key,get(key));
        }
        else{
            for (Map.Entry<T, V> iterator : root.getEntries(root.getRoot())){
                if (iterator.getKey().compareTo(key) > 0){
                    return new MapEntry<>(iterator.getKey(),iterator.getValue());
                }
            }
        }
            return null;

    }

    @Override
    public T ceilingKey(T key) {
        return ceilingEntry(key).getKey();
    }

    @Override
    public void clear() {
        this.size = 0;
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
     * @param key
     * @return
     */
    @Override
    public Map.Entry<T, V> floorEntry(T key) {
        if (key == null){
            throw new RuntimeErrorException(new Error("key can't be null"));
        }
        if (root.contains(key)){
            return new MapEntry<>(key,get(key));
        }
        else{
            LinkedList<Map.Entry<T, V>> list = new LinkedList<>(root.getEntries(root.getRoot()));
            Iterator<Map.Entry<T, V>> itr = list.descendingIterator();
            while (itr.hasNext()) {
                if (itr.next().getKey().compareTo(key) < 0){
                    return new MapEntry<>(itr.next().getKey(), itr.next().getValue());
                }
            }
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
        return (V) this.root.search(key);
    }
    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
        if(toKey == null)
            throw new RuntimeErrorException(new Error("Key can't be Null"));
        return getHeadMap(toKey,false);
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
        if(toKey == null)
            throw new RuntimeErrorException(new Error("Key can't be Null"));
        if(inclusive)
            return getHeadMap(toKey,inclusive);
        else
            return getHeadMap(toKey,inclusive);
    }

    private ArrayList<Map.Entry<T, V>> getHeadMap(T toKey, boolean inclusive){
        List<Map.Entry<T, V>> head = new ArrayList<>();
        for(Map.Entry<T, V> entry:root.getEntries(root.getRoot())){
            if(entry.getKey().compareTo(toKey)>0)
                break;
            else
                head.add(entry);
        }
        if(!inclusive)
            head.remove(head.size()-1);
        return (ArrayList) head;

    }
    /**
     * Returns a Set view of the keys contained in this map.
     * @return
     */
    @Override
    public Set<T> keySet() {
        TreeSet<T> keys = new TreeSet<T>();
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
            size--;
            return first;
        }
        return null;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        Map.Entry<T, V> last = lastEntry();
        if (last != null) {
            remove(last.getKey());
            size--;
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
        size++;
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
        root.delete(key);
        size--;
        return false;
    }

    @Override
    public int size() {
        return size;
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
