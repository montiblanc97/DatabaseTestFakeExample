import database.Database;
import database.FakeDatabase;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoofyQueryRunnerTest {
    
    @Test
    public void testStandard() {
        Database database = new FakeDatabase();
        GoofyQueryRunner runner = new GoofyQueryRunner(database);

        List<LinkedHashMap<String, Object>> result = runner.execute(FakeDatabase.STANDARD);
        assertEquals(1, result.size(), "expected one row");
        LinkedHashMap<String, Object> row = result.get(0);
        assertEquals("gfedcba", row.get("my_text"), "expected text to be reversed");
        assertEquals(6, row.get("my_int"), "expected int to be incremented by 1");
    }
}
