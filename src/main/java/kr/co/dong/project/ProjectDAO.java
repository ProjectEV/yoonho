package kr.co.dong.project;

import java.util.List;
import java.util.Map;


public interface ProjectDAO {
	
	
	//홈 제품 목록
	public List<ProductVO> homeList(Map<String, Object> codeMap);
	
	
	public int product_totalRecord();
	public int mypage_totalRecord(String user_id);
	public int address_totalRecord(String user_id);
	public int cart_totalRecord(String user_id);
	public int totalReview(String product_id);
	
	public int boardsReadCnt(int boards_no);
	public List<BoardsVO> reviewlist(String product_id);
	
	
	
	public ProductVO productDetail(String product_id);
	public GradeVO gradeDetail(String user_id);
	
	
	//product 화면 호출 시 필요
	public List<ProductVO> listProduct(int start, int pageSIZE);
	public List<ProductVO> listMypage(int start, int pageSIZE, String user_id);
	public List<ProductVO> listProduct();
	public List<AddressVO> listAddress(String user_id);
	public List<CartVO> listCart(String user_id);
	public List<BuydetailVO> listSales();
	
	
	public List<BuyVO> listBuy(String userid);
	public List<BuydetailVO> listBuydetail(int[] buyno);
	
	
	
	public List<ProductVO> mypageDetailProduct(String[] productno);
	
	
	//로그인
	public Map<String, Object> projectLogin(Map<String, Object> map);
	
	
	public int join(UserVO userVO);
	public int pay(BuyVO buyVO);
	public int review(BoardsVO boardsVO);
	public int grade(UserVO userVO);
	public int grade(String user_id);
	public int findGradeUser(String user_id);
	public int findGradeTotalPrice(String user_id);
	public int updateGrade(String user_id, int totalPrice, int grade, int discount, String gradename);
	
	
	
	
	
	public int productRegister(ProductVO productVO);
	public int productDelete(String product_id);
	public int productRemainPlus(int product_plus, String product_id);
	
	
	public int productUpdate(ProductVO productVO);
	public int productAdd(String product_id, int product_add);
	
	public int cartRegister(String user_id, String product_id, String product_name, int amount);
	// public int cartUpdate(String user_id, String productId, int amount);
	// public int cartUpdate(String user_id, List<CartVO> updateList);
	public int cartUpdate(String user_id, String product_id, int cart_amount);
	public int findCart(String product_id);
	public int findProductPrice(String product_id);
	
	public int remainCheck(List<CartVO> list);
	public int buyRegister(String buy_address, String buy_receive, int totalRecord, String user_id, int totalPrice);
	public int buyDetailRegister(List<CartVO> list, int u, int discount);
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
	
	
	//다중 이미지 저장
	public int fileUpload(FileVO fileVO);
		
	//다중 이미지 조회
	public List<String> fileSelect(String product_id);
		
	//제품 목록에서 대표 이미지 조회
	public List<FileVO> listFileSelect(String[] productno);
	
	//보드 테이블 마지막 튜플의 번호 조회
	public int boardsNoLast();
	
	
	
	
	//아이디 중복체크 처리
	public int id_check(String user_id);
		
	//아이디 찾기 처리
	public String id_search(Map<String, Object> map);
		
	//비밀번호 찾기 처리
	public String pwd_search(String user_id);
		
	//비밀번호 변경 처리
	public int pwd_change(String user_id, String user_password);
				
	
	
	//주소지 관리 주소지 리스트 조회
	public List<AddressVO> addressManageSelect1 (String userid);
	
	//주소지 관리 주소지 수정(get)
	public AddressVO addressManageUpdate1(int address_no);
	
	//주소지 관리 주소지 수정(post)
	public int addressManageUpdate2(AddressVO addressVO);
	
	//주소지 관리 주소지 추가(post)
	public int addressManageAdd2(AddressVO addressVO);
	
	//주소지 관리 주소지 삭제(get)
	public int addressManageDelete1(int address_no);
	
	//메인 주소 리셋
	public int addressManageMainReset();
	

	
	
	
	
	//검색
	public List<ProductVO> productSearch(String keyword);
	public List<ProductVO> productSearch(String keyword, int start, int plPageSIZE);
	
	//카테고리 검색 
	public List<ProductVO> categorySearch(Map<String, Object> categoryMap);
	public List<ProductVO> categorySearch(Map<String, Object> categoryMap, int start, int lcPageSIZE);
	
	//정렬 검색 
	public List<ProductVO> orderSearch(Map<String, Object> codeMap);
	public List<ProductVO> orderSearch(Map<String, Object> codeMap, int start, int ocPageSIZE);
	
	
	
	public float findAvgScore(String product_id);
	public int updateAvgScore(String product_id, float productAvgScore);
	
	
	
	public List<ProductVO> listUserCartProduct(String user_id);
	
	public int updateBuydetailCode(int buydetail_no, String code);
	
	
	
	
	
	
	
	
}
