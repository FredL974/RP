package fr.ensisa.hassenforder.chatrooms.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.client.model.Channel;
import fr.ensisa.hassenforder.chatrooms.client.model.ChannelType;
import fr.ensisa.hassenforder.chatrooms.client.model.Message;
import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandReader extends BasicAbstractReader {

	private List<Channel> channels;
	
	public CommandReader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case Protocol.OK:
			
			break;
		case Protocol.RP_LOADROOMSOK:
			channels= new ArrayList<Channel>();
			int size=readInt();
			String name;
			ChannelType channelType = null;
			String moderator = null;
			boolean subscribed;
			for(int i = 0; i < size; i++){
				name = readString();
				switch (readInt()) {
				case 0:
					channelType = ChannelType.FREE;
					moderator = null;
					break;
				case 1:
					channelType = ChannelType.MODERATED;
					moderator = readString();
					break;
				}
				subscribed = readBoolean();
				channels.add(new Channel(name, channelType, moderator, subscribed));
			}
			break;
		case 0 : break;
		}
	}

	public List<Channel> getAllChannel() {
		return channels;
	}

	private ChannelType getChannelType() {
		int tmp=readInt();
		switch (tmp) {
		case 0:
			return ChannelType.FREE;
		case 1:
			return ChannelType.MODERATED;
		}
		return null;
	}
}