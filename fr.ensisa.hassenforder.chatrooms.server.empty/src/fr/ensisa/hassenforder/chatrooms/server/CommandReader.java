package fr.ensisa.hassenforder.chatrooms.server;

import java.io.InputStream;

import fr.ensisa.hassenforder.chatrooms.server.model.ChannelType;
import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandReader extends BasicAbstractReader {

	private String userName, roomName, moderatorName, message, channel;
	private OperationStatus operationStatus;
	private Application application;
	private MessagesSession messagesSession;
	private CommandSession commandSession;
	private CommandWriter w;
	private int mode, length, valid, messageId, channelTypeNumber;
	private ChannelType channelType;

	public CommandReader(InputStream inputStream) {
		super(inputStream);
	}

	public void receive() {
		type = readInt();
		switch (type) {
		case Protocol.RQ_CONNECT:
			this.userName = readString();
			this.operationStatus = this.application.connectCommandUser(this.userName, this.commandSession);
			this.w.sendOperationStatus(this.operationStatus);
			break;
		case Protocol.RQ_DISCONNECT:
			this.userName = readString();
			this.operationStatus = this.application.disconnectUser(this.userName);
			this.w.sendOperationStatus(this.operationStatus);
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

			this.operationStatus = this.application.createChannel(this.userName, this.roomName, this.channelType);
			this.w.sendOperationStatus(this.operationStatus);
			break;
		/*
		 * 
		 * case Protocol.RQ_LOADROOMS : this.userName = readString();
		 * List<Channel> channels =
		 * this.application.loadChannels(this.userName);
		 * this.w.loadrooms(this.user, channels); break; case
		 * Protocol.RQ_CHANNELSUBSCRIPTIONCHANGE : this.userName = readString();
		 * this.roomName = readString(); this.subscription = readBoolean();
		 * this.operationStatus =
		 * this.application.ChangeChannelSubscription(this.userName,
		 * this.roomName, this.subscription);
		 * this.w.sendOperationStatus(this.operationStatus); break; case
		 * Protocol.RQ_SETAPPROBATION : this.userName = readString();
		 * this.messageId = readInt(); this.approved = readBoolean();
		 * this.operationStatus = this.application.SetApprobation(this.userName,
		 * this.messageId, this.approved);
		 * this.w.sendOperationStatus(this.operationStatus); break;
		 */

		}
	}
}
