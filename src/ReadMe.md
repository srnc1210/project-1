# Project-1
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
> Note: Replace <port> with desired port number (between 0 to 65535) (in the example I have used port: 12345 for tcp and 54321 for udp) and <protocol> with either tcp or udp.

### Running the client
1. Navigate to the ```src/client``` directory.
2. Compile the code using: ```javac *.java```
3. Run the server using: ```java ClientApp <hostname> <port> <protocol>```
> Note: Replace <hostname> with server's address (in this example I have used localhost), <port> with desired port number (this will be the same port used for server) and <protocol> with either tcp or udp (same as used for server).