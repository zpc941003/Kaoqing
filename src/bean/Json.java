package bean;

import java.util.List;

public class Json {

	private String total;
	private List rows;
	public Json(){
		
	}
	public Json(String total, List<Kqjl> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<Kqjl> getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
