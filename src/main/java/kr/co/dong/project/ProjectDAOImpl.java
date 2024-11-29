package kr.co.dong.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String nameSpace="kr.co.dong.projectMapper";
	
	
	
	
	
	@Override
	public int product_totalRecord() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".product_totalRecord");
	}
	@Override
	public int mypage_totalRecord(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".mypage_totalRecord", user_id);
	}
	@Override
	public int address_totalRecord(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".address_totalRecord", user_id);
	}
	@Override
	public int cart_totalRecord(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".cart_totalRecord", user_id);
	}
	@Override
	public int totalReview(String product_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".totalReview", product_id);
	}
	
	
	

	@Override
	public int boardsReadCnt(int boards_no) {
		// TODO Auto-generated method stub
		return sqlSession.update(nameSpace+".boardsReadCnt", boards_no);
	}
	
	@Override
	public List<BoardsVO> reviewlist(String product_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(nameSpace + ".reviewlist", product_id);
	}
	
	
	
	
	@Override
	public ProductVO productDetail(String product_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace+".productDetail", product_id);
	}
	@Override
	public GradeVO gradeDetail(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace+".gradeDetail", user_id);
	}
	
	
	
	
	
	@Override
	public List<ProductVO> listProduct(int start, int pageSIZE) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("pageSIZE", pageSIZE);
		return sqlSession.selectList(nameSpace + ".listProduct", map);
	}
	@Override
	public List<ProductVO> listMypage(int start, int pageSIZE, String user_id) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("pageSIZE", pageSIZE);
		map.put("user_id", user_id);
		return sqlSession.selectList(nameSpace + ".listMypage", map);
	}
	@Override
	public List<ProductVO> listProduct() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(nameSpace + ".listProductVoid");
	}
	@Override
	public List<AddressVO> listAddress(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(nameSpace+".listAddress", user_id);
	}
	@Override
	public List<CartVO> listCart(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(nameSpace+".listCart", user_id);
	}
	@Override
	public List<BuyVO> listBuy(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(nameSpace + ".listBuy", userid);
	}
	@Override
	public List<BuydetailVO> listBuydetail(int[] buyno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(nameSpace + ".listBuydetail", buyno);
	}
	
	@Override
	public List<ProductVO> mypageDetailProduct(String[] productno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(nameSpace + ".mypageDetailProduct", productno);
	}
	
	

	@Override
	public Map<String, Object> projectLogin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace +".projectLogin",map);
	}

	@Override
	public int join(UserVO userVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(nameSpace + ".join", userVO);
	}
	@Override
	public int pay(BuyVO buyVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(nameSpace+".pay", buyVO);
	}
	@Override
	public int review(BoardsVO boardsVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(nameSpace+".review", boardsVO);
	}
	@Override
	public int grade(UserVO userVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(nameSpace + ".grade", userVO);
	}
	@Override
	public int grade(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.insert(nameSpace + ".grade2", user_id);
	}
	@Override
	public int findGradeUser(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".findGradeUser", user_id);
	}
	@Override
	public int findGradeTotalPrice(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".findGradeTotalPrice", user_id);
	}
	@Override
	public int updateGrade(String user_id, int totalPrice, int grade, int discount, String gradename) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("totalPrice", totalPrice);
		map.put("grade", grade);
		map.put("discount", discount);
		map.put("gradename", gradename);
		return sqlSession.update(nameSpace + ".updateGrade", map);
	}
	
	
	
	
	
	
	
	
	
	@Override
	public int productRegister(ProductVO productVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(nameSpace + ".productRegister", productVO);
	}
	@Override
	public int productDelete(String product_id) {
		// TODO Auto-generated method stub
		return sqlSession.update(nameSpace + ".productDelete", product_id);
	}
	@Override
	public int productRemainPlus(int product_plus, String product_id) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("product_plus", product_plus);
		map.put("product_id", product_id);
		return sqlSession.update(nameSpace+".productRemainPlus", map);
	}
	
	
	
	
	@Override
	public int productUpdate(ProductVO productVO) {
		// TODO Auto-generated method stub
		return sqlSession.update(nameSpace + ".productUpdate", productVO);
	}
	
	@Override
	public int productAdd(String product_id, int product_add) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("product_id", product_id);
		map.put("product_add", product_add);
		return sqlSession.update(nameSpace+".productAdd", map);
	}
	
	
	
	@Override
	public int cartRegister(String user_id, String product_id, String product_name, int amount) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("product_id", product_id);
		map.put("product_name", product_name);
		map.put("amount", amount);
		return sqlSession.insert(nameSpace + ".cartRegister", map);
	}
	
	
	@Override
	public int cartUpdate(String user_id, String product_id, int cart_amount) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("product_id", product_id);
		map.put("cart_amount", cart_amount);
		return sqlSession.update(nameSpace + ".cartUpdate", map);
	}
	@Override
	public int findCart(String product_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".findCart", product_id);
	}
	@Override
	public int findProductPrice(String product_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".findProductPrice", product_id);
	}
	
	
	
	
	@Override
	public int remainCheck(List<CartVO> list) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".remainCheck", list);
	}
	@Override
	public int buyRegister(String buy_address, String buy_receive, int totalRecord, String user_id, int totalPrice) {
			// TODO Auto-generated method stub
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("buy_address", buy_address);
			map.put("buy_receive", buy_receive);
			map.put("totalRecord", totalRecord);
			map.put("totalPrice", totalPrice);
			map.put("user_id", user_id);
			return sqlSession.insert(nameSpace + ".buyRegister", map);
		}
	@Override
	public int buyDetailRegister(List<CartVO> list, int u) {
			// TODO Auto-generated method stub
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("u", u);
			return sqlSession.insert(nameSpace + ".buyDetailRegister", map);
		}
	@Override
	public int cartDelete(String user_id) {
			// TODO Auto-generated method stub
			return sqlSession.delete(nameSpace + ".cartDelete", user_id);
		}
	@Override
	public int cartDelete(String user_id, String product_id) {
			// TODO Auto-generated method stub
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", user_id);
			map.put("product_id", product_id);
			return sqlSession.delete(nameSpace + ".cartDeleteOne", map);
		}
	@Override
	public int findBuyno() {
			// TODO Auto-generated method stub
			return sqlSession.selectOne(nameSpace + ".findBuyno");
		}
	@Override
	public int salesUpdate(List<CartVO> list) {
		// TODO Auto-generated method stub
		return sqlSession.update(nameSpace + ".salesUpdate", list);
	}
	
	
	
	
	@Override
	public AddressVO findMainAddress(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".findMainAddress", user_id);
	}
	@Override
	public int findProductNo(String product_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".findProductNo", product_id);
	}
	@Override
	public List<ProductVO> findSameCategory(int category, String product_id) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("product_id", product_id);
		return sqlSession.selectList(nameSpace + ".findSameCategory", map);
	}
	
	
	
	
	@Override
	public BuydetailVO buydetailDetail(int buydetail_no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".buydetailDetail", buydetail_no);
	}
	@Override
	public BuyVO buyDetail(int buy_no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(nameSpace + ".buyDetail", buy_no);
	}
	@Override
	public int cancelUpdateBuy(int buy_no, int buydetailPrice) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("buy_no", buy_no);
		map.put("buydetailPrice", buydetailPrice);
		return sqlSession.update(nameSpace + ".cancelUpdateBuy", map);
	}
	@Override
	public int cancelUpdateProduct(String product_id, int amount) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("product_id", product_id);
		map.put("amount", amount);
		return sqlSession.update(nameSpace + ".cancelUpdateProduct", map);
	}
	@Override
	public int deleteBuydetail(int buydetail_no) {
		// TODO Auto-generated method stub
		return sqlSession.delete(nameSpace + ".deleteBuydetail", buydetail_no);
	}
	@Override
	public int deleteBuy(int buy_no) {
		// TODO Auto-generated method stub
		return sqlSession.delete(nameSpace + ".deleteBuy", buy_no);
	}
	
	
	
	
	
	
	
	
	
	

	

	

	

}
