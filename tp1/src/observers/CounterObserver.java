package observers;

import javax.swing.JLabel;

import mailbox.MailBox;
import pattern.observer.Observer;
import pattern.observer.subject;

public class CounterObserver extends JLabel implements Observer{

	@Override
	public void update(subject s) {
		MailBox mailbox = (MailBox) s;
		setText("nombre de mails : " +mailbox.getNbreMail());
	}

}
