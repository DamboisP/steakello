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
	//private int numPort = 2869; num de port à définir
	Socket socket;
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), 64899);
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
