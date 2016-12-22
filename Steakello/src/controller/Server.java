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

public class Server extends Thread {
	//Pour tester les sockets, d'abord lancer le Server puis le Client.

	private static int port = 0;
	private boolean stopServer;
	private GameController controller;
	private InetAddress localAddress;
	private boolean isPortSet;
	private GameCore gameCore;
	private boolean connected;
	PrintWriter out = null;
	
	public Server(GameCore gc) {
		controller = new GameController(gc);
		try {
			this.localAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public InetAddress getLocalAddress(){
		return localAddress;
	}
	
	public void setLocalAddress(InetAddress localAddress){
		this.localAddress = localAddress;
	}
	
	public boolean getIsPortSet() {
		return isPortSet;
	}
	
	public void setIsPortSet(boolean isPortSet){
		this.isPortSet = isPortSet;
	}
	
	public boolean getConnected() {
		return connected;
	}
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void stopServer(){
		this.stopServer=true;
	}
	
	public void run(){
		
		Socket socket = null;
		ServerSocket serverSocket = null;

		BufferedReader in = null;
		
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
		connected = true;
		controller.getGameCore().refreshView();
		controller.getGameCore().setGameStarted(true);
		while(!stopServer){

				String input = null;
				try {
					input = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(input != null){
					System.out.println(input);
					if(controller.getGameCore().getGameBoard().getPlayer() == 2){
						controller.setInput(input);
					}
				}				
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendInput(int inputInt){
		
		out.println(inputInt);

} 
}