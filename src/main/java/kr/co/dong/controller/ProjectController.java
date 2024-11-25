package kr.co.dong.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dong.project.AddressVO;
import kr.co.dong.project.BoardsVO;
import kr.co.dong.project.BuyVO;
import kr.co.dong.project.BuydetailVO;
import kr.co.dong.project.CartVO;
import kr.co.dong.project.ProductVO;
import kr.co.dong.project.ProjectService;
import kr.co.dong.project.UserVO;

@Controller
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Inject
	ProjectService projectService;
	
	
	
	 // 재고관리 화면으로 이동시키며 동시에 필요한 재고 정보를 호출하는 컨트롤러
	@RequestMapping(value="project/inventory",method= RequestMethod.GET)
	public ModelAndView inventory() {
		ModelAndView mav = new ModelAndView();
		
		int totalRecord = projectService.product_totalRecord();
		List<ProductVO> list = projectService.listProduct();
		mav.addObject("list", list);
		mav.addObject("totalRecord",totalRecord);
		mav.setViewName("inventory2");
		return mav;
	}
	
	
	// 프로젝트 로그인 화면으로 단순 이동시키는 컨트롤러
	@RequestMapping(value="project/login", method= RequestMethod.GET)
	public String projectLogin() {
		logger.info("project login view 이동");
		return "projectLogin";
	}
	
	// 로그인 처리
	@RequestMapping(value="project/login", method= RequestMethod.POST)
	public String projectLogin(@RequestParam Map<String,Object> map,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		logger.info("요청정보 받아서 보내기");
		
		Map<String, Object> user = projectService.projectLogin(map);
		
		if(user == null) { //로그인 실패
			logger.info("로그인 안됨");
			return "redirect:login";
		}else {  // 로그인 성공
			//세션부여
			session.setAttribute("user", user);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "project/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate();
		rttr.addFlashAttribute("msg", "로그아웃되었습니다");
		return "redirect:/";
	}	
	
	
	
	
	
	
	public static int productPageSIZE = 10;  // 한 페이지에 담을 게시글의 개수
	public static int productTotalRecord = 0;
	public static int productTotalPage = 1;
		
	
	public static int productStartPage = 1;
	public static int productEndPage = 10;
	public static int productPageListSIZE = 10;
	// public static int pageListNUM = 1;
	
	
	// 제품 목록 화면 + 페이징처리
	@RequestMapping(value = "project/product" , method = RequestMethod.GET)
	public ModelAndView product(@RequestParam(value="product_pageNUM", defaultValue="1")int product_pageNUM,
							@RequestParam(value="product_pageListNUM", defaultValue="1") int product_pageListNUM
			) {
		
		productTotalRecord = projectService.product_totalRecord();  // del=0인 게시글의 총 개수
		productTotalPage = productTotalRecord / productPageSIZE;
		if (productTotalRecord % productPageSIZE != 0) {
			productTotalPage++;
		}    // 결국 totalRecord ÷ pageSIZE 를 올림처리한 것과 동일함
		
		int start = (product_pageNUM-1) * productPageSIZE;
		
		
		productStartPage = (product_pageListNUM-1) * productPageListSIZE + 1;
		productEndPage = productStartPage + productPageListSIZE - 1;
		if (productEndPage > productTotalPage) {
			productEndPage = productTotalPage;
		}
			
		//	int end = start + pageSIZE - 1;
			// 해당 pageNUM 을 가진 페이지에는 bno가 start 부터 end 까지인 게시글들이 보여짐
			
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", projectService.listProduct(start, productPageSIZE));
		mav.addObject("totalPage", productTotalPage);
		mav.addObject("startPage", productStartPage);
		mav.addObject("pageListNUM", product_pageListNUM);
		mav.addObject("endPage", productEndPage);
		mav.setViewName("product");
		return mav;
			
	}
	
	
	
	
	// 제품 목록 화면에서 제품을 클릭했을 때 출력될 상세페이지 (product_detail.jsp)
	@RequestMapping(value = "project/product_detail", method = RequestMethod.GET)
	public String productDetail(@RequestParam("product_id")String product_id, Model model) {	
		ProductVO productVO = projectService.productDetail(product_id);
		
		model.addAttribute("product", productVO);
		
		//해당 제품에 대한 리뷰 조회
		List<BoardsVO> reviewlist = projectService.reviewlist(product_id);
		model.addAttribute("list", reviewlist);
		
		return "product_detail";
	}
	
	@RequestMapping(value = "project/inventory_detail", method = RequestMethod.GET)
	public String inventoryDetail(@RequestParam("product_id")String product_id, Model model) {	
		ProductVO productVO = projectService.productDetail(product_id);
		
		model.addAttribute("product", productVO);
		
		
		return "inventory_detail2";
	}
	
	
	
	@RequestMapping(value="project/product_register", method= RequestMethod.GET)
	public String productRegister() {
		logger.info("관리자 글 작성 이동");
		return "admin_post2";
	}
	
	// 신규 product 등록
	@RequestMapping(value="project/product_register", method= RequestMethod.POST) 
	public String productRegister(ProductVO productVO, HttpServletRequest request,RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + productVO);

		int r = projectService.productRegister(productVO);
		
		if(r>0) {
			rttr.addFlashAttribute("msg","추가에 성공하였습니다.");	//세션저장
			}
		return "redirect:inventory";
	}
	
	// 기존에 있던 product 수량만 추가
	@RequestMapping(value="project/productRemainPlus", method= RequestMethod.POST)
	public String productRemainPlus(@RequestParam("product_plus") int product_plus,
						@RequestParam("product_id") String product_id) {
		int r = projectService.productRemainPlus(product_plus, product_id);
		
		return "redirect:inventory";
	}
	
	
	@RequestMapping(value="project/product_update", method= RequestMethod.GET)
	public String productUpdate(@RequestParam("product_id") String product_id, Model model) {
		ProductVO productVO = projectService.productDetail(product_id);
		model.addAttribute("product", productVO);
		return "admin_update";
	}
	
	@RequestMapping(value="project/product_update", method = RequestMethod.POST)
	public String productUpdate(ProductVO productVO, RedirectAttributes attr,HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		int r = projectService.productUpdate(productVO);
		// 수정에 성공하면 목록보기로 이동
		if (r > 0) {
			attr.addFlashAttribute("msg", "수정에 성공 하였습니다.");
			return "redirect:inventory";
		}
		// 수정에 실패하면 수정보기 화면으로 이동
		return "redirect:inventory";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="project/product_delete",method = RequestMethod.GET)
	public String productDelete(@RequestParam("product_id") String product_id, RedirectAttributes rttr) {
		int r = projectService.productDelete(product_id);
		
		if(r > 0) {
			rttr.addFlashAttribute("msg","글삭제에 성공하였습니다.");
			return "redirect:inventory";
		}
		return "redirect:inventory_detail?product_id=" + product_id;
	}
	
	
	
	
	
	
	
	
	
	// 주문/결제 페이지로 이동
	@RequestMapping(value="project/pay", method= RequestMethod.GET)
	public ModelAndView pay(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		logger.info("pay 이동");
		
		Map<String, Object> user = (Map)session.getAttribute("user");
		String user_id = (String)user.get("user_id");
	    
		
		ModelAndView mav = new ModelAndView();
		
		
		
		List<CartVO> list = projectService.listCart(user_id);
		
		AddressVO address = projectService.findMainAddress(user_id);
		
		int totalRecord = projectService.cart_totalRecord(user_id);
		
		mav.addObject("address",address);
		mav.addObject("list", list);
		mav.addObject("totalRecord",totalRecord);
		mav.setViewName("pay2");
		return mav;
	}
	
	
	
