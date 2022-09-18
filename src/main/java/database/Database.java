package database;

import java.util.LinkedHashMap;
import java.util.List;

public interface Database {
    List<LinkedHashMap<String, Object>> execute(String command);
}
