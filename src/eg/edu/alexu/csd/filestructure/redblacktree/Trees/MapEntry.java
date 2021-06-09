package eg.edu.alexu.csd.filestructure.redblacktree.Trees;

import java.util.Map;

public class MapEntry<T extends Comparable<T>, V> implements Map.Entry<T,V>{
    private final T key;
    private V value;
    public MapEntry(T key,V value){
        this.key = key;
        this.value = value;
    }
    @Override
    public T getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V oldVal = this.value;
        this.value = value;
        return oldVal;
    }
}
