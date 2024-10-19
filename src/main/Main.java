package main;

import java.io.File;
import java.rmi.registry.*;
import remote.RemoteObserver;
import remote.RemoteObserverAdder;

public class Main {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5050);

            RemoteObserverAdder remoteController = (RemoteObserverAdder) registry.lookup("TASkOcupado");
            var fileName = System.getProperty("user.home") + File.separator + "AssignmentLogger.txt";
            RemoteObserver remoteObserver = new AssignmentLogger(fileName);

            remoteController.addObserver(remoteObserver);

            System.out.println("Success: RemoteObserver listening");
        } catch (Exception e) {
            if (e.hashCode() == 248609774) {
                System.out.println("Nothing to listen: TASkOcupadoApp not running");
                return;
            }
            System.out.println("?error registering RemoteObserver: " + e.getMessage());
        }
    }

}
