package kr.co.dong.project;

import java.util.List;
import java.util.Map;


public interface ProjectDAO {

	public int product_totalRecord();
	public int mypage_totalRecord(String user_id);
	
	
	public int boardsReadCnt(int boards_no);
	public List<BoardsVO> reviewlist(String product_id);
	
	
	
	public ProductVO productDetail(String product_id);
	public List<AddressVO> addresslist(String user_id);
	
	
	
	//product 화면 호출 시 필요
	public List<ProductVO> listProduct(int start, int pageSIZE);
	public List<ProductVO> listMypage(int start, int pageSIZE, String user_id);
	
	//로그인
	public Map<String, Object> projectLogin(Map<String, Object> map);
	
	
	public int join(UserVO userVO);
	public int pay(BuyVO buyVO);
	public int review(BoardsVO boardsVO);
	
	
}
