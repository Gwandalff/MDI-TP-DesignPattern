package mailbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pattern.observer.Observer;
import pattern.observer.subject;



public class MailBox extends subject{

	private static MailBox instance = null;
	
	List<Mail> mail = new ArrayList<Mail>();
	List<Observer> observers = new ArrayList<Observer>();
	
	private MailBox(){}
	
	public static MailBox getInstance() {
		if (instance==null)
			instance = new MailBox();
		return instance;
	}
	
	public void addMail(Mail m ){
		this.mail.add(m);
		notifyObservers();
	}


	public Mail getLastMail() {
		return this.mail.get(mail.size()-1);
	}


	public Integer getNbreMail() {
		return mail.size();
	}

	public void attach(Observer o) {
		observers.add(o);
	}
	
	public void detach(Observer o) {
		if(observers.contains(o)) {
			observers.remove(observers.indexOf(o));
		}
	}
	
	public void notifyObservers() {
		for(Observer o: observers) {
			o.update(this);
		}
	}
	
	
}
