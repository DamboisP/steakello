/**
 * 
 */
package controller;

/**
 * @author jb
 *
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	//private int numPort = 0; num de port à définir
	//Pour tester les sockets, d'abord lancer le Server puis le Client.
	public Socket socket;
	
	public void connnectToServer(InetAddress ipAddress) {
		try {
			Socket socket = new Socket(ipAddress, 64899);
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
