SCommunication
==============

[![Build Status](https://travis-ci.org/vaclavblazej/SCommunication.svg)](https://travis-ci.org/vaclavblazej/SCommunication)

SCommunication is usefull for creating server-client communication. The idea is to create instance of a class and send data (attributes) through internet, when delivered the class performs a specific method. This ensures that one action is created and handeled on one place in the code. (See example for further clarification!)

==============

Example
--------------

start the server
```java
SCommunicationServer server = SCommunication.createNewServer(port);
server.start();
```

start the client
```java
SCommunicationClient client = SCommunication.createNewClient();
client.connect(ip, port);
```

create class which will represent message
```java
package your.custom.package
import spacks.communication.utilities.SAction;

public class PrintAction implements SAction {
    public final String message;
    public PrintAction(String message) {
        this.message = message;
    }
    @Override
    public void perform() {
        System.out.println(message);
    }
}
```

now you can send this message to the other side and code defined in perform method will be executed
```java
client.send(new TestAction("This will be printed on server side!"));
```

==============

Any questions? Please contact me on vaclavblazej (at) seznam.cz
