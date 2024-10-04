package server;

import java.net.Socket;

public abstract class AbstractHandler implements Runnable {
    protected Socket socket;

    public AbstractHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public abstract void run();
}
