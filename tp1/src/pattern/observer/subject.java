package pattern.observer;

public abstract class subject {
	public abstract void attach(Observer o) ;
	
	public abstract void detach(Observer o) ;
	
	public abstract void notifyObservers() ;
}
