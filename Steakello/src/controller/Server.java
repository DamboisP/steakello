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

	private boolean stopServer;
	private GameController controller;
	public InetAddress localAddress;
	public boolean isPortSet;
	private GameCore gameCore;
	

	public Server(GameController controller) {
		this.controller = controller;
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
		try {
			serverSocket = new ServerSocket(port, 2);
			this.port = serverSocket.getLocalPort();
			isPortSet = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		while(!stopServer){
			try {
				socket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(
						new BufferedWriter(
								new OutputStreamWriter(socket.getOutputStream())), true);
				String input = in.readLine();
				if (input == "1" || input == "2") {
					controller.setInput(Integer.parseInt(input));
				} else {
					try {
					    // Convert from an IPv4 address to an integer
					    InetAddress ip = InetAddress.getByName(input);
					    int value = ByteBuffer.wrap(ip.getAddress()).getInt();
					    System.out.println("TOAST");
					    controller.setInput(value);
					} catch (Exception e) {
					    e.printStackTrace();
					}
				}		
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}