import database.PostgresDatabase;
import org.apache.commons.cli.*;

import java.util.LinkedHashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option hostOption = new Option("h", "host", true, "host");
        hostOption.setRequired(true);
        options.addOption(hostOption);

        Option portOption = new Option("p", "port", true, "port");
        portOption.setRequired(true);
        options.addOption(portOption);

        Option databaseOption = new Option("db", "database", true, "database");
        databaseOption.setRequired(true);
        options.addOption(databaseOption);

        Option usernameOption = new Option("u", "username", true, "username");
        usernameOption.setRequired(true);
        options.addOption(usernameOption);

        Option passwordOption = new Option("pw", "password", true, "password");
        passwordOption.setRequired(true);
        options.addOption(passwordOption);

        Option queryOption = new Option("q", "query", true, "SQL query");
        queryOption.setRequired(true);
        options.addOption(queryOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
        
        String hostVal = cmd.getOptionValue("h");
        String portVal = cmd.getOptionValue("p");
        String databaseVal = cmd.getOptionValue("db");
        String userVal = cmd.getOptionValue("u");
        String passwordVal = cmd.getOptionValue("pw");
        String queryVal = cmd.getOptionValue("q");

        PostgresDatabase database = new PostgresDatabase(hostVal, portVal, databaseVal, userVal, passwordVal);
        List<LinkedHashMap<String, Object>> result = new GoofyQueryRunner(database).execute(queryVal);
        result.forEach(System.out::println);
    }
}
