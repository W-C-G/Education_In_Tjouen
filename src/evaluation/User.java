package evaluation;

public class User {
	private int uid;
	private String username;
	private String upass;
	
	User(){}
	User(int uid, String username, String upass){
		setUid(uid);
		setUsername(username);
		setUpass(upass);
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	
}