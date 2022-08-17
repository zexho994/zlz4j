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

}