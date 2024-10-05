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
                            out.println("ERROR: Invalid PUT command");
                        }
                        break;
                    case "get":
                        if (parts.length == 2) {
                            String value = keyValueStore.get(Integer.parseInt(parts[1]));
                            out.println(value != null ? value : "ERROR: Key not found");
                        } else {
                            out.println("ERROR: Invalid GET command");
                        }
                        break;
                    case "remove":
                        if (parts.length == 2) {
                            if (keyValueStore.get(Integer.parseInt(parts[1])) != null) {
                                keyValueStore.remove(Integer.parseInt(parts[1]));
                                out.println("Removed: " + parts[1]);
                            } else {
                                out.println("ERROR: Key not found");
                            }
                        } else {
                            out.println("ERROR: Invalid REMOVE command");
                        }
                        break;
                    case "print":
                        out.println(keyValueStore.getMap().toString());
                        break;
                    case "exit":
                        out.println("Received exit command. Server continues to run.");
                        break;
                    default:
                        out.println("ERROR: Unknown command");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
