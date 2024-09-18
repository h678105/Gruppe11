package oppgave1;

public class Main {

  public static void main(String[] args) {

    Message message = new Message("Hallo verden!");
    Thread sender = new Sender(message);
    Thread receiver = new Receiver(message);
    
    sender.start();
    receiver.start();
  }
}
