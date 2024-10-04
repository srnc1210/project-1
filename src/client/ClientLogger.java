package client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ClientLogger {
    private final static Logger logger = Logger.getLogger("ClientLog");

    static {
        try {
            // Ensure the logs directory exists
            Files.createDirectories(Paths.get("logs"));

            // Use "logs/client.log" as the log file path
            FileHandler fileHandler = new FileHandler("logs/client.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        logger.info(message);
    }
}
