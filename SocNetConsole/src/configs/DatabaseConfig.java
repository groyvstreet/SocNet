package configs;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConfig {
    private static final Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(DatabaseConfig.class.getName());

    private DatabaseConfig() {}

    static {
        try (var inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (inputStream == null) {
                logger.info("db.properties not found");
                System.exit(1);
            }

            properties.load(inputStream);
        } catch (IOException exception) {
            logger.info(exception.getMessage());
        }
    }

    public static String getDatabaseUrl() {

        return properties.getProperty("db.url");
    }

    public static String getDatabaseUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDatabasePassword() {
        return properties.getProperty("db.password");
    }
}
