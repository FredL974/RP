package fr.ensisa.hassenforder.chatrooms.server;

import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class MessagesReader extends BasicAbstractReader {

	private String name;
	
	public MessagesReader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt();
		switch (type) {
		case Protocol.RP_CONNECTMESSAGE:
			this.name = readString();
			break;
		}
	}

	public String getName() {
		return this.name;
	}
}
