/**
 * 
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
/**
 * @author jb
 *
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	//Pour tester les sockets, d'abord lancer le Server puis le Client.

	public int port = 0;
	private boolean stopServer;
	private GameController controller;
	
	public Server(GameController controller) {
		this.controller = controller;

	}

	public void stopServer(){
		this.stopServer=true;
	}
	
	public void run(){
		Socket socket;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			this.port = serverSocket.getLocalPort();
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
				controller.setInput(Integer.parseInt(input));
				
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
