package fr.ensisa.hassenforder.chatrooms.server;

import java.io.OutputStream;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.server.model.Channel;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandWriter extends BasicAbstractWriter {
	
	
	public CommandWriter(OutputStream outputStream) {
		super(outputStream);
	}

	public void loadChannel(String userName, List<Channel> channels) {
		writeInt(Protocol.RP_LOADROOMSOK);
		writeInt(channels.size());
		int type;
		for (int i=0;i<channels.size();i++){
			writeString(channels.get(i).getName());
			type = channels.get(i).getType().ordinal();
			writeInt(type);
			switch (type) {
			case 0:
				break;
			case 1:
				writeString(channels.get(i).getModerator().getName());
				break;
			}
			writeBoolean(channels.get(i).isSubscriptor(userName));
		}
	}

	public void writeOK() {
		writeInt(Protocol.OK);
	}

	public void writeKO() {
		writeInt(Protocol.KO);
	}
}