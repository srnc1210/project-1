package client;

import java.io.*;
import java.net.*;

public class UDPClient extends AbstractClient {
    private final DatagramSocket socket;
    private final InetAddress address;

    public UDPClient(String serverAddress, int port, int timeout) throws IOException {
        super(serverAddress, port);
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(timeout);  // timeout is set to 5secs
            address = InetAddress.getByName(serverAddress);
        } catch (UnknownHostException e) {
            throw new IOException("Unknown host: " + serverAddress, e);
        } catch (SocketException e) {
            throw new IOException("Error creating socket for: " + serverAddress + ":" + port, e);
        }
    }

    @Override
    public void sendRequest(String request) throws IOException {
        byte[] buf = request.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
        ClientLogger.log("Sent: " + request);
    }

    @Override
    public String receiveResponse() throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
            String response = new String(packet.getData(), 0, packet.getLength());
            ClientLogger.log("Received: " + response);
            return response;
        } catch (SocketTimeoutException e) {
            return "ERROR: Server response timeout";
        }
    }

    @Override
    public void close() {
        socket.close();
    }
}
