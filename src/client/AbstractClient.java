package client;

import java.io.IOException;

public abstract class AbstractClient {
    protected String serverAddress;
    protected int port;

    public AbstractClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public abstract void sendRequest(String request) throws IOException;
    public abstract String receiveResponse() throws IOException;
    public abstract void close() throws IOException;
}
