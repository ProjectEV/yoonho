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
	public int cartRegister(String user_id, String product_id, String product_name, int amount) {
		// TODO Auto-generated method stub
		return projectDAO.cartRegister(user_id, product_id, product_name, amount);
	}
	@Override
	public int cartUpdate(String user_id, String product_id, int amount) {
		// TODO Auto-generated method stub
		return projectDAO.cartUpdate(user_id, product_id, amount);
	}
	@Override
	public int buyRegister(BuyVO buyVO, int totalRecord) {
		// TODO Auto-generated method stub
		return projectDAO.buyRegister(buyVO, totalRecord);
	}
	@Override
	public int buyDetailRegister(List<CartVO> list) {
		// TODO Auto-generated method stub
		return projectDAO.buyDetailRegister(list);
	}
	@Override
	public int cartDelete(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.cartDelete(user_id);
	}
	@Override
	public int findBuyno() {
		// TODO Auto-generated method stub
		return projectDAO.findBuyno();
	}

	

	

	
	
	
	
	
	

}
