package bean;

public class Ccsq {

	private int id;
	private String time;
	private String period;
	private String name;
	private String friend;
	private String place;
	private String transport;
	private String state;
	public Ccsq(){
		
	}
	public Ccsq(int id, String time, String period, String name, String friend,
			String place, String transport, String state) {
		super();
		this.id = id;
		this.time = time;
		this.period = period;
		this.name = name;
		this.friend = friend;
		this.place = place;
		this.transport = transport;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
