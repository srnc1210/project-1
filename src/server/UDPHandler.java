package server;

import java.io.*;
import java.net.*;

public class UDPHandler implements Runnable {
    private final DatagramSocket socket;
    private final KeyValue keyValueStore;

    public UDPHandler(DatagramSocket socket, KeyValue keyValueStore) {
        this.socket = socket;
        this.keyValueStore = keyValueStore;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        try {
            while (true) {  // Loop indefinitely
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String command = new String(packet.getData(), 0, packet.getLength());
                ServerLogger.log("Received: " + command);
                String[] parts = command.split(" ");
                String action = parts[0];
                String response;

                switch (action) {
                    case "put":
                        if (parts.length == 3) {
                            keyValueStore.put(Integer.parseInt(parts[1]), parts[2]);
                            response = "Put: (" + parts[1] + ", " + parts[2] + ")";
                        } else {
                            response = "Invalid put command format. Use: put <key> <value>";
                        }
                        break;
                    case "get":
                        if (parts.length == 2) {
                            String value = keyValueStore.get(Integer.parseInt(parts[1]));
                            response = "Get: " + (value != null ? value : "Key not found");
                        } else {
                            response = "Invalid get command format. Use: get <key>";
                        }
                        break;
                    case "remove":
                        if (parts.length == 2) {
                            if (keyValueStore.get(Integer.parseInt(parts[1])) != null) {
                                keyValueStore.remove(Integer.parseInt(parts[1]));
                                response = "Removed: " + parts[1];
                            } else {
                                response = "ERROR: Key not found";
                            }
                        } else {
                            response = "Invalid remove command format. Use: remove <key>";
                        }
                        break;
                    case "print":
                        response = keyValueStore.getMap().toString();
                        break;
                    case "exit":
                        response = "Received exit command. Server continues to run.";
                        break;
                    default:
                        response = "Unknown command";
                }

                byte[] responseBuffer = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
                ServerLogger.log("Processed command: " + command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
