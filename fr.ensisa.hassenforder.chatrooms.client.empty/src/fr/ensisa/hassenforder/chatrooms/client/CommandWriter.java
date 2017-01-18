package fr.ensisa.hassenforder.chatrooms.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.chatrooms.client.model.Channel;
import fr.ensisa.hassenforder.chatrooms.client.model.ChannelType;
import fr.ensisa.hassenforder.chatrooms.client.model.Message;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandWriter extends BasicAbstractWriter {

	public CommandWriter(OutputStream outputStream) {
		super (outputStream);
	}

	public void createConnect(String name) {
		writeInt(Protocol.RQ_CONNECT);
		writeString(name);
		
	}

	public void createDisconnect(String name) {
		writeInt(Protocol.RQ_DISCONNECT);
		writeString(name);
		
	}

	public void createChannelCreation(String name, String channel, ChannelType type) {
		writeInt(Protocol.RQ_CREATEROOM);
		writeString(name);
		writeString(channel);
		writeInt(type.ordinal());
	}

	public void createLoadAllChannel(String name) {
		writeInt(Protocol.RQ_LOADROOMS);
		writeString(name);
		
	}

	public void createChannelSubscriptionChange(String name, Channel description, boolean selected) {
		writeInt(Protocol.RQ_CHANNELSUBSCRIPTIONCHANGE);
		writeString(name);
		writeString(description.getName());
		writeBoolean(selected);
		
	}

	public void createModerationState(String name, Message message, boolean approved) {
		writeInt(Protocol.RQ_MODERATIONSTATE);
		writeString(name);
		writeInt(message.getId());
		writeBoolean(approved);
		
	}

	public void createMessage(String name, String channelName, String text) {
		writeInt(Protocol.RQ_POSTMESSAGE);
		writeString(name);
		writeString(channelName);
		writeString(text);
		
	}
}