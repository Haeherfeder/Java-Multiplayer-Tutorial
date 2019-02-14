package com.arcxesgames.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	
	@SuppressWarnings("unused")
	private int port;
	private ServerSocket serverSocket;
	private boolean running = false;
	private int id = 0;
	
	public Server(int port) {
		this.port = port;
		
		try {
			serverSocket = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		running = true;
		
		while(running) {
			try {
				Socket socket = serverSocket.accept();
				initSocket(socket);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		shutdown();
	}
	
	private void initSocket(Socket socket) {
		Connection connection = new Connection(socket,id);
		new Thread(connection).start();
		id++;
	}
	
	public void shutdown() {
		running = false;
		
		try {
			serverSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}