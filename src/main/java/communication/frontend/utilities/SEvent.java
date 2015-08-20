package communication.frontend.utilities;

import java.awt.event.ActionEvent;

/**
 *
 * @author V�clav Bla�ej
 */
public class SEvent extends ActionEvent {

    public SEvent(Object source, int id, SEventType command) {
        super(source, id, command.toString());
    }

    enum SEventType {

        CONNECT("Connect"),
        DISCONNECT("Disonnect");
        String name;

        SEventType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
