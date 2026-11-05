```java
public class DesignHashMap {

    // 1. BRUTE FORCE APPROACH (Direct Array)
    // Time Complexity: O(1) for put, get and remove.
    // Space Complexity: O(1,000,001) - Two fixed-size arrays.
    // Strategy: Use the key as the array index and store its value.

    public static class MyHashMapBrute {

        int[] values = new int[1000001];
        boolean[] present = new boolean[1000001];

        public void put(int key, int value) {

            values[key] = value;
            present[key] = true;
        }

        public int get(int key) {

            if (!present[key]) {
                return -1;
            }

            return values[key];
        }

        public void remove(int key) {

            present[key] = false;
        }
    }


    // 2. BETTER APPROACH (Array with Linear Search)
    // Time Complexity: O(N) for put, get and remove.
    // Space Complexity: O(N) - Stores key-value pairs.
    // Strategy: Store pairs in arrays and search for the key.

    public static class MyHashMapBetter {

        int[] keys = new int[10000];
        int[] values = new int[10000];

        int size = 0;

        public void put(int key, int value) {

            for (int i = 0; i < size; i++) {

                if (keys[i] == key) {

                    values[i] = value;
                    return;
                }
            }

            keys[size] = key;
            values[size] = value;

            size++;
        }

        public int get(int key) {

            for (int i = 0; i < size; i++) {

                if (keys[i] == key) {
                    return values[i];
                }
            }

            return -1;
        }

        public void remove(int key) {

            for (int i = 0; i < size; i++) {

                if (keys[i] == key) {

                    keys[i] = keys[size - 1];
                    values[i] = values[size - 1];

                    size--;

                    return;
                }
            }
        }
    }


    // 3. OPTIMAL APPROACH (Hashing with Separate Chaining)
    // Average Time Complexity: O(1) for put, get and remove.
    // Worst Time Complexity: O(N) due to hash collisions.
    // Space Complexity: O(N + B) - B is the number of buckets.
    // Strategy: Use a hash function to distribute keys into buckets.
    // Handle collisions using linked lists.

    public static class MyHashMapOptimal {

        private static final int SIZE = 1000;

        private Node[] buckets = new Node[SIZE];

        private static class Node {

            int key;
            int value;

            Node next;

            Node(int key, int value) {

                this.key = key;
                this.value = value;
            }
        }

        private int getIndex(int key) {
            return key % SIZE;
        }

        public void put(int key, int value) {

            int index = getIndex(key);

            Node current = buckets[index];

            // Check whether the key already exists.
            while (current != null) {

                if (current.key == key) {

                    // Update existing value.
                    current.value = value;

                    return;
                }

                current = current.next;
            }

            // Key does not exist. Insert a new node.
            Node newNode = new Node(key, value);

            newNode.next = buckets[index];

            buckets[index] = newNode;
        }

        public int get(int key) {

            int index = getIndex(key);

            Node current = buckets[index];

            while (current != null) {

                if (current.key == key) {
                    return current.value;
                }

                current = current.next;
            }

            return -1;
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
    }


    // Driver Code

    public static void main(String[] args) {

        MyHashMapOptimal map = new MyHashMapOptimal();

        map.put(1, 1);
        map.put(2, 2);

        System.out.println(map.get(1)); // 1
        System.out.println(map.get(3)); // -1

        map.put(2, 1);

        System.out.println(map.get(2)); // 1

        map.remove(2);

        System.out.println(map.get(2)); // -1
    }
}
```
