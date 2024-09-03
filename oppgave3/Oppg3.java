package oppgave3;


import java.util.Arrays;
import java.util.stream.Collectors;

import oppgave2.Ansatt;
import oppgave2.Kjonn;

import java.util.*;

public class Oppg3 {
	public static void main(String[] args) {
		// Eksempel liste med ansatte
		List<Ansatt> ansatte = Arrays.asList(new Ansatt("Tom", "Hermansen", Kjonn.MANN, "Sjef", 900000),
				new Ansatt("Stine", "Foss", Kjonn.KVINNE, "Utvikler", 850000),
				new Ansatt("Olav", "Tron", Kjonn.MANN, "Utvikler", 680000),
				new Ansatt("Grete", "Hansen", Kjonn.KVINNE, "Økonomisjef", 600000),
				new Ansatt("Sofie", "Christiansen", Kjonn.KVINNE, "Selger", 550000),
				new Ansatt("Henrik", "Holm", Kjonn.MANN, "Markedsføring", 600000));

		// a) Lag en ny liste som kun inneholder etternavnene til de ansatte.
		List<String> eListe = ansatte.stream().map(Ansatt::getEtternavn).collect(Collectors.toList());
		System.out.println("Etternavnene til de ansatte: " + eListe);

		// b) Finn ut antall kvinner blant de ansatte.
		long aKvinner = ansatte.stream().filter(ansatt -> ansatt.getKjonn() == Kjonn.KVINNE).count();
		System.out.println("Antall kvinner blant de ansatte: " + aKvinner);

		// c) Regn ut gjennomsnittslønnen til kvinnene.
		double gjLonnKvinner = ansatte.stream().filter(ansatt -> ansatt.getKjonn() == Kjonn.KVINNE)
				.mapToDouble(Ansatt::getAarslonn).average().orElse(0.00); // Dersom det ikke er kvinner
		System.out.println("Gjennomsnittslønn for kvinner: " + String.format("%.2f", gjLonnKvinner));
 
		// d) Gi alle sjefer (stilling inneholder noe med "sjef") en lønnsøkning på 7%
		// ved å bruke
		// streams direkte i stedet for metoden du laget i Oppg2. Skriv ut listen av
		// ansatte etter lønnsøkningen.
		ansatte.stream().filter(ansatt -> ansatt.getStilling().toLowerCase().contains("sjef")).forEach(ansatt -> {
			int nyLonn = (int) (ansatt.getAarslonn() * 1.07); // Beregn ny lønn som int
			ansatt.setAarslonn(nyLonn); // Oppdaterer lønn direkte
		});

		System.out.println("\nListe etter lønnsøkning for sjefer:");
		ansatte.forEach(System.out::println);

		// e) Finn ut (true|false) om det er noen ansatte som tjener mer enn 800.000,-
		boolean merEnn = ansatte.stream().anyMatch(ansatt -> ansatt.getAarslonn() > 800000);
		System.out.println("Tjener noen mer enn 800 000,- : " + merEnn);

		// f) Skriv ut alle de ansatte med System.out.println() uten å bruke løkke.
		ansatte.forEach(System.out::println);

		// g) Finn den/de ansatte som har lavest lønn.
		Ansatt lavestLonn = ansatte.stream().min(Comparator.comparingInt(Ansatt::getAarslonn)).orElse(null); // Tilfelle
																												// listen
																												// er
																												// tom
		System.out.println("Ansatte med lavest lønn: " + lavestLonn);

		// h) Finn ut summen av alle heltall i [1, 1000> som er delelig med 3 eller 5.
		int sumAvTall = java.util.stream.IntStream.range(1, 1000).filter(tall -> tall % 3 == 0 || tall % 5 == 0).sum();
		System.out.println("Sum heltall [1, 1000> delelig på 3 eller 5: " + sumAvTall);
	}
}
