package fr.ensisa.hassenforder.chatrooms.server;

import java.io.IOException;
import java.net.Socket;
import java.rmi.server.Operation;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.server.model.Channel;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandSession extends Thread {

	private Socket connection;
	private NetworkListener listener;
	private String userName;
	
	public CommandSession(Socket connection, NetworkListener listener) {
		this.connection = connection;
		this.listener = listener;
		if( listener == null) throw new RuntimeException("listener cannot be null");
	}

	public void close () {
		this.interrupt();
		try {
			if (connection != null)
				connection.close();
		} catch (IOException e) {
		}
		connection = null;
	}

	public boolean operate() {
		try {
			CommandWriter writer = new CommandWriter (connection.getOutputStream());
			CommandReader reader = new CommandReader (connection.getInputStream());
			reader.receive ();
			switch (reader.getType ()) {
			case Protocol.RQ_CONNECT:
				if (listener.connectCommandUser(reader.getName(), this) == OperationStatus.NOW_CONNECTED)
					writer.writeOK();
				else writer.writeKO();
				break;
			case Protocol.RQ_DISCONNECT:
				if (listener.disconnectUser(reader.getName()) == OperationStatus.NOW_DISCONNECTED)
					writer.writeOK();
				else writer.writeKO();
				break;
			case Protocol.RQ_CREATEROOM:
				if (listener.createChannel(reader.getName(), reader.getChannel(), reader.getChannelType()) == OperationStatus.CHANNEL_CREATED){
					writer.writeOK();
				}				
				else writer.writeKO();
				break;
			case Protocol.RQ_LOADROOMS:
				userName = reader.getName(); 
				writer.loadChannel(userName, listener.loadChannels(userName));
				break;
			case Protocol.RQ_CHANNELSUBSCRIPTIONCHANGE:
				if (listener.ChangeChannelSubscription(reader.getName(), reader.getChannel(), reader.getSubscription()) == OperationStatus.SUBSCRIPTION_CHANGED)
					writer.writeOK();
				else writer.writeKO();
				break;
			case Protocol.RQ_SETAPPROBATION:
				if (listener.SetApprobation(reader.getName(), reader.getMessageId(), reader.getApproved()) == OperationStatus.APPROBATION_CHANGED)
					writer.writeOK();
				else writer.writeKO();
				break;
			case Protocol.RQ_POSTMESSAGE:
				if (listener.sendMessage(reader.getName(), reader.getChannel(), reader.getText()) == OperationStatus.MESSAGE_SENT)
					writer.writeOK();
				else writer.writeKO();
				break;
			case 0 : return false; // socket closed
			case -1 : break;
			default: return false; // connection jammed
			}
			writer.send ();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public void run() {
		while (true) {
			if (! operate())
				break;
		}
		try {
			if (connection != null) connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}