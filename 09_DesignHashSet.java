```java
import java.util.Arrays;

public class DesignHashSet {

    // 1. BRUTE FORCE APPROACH (Direct Boolean Array)
    // Time Complexity: O(1) for add, contains and remove.
    // Space Complexity: O(1,000,001) - Fixed-size array.
    // Strategy: Use the key itself as the array index.

    public static class MyHashSetBrute {

        boolean[] set = new boolean[1000001];

        public void add(int key) {
            set[key] = true;
        }

        public void remove(int key) {
            set[key] = false;
        }

        public boolean contains(int key) {
            return set[key];
        }
    }


    // 2. BETTER APPROACH (Array with Linear Search)
    // Time Complexity: O(N) for add, contains and remove.
    // Space Complexity: O(N) - Stores inserted elements.
    // Strategy: Store elements in an array and search linearly.

    public static class MyHashSetBetter {

        int[] set = new int[10000];
        int size = 0;

        public void add(int key) {

            if (contains(key)) {
                return;
            }

            set[size] = key;
            size++;
        }

        public void remove(int key) {

            for (int i = 0; i < size; i++) {

                if (set[i] == key) {

                    set[i] = set[size - 1];
                    size--;

                    return;
                }
            }
        }

        public boolean contains(int key) {

            for (int i = 0; i < size; i++) {

                if (set[i] == key) {
                    return true;
                }
            }

            return false;
        }
    }


    // 3. OPTIMAL APPROACH (Hashing with Separate Chaining)
    // Average Time Complexity: O(1) for add, contains and remove.
    // Worst Time Complexity: O(N) due to hash collisions.
    // Space Complexity: O(N + B) - B is the number of buckets.
    // Strategy: Use a hash function to distribute keys into buckets.
    // Handle collisions using linked lists.

    public static class MyHashSetOptimal {

        private static final int SIZE = 1000;

        private Node[] buckets = new Node[SIZE];

        private static class Node {

            int key;
            Node next;

            Node(int key) {
                this.key = key;
            }
        }

        private int getIndex(int key) {
            return key % SIZE;
        }

        public void add(int key) {

            int index = getIndex(key);

            Node current = buckets[index];

            while (current != null) {

                if (current.key == key) {
                    return;
                }

                current = current.next;
            }

            Node newNode = new Node(key);

            newNode.next = buckets[index];

            buckets[index] = newNode;
        }

        public void remove(int key) {

            int index = getIndex(key);

            Node current = buckets[index];
            Node previous = null;

            while (current != null) {

                if (current.key == key) {

                    if (previous == null) {
                        buckets[index] = current.next;
                    } else {
                        previous.next = current.next;
                    }

                    return;
                }

                previous = current;
                current = current.next;
            }
        }

        public boolean contains(int key) {

            int index = getIndex(key);

            Node current = buckets[index];

            while (current != null) {

                if (current.key == key) {
                    return true;
                }

                current = current.next;
            }

            return false;
        }
    }


    // Driver Code

    public static void main(String[] args) {

        MyHashSetOptimal set = new MyHashSetOptimal();

        set.add(1);
        set.add(2);

        System.out.println(set.contains(1)); // true
        System.out.println(set.contains(3)); // false

        set.add(2);

        System.out.println(set.contains(2)); // true

        set.remove(2);

        System.out.println(set.contains(2)); // false
    }
}
```
