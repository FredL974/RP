package fr.ensisa.hassenforder.chatrooms.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.server.model.Message;
import fr.ensisa.hassenforder.network.Protocol;

public class MessagesSession {

	private Socket connection;
	private NetworkListener listener;
	
	public MessagesSession(Socket connection, NetworkListener listener) {
		this.connection = connection;
		this.listener = listener;
		if( listener == null) throw new RuntimeException("listener cannot be null");
	}

	public void close () {
		try {
			if (connection != null)
				connection.close();
		} catch (IOException e) {
		}
		connection = null;
	}

	public boolean processConnection () {
		try {
			MessagesWriter writer = new MessagesWriter (connection.getOutputStream());
			MessagesReader reader = new MessagesReader (connection.getInputStream());
			reader.receive ();
			switch (reader.getType ()) {
			case Protocol.RP_CONNECTMESSAGE:
				listener.connectMessagesUser(reader.getName(), this);
				break;
			}
			return true;
		}
		catch (Exception e) {
		}
		return false;
	}

	public boolean dispatchIncomingMessages (List<Message> messages) {
		try {
			MessagesWriter writer = new MessagesWriter (connection.getOutputStream());
			MessagesReader reader = new MessagesReader (connection.getInputStream());
			writer.dispatchIncomingMessages(messages);
			return true;
		}
		catch (Exception e) {
		}
		return false;
	}

	public boolean dispatchPendingMessages (List<Message> messages) {
		try {
			MessagesWriter writer = new MessagesWriter (connection.getOutputStream());
			MessagesReader reader = new MessagesReader (connection.getInputStream());
			writer.dispatchPendingMessages(messages);
			return true;
		}
		catch (Exception e) {
		}
		return false;
	}
}