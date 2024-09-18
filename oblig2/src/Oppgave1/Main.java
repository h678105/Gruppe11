package Oppgave1;

import javax.swing.JOptionPane;

public class Main {
	private static String message = "Hallo verden";
	private static boolean running = true;
	
	public static void main(String[] args) {
		// Tråd for å skrive ut meldingen hvert 3. sekund
		Thread printThread = new Thread(() -> {
			while(running) {
				System.out.println(message);
				try {
					Thread.sleep(3000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		// Tråd for å motta brukerinput og oppdatere meldingen
		Thread inputThread = new Thread(() -> {
			while(running) {
				String input = JOptionPane.showInputDialog(null, "Skriv inn din melding, quit for å slutte");
				
				if(input != null && input.equalsIgnoreCase("quit")) {
					running = false;
					System.exit(0);
				} else {
					message = input;
				}
			}
		});
		
		//Starte begge trådene
		printThread.start();
		inputThread.start();
	}
}
