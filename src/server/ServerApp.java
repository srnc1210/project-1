package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerApp {
    private static final boolean running = true;  // Make sure the server runs indefinitely
    private static ServerSocket tcpServerSocket;
    private static DatagramSocket udpSocket;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java server.ServerApp <port> <protocol>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        String protocol = args[1].toLowerCase();

        KeyValue keyValue = new KeyValue();

        if (protocol.equals("tcp")) {
            startTCPServer(port, keyValue);
        } else if (protocol.equals("udp")) {
            startUDPServer(port, keyValue);
        } else {
            System.out.println("Invalid protocol. Use 'tcp' or 'udp'.");
        }
    }

    private static void startTCPServer(int port, KeyValue keyValue) {
        try {
            tcpServerSocket = new ServerSocket(port);
            System.out.println("TCP Server is running on port " + port);
            while (running) {
                try {
                    new Thread(new TCPHandler(tcpServerSocket.accept(), keyValue)).start();
                } catch (IOException e) {
                    if (running) {
                        e.printStackTrace();
                    } else {
                        System.out.println("Server socket closed.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (tcpServerSocket != null && !tcpServerSocket.isClosed()) {
                try {
                    tcpServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("TCP Server stopped.");
        }
    }

    private static void startUDPServer(int port, KeyValue keyValue) {
        try {
            udpSocket = new DatagramSocket(port);
            System.out.println("UDP Server is running on port " + port);
            while (running) {
                new UDPHandler(udpSocket, keyValue).run(); // Start handler directly
            }
        } catch (SocketException e) {
            if (running) {
                e.printStackTrace();
            } else {
                System.out.println("UDP Server socket closed gracefully.");
            }
        } finally {
            if (udpSocket != null && !udpSocket.isClosed()) {
                udpSocket.close();
            }
            System.out.println("UDP Server stopped.");
        }
    }
}
