package client;

import java.io.IOException;

public class Client {
    private final AbstractClient client;

    public Client(AbstractClient client) {
        this.client = client;
    }

    public void sendRequest(String request) throws IOException {
        client.sendRequest(request);
    }

    public String receiveResponse() throws IOException {
        return client.receiveResponse();
    }
}
