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

        // 是否需要扩容
        if (size >= capacity * threshold) {
            resize();
        }
    }

    @Override
    public V get(K k) {
        int hashcode = k.hashCode();
        int index = hashcode % capacity;
        Entry<K, V> e = entries[index];
        while (e != null) {
            if (e.getKey().equals(k)) {
                return e.getVal();
            }
            e = e.getNext();
        }
        return null;
    }

    @Override
    public void remove(K k) {
        int hashcode = k.hashCode();
        int index = hashcode % capacity;
        Entry<K, V> e = entries[index];
        while (e != null) {
            if (e.getKey().equals(k)) {
                Entry<K, V> pre = e.getPre();
                Entry<K, V> next = e.getNext();
                if (pre == null) {
                    // 位于链表的第一个
                    entries[index] = e.getNext();
                } else if (next == null) {
                    // 位于链表的最后一个
                    pre.setNext(null);
                } else {
                    // 位于链表的中间位置
                    pre.setNext(next);
                    next.setPre(pre);
                }
                // 将e的pre,next指针设为null
                e.setPre(null);
                e.setNext(null);
                size--;
                return;
            }
            e = e.getNext();
        }
    }


    @Override
    void resize() {
        if (capacity == Integer.MAX_VALUE) return;
        int newCapacity = capacity > Integer.MAX_VALUE / 2 ? Integer.MAX_VALUE : capacity * 2;
        Entry<K, V>[] newEntries = new Entry[newCapacity];

        Entry<K, V> e;
        for (int i = 0; i < capacity; i++) {
            while (entries[i] != null) {
                e = entries[i];
                entries[i] = e.getNext();
                if (entries[i] != null) entries[i].setPre(null);
                int newIdx = e.getKey().hashCode() % newCapacity;
                Entry<K, V> o = newEntries[newIdx];
                newEntries[newIdx] = e;
                e.setNext(o);
                if (o != null) o.setPre(e);
            }
        }

        capacity = newCapacity;
        entries = newEntries;
    }

}
