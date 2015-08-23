package spacks.communication.utilities;

/**
 *
 * @author Štěpán Plachý
 * @author Václav Blažej
 */
public class SAsynchronousPacket extends SPacket {

    public SAsynchronousPacket(SAction action) {
        super(action);
    }

    @Override
    public boolean isAsynchronous() {
        return true;
    }
}