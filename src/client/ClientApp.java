package client;

import java.io.*;
// import java.net.SocketException;

public class ClientApp {
    private static final int TIMEOUT_MS = 5000;  // Set timeout to 5 seconds

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java client.ClientApp <server_address> <port> <protocol>");
            return;
        }

        String serverAddress = args[0];
        int port = Integer.parseInt(args[1]);
        String protocol = args[2].toLowerCase();

        AbstractClient client;
        try {
            if (protocol.equals("tcp")) {
                client = new TCPClient(serverAddress, port, TIMEOUT_MS);
            } else if (protocol.equals("udp")) {
                client = new UDPClient(serverAddress, port, TIMEOUT_MS);
            } else {
                System.out.println("Invalid protocol. Use 'tcp' or 'udp'.");
                return;
            }

            Client app = new Client(client);
            try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
                String command;
                while ((command = console.readLine()) != null) {
                    app.sendRequest(command);
                    String response = app.receiveResponse();
                    if (response == null) {
                        System.out.println("Server did not respond. Please try again.");
                    } else {
                        System.out.println("Server response: " + response);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                client.close();
            }
        } catch (IOException e) {
            System.err.println("Error initializing client: " + e.getMessage());
        }
    }
}
