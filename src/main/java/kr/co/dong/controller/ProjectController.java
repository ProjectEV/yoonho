package kr.co.dong.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dong.project.AddressVO;
import kr.co.dong.project.BoardsVO;
import kr.co.dong.project.BuyVO;
import kr.co.dong.project.ProductVO;
import kr.co.dong.project.ProjectService;
import kr.co.dong.project.UserVO;

@Controller
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Inject
	ProjectService projectService;
	
	
	
	// 재고관리 화면으로 이동시키며 동시에 필요한 재고 정보를 호출하는 컨트롤러
//	@RequestMapping(value="project/inventory",method= RequestMethod.GET)
//	public ModelAndView inventory() {
//		ModelAndView mav = new ModelAndView();
//		
//		List<InventoryVO> list = projectService.listInventory();
//		mav.addObject("list", list);
//		mav.setViewName("inventory");
//		return mav;
//	}
	
	
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
	public String detail(@RequestParam("product_id")String product_id, Model model) {	
		ProductVO productVO = projectService.productDetail(product_id);
		
		model.addAttribute("product", productVO);
		
		//해당 제품에 대한 리뷰 조회
		List<BoardsVO> reviewlist = projectService.reviewlist(product_id);
		model.addAttribute("list", reviewlist);
		
		return "product_detail";
	}
	
	
	
	
	
	
	// 주문/결제 페이지로 이동
	@RequestMapping(value="project/pay", method= RequestMethod.GET)
	public String pay(@RequestParam("product_id")String product_id, Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			{
		logger.info("pay 이동");
		
		Map<String, Object> user = (Map)session.getAttribute("user");
		String user_id = (String)user.get("user_id");
		
		ProductVO productVO = projectService.productDetail(product_id);
		List<AddressVO> addressVO = projectService.addresslist(user_id);
		
		model.addAttribute("product", productVO);
		model.addAttribute("address", addressVO);
		
		return "pay";
	}
	
	@RequestMapping(value="project/pay", method= RequestMethod.POST)
	public String pay(BuyVO buyVO, HttpServletRequest request,RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + buyVO);
		int r = projectService.pay(buyVO);
		
		if(r>0) {
			rttr.addFlashAttribute("msg","추가에 성공하였습니다.");	//세션저장
		}
		return "redirect:mypage";
	}
	
	
	
	
	
	
	// 회원가입 화면으로 이동
	@RequestMapping(value="project/join", method= RequestMethod.GET)
	public String join() {
		logger.info("글쓰기 이동");
		return "register";
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
		return "redirect:join";
	}	
		
	
	
	
	public static int mypagePageSIZE = 10;  // 한 페이지에 담을 게시글의 개수
	public static int mypageTotalRecord = 0;
	public static int mypageTotalPage = 1;
		
	
	public static int mypageStartPage = 1;
	public static int mypageEndPage = 10;
	public static int mypagePageListSIZE = 10;
	// public static int pageListNUM = 1;
	
	// 마이페이지로 이동
	@RequestMapping(value = "project/mypage" , method = RequestMethod.GET)
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
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
}
