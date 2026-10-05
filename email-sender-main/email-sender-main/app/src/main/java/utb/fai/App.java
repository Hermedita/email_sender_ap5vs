package utb.fai;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 6){
            System.err.println("Pouzijte mene argumentu(6). <host>,<port>,<from>,<to>,<subject>,<text>");
            System.exit(1);
        }

        String host = args[0]; //args[0] - Adresa SMTP serveru (String)

        int port;
        try{
            port = Integer.parseInt(args[1]);
        } catch(NumberFormatException e){
            System.err.println("Port musi byt cislo.");
            return;
        }
        
        String from= args[2]; //args[2] - Email odesílatele (String)
        String to = args[3]; //args[3] - Email příjemce (String)
        String subject = args[4]; //args[4] - Předmět emailu (String)
        String text = args[5]; //args[5] - Obsah emailu (String)

////////////////////////////////////////
        System.out.println("Odesilam na: " + host);
///////////////////////////////////////
        try {
            EmailSender sender = new EmailSender(host,port);
            sender.send(from,to,subject,text);
            sender.close();

            
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
