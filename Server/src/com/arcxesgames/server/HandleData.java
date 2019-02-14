package com.arcxesgames.server;

import com.arcxesgames.packets.*;

public class HandleData {
	public HandleData(Object data, int id) {
		System.out.println("Class: "+data.getClass()+" from "+ id +" recived." );
		if(data instanceof AddPlayerPacket) {
			AddPlayerPacket player = (AddPlayerPacket)data;
			System.out.println("Player add packet recived. Name: "+player.name+" Id: "+player.id);
		}else if(data instanceof RemovePlayerPacket) {
			@SuppressWarnings("unused")
			RemovePlayerPacket input = (RemovePlayerPacket)data;
			//new RemovePlayer(input,id);
		}
	}
}
