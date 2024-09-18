package o1;

/**
 * Class that represents a {@link Message}. The message has a default {@link String}-value that
 * is given as input at construction.
 * <p>
 * The message can be altered using {@link #setText(String)}.
 */
public class Message {

  private String defaultMessage;

  /**
   * Constructs a new {@link Message} with a default value based on the given
   * {@link String}-input.
   * 
   * @param string the initial value of the message
   */
  public Message(String string) {
    this.defaultMessage = string;
  }

  /** Gets the current {@link String}-value of the message. */
  public String getText() {
    return defaultMessage;
  }

  /** Alters the {@link String}-value of the message. */
  public void setText(String newText) {
    this.defaultMessage = newText;
  }
}
