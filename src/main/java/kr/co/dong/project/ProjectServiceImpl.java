package kr.co.dong.project;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Inject
	private ProjectDAO projectDAO;
	
	
	
	@Override
	public int product_totalRecord() {
		// TODO Auto-generated method stub
		return projectDAO.product_totalRecord();
	}
	@Override
	public int mypage_totalRecord(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.mypage_totalRecord(user_id);
	}
	@Override
	public int address_totalRecord(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.address_totalRecord(user_id);
	}
	@Override
	public int cart_totalRecord(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.cart_totalRecord(user_id);
	}
	@Override
	public int totalReview(String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.totalReview(product_id);
	}
	

	@Override
	public int boardsReadCnt(int boards_no) {
		// TODO Auto-generated method stub
		return projectDAO.boardsReadCnt(boards_no);
	}
	
	@Override
	public List<BoardsVO> reviewlist(String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.reviewlist(product_id);
	}
	
	
	
	
	@Override
	public ProductVO productDetail(String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.productDetail(product_id);
	}
	@Override
	public GradeVO gradeDetail(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.gradeDetail(user_id);
	}
	
	
	
	
	
	@Override
	public List<ProductVO> listProduct(int start, int pageSIZE) {
		// TODO Auto-generated method stub
		return projectDAO.listProduct(start, pageSIZE);
	}
	@Override
	public List<ProductVO> listMypage(int start, int pageSIZE, String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.listMypage(start, pageSIZE, user_id);
	}
	@Override
	public List<ProductVO> listProduct() {
		// TODO Auto-generated method stub
		return projectDAO.listProduct();
	}
	@Override
	public List<AddressVO> listAddress(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.listAddress(user_id);
	}
	@Override
	public List<CartVO> listCart(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.listCart(user_id);
	}
	@Override
	public List<BuyVO> listBuy(String userid) {
		// TODO Auto-generated method stub
		return projectDAO.listBuy(userid);
	}
	@Override
	public List<BuydetailVO> listBuydetail(int[] buyno) {
		// TODO Auto-generated method stub
		return projectDAO.listBuydetail(buyno);
	}
	
	@Override
	public List<ProductVO> mypageDetailProduct(String[] productno) {
		// TODO Auto-generated method stub
		return projectDAO.mypageDetailProduct(productno);
	}
	
	
	

	@Override
	public Map<String, Object> projectLogin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return projectDAO.projectLogin(map);
	}

	@Override
	public int join(UserVO userVO) {
		// TODO Auto-generated method stub
		return projectDAO.join(userVO);
	}

	@Override
	public int pay(BuyVO buyVO) {
		// TODO Auto-generated method stub
		return projectDAO.pay(buyVO);
	}
	@Override
	public int review(BoardsVO boardsVO) {
		// TODO Auto-generated method stub
		return projectDAO.review(boardsVO);
	}
	@Override
	public int grade(UserVO userVO) {
		// TODO Auto-generated method stub
		return projectDAO.grade(userVO);
	}
	@Override
	public int grade(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.grade(user_id);
	}
	@Override
	public int findGradeUser(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.findGradeUser(user_id);
	}
	@Override
	public int findGradeTotalPrice(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.findGradeTotalPrice(user_id);
	}
	@Override
	public int updateGrade(String user_id, int totalPrice, int grade, int discount, String gradename) {
		// TODO Auto-generated method stub
		return projectDAO.updateGrade(user_id, totalPrice, grade, discount, gradename);
	}
	
	
	
	
	
	
	@Override
	public int productRegister(ProductVO productVO) {
		// TODO Auto-generated method stub
		return projectDAO.productRegister(productVO);
	}
	@Override
	public int productDelete(String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.productDelete(product_id);
	}
	@Override
	public int productRemainPlus(int product_plus, String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.productRemainPlus(product_plus, product_id);
	}
	
	
	
	@Override
	public int productUpdate(ProductVO productVO) {
		// TODO Auto-generated method stub
		return projectDAO.productUpdate(productVO);
	}
	
	@Override
	public int productAdd(String product_id, int product_add) {
		// TODO Auto-generated method stub
		return projectDAO.productAdd(product_id, product_add);
	}
	
	
	@Override
	public int cartRegister(String user_id, String product_id, String product_name, int amount) {
		// TODO Auto-generated method stub
		return projectDAO.cartRegister(user_id, product_id, product_name, amount);
	}
