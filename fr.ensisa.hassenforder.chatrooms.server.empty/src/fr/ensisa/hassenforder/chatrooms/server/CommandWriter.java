package fr.ensisa.hassenforder.chatrooms.server;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;

public class CommandWriter extends BasicAbstractWriter {

	public CommandWriter(OutputStream outputStream) {
		super (outputStream);
	}

	public void sendOperationStatus(OperationStatus operationStatus) {
		// TODO Auto-generated method stub
		
	}

}
