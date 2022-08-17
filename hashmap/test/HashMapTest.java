import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashMapTest {

    @Test
    public void TestPut() {
        HashMap<Integer, String> hashMap = new HashMap<>(8);
        hashMap.put(1, "1");
        Assertions.assertEquals(1, hashMap.size());

        hashMap.put(9, "9");
        Assertions.assertEquals(2, hashMap.size());
        Assertions.assertTrue(hashMap.containsKey(1));
        Assertions.assertTrue(hashMap.containsKey(9));

        hashMap.put(9, "10");
        Assertions.assertEquals(2, hashMap.size());
        Assertions.assertTrue(hashMap.containsKey(9));
    }

    @Test
    public void TestGet() {
        // put的数据应该查询到
        HashMap<Integer, String> hashMap = new HashMap<>(8);
        hashMap.put(1, "1");
        Assertions.assertEquals(hashMap.get(1), "1");
        hashMap.put(2, "2");
        Assertions.assertEquals(hashMap.get(2), "2");

        // 同一索引下的，检查链址法的准确性
        hashMap.put(9, "9");
        Assertions.assertEquals(hashMap.get(9), "9");
        hashMap.put(9, "10");
        Assertions.assertEquals(hashMap.get(9), "10");

        // 没有put的数据应该为null
        Assertions.assertNull(hashMap.get(10));
        Assertions.assertNull(hashMap.get(17));
    }


    @Test
    public void TestRemove() {
        HashMap<Integer, Integer> hashMap = new HashMap<>(8);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(9, 9);
        hashMap.put(17, 17);
        Assertions.assertEquals(hashMap.size(), 4);
        Assertions.assertEquals(hashMap.get(9), 9);

        hashMap.remove(9);
        Assertions.assertNull(hashMap.get(9));
        Assertions.assertEquals(hashMap.size(), 3);

        hashMap.remove(3);
        hashMap.remove(9);
        Assertions.assertNull(hashMap.get(9));
        Assertions.assertEquals(hashMap.size(), 3);
    }

    @Test
    void resize() {
        HashMap<Integer, Integer> hashMap = new HashMap<>(4);
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(5, 5);
        Assertions.assertEquals(hashMap.size(), 3);
        Assertions.assertEquals(hashMap.capacity, 8);
        Assertions.assertNotNull(hashMap.get(1));
        Assertions.assertNotNull(hashMap.get(2));
        Assertions.assertNotNull(hashMap.get(5));
        Assertions.assertNull(hashMap.get(4));

        hashMap.put(4, 4);
        hashMap.put(10, 10);
        Assertions.assertEquals(hashMap.size(), 5);
        Assertions.assertEquals(hashMap.capacity, 8);

        hashMap.put(20, 20);
        Assertions.assertNotNull(hashMap.get(4));
        Assertions.assertNotNull(hashMap.get(10));
        Assertions.assertNotNull(hashMap.get(20));
        Assertions.assertEquals(hashMap.size(), 6);
        Assertions.assertEquals(hashMap.capacity, 16);
    }
}