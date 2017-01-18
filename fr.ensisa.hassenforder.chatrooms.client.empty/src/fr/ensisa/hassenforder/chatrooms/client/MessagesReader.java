package fr.ensisa.hassenforder.chatrooms.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.client.model.Message;
import fr.ensisa.hassenforder.network.BasicAbstractReader;

public class MessagesReader extends BasicAbstractReader {

	public MessagesReader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0: break;
		}
	}

	public List<Message> getMessages() {
		List<Message> messages = new ArrayList<Message>();
		int length = readInt();
		for (int i = 1; i < length; i++) {
			messages.add(new Message(readString(), readInt(), readString(), readString()));
		}
		return messages;
	}
}