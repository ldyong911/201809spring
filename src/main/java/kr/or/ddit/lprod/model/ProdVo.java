package kr.or.ddit.lprod.model;

public class ProdVo {
	private String prod_id;
	private String prod_name;
	private String prod_buyer;
	private String prod_cost;
	
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_buyer() {
		return prod_buyer;
	}
	public void setProd_buyer(String prod_buyer) {
		this.prod_buyer = prod_buyer;
	}
	public String getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(String prod_cost) {
		this.prod_cost = prod_cost;
	}
	
	@Override
	public String toString() {
		return "ProdVo [prod_id=" + prod_id + ", prod_name=" + prod_name
				+ ", prod_buyer=" + prod_buyer + ", prod_cost=" + prod_cost
				+ "]";
	}
	
}