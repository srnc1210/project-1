package server;

import java.io.*;
import java.net.*;

public class TCPHandler extends Thread {
    private final Socket socket;
    private final KeyValue keyValueStore;

    public TCPHandler(Socket socket, KeyValue keyValueStore) {
        this.socket = socket;
        this.keyValueStore = keyValueStore;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] parts = inputLine.split(" ");
                String command = parts[0].toLowerCase();

                switch (command) {
                    case "put":
                        if (parts.length == 3) {
                            keyValueStore.put(Integer.parseInt(parts[1]), parts[2]);
                            out.println("OK");
                        } else {
                            out.println("Invalid format. Use: put <key> <value>");
                        }
                        break;
                    case "get":
                        if (parts.length == 2) {
                            String value = keyValueStore.get(Integer.parseInt(parts[1]));
                            out.println(value != null ? value : "ERROR: Key not found in map");
                        } else {
                            out.println("Invalid format. Use: get <key> <value>");
                        }
                        break;
                    case "remove":
                        if (parts.length == 2) {
                            if (keyValueStore.get(Integer.parseInt(parts[1])) != null) {
                                keyValueStore.remove(Integer.parseInt(parts[1]));
                                out.println("Removed: " + parts[1]);
                            } else {
                                out.println("ERROR: Key not found in map");
                            }
                        } else {
                            out.println("Invalid format. Use: remove <key>");
                        }
                        break;
                    case "print":
                        out.println(keyValueStore.getMap().toString());
                        break;
                    case "exit":
                        out.println("Disconnecting from server.....");
                        break;
                    default:
                        out.println("Unknown command. Use: put <key> <value>, get <key> <value> or remove <key>");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
