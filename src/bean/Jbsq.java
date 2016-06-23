package bean;

public class Jbsq {

	private int id;
	private String time;
	private String jbtime;
	private String period;
	private String name;
	private String state;
	public Jbsq(){
		
	}
	public Jbsq(int id, String time, String jbtime, String period, String name,
			String state) {
		super();
		this.id = id;
		this.time = time;
		this.jbtime = jbtime;
		this.period = period;
		this.name = name;
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
	public String getJbtime() {
		return jbtime;
	}
	public void setJbtime(String jbtime) {
		this.jbtime = jbtime;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
