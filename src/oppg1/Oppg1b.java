package oppg1;

import java.util.function.BiFunction;

public class Oppg1b {

  public static void main(String[] args) {

    System.out.print("i. Summen av 12 og 13: ");
    BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
    System.out.println(beregn(12, 13, sum));

    System.out.print("\nii. Den største av -5 og 3: ");
    BiFunction<Integer, Integer, Integer> greatest = (a, b) -> Integer.max(a, b);
    System.out.println(beregn(-5, 3, greatest));

    System.out.print("\niii. Avstanden (absoluttverdien av differansen) mellom 54 og 45: ");
    BiFunction<Integer, Integer, Integer> difference = (a, b) -> Math.abs(a - b);
    System.out.println(beregn(54, 45, difference));
  }

  private static int beregn(int a, int b, BiFunction<Integer, Integer, Integer> function) {
    return function.apply(a, b);
  }
}