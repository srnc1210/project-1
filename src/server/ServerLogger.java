package server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerLogger {
    public static Logger logger = Logger.getLogger("ServerLog");

    static {
        try {
            // Storing the logs in the src/logs/server.log
            Files.createDirectories(Paths.get("src/logs")); //Checking if the folder path exists
            //creating the file if it doesn't exit or else appending to the file.
            FileHandler fileHandler = new FileHandler("src/logs/server.log", true);
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
