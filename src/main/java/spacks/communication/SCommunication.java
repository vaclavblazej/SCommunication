package spacks.communication;

import spacks.communication.client.SCommunicationClient;
import spacks.communication.client.impl.SCommunicationClientImpl;
import spacks.communication.server.SCommunicationServer;
import spacks.communication.server.impl.SCommunicationServerImpl;
import spacks.communication.utilities.SAction;
import spacks.communication.utilities.SEmptyAction;
import spacks.communication.utilities.SEmptyListener;
import spacks.communication.utilities.SListener;

import java.io.IOException;

public class SCommunication {

    public static SCommunicationServer createNewServer(int port) throws IOException {
        return createNewServer(port, new SEmptyListener());
    }

    public static SCommunicationServer createNewServer(int port, SListener sListener) throws IOException {
        return new SCommunicationServerImpl(port, sListener);
    }


    public static SCommunicationClient createNewClient() {
        return createNewClient(new SEmptyAction());
    }

    public static SCommunicationClient createNewClient(SAction repair) {
        return new SCommunicationClientImpl(repair);
    }
}
