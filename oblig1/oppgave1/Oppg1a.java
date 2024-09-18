package oppgave1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Oppg1a {

  public static void main(String[] args) {

    List<String> listen = Arrays.asList("10", "1", "20", "110", "21", "12");
    Collections.sort(listen, (x1, x2) -> Integer.valueOf(x1) - Integer.valueOf(x2));
    System.out.println("Sortert liste av tallverdier: " + listen);
  }

}
