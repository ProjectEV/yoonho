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
	public List<AddressVO> addresslist(String user_id) {
		// TODO Auto-generated method stub
		return projectDAO.addresslist(user_id);
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

	

	

	
	
	
	
	
	

}
