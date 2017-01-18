package fr.ensisa.hassenforder.chatrooms.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.client.model.Message;
import fr.ensisa.hassenforder.network.BasicAbstractReader;

public class MessagesReader extends BasicAbstractReader {

	private List<Message> messages;
	
	public MessagesReader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		messages = new ArrayList<Message>();
		int length = readInt();
		String channel;
		int messageID;
		String author;
		String text;
		for (int i = 0; i < length; i++) {
			channel = readString();
			messageID = readInt();
			author = readString();
			text = readString();
			messages.add(new Message(channel, messageID, author, text));
		}
	}

	public List<Message> getMessages() {
		return messages;
	}
}