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

public class Server {
	//Pour tester les sockets, d'abord lancer le Server puis le Client.
	public boolean connected = false;
	private int port = 64899;
	public Server() {
		
		Socket socket;
		ServerSocket serverSocket;
		try {
		serverSocket = new ServerSocket(port);
		socket = serverSocket.accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		connected = true;
		
		while(true){
			String input = in.readLine();
			if(input.equals("END"))
				break;
			System.out.println(input);
		}

		in.close();
		out.close();
		socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
