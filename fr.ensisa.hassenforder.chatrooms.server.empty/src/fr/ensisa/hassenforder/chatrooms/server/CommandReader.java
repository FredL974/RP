package fr.ensisa.hassenforder.chatrooms.server;

import java.io.InputStream;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.server.model.Channel;
import fr.ensisa.hassenforder.chatrooms.server.model.ChannelType;
import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandReader extends BasicAbstractReader {

	private String userName, roomName, moderatorName, message, channel, text;
	private OperationStatus operationStatus;
	private Application application;
	private MessagesSession messagesSession;
	private CommandSession commandSession;
	private CommandWriter w;
	private int mode, length, valid, messageId, channelTypeNumber;
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
			// TODO : 1 pour FREE, 2 pour MODERATED
			switch (this.channelTypeNumber) {
			case 1:
				this.channelType = ChannelType.FREE;
				break;
			case 2:
				this.channelType = ChannelType.MODERATED;
				break;
			}
			break;
		/*case Protocol.RQ_LOADROOMS :
			this.userName = readString();
			List<Channel> channels = this.application.loadChannels(this.userName);
			this.w.loadChannel(this.userName, channels);
			break;
		case Protocol.RQ_CHANNELSUBSCRIPTIONCHANGE :
			this.userName = readString();
			this.roomName = readString();
			this.subscription = readBoolean();
			this.operationStatus = this.application.ChangeChannelSubscription(this.userName, this.roomName, this.subscription);
			this.w.sendOperationStatus(this.operationStatus);
			break;
		case Protocol.RQ_SETAPPROBATION :
			this.userName = readString();
			this.messageId = readInt();
			this.approved = readBoolean();
			this.operationStatus = this.application.SetApprobation(this.userName, this.messageId, this.approved);
			this.w.sendOperationStatus(this.operationStatus);
			break;*/
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
	
	public MessagesSession getMessagesSession() {
		return this.messagesSession;
	}

	public String getText() {
		return text;
	}
}