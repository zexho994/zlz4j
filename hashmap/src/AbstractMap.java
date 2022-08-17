import java.util.HashMap;

/**
 * @author Zexho
 * @date 8/17/22 10:47 AM
 */
public abstract class AbstractMap<K, V> {

    protected Entry<K, V>[] entries;
    protected int capacity = 16;
    protected int size = 0;
    protected float threshold = 0.75f;

    protected static class Entry<K, V> {
        private K key;
        private V val;
        private final int hash;
        private Entry<K, V> pre;
        private Entry<K, V> next;

        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
            hash = key.hashCode();
        }

        public K getKey() {
            return this.key;
        }

        public V getVal() {
            return this.val;
        }

        public int hashcode() {
            return this.hash;
        }

        public Entry<K, V> getPre() {
            return pre;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setPre(Entry<K, V> pre) {
            this.pre = pre;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }

    public AbstractMap(int capacity) {
        this.capacity = capacity;
        this.entries = new Entry[capacity];
    }

    /**
     * 新增键值对
     */
    abstract void put(K k, V v);

    /**
     * 根据key获取value
     */
    abstract V get(K k);

    /**
     * 移除key
     *
     * @param k
     */
    abstract void remove(K k);

    /**
     * 扩容/缩容
     */
    abstract void resize();

    public boolean containsKey(K k) {
        int hashcode = k.hashCode();
        int idx = hashcode % capacity;
        Entry<K, V> e = entries[idx];
        if (e == null) return false;
        while (e != null) {
            if (e.key.equals(k)) {
                return true;
            }
            e = e.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

}
