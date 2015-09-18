package spacks.communication.server.impl;


import spacks.communication.server.SCommunicationServer;
import spacks.communication.utilities.SAction;
import spacks.communication.utilities.SListener;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Stepan Plachy
 * @author Vaclav Blazej
 */
public class SCommunicationServerImpl implements SCommunicationServer {

    private static final Logger logger = Logger.getLogger(SCommunicationServer.class.getName());

    private SCommunicationServerCreateService connectionService;
    private Map<Integer, SCommunicationClientHandler> connections;
    private SListener sListener;

    public SCommunicationServerImpl(int port, SListener sListener) throws IOException {
        connections = new HashMap<>(20);
        connectionService = new SCommunicationServerCreateService(port, connections, sListener);
        this.sListener = sListener;
    }

    @Override
    public boolean start() {
        return connectionService.start();
    }

    @Override
    public void stop() {
        connectionService.stop();
    }

    @Override
    public void send(int id, SAction action) throws IOException {
        final SCommunicationClientHandler handler = connections.get(id);
        if (handler != null) {
            handler.sendAsynchronous(action);
        } else {
            throw new RuntimeException("Asking for handler for client with invalid id=" + id);
        }
    }

    @Override
    public void sendToGroup(Collection<Integer> ids, SAction action) throws IOException {
        for (int id : ids) send(id, action);
    }

    @Override
    public void broadcast(SAction action) throws IOException {
        for (Integer connection : connections.keySet()) {
            send(connection, action);
        }
    }

    @Override
    public void broadcastExceptOne(int leftOut, SAction action) throws IOException {
        final Set<Integer> ids = connections.keySet();
        for (int id : ids) if (id != leftOut) send(id, action);
    }

    @Override
    public Boolean isRunning() {
        return connectionService.isRunning();
    }

    @Override
    public int getNumberOfConnections() {
        return connections.size();
    }

    @Override
    public void disconnectClient(int id) throws IOException {
        sListener.connectionRemoved(id);
        SCommunicationClientHandler handler = connections.get(id);
        connections.remove(id);
        handler.disconnect();
    }

}
