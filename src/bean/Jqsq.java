package bean;

public class Jqsq {

	private int id;
	private String starttime;
	private String endtime;
	private String period;
	private String name;
	private String category;
	private String state;
	public Jqsq(){
		
	}
	public Jqsq(int id, String starttime, String endtime, String period,
			String name, String category, String state) {
		super();
		this.id = id;
		this.starttime = starttime;
		this.endtime = endtime;
		this.period = period;
		this.name = name;
		this.category = category;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
