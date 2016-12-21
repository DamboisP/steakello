/**
 * 
 */
package controller;
/**
 * @author jb
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.GameCore;

public class Client extends Thread{
	public Socket socket;
	public int port = 0;
	private InetAddress ipAddress = null;
	private boolean stopClient;
	public boolean ready;
	public boolean connected;
	private GameController controller;
	PrintWriter out;
	
	public Client(GameCore gc){
		controller = new GameController(gc);
	}
	
	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String host) {
		try {
			this.ipAddress = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void run(){
		while(!this.ready){
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			socket = new Socket(ipAddress, port);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			String input;
			connected = true;
			controller.getGameCore().refreshView();
			controller.getGameCore().gameStarted = true;
			while(!stopClient){
				input = in.readLine();
				if(input != null){
					if(controller.getGameCore().getGameBoard().getPlayer() == 1){
						controller.setInput(input);
					}
				}
				try {
					sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	

	public void sendInput(int inputInt){
			
			out.println(inputInt);

	} 
	public void stopClient(){
		this.stopClient=true;
	}

}