//	public static void alert(HttpServletResponse response, String msg) {
//	    try {
//			response.setContentType("text/html; charset=utf-8");
//			PrintWriter w = response.getWriter();
//			w.write("<script>alert('"+msg+"');</script>");
//			w.flush();
//			w.close();
//	    } catch(Exception e) {
//			e.printStackTrace();
//	    }
//	}
	
	
	
	@RequestMapping(value="project/pay", method= RequestMethod.POST)
	public String pay(@RequestParam("buy_address") String buy_address,@RequestParam("buy_receive") String buy_receive,
			HttpSession session, HttpServletRequest request,  HttpServletResponse response,
			 			RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용");
		
		Map<String, Object> user = (Map)session.getAttribute("user");
		String user_id = (String)user.get("user_id");
		
		List<CartVO> list = projectService.listCart(user_id);
		int totalRecord = projectService.cart_totalRecord(user_id);
		
		
		
		
		int r = projectService.buyRegister(buy_address, buy_receive, totalRecord, user_id);
		int u = projectService.findBuyno();
		int s = projectService.buyDetailRegister(list, u);
		int t = projectService.cartDelete(user_id);
		
		
		if(r>0) {
			rttr.addFlashAttribute("msg","추가에 성공하였습니다.");	//세션저장
		}
		
		return "redirect:/";
	}
	
	
	
	
	
	
	// 회원가입 화면으로 이동
	@RequestMapping(value="project/join", method= RequestMethod.GET)
	public String join() {
		logger.info("글쓰기 이동");
		return "join";
	}
		
	// 회원가입 정보 제출 + 등록
	@RequestMapping(value="project/join", method= RequestMethod.POST)
	public String join(UserVO userVO, HttpServletRequest request,RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + userVO);
		int r = projectService.join(userVO);
		
		if(r>0) {
			rttr.addFlashAttribute("msg","추가에 성공하였습니다.");	//세션저장
		}
		return "redirect:login";
	}	
		
	
	
	
	public static int mypagePageSIZE = 10;  // 한 페이지에 담을 게시글의 개수
	public static int mypageTotalRecord = 0;
	public static int mypageTotalPage = 1;
		
	
	public static int mypageStartPage = 1;
	public static int mypageEndPage = 10;
	public static int mypagePageListSIZE = 10;
	// public static int pageListNUM = 1;
	
	// 마이페이지로 이동
	@RequestMapping(value = "project/mypage1" , method = RequestMethod.GET)
	public ModelAndView mypage(@RequestParam(value="mypage_pageNUM", defaultValue="1")int mypage_pageNUM,
							@RequestParam(value="mypage_pageListNUM", defaultValue="1") int mypage_pageListNUM,
							HttpSession session, HttpServletRequest request, HttpServletResponse response
		) {
	
		Map<String, Object> user = (Map)session.getAttribute("user");
		String user_id = (String)user.get("user_id");
		
		mypageTotalRecord = projectService.mypage_totalRecord(user_id);  // del=0인 게시글의 총 개수
		mypageTotalPage = mypageTotalRecord / mypagePageSIZE;
		if (mypageTotalRecord % mypagePageSIZE != 0) {
				mypageTotalPage++;
			}    // 결국 totalRecord ÷ pageSIZE 를 올림처리한 것과 동일함
	
		int start = (mypage_pageNUM-1) * mypagePageSIZE;
		
		
		mypageStartPage = (mypage_pageListNUM-1) * mypagePageListSIZE + 1;
		mypageEndPage = mypageStartPage + mypagePageListSIZE - 1;
		if (mypageEndPage > mypageTotalPage) {
				mypageEndPage = mypageTotalPage;
			}
		
		//	int end = start + pageSIZE - 1;
		// 해당 pageNUM 을 가진 페이지에는 bno가 start 부터 end 까지인 게시글들이 보여짐
			
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", projectService.listMypage(start, mypagePageSIZE, user_id));
		mav.addObject("totalPage", mypageTotalPage);
		mav.addObject("startPage", mypageStartPage);
		mav.addObject("pageListNUM", mypage_pageListNUM);
		mav.addObject("endPage", mypageEndPage);
		mav.setViewName("mypage");
		return mav;
			
	}
	
	
	
	//마이페이지
		@RequestMapping(value="project/mypage", method=RequestMethod.GET)
		public String ProductMypage(Model model, HttpSession session) {
			//아이디 가져오기
//			String userid = session.getId();
//			String userid = "yoonho";
			
			Map<String, Object> user = (Map)session.getAttribute("user");
			String userid = (String)user.get("user_id");
			
			//결제건 데이터 가져오기
			List<BuyVO> VO = projectService.listBuy(userid);
			model.addAttribute("buy_list", VO);
			
			int[] buyno = new int[100];
			
			//결제건 데이터에서 buy_no 뽑기
			for(int i=0; i<VO.size(); i++) {
				BuyVO buyVO = VO.get(i);
				buyno[i] = buyVO.getBuy_no();
			}
				
			//결제상세건 데이터 가져오기
			List<BuydetailVO> detailVO = projectService.listBuydetail(buyno);
			model.addAttribute("buy_detail_list", detailVO);
			
			String[] productno = new String[100];
			
			//결제상세건 데이터에서 buydetail_productid 뽑기
			for(int i=0; i<detailVO.size(); i++) {
				BuydetailVO buyDetailVO = detailVO.get(i);
				productno[i] = buyDetailVO.getBuydetail_productid();
			}
			
			//결제상세건 데이터에서 제품 데이터 가져오기
			List<ProductVO> productVO = projectService.mypageDetailProduct(productno);
			model.addAttribute("buy_detail_product_list", productVO);

			return "mypage";
		}
	
	
	
	
		
		
		
		
	
	
	
	// 리뷰 작성 페이지로 이동
	@RequestMapping(value = "project/review_form", method = RequestMethod.GET)
	public String review_form(@RequestParam("product_id")String product_id, Model model, HttpSession session) {	
		
		ProductVO productVO = projectService.productDetail(product_id);
		
		session.getAttribute("user");
		model.addAttribute("product", productVO);
		
		return "review_form";
	}
	
	
	// 리뷰 작성 후 '완료' 버튼 클릭해서 리뷰 등록
	@RequestMapping(value="project/review_form", method= RequestMethod.POST)
	public String review(BoardsVO boardsVO, HttpServletRequest request,RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + boardsVO);
		int r = projectService.review(boardsVO);
		
		if(r>0) {
			rttr.addFlashAttribute("msg","추가에 성공하였습니다.");	//세션저장
		}
		return "redirect:mypage";
	}	
	
	
		
		
	
	
	
	@RequestMapping(value="project/address_select",method= RequestMethod.GET)
	public ModelAndView address_select(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> user = (Map)session.getAttribute("user");
		String user_id = (String)user.get("user_id");
		
		ModelAndView mav = new ModelAndView();
		
		int totalRecord = projectService.address_totalRecord(user_id);
		List<AddressVO> list = projectService.listAddress(user_id);
		mav.addObject("list", list);
		mav.addObject("totalRecord",totalRecord);
		mav.setViewName("address_select");
		return mav;
	}
		
	
	
	
	
	
	@RequestMapping(value="project/cart",method= RequestMethod.GET)
	public ModelAndView cart(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> user = (Map)session.getAttribute("user");
		String user_id = (String)user.get("user_id");
		
		int totalRecord = projectService.cart_totalRecord(user_id);
		List<CartVO> list = projectService.listCart(user_id);
		
		
		mav.addObject("list", list);
		mav.addObject("totalRecord",totalRecord);
		mav.setViewName("cart2");
		return mav;
	}
	
	
		
	@RequestMapping(value="project/cart_register", method= RequestMethod.GET) 
	public String cartRegister(@RequestParam("product_id")String product_id, 
			@RequestParam("amount")int amount, HttpServletRequest request,
			RedirectAttributes rttr, HttpSession session, HttpServletResponse response, Model model) throws Exception {
		request.setCharacterEncoding("UTF-8");

		Map<String, Object> user = (Map)session.getAttribute("user");
		String user_id = (String)user.get("user_id");
		
		ProductVO productVO = projectService.productDetail(product_id);
		String product_name = (String)productVO.getProduct_name();
		
		
		int s = projectService.findCart(product_id);
		if (s==0) {
			int r = projectService.cartRegister(user_id, product_id, product_name, amount);
		}
		
		return "redirect:product";
	}
		
	@ResponseBody
	@RequestMapping(value="/project/cart_update", method= RequestMethod.POST)
	public String cartUpdate(CartVO cartVO, HttpServletRequest request,
			RedirectAttributes rttr, HttpSession session, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, Object> user = (Map)session.getAttribute("user");
		String user_id = (String)user.get("user_id");
		
		
		
		int r = projectService.cartUpdate(user_id, cartVO);
		
		return "redirect:cart2";
	}
	
		
		
	
	
	
}