//	@Override
//	public int cartUpdate(String user_id, String productId, int amount) {
//		// TODO Auto-generated method stub
//		return projectDAO.cartUpdate(user_id, productId, amount);
//	}
//	@Override
//	public int cartUpdate(String user_id, List<CartVO> updateList) {
//		// TODO Auto-generated method stub
//		return projectDAO.cartUpdate(user_id, updateList);
//	}
	@Override
	public int cartUpdate(String user_id, String product_id, int cart_amount) {
		// TODO Auto-generated method stub
		return projectDAO.cartUpdate(user_id, product_id, cart_amount);
	}
	
	@Override
	public int findCart(String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.findCart(product_id);
	}
	@Override
	public int findProductPrice(String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.findProductPrice(product_id);
	}
	

	
	
	@Override
	public int remainCheck(List<CartVO> list) {
		// TODO Auto-generated method stub
		return projectDAO.remainCheck(list);
	}
	@Override
	public int buyRegister(String buy_address, String buy_receive, int totalRecord, String user_id, int totalPrice) {
			// TODO Auto-generated method stub
			return projectDAO.buyRegister(buy_address, buy_receive, totalRecord, user_id, totalPrice);
		}
	@Override
	public int buyDetailRegister(List<CartVO> list, int u) {
			// TODO Auto-generated method stub
			return projectDAO.buyDetailRegister(list, u);
		}
	@Override
	public int cartDelete(String user_id) {
			// TODO Auto-generated method stub
			return projectDAO.cartDelete(user_id);
		}
	@Override
	public int cartDelete(String user_id, String product_id) {
			// TODO Auto-generated method stub
			return projectDAO.cartDelete(user_id, product_id);
		}
	@Override
	public int findBuyno() {
			// TODO Auto-generated method stub
			return projectDAO.findBuyno();
		}
	@Override
	public int salesUpdate(List<CartVO> list) {
		// TODO Auto-generated method stub
		return projectDAO.salesUpdate(list);
	}
	
	
	
	
	
	@Override
	public AddressVO findMainAddress(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.findMainAddress(user_id);
	}
	@Override
	public int findProductNo(String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.findProductNo(product_id);
	}
	@Override
	public List<ProductVO> findSameCategory(int category, String product_id) {
		// TODO Auto-generated method stub
		return projectDAO.findSameCategory(category, product_id);
	}
	
	
	
	
	
	@Override
	public BuydetailVO buydetailDetail(int buydetail_no) {
		// TODO Auto-generated method stub
		return projectDAO.buydetailDetail(buydetail_no);
	}
	@Override
	public BuyVO buyDetail(int buy_no) {
		// TODO Auto-generated method stub
		return projectDAO.buyDetail(buy_no);
	}
	@Override
	public int cancelUpdateBuy(int buy_no, int buydetailPrice) {
		// TODO Auto-generated method stub
		return projectDAO.cancelUpdateBuy(buy_no, buydetailPrice);
	}
	@Override
	public int cancelUpdateProduct(String product_id, int amount) {
		// TODO Auto-generated method stub
		return projectDAO.cancelUpdateProduct(product_id, amount);
	}
	@Override
	public int deleteBuydetail(int buydetail_no) {
		// TODO Auto-generated method stub
		return projectDAO.deleteBuydetail(buydetail_no);
	}
	@Override
	public int deleteBuy(int buy_no) {
		// TODO Auto-generated method stub
		return projectDAO.deleteBuy(buy_no);
	}
	
	
	
	
	
	
	
	
	
	
	

	

	
	
	
	
	
	

}
