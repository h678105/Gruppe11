package o1;

import javax.swing.JOptionPane;

/**
 * This is a {@link Thread} class responsible for sending messages.
 * This class extends {@link Thread} and overrides the {@link #run()} method
 * to continuously prompt the user for input and update a shared {@link Message}
 * instance.
 */
public class Sender extends Thread {

  private final Message message;

  /**
   * Constructs a new {@link Sender} with the given {@link Message}.
   * 
   * @param message a shared {@link Message} instance to be updated by this
   *                sender
   */
  public Sender(Message message) {
    this.message = message;
  }

  @Override
  public void run() {
    while (true) {
      String newText = JOptionPane.showInputDialog("Skriv inn din melding, quit for å slutte");

      if (newText.equalsIgnoreCase("quit")) {
        System.exit(0);
      }
      synchronized (message) {
        message.setText(newText);
        message.notifyAll();
      }
    }
  }
}
