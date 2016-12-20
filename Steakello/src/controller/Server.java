/**
 * 
 */
package controller;
/**
 * @author jb
 *
 */
import model.GameCore;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Server extends Thread {
	//Pour tester les sockets, d'abord lancer le Server puis le Client.

	private static int port = 0;
	private boolean stopServer;
	private GameController controller;
	public InetAddress localAddress;
	public boolean isPortSet;
	private GameCore gameCore;
	public boolean connected;
	
	

	public Server(GameController controller) {
		this.controller = controller;
	
	}
	
	public Server() {
		try {
			this.localAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	/*public void setPort(int port) {
		this.port = port;
	}*/

	public void stopServer(){
		this.stopServer=true;
	}
	
	public void run(){
		
		Socket socket = null;
		ServerSocket serverSocket = null;

		BufferedReader in = null;
		PrintWriter out = null;
		try {
			serverSocket = new ServerSocket(port, 2);
			port = serverSocket.getLocalPort();
			isPortSet = true;
			socket = serverSocket.accept();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (IOException e) {
			e.printStackTrace();
		};
		while(!stopServer){

				String input = null;
				try {
					input = in.readLine();
					controller.setInput(input);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(input != null){
					System.out.println(input);
				}				
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}