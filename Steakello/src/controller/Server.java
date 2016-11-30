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
	
	public static void main(String[] args) {
		
		Socket socketOfServer;
		
		try {
		ServerSocket serversocket= new ServerSocket(64899);
		socketOfServer = serversocket.accept();
		System.out.println("connected");
			serversocket.close();
			socketOfServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
