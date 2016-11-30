/**
 * 
 */
package controller;

/**
 * @author jb
 *
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	//Pour tester les sockets, d'abord lancer le Server puis le Client.
	public boolean connected = false;
	
	public void waitForConnection() {
		
		Socket socketOfServer;
		
		try {
		ServerSocket serversocket= new ServerSocket(64899);
		socketOfServer = serversocket.accept();
		System.out.println("Client connected");
		connected = true;
			serversocket.close();
			socketOfServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
