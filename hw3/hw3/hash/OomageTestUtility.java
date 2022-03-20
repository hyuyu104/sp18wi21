package hw3.hash;

import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        HashMap<Integer, Integer> bucketCount = new HashMap<>();
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if (bucketCount.containsKey(bucketNum)) {
                bucketCount.put(bucketNum, bucketCount.get(bucketNum) + 1);
            } else {
                bucketCount.put(bucketNum, 1);
            }
        }
        for (int key : bucketCount.keySet()) {
            int count = bucketCount.get(key);
            if (count < oomages.size() / 50 || count > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
