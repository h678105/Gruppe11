package Oppgave3;

public class Kokk extends Thread{
	private final HamburgerBrett brett;
	private final String navn;

	public Kokk(HamburgerBrett brett, String navn) {
		this.brett = brett;
		this.navn = navn;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep((int) (Math.random() * 4000) + 2000); // Simulerer tid for å lage en hamburger
				brett.leggTilHamburger(navn);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
