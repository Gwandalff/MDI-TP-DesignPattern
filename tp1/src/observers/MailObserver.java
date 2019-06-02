package observers;

import javax.swing.JLabel;

import mailbox.MailBox;
import pattern.observer.Observer;
import pattern.observer.subject;

public class MailObserver extends JLabel implements Observer{
	
	String nomsMail = "";
	String lastMail = "";
	int index = 0;

	@Override
	public void update(subject s) {
		index++;
		MailBox mailbox = (MailBox) s;
		nomsMail = lastMail + "<br>" + nomsMail;
		lastMail = index+" - "+mailbox.getLastMail().getSubject();
		setText("<html><font color=red>"+lastMail+"</font><br>"+nomsMail+"</html>");
	}

}
