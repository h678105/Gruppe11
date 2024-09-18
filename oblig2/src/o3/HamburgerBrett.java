package o3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class HamburgerBrett {
	private final BlockingQueue<Hamburger> brett;
    private int hamburgerTeller = 0;
	
	public HamburgerBrett(int kapasitet) {
		this.brett = new ArrayBlockingQueue<>(kapasitet);
	}
	
	public void leggTilHamburger(String kokkNavn) throws InterruptedException {
		hamburgerTeller++;
        Hamburger hamburger = new Hamburger(hamburgerTeller);
        brett.put(hamburger);  // Blokkerer hvis brettet er fullt
		System.out.println(kokkNavn + " (kokk) legger på hamburger " + hamburger + ". Brett: " + brett);
	}
	
	public Hamburger fjernHamburger(String servitorNavn) throws InterruptedException {
		 Hamburger hamburger = brett.take();  // Blokkerer hvis brettet er tomt
	        System.out.println(servitorNavn + " (servitør) tar av hamburger " + hamburger + ". Brett: " + brett);
	        return hamburger;
	}
}
