package logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import observer.Observer;

public class Logger implements Observer {
    
    private String filename;

    public Logger(String filename) {
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
