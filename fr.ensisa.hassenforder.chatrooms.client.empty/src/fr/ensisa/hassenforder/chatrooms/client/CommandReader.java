package fr.ensisa.hassenforder.chatrooms.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.client.model.Channel;
import fr.ensisa.hassenforder.chatrooms.client.model.ChannelType;
import fr.ensisa.hassenforder.network.BasicAbstractReader;

public class CommandReader extends BasicAbstractReader {

	public CommandReader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		}
	}

	public List<Channel> getAllChannel() {
		List<Channel> tmpl= new ArrayList<Channel>();
		for(int i=0;i<readInt();i++){
			tmpl.add(new Channel(readString(),getChannelType(),readString(),readBoolean()));
		}
		return tmpl;
	}

	private ChannelType getChannelType() {
		String tmp=readString();
		for (ChannelType chan : ChannelType.values()){
			if(tmp.equals(chan.toString())){
				return chan;
			}
		}
		return null;
	}

}