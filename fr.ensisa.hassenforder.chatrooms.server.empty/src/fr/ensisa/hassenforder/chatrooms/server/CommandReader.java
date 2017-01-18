package fr.ensisa.hassenforder.chatrooms.server;

import java.io.InputStream;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.server.model.Channel;
import fr.ensisa.hassenforder.chatrooms.server.model.ChannelType;
import fr.ensisa.hassenforder.chatrooms.server.model.Message;
import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandReader extends BasicAbstractReader {

	private String userName, roomName, moderatorName, text;
	private int messageId, channelTypeNumber;
	private ChannelType channelType;
	private boolean subscription, approved;

	public CommandReader(InputStream inputStream) {
		super(inputStream);
	}

	public void receive() {
		type = readInt();
		switch (type) {
		case Protocol.RQ_CONNECT:
			this.userName = readString();
			break;
		case Protocol.RQ_DISCONNECT:
			this.userName = readString();
			break;
		case Protocol.RQ_CREATEROOM:
			this.userName = readString();
			this.roomName = readString();
			this.channelTypeNumber = readInt();
			// TODO : 0 pour FREE, 1 pour MODERATED, 2 pour MODERATOR
			switch (this.channelTypeNumber) {
			case 0:
				this.channelType = ChannelType.FREE;
				this.moderatorName = null;
				break;
			case 1:
				this.channelType = ChannelType.MODERATED;
				this.moderatorName = this.userName;
				break;
			}
			break;
		case Protocol.RQ_LOADROOMS :
			this.userName = readString();
			break;
		case Protocol.RQ_CHANNELSUBSCRIPTIONCHANGE :
			this.userName = readString();
			this.roomName = readString();
			this.subscription = readBoolean();
			break;
		case Protocol.RQ_MODERATIONSTATE:
			this.userName = readString();
			this.messageId = readInt();
			this.approved = readBoolean();
			break;
		case Protocol.RQ_POSTMESSAGE:
			this.userName = readString();
			this.roomName = readString();
			this.text = readString();
			break;
		}
	}
	
	public String getName()
	{
		return this.userName;
	}

	public String getChannel() {
		return this.roomName;
	}

	public ChannelType getChannelType() {
		return this.channelType;
	}

	public boolean getSubscription() {
		return this.subscription;
	}

	public int getMessageId() {
		return this.messageId;
	}

	public boolean getApproved() {
		return this.approved;
	}

	public String getText() {
		return text;
	}
}