package com.arcxesgames.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable{
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	@SuppressWarnings("unused")
	private int id;
	
	public Connection(Socket socket,int id) {
		this.socket = socket;
		this.id = id;
		
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while(socket.isConnected()) {
				try {
					@SuppressWarnings("unused")
					Object data = in.readObject();
					//new HandleData(data, id);
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendObject(Object packet) {
		try {
			out.writeObject(packet);
			out.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
