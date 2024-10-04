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
> Note: Replace <port> with desired port number (between 0 and 65535) (in the example I have used port: 12345 for tcp and 54321 for udp) and <protocol> with either tcp or udp.

### Running the client
1. Navigate to the ```src/client``` directory.
2. Compile the code using: ```javac *.java```
3. Run the server using: ```java ClientApp <hostname> <port> <protocol>```
> Note: Replace <hostname> with server's address (in this example I have used localhost), <port> with desired port number (this will be the same port used for server) and <protocol> with either tcp or udp (same as used for server).

### Communicating with the server
> Here key and value are String variables even if 1 or 2+4 is inputted, the code will treat it as a String.
1. For the Put command use: ```put <key> <value>```
2. For the Get command use: ```get <key>```
3. For the Remove command use: ```remove <key>```
4. For Printing the map use: ```print```
