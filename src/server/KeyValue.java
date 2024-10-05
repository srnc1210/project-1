package server;

import java.util.HashMap;
import java.util.Map;

public class KeyValue {
    private final Map<Integer, String> map;

    public KeyValue() {
        map = new HashMap<>();
        // Initialize with 5 key-value pairs
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
    }

    public synchronized void put(int key, String value) {
        map.put(key, value);
    }

    public synchronized String get(int key) {
        return map.get(key);
    }

    public synchronized void remove(int key) {
        map.remove(key);
    }

    // New method to expose the map for printing
    public synchronized Map<Integer, String> getMap() {
        return map;
    }
}
