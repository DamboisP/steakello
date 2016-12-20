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

public class Client extends Thread{
	public Socket socket;
	public int port = 0;
	private InetAddress ipAddress = null;
	private boolean stopClient;
	public boolean ready;
	public boolean connected;

	
	
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
		System.out.println("Port et adresse:"+ ipAddress +" " + port);
		try {
			socket = new Socket(ipAddress, port);
			
			sendInput("Hello server :)");
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public void sendInput(String input){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			out.println(input);
			in.close();
			out.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	public void stopClient(){
		this.stopClient=true;
	}

}
