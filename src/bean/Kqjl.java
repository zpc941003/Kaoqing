package bean;

public class Kqjl {
	private int id;
	private String time;
	private String category;
	private String name;
	private String period;
	private String state;
	public Kqjl(){
		
	}
	public Kqjl(int id,String time, String category, String name, String period,
			String state) {
		//super();
		this.id = id;
		this.time = time;
		this.category = category;
		this.name = name;
		this.period = period;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
