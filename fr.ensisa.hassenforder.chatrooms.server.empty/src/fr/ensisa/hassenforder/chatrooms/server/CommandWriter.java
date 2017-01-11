package fr.ensisa.hassenforder.chatrooms.server;

import java.io.OutputStream;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.server.model.Channel;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandWriter extends BasicAbstractWriter {
	
	private OperationStatus operationStatus;
	
	public CommandWriter(OutputStream outputStream) {
		super(outputStream);
	}

	public void sendOperationStatus(OperationStatus operationStatus) {
		// TODO
		switch (operationStatus) {
		case ALLREADY_CONNECTED:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			
			// TODO : voir BasicAbstractWriter
			writeString("test"); 
			
			break;
		case APPROBATION_CHANGED:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case CHANNEL_CREATED:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case CHANNEL_CREATION_FAILED:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case CHANNEL_EXISTS:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case MESSAGE_SENT:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case NOT_CONNECTED:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case NOW_CONNECTED:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case NOW_DISCONNECTED:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case SUBSCRIPTION_CHANGED:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case UNKNOWN_CHANNEL:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case UNKNOWN_MESSAGE:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		case UNKNOWN_USER:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		default:
			System.out.println("Serveur CommandWriter sendOperationStatus");
			break;
		}
	}

	public void loadChannel(String userName, List<Channel> channels) {
		// TODO Auto-generated method stub
		
	}

	public void writeOK() {
		writeInt(Protocol.OK);
	}

	public void writeKO() {
		writeInt(Protocol.KO);
	}
}