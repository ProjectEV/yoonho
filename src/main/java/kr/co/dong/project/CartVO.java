package kr.co.dong.project;

public class CartVO {
	private int cart_no;
	private String cart_userid;
	private String cart_productid;
	private String cart_productname;
	private int cart_amount;
	private String product_price;
	private String product_remain;
	
	public String getProduct_remain() {
		return product_remain;
	}
	public void setProduct_remain(String product_remain) {
		this.product_remain = product_remain;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public int getCart_no() {
		return cart_no;
	}
	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}
	public String getCart_userid() {
		return cart_userid;
	}
	public void setCart_userid(String cart_userid) {
		this.cart_userid = cart_userid;
	}
	public String getCart_productid() {
		return cart_productid;
	}
	public void setCart_productid(String cart_productid) {
		this.cart_productid = cart_productid;
	}
	public String getCart_productname() {
		return cart_productname;
	}
	public void setCart_productname(String cart_productname) {
		this.cart_productname = cart_productname;
	}
	public int getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	@Override
	public String toString() {
		return "CartVO [cart_no=" + cart_no + ", cart_userid=" + cart_userid + ", cart_productid=" + cart_productid
				+ ", cart_productname=" + cart_productname + ", cart_amount=" + cart_amount + ", product_price="
				+ product_price + ", product_remain=" + product_remain + "]";
	}
	
	
	
	
	
	
}
