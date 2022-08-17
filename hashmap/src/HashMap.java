/**
 * @author Zexho
 * @date 8/17/22 10:45 AM
 */
public class HashMap<K, V> extends AbstractMap<K, V> {

    public HashMap(int capacity) {
        super(capacity);
    }

    @Override
    public void put(K k, V v) {
        Entry<K, V> entry = new Entry<>(k, v);
        int hashcode = entry.hashcode();
        int index = hashcode % capacity;
        if (entries[index] == null) {
            // 如果slot为空，直接保存
            entries[index] = entry;
            size++;
        } else {
            // 否则追加到队尾
            entries[index].addLast(entry);
        }
    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public void remove(K k) {
    }

    @Override
    void resize() {
    }

}
