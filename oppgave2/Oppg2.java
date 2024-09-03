package oppgave2;


import java.util.List;
import java.util.function.Function;

import java.util.Arrays;

public class Oppg2 {

    // Metode for å utføre lønnsoppgjør
    private static void lonnsoppgjor(List<Ansatt> ansatte, Function<Ansatt, Integer> beregnNyLonn) {
        for (Ansatt a : ansatte) {
            int nyLonn = beregnNyLonn.apply(a);  // Beregn ny lønn
            a.setAarslonn(nyLonn);               // Oppdaterer den ansattes lønn
        }
    }

    // Hjelpemetode for å skrive ut alle ansatte
    private static void skrivUtAlle(List<Ansatt> ansatte) {
        for (Ansatt a : ansatte) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        // Oppretter en liste med 5 ansatte
        List<Ansatt> ansatte = Arrays.asList(
        		new Ansatt("Tom", "Hermansen", Kjonn.MANN, "Sjef", 900000),
				new Ansatt("Stine", "Foss", Kjonn.KVINNE, "Utvikler", 850000),
				new Ansatt("Olav", "Tron", Kjonn.MANN, "Utvikler", 680000),
				new Ansatt("Grete", "Hansen", Kjonn.KVINNE, "Økonomisjef", 600000),
				new Ansatt("Sofie", "Christiansen", Kjonn.KVINNE, "Selger", 550000),
				new Ansatt("Henrik", "Holm", Kjonn.MANN, "Markedsføring", 600000)
        );
  
        // Skriver ut alle ansatte før lønnsoppgjør
        System.out.println("Før lønnsoppgjør:"); 
        skrivUtAlle(ansatte); 

        // Ulike lønnsberegninger (lambda-uttrykk)

        // i. Fast kronetillegg på 10 000 kr
        Function<Ansatt, Integer> fastKronetillegg = a -> a.getAarslonn() + 10000;
        lonnsoppgjor(ansatte, fastKronetillegg);
        System.out.println("\nEtter fast kronetillegg:");
        skrivUtAlle(ansatte);

        // ii. Fast prosenttillegg på 5%
        Function<Ansatt, Integer> prosenttillegg = a -> (int) (a.getAarslonn() * 1.05);
        lonnsoppgjor(ansatte, prosenttillegg);
        System.out.println("\nEtter fast prosenttillegg:");
        skrivUtAlle(ansatte);

        // iii. Fast kronetillegg på 15 000 kr for de med lønn under 500 000 kr
        Function<Ansatt, Integer> lavLonnKronetillegg = a -> a.getAarslonn() < 500000 ? a.getAarslonn() + 15000 : a.getAarslonn();
        lonnsoppgjor(ansatte, lavLonnKronetillegg);
        System.out.println("\nEtter kronetillegg for lav lønn:");
        skrivUtAlle(ansatte);

        // iv. Fast prosenttillegg på 3% hvis du er mann
        Function<Ansatt, Integer> prosenttilleggForMenn = a -> a.getKjonn() == Kjonn.MANN ? (int) (a.getAarslonn() * 1.03) : a.getAarslonn();
        lonnsoppgjor(ansatte, prosenttilleggForMenn);
        System.out.println("\nEtter prosenttillegg for menn:");
        skrivUtAlle(ansatte);
    }
}
