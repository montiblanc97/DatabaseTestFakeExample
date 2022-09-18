package database;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FakeDatabase implements Database {
    private final Map<String, List<LinkedHashMap<String, Object>>> entries = new HashMap<>();
    public static final String STANDARD = "select my_text, my_int from my_table";
    
    public FakeDatabase() {
        LinkedHashMap<String, Object> row = new LinkedHashMap<>();
        row.put("my_text", "abcdefg");
        row.put("my_int", 5);
        List<LinkedHashMap<String, Object>> value = List.of(row);
        entries.put("select my_text, my_int from my_table", value);
    }
    
    @Override
    public List<LinkedHashMap<String, Object>> execute(String query) {
        if (!entries.containsKey(query.toLowerCase())) {
            throw new RuntimeException("missing entry for test case query: " + query);
        }
        return entries.get(query.toLowerCase());
    }
}
