package fr.ensisa.hassenforder.chatrooms.server;

import java.io.OutputStream;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.server.model.Channel;
import fr.ensisa.hassenforder.chatrooms.server.model.Message;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class MessagesWriter extends BasicAbstractWriter {

	public MessagesWriter(OutputStream outputStream) {
		super (outputStream);
	}
	
	public void writeOK() {
		writeInt(Protocol.OK);
	}

	public void writeKO() {
		writeInt(Protocol.KO);
	}

	public void dispatchIncomingMessages(List<Message> messages) {
		writeInt(Protocol.INCOMMING_MESSAGE);
		writeInt(messages.size());
		System.out.println(messages.size());
		for (Message message : messages) {
			writeString(message.getChannel().getName());
			writeInt(message.getId());
			writeString(message.getAuthor());
			writeString(message.getText());
		}
	}
	
	public void dispatchPendingMessages(List<Message> messages) {
		writeInt(Protocol.PENDING_MESSAGE);
		writeInt(messages.size());
		for (Message message : messages) {
			writeString(message.getChannel().getName());
			writeInt(message.getId());
			writeString(message.getAuthor());
			writeString(message.getText());
		}
	}
}