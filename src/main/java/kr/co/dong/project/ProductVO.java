package kr.co.dong.project;

public class ProductVO {
	private String product_id;
	private int product_price;
	private String product_name;
	private String product_content;
	private String product_category;
	private int product_remain;
	private int product_sales;
	
	
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public int getProduct_remain() {
		return product_remain;
	}
	public void setProduct_remain(int product_remain) {
		this.product_remain = product_remain;
	}
	public int getProduct_sales() {
		return product_sales;
	}
	public void setProduct_sales(int product_sales) {
		this.product_sales = product_sales;
	}
	@Override
	public String toString() {
		return "ProductVO [product_id=" + product_id + ", product_price=" + product_price + ", product_name="
				+ product_name + ", product_content=" + product_content + ", product_category=" + product_category
				+ ", product_remain=" + product_remain + ", product_sales=" + product_sales + "]";
	}
	
	
	
	
	
	
}
