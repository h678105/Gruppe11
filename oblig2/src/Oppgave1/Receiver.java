package oppgave1;

/**
 * This is a {@link Thread} class responsible for receiving and printing
 * messages. This class extends {@link Thread} and overrides the {@link #run()}
 * method to continuously retrieve the {@link Message} text and print it to the
 * console in given intervals.
 */
public class Receiver extends Thread {

  private final Message message;

  /**
   * Constructs a new {@link Receiver} with the given {@link Message}.
   * 
   * @param message a shared {@link Message} instance to be read by this
   *                receiver
   */
  public Receiver(Message message) {
    this.message = message;
  }

  @Override
  public void run() {
    while (true) {
      try {
        sleep(3_000);
      } catch (InterruptedException e) {
      }

      String inputText;

      synchronized (message) {
        inputText = message.getText();
        message.notifyAll();
      }
      System.out.println(inputText);
    }
  }

}
