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
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread{
	//private int numPort = 0; num de port à définir
	//Pour tester les sockets, d'abord lancer le Server puis le Client.
	public Socket socket;
	private int port;
	private InetAddress ipAddress;
	private boolean stopClient;
	private Scanner scanner;
	public Client(InetAddress ipAddress, int port) {
		this.ipAddress = ipAddress;
		scanner = new Scanner(System.in);
		this.port = port;
	}
	
	public void sendInput(int input){
		try {
			Socket socket = new Socket(ipAddress, port);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			out.println(input);
			in.close();
			out.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopClient(){
		this.stopClient=true;
	}
	public void run(){
		while(!stopClient){
			int input = scanner.nextInt();
			sendInput(input);
		}
	}
	


}
