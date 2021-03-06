package spacks.communication.server;

import spacks.communication.utilities.SAction;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Stepan Plachy
 * @author Vaclav Blazej
 */
public interface SCommunicationServer {

    boolean start();

    void stop();

    void send(int id, SAction action) throws IOException;

    void sendToGroup(Collection<Integer> ids, SAction action) throws IOException;

    void broadcast(SAction action) throws IOException;

    void broadcastExceptOne(int leftOut, SAction action) throws IOException;

    Boolean isRunning();

    int getNumberOfConnections();

    void disconnectClient(int id) throws IOException;
}
