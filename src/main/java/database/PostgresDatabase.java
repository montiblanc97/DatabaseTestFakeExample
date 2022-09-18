package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class PostgresDatabase implements Database {
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;

    public PostgresDatabase(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public List<LinkedHashMap<String, Object>> execute(String command) {
        String connectionString = String.format("jdbc:postgresql://%s:%s/%s", this.host, this.port, this.database);
        try (Connection conn = DriverManager.getConnection(connectionString, this.username, this.password)) {
            ResultSet result = conn.prepareStatement(command).executeQuery();
            return transformResultSet(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private List<LinkedHashMap<String, Object>> transformResultSet(ResultSet result) {
        List<LinkedHashMap<String, Object>> out = new ArrayList<>();
        try {
            ResultSetMetaData metadata = result.getMetaData();

            while (result.next()) {
                LinkedHashMap<String, Object> row = new LinkedHashMap<>();
                for (int i=1; i<=metadata.getColumnCount(); i++) {
                    row.put(metadata.getColumnName(i), result.getObject(i));
                }
                out.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return out;
    }
}
