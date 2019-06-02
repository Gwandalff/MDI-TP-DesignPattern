package mailbox;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import observers.CounterObserver;
import observers.MailObserver;
import pattern.observer.Observer;

public class Main {

	private void init() {
		System.out.println("init");

		JFrame f = new JFrame();
		f.setTitle("Test de JFrame"); 
	    f.setSize(300,200); 
		
		JPanel p = new JPanel();
			
		f.add(p);
		
		//TODO Register observer
		/*Observer obsCount = (Observer) new CounterObserver();
		f.add((JLabel)obsCount);
		MailBox.getInstance().attach(obsCount);*/
		
		Observer obsMail = (Observer) new MailObserver();
		f.add((JLabel)obsMail);
		MailBox.getInstance().attach(obsMail);

		f.setVisible(true);
		f.dispose();
		System.out.println("init");
		f.show();
		
		
		
	}
	private void runScenario() {
		
		MailBox.getInstance().addMail(new Mail("ESIR" + new Random().nextInt(), "tp fini"));
		System.out.println(MailBox.getInstance().getNbreMail());
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("init");
		Main m= new Main();
		m.init();
		while (true){
		Thread.sleep(3000);
		m.runScenario();
		}
	}
	

}
