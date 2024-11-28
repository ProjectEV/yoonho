package kr.co.dong.project;

import java.util.List;
import java.util.Map;


public interface ProjectService {

	public int product_totalRecord();
	public int mypage_totalRecord(String user_id);
	public int address_totalRecord(String user_id);
	public int cart_totalRecord(String user_id);
	public int totalReview(String product_id);
	
	public int boardsReadCnt(int boards_no);
	public List<BoardsVO> reviewlist(String product_id);
	
	
	public ProductVO productDetail(String product_id);
	
	
	public List<ProductVO> listProduct(int start, int pageSIZE);
	public List<ProductVO> listMypage(int start, int pageSIZE, String user_id);
	public List<ProductVO> listProduct();
	public List<AddressVO> listAddress(String user_id);
	public List<CartVO> listCart(String user_id);
	public List<BuyVO> listBuy(String userid);
	public List<BuydetailVO> listBuydetail(int[] buyno);
	
	public List<ProductVO> mypageDetailProduct(String[] productno);
	
	
	
	
	public Map<String, Object> projectLogin(Map<String, Object> map);
	
	
	
	public int join(UserVO userVO);
	public int pay(BuyVO buyVO);
	public int review(BoardsVO boardsVO);
	
	
	
	
	
	
	public int productRegister(ProductVO productVO);
	public int productDelete(String product_id);
	public int productRemainPlus(int product_plus, String product_id);
	
	public int productAdd(String product_id, int product_add);
	
	
	
	public int productUpdate(ProductVO productVO);
	
	public int cartRegister(String user_id, String product_id, String product_name, int amount);
//	public int cartUpdate(String user_id, String productId, int amount);
	// public int cartUpdate(String user_id, List<CartVO> updateList);
	public int cartUpdate(String user_id, String product_id, int cart_amount);
	public int findCart(String product_id);
	public int findProductPrice(String product_id);
	
	
	public int remainCheck(List<CartVO> list);
	public int buyRegister(String buy_address, String buy_receive, int totalRecord, String user_id, int totalPrice);
	public int buyDetailRegister(List<CartVO> list, int u);
	public int cartDelete(String user_id);
	public int cartDelete(String user_id, String product_id);
	public int findBuyno();
	public int salesUpdate(List<CartVO> list);
	
	public AddressVO findMainAddress(String user_id);
	
	public int findProductNo(String product_id);
	public List<ProductVO> findSameCategory(int category, String product_id);
	
	public BuydetailVO buydetailDetail(int buydetail_no);
	public BuyVO buyDetail(int buy_no);
	public int cancelUpdateBuy(int buy_no, int buydetailPrice);
	public int cancelUpdateProduct(String product_id, int amount);
	public int deleteBuydetail(int buydetail_no);
	public int deleteBuy(int buy_no);
	
}
