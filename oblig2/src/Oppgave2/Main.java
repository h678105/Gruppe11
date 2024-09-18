package Oppgave2;

public class Main {
	public static void main(String[] args) {
		final String[] kokker = {"Anne", "Erik", "Knut"};
		final String[] servitorer = {"Mia", "Per"};
		final int KAPASITET = 4;
		
		skrivUtHeader(kokker, servitorer, KAPASITET);
		
		HamburgerBrett brett = new HamburgerBrett(KAPASITET);
		
		for(String navn : kokker) {
			new Kokk(brett, navn).start();
		}
		
		for(String navn : servitorer) {
			new Servitor(brett, navn).start();
		}
	}
	
	private static void skrivUtHeader(String[] kokker, String[] servitorer, int kapasitet) {
		System.out.println("I denne simuleringen har vi");
		System.out.print("Kokker: [ ");
		for(int i = 0; i < kokker.length; i++) {
			System.out.print(kokker[i]);
			if(i < kokker.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println(" ]");
		System.out.print("Servitører: [ ");
		for(int i = 0; i < servitorer.length; i++) {
			System.out.print(servitorer[i]);
			if(i < servitorer.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println(" ]");
		System.out.println("Kapasiteten til brettet er " + kapasitet + " hamburgere.");
		System.out.println("Vi starter...");
	}
}
