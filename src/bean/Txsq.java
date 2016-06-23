package bean;

public class Txsq {
	private int id;
	private String time;
	private String starttime;
	private String endtime;
	private String period;
	private String name;
	private String state;
	public Txsq(){
		
	}
	public Txsq(int id, String time, String starttime, String endtime,
			String period, String name, String state) {
		super();
		this.id = id;
		this.time = time;
		this.starttime = starttime;
		this.endtime = endtime;
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
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
