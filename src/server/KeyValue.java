package server;

import java.util.HashMap;
import java.util.Map;

public class KeyValue {
    private final Map<String, String> map;

    public KeyValue() {
        map = new HashMap<>();
        // Initialize with 5 key-value pairs
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
    }

    public synchronized void put(String key, String value) {
        map.put(key, value);
    }

    public synchronized String get(String key) {
        return map.get(key);
    }

    public synchronized void remove(String key) {
        map.remove(key);
    }

    // New method to expose the map for printing
    public synchronized Map<String, String> getMap() {
        return map;
    }
}
