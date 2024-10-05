# Project-1
## Summary
In this project there is an implementation of a simple client server program that will server as a key-value store (implementing it with HashMap).

The map initializes five values in the map when we connect with the server. There are 4 functions available for accessing the HashMap:
1. Put (key, value)
2. Get (key)
3. Remove (key)
4. Print
## Folder structure
```bash
src
|- client
|    |- AbstractClient.java
|    |- Client.java
|    |- ClientApp.java
|    |- ClientLogger.java
|    |- TCPClient.java
|    |- UDPClient.java
|- server
|    |- AbstractHandler.java
|    |- KeyValue.java
|    |- ServerApp.java
|    |- ServerLogger.java
|    |- TCPHandler.java
|    |- UDPHandler.java
|- logs
     |- client.log
     |- server.log
     
3 directories, 14 files
```
## Steps to run this project
### Pre-requisites
* Java Development Kit should be installed.

### Running the server
1. Navigate to the ```src/server``` directory.
2. Compile the code using: ```javac *.java```
3. Run the server using: ```java ServerApp <port> <protocol>```
> Note: Replace <port> with desired port number (between 0 and 65535) (in the example I have used port: 12345 for tcp and 54321 for udp) and <protocol> with either tcp or udp.

### Running the client
1. Navigate to the ```src/client``` directory.
2. Compile the code using: ```javac *.java```
3. Run the server using: ```java ClientApp <hostname> <port> <protocol>```
> Note: Replace <hostname> with server's address (in this example I have used localhost), <port> with desired port number (this will be the same port used for server) and <protocol> with either tcp or udp (same as used for server).

### Communicating with the server
> Here key is integer variable and value is String variable. "{1=A, 2=B, 3=C, 4=D, 5=E}", is the initialized map.
1. For **putting** a key-value pair in the store: ```put <key> <value>```
2. For **getting** the value based on the input key: ```get <key>```
3. For **removing** key-value pair: ```remove <key>```
4. For **printing** the key-value store use: ```print```
5. For **disconnecting** the client from the server: ```exit```
