/**
 * @author Zexho
 * @date 8/17/22 10:47 AM
 */
public abstract class AbstractMap<K, V> {

    protected Entry<K, V>[] entries;
    protected int capacity = 16;
    protected static int size = 0;

    protected static class Entry<K, V> {
        private K key;
        private V val;
        private Entry<K, V> pre;
        private Entry<K, V> next;

        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return this.key;
        }

        public V getVal() {
            return this.val;
        }

        public int hashcode() {
            return key.hashCode();
        }

        /**
         * 插入到队尾
         */
        public void addLast(Entry<K, V> entry) {
            // 存在相同key，进行覆盖
            if (this.key.equals(entry.key)) {
                this.val = entry.val;
                return;
            }

            if (next == null) {
                next = entry;
                entry.pre = this;
                size++;
            } else {
                next.addLast(entry);
            }
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
