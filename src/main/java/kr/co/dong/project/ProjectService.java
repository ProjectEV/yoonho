package kr.co.dong.project;

import java.util.List;
import java.util.Map;


public interface ProjectService {

	public int product_totalRecord();
	public int mypage_totalRecord(String user_id);
	public int address_totalRecord(String user_id);
	public int cart_totalRecord(String user_id);
	
	public int boardsReadCnt(int boards_no);
	public List<BoardsVO> reviewlist(String product_id);
	
	
	public ProductVO productDetail(String product_id);
	
	
	public List<ProductVO> listProduct(int start, int pageSIZE);
	public List<ProductVO> listMypage(int start, int pageSIZE, String user_id);
	public List<ProductVO> listProduct();
	public List<AddressVO> listAddress(String user_id);
	public List<CartVO> listCart(String user_id);
	
	
	public Map<String, Object> projectLogin(Map<String, Object> map);
	
	
	
	public int join(UserVO userVO);
	public int pay(BuyVO buyVO);
	public int review(BoardsVO boardsVO);
	
	
	
	
	
	
	public int productRegister(ProductVO productVO);
	public int productDelete(String product_id);
	public int productRemainPlus(int product_plus, String product_id);
	
	
	public int productUpdate(ProductVO productVO);
	
	public int cartRegister(String user_id, String product_id, String product_name, int amount);
	public int cartUpdate(String user_id, String product_id, int amount);
	public int findCart(String product_id);
	
	
	public int buyRegister(BuyVO buyVO, int totalRecord, String user_id);
	public int buyDetailRegister(List<CartVO> list, int u);
	public int cartDelete(String user_id);
	public int findBuyno();
	
	
	
}
