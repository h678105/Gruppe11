package Oppgave2;

public class Servitor extends Thread{
	private final HamburgerBrett brett;
	private final String navn;
	
	public Servitor(HamburgerBrett brett, String navn) {
		this.brett = brett;
		this.navn = navn;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep((int) (Math.random() * 4000) + 2000);
				brett.fjernHamburger(navn);
			}
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
