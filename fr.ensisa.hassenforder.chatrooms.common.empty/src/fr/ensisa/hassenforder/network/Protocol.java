package fr.ensisa.hassenforder.network;

public interface Protocol {

	public static final int CHATROOMS_CMD_PORT_ID		= 9876;
	public static final int CHATROOMS_MSG_PORT_ID		= 9877;
	//
	public static final int OK = 254;	//commande demandée a réussi
	public static final int KO = 255;	//commande demandée a échoué
			//TODO
	public static final int RQ_CONNECT = 1;					//(String userName)
	public static final int RQ_CREATEROOM = 2;				//(String moderatorName, string Channel,Channeltype type )
	public static final int RQ_LOADROOMS = 3;				//(String name)
	public static final int RQ_SETAPPROBATION = 4;			//(String name, int messageId, boolean approved)
	/*
	public static final int RQ_VALIDMODERATOR = 4;			//(String roomName, String userName, String message, short int valid)
	public static final int RQ_INVALIDMODERATOR = 5;		//(String roomName, String userName, String message, short int valid)
	*/
	public static final int RQ_POSTMESSAGE = 6;				//(String userName ,String roomName , String message)
	public static final int RQ_DISCONNECT = 7;				//(String userName)
	public static final int RQ_CHANNELSUBSCRIPTIONCHANGE=8; //(String name, String roomName, boolean selected
	public static final int RQ_MODERATIONSTATE=9;			//String name, message message,boolean approved
	public static final int RP_CREATEROOMOK = 102;			//()
	public static final int RP_LOADROOMSOK = 103;			//(short int length, Room [] rooms // de la forme {String roomName; short int mode;} )
	public static final int RQ_VALIDATIONMODERATOR = 104;	//(String roomName, String userName, String message, short int valid) avec valid = 1
	public static final int RP_VALIDMODERATOROK = 104;		//(String roomName, String userName, String message, short int valid)
	public static final int RP_INVALIDMODERATOROK = 105;	//(String roomName, String userName, String message, short int valid)
	public static final int RP_POSTMESSAGEOK = 106;			//()
	public static final int RP_ERROR_USERNAMEALREADYEXIST = 200;
	public static final int RP_ERROR_STRINGFORBIDDEN = 201;
	public static final int RP_ERROR_MEMORYSPACE = 202;
	public static final int RP_ERROR_CONNECTION = 203;
			
}