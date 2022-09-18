import database.Database;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GoofyQueryRunner {
    private final Database database;
    
    public GoofyQueryRunner(Database database) {
        this.database = database;
    }

    /**
     * Execute SQL command, returning weird results. Strings are reversed, integers are incremented.
     */
    public List<LinkedHashMap<String, Object>> execute(String query) {
        List<LinkedHashMap<String, Object>> result = this.database.execute(query);
        
        for (LinkedHashMap<String, Object> row : result) {
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getValue() instanceof String) {
                    row.put(entry.getKey(), reverse((String)entry.getValue()));
                } else if (entry.getValue() instanceof Integer) {
                    row.put(entry.getKey(), ((Integer)entry.getValue()) + 1);
                }
            }
        }
        return result;
    }
    
    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.reverse();
        return sb.toString();
    }
}
