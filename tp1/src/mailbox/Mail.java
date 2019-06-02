package mailbox;

public class Mail {
	
	String subject;
	String body;
	
	public String getSubject() {
		return subject;
	}
	
	public String getBody() {
		return body;
	}
	
	public Mail(String subject, String body) {
		super();
		this.subject = subject;
		this.body = body;
	}

}
