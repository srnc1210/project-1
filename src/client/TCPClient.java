package client;

import java.io.*;
import java.net.*;

public class TCPClient extends AbstractClient {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    public TCPClient(String serverAddress, int port, int timeout) throws IOException {
        super(serverAddress, port);
        try {
            socket = new Socket(serverAddress, port);
            socket.setSoTimeout(timeout);  // Set the timeout for the socket
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            throw new IOException("Unknown host: " + serverAddress, e);
        } catch (IOException e) {
            throw new IOException("Error connecting to server: " + serverAddress + ":" + port, e);
        }
    }

    @Override
    public void sendRequest(String request) {
        out.println(request);
        ClientLogger.log("Sent: " + request);
    }

    @Override
    public String receiveResponse() throws IOException {
        try {
            String response = in.readLine();
            ClientLogger.log("Received: " + response);
            return response;
        } catch (SocketTimeoutException e) {
            return "ERROR: Server response timeout";
        }
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
