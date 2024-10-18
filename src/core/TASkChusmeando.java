package core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.rmi.*;
import java.rmi.server.*;
import remote.RemoteObserver;

public class TASkChusmeando extends UnicastRemoteObject implements RemoteObserver {

    private String filename;

    public TASkChusmeando(String filename) throws RemoteException {
        super();
        this.filename = filename;
    }

    @Override
    public void update(Object event) {
        var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm]:"));
        System.out.println("[Debugging] " + timestamp + event);

        try {
            var writer = new OutputStreamWriter(new FileOutputStream(filename, true));
            writer.write(timestamp + event + "\n");
            writer.close();
        } catch (IOException ex) {
            System.out.println("?something wrong with java... well, everything is wrong with java");
        }
    }

}
