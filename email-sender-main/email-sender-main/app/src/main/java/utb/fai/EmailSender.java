package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {

    private Socket socket;
    private InputStream socketIn;
    private OutputStream socketOut;

    public EmailSender(String host, int port) throws UnknownHostException, IOException, InterruptedException {
        socket = new Socket(host, port);
        socketIn = socket.getInputStream();
        socketOut = socket.getOutputStream();
        readNsleep();

    }

    public void send(String from, String to, String subject, String text) throws IOException, InterruptedException {
        socketOut.write("EHLO localhost\r\n".getBytes());
        readNsleep();
        socketOut.write(("MAIL FROM:<" + from + ">\r\n").getBytes());
        readNsleep();
        socketOut.write(("RCPT TO:<" + to + ">\r\n").getBytes());
        readNsleep();

        socketOut.write(("DATA\r\n").getBytes());
        readNsleep();
        socketOut.write(
            ("From: " + from + "\r\n"
            + "To: " + to + "\r\n"
            + "Subject: " + subject + "\r\n\r\n"
            + text + "\r\n.\r\n").getBytes());
        readNsleep();
    }

    public void close() throws IOException, InterruptedException{
        socketOut.write("QUIT\r\n".getBytes());
        readNsleep();
        socket.close();
    }

    private void readNsleep() throws IOException, InterruptedException{
        final byte[] buffer = new byte[1024];
        int len;

        Thread.sleep(500);
        if (socketIn.available() > 0){
            len = socketIn.read(buffer, 0, 1024);
            System.out.write(buffer, 0, len);
            System.out.flush();
        }

    }

}