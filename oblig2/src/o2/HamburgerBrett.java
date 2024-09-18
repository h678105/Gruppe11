package o2;

import java.util.LinkedList;
import java.util.Queue;

public class HamburgerBrett {
	public final int kapasitet;
	public final Queue<Hamburger> brett = new LinkedList<>();
	private int hamburgerTeller = 0;
	
	public HamburgerBrett(int kapasitet) {
		this.kapasitet = kapasitet;
	}
	
	public synchronized void leggTilHamburger(String kokkNavn) throws InterruptedException {
		while(brett.size() >= kapasitet) {
			System.out.println(kokkNavn + " (kokk) klar med hamburger, men brett fullt. Venter!");
			wait();
		}
		hamburgerTeller++;
		Hamburger hamburger = new Hamburger(hamburgerTeller);
		brett.add(hamburger);
		System.out.println(kokkNavn + "(kokk) legger på hamburger " + hamburger + ". Brett: " + brett);
		notifyAll(); //Varsler servitør om ny hamburger
	}
	
	public synchronized Hamburger fjernHamburger(String servitorNavn) throws InterruptedException {
		while(brett.isEmpty()) {
			System.out.println(servitorNavn + " (servitør) ønsker å ta hamburger, men brett tomt. Venter!");
			wait();
		}
		Hamburger hamburger = brett.poll();
		System.out.println(servitorNavn + "(servitør) tar av hamburger " + hamburger + ". Brett: " + brett);
		notifyAll(); //Varsler kokk om ledig plass på brettet
		return hamburger;
	}
}
