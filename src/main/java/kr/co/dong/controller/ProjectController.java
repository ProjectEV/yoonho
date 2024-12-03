package kr.co.dong.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dong.project.AddressVO;
import kr.co.dong.project.AuthService;
import kr.co.dong.project.BoardsVO;
import kr.co.dong.project.BuyVO;
import kr.co.dong.project.BuydetailVO;
import kr.co.dong.project.CartVO;
import kr.co.dong.project.FileVO;
import kr.co.dong.project.GradeVO;
import kr.co.dong.project.NaverUserInfo;
import kr.co.dong.project.ProductVO;
import kr.co.dong.project.ProjectService;
import kr.co.dong.project.ScriptUtils;
import kr.co.dong.project.UserVO;

@Controller
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Inject
	ProjectService projectService;
	
	@Inject
	AuthService authService;

	// 기존에 있던 board/list 는 주석처리

	public static int ipageSIZE = 10; // 한 페이지에 담을 게시글의 개수
	public static int itotalRecord = 0;
	public static int itotalPage = 1;

	public static int istartPage = 1;
	public static int iendPage = 10;
	public static int ipageListSIZE = 10;

	@RequestMapping(value = "project/inventory", method = RequestMethod.GET)
	public ModelAndView inventory(@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM,
			@RequestParam(value = "pageListNUM", defaultValue = "1") int pageListNUM) {

		itotalRecord = projectService.product_totalRecord(); // del=0인 게시글의 총 개수
		itotalPage = itotalRecord / ipageSIZE;
		if (itotalRecord % ipageSIZE != 0) {
			itotalPage++;
		} // 결국 totalRecord ÷ pageSIZE 를 올림처리한 것과 동일함

		int start = (pageNUM - 1) * ipageSIZE;

		istartPage = (pageListNUM - 1) * ipageListSIZE + 1;
		iendPage = istartPage + ipageListSIZE - 1;
		if (iendPage > itotalPage) {
			iendPage = itotalPage;
		}

		// int end = start + pageSIZE - 1;
		// 해당 pageNUM 을 가진 페이지에는 bno가 start 부터 end 까지인 게시글들이 보여짐

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", projectService.listProduct(start, ipageSIZE));
		mav.addObject("totalPage", itotalPage);
		mav.addObject("startPage", istartPage);
		mav.addObject("pageListNUM", pageListNUM);
		mav.addObject("endPage", iendPage);
		mav.setViewName("inventory");
		return mav;

	}

	// 재고관리 화면으로 이동시키며 동시에 필요한 재고 정보를 호출하는 컨트롤러
//	@RequestMapping(value="project/inventory",method= RequestMethod.GET)
//	public ModelAndView inventory() {
//		ModelAndView mav = new ModelAndView();
//		
//		int totalRecord = projectService.product_totalRecord();
//		List<ProductVO> list = projectService.listProduct();
//		mav.addObject("list", list);
//		mav.addObject("totalRecord",totalRecord);
//		mav.setViewName("inventory");
//		return mav;
//	}

	
	
	
	
	
	@RequestMapping(value = "project/notice", method = RequestMethod.GET)
	public String notice() {
		logger.info("notice view 이동");
		return "notice";
	}
	
	
	
	
	// 프로젝트 로그인 화면으로 단순 이동시키는 컨트롤러
	@RequestMapping(value = "project/login", method = RequestMethod.GET)
	public String projectLogin() {
		logger.info("project login view 이동");
		return "login";
	}

	// 로그인 처리
	@RequestMapping(value = "project/login", method = RequestMethod.POST)
	public String projectLogin(@RequestParam Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		logger.info("요청정보 받아서 보내기");

		Map<String, Object> user = projectService.projectLogin(map);

		if (user == null) { // 로그인 실패
			logger.info("로그인 안됨");
			
			ScriptUtils.alertAndMovePage(response, "아이디 또는 비밀번호를 다시 확인해주세요.", "login");
			return "redirect:login";
		} else { // 로그인 성공
			// 세션부여
			
			String user_id = (String) user.get("user_id");
			int r = projectService.findGradeUser(user_id);
			
			if (r == 0) {
				int s = projectService.grade(user_id);
				updateUserGrade(user_id);
			}
			
			session.setAttribute("user", user);
			return "redirect:/";
		}
	}
	
	public void updateUserGrade(String user_id) {
		
		int totalPrice = projectService.findGradeTotalPrice(user_id);
		int grade = 0;
		String gradename = "Family";
		int discount = 0;
		if (totalPrice < 200000 ) {
			discount = 1;
		} else if (totalPrice < 500000) {
			grade = 1;
			gradename = "Silver";
			discount = 2;
		} else if (totalPrice < 1500000) {
			grade = 2;
			gradename = "Gold";
			discount = 4;
		} else {
			grade = 3;
			gradename = "VIP";
			discount = 6;
		}
		int r = projectService.updateGrade(user_id, totalPrice, grade, discount, gradename);
		
	}
	
	

	@RequestMapping(value = "project/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate();
		rttr.addFlashAttribute("msg", "로그아웃되었습니다");
		return "redirect:/";
	}

	
	 // 네이버 로그인 콜백
	@RequestMapping(value ="project/naver_login", method = RequestMethod.GET)
	public String naverCallback(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) {
		try {
			// 1. 액세스 토큰 발급
			String accessToken = authService.getAccessToken(code);

			// 2. 사용자 정보 가져오기
			NaverUserInfo userInfo = authService.getUserInfo(accessToken);

			// 3. 세션에 사용자 정보 저장
			session.setAttribute("socialUser", userInfo);
	            
			// 4. 메인 페이지로 리다이렉트
			return "redirect:/";

	} catch (Exception e) {
		e.printStackTrace();
		return "redirect:/login?error=true";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int productPageSIZE = 9; // 한 페이지에 담을 게시글의 개수
	public static int productTotalRecord = 0;
	public static int productTotalPage = 1;

	public static int productStartPage = 1;
	public static int productEndPage = 10;
	public static int productPageListSIZE = 10;
	// public static int pageListNUM = 1;

	// 제품 목록 화면 + 페이징처리
	@RequestMapping(value = "project/product", method = RequestMethod.GET)
	public ModelAndView product(@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM,
			@RequestParam(value = "pageListNUM", defaultValue = "1") int pageListNUM) {

		productTotalRecord = projectService.product_totalRecord(); // del=0인 게시글의 총 개수
		productTotalPage = productTotalRecord / productPageSIZE;
		if (productTotalRecord % productPageSIZE != 0) {
			productTotalPage++;
		} // 결국 totalRecord ÷ pageSIZE 를 올림처리한 것과 동일함

		int start = (pageNUM - 1) * productPageSIZE;

		productStartPage = (pageListNUM - 1) * productPageListSIZE + 1;
		productEndPage = productStartPage + productPageListSIZE - 1;
		if (productEndPage > productTotalPage) {
			productEndPage = productTotalPage;
		}

		// int end = start + pageSIZE - 1;
		// 해당 pageNUM 을 가진 페이지에는 bno가 start 부터 end 까지인 게시글들이 보여짐

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", projectService.listProduct(start, productPageSIZE));
		mav.addObject("imageList",listSelect(projectService.listProduct(start, productPageSIZE)));
		mav.addObject("totalPage", productTotalPage);
		mav.addObject("startPage", productStartPage);
		mav.addObject("pageListNUM", pageListNUM);
		mav.addObject("endPage", productEndPage);
		mav.setViewName("product_list");
		return mav;

	}

	// 제품 목록 화면에서 제품을 클릭했을 때 출력될 상세페이지 (product_detail.jsp)
	@RequestMapping(value = "project/product_detail", method = RequestMethod.GET)
	public String productDetail(@RequestParam("product_id") String product_id, Model model) {
		
		ProductVO productVO = projectService.productDetail(product_id);
		int totalReview = projectService.totalReview(product_id);
		model.addAttribute("totalReview", totalReview);
		model.addAttribute("product", productVO);
		
		int category = productVO.getProduct_category();
		List<ProductVO> sameCategoryList = projectService.findSameCategory(category, product_id);
		
		int size = sameCategoryList.size();
		model.addAttribute("size", size);
		
		if (size > 0) {
			int t = 0;
			String[] productArray = new String[size];
			
			for (ProductVO list : sameCategoryList) {
	            productArray[t] = list.getProduct_id();
	            t++;
	        }
			
			int[] original = new int[size];
			for(int i=0;i<size;i++) {
				original[i] = i;
			}
			int cup = 0;
			for(int i=0;i<size;i++) {
				cup = original[size-i-1];
				int random = (int)((size-i)*(Math.random()));
				original[size-i-1] = original[random];
				original[random] = cup;
			}
			
			String[] productno = new String[size];
			if(size > 4) {
				size = 4;
			}
			for(int i=0;i<size;i++) {
				productno[i] = productArray[original[i]];
			}
			List<ProductVO> list = projectService.mypageDetailProduct(productno);
			model.addAttribute("randomProductList", list);
		}
		
		// 해당 제품에 대한 리뷰 조회
		List<BoardsVO> reviewlist = projectService.reviewlist(product_id);
		model.addAttribute("list", reviewlist);
		
		List<String> file_name = projectService.fileSelect(product_id);
		model.addAttribute("file_name", file_name);
		
		return "product_detail";
	}

	@RequestMapping(value = "project/inventory_detail", method = RequestMethod.GET)
	public String inventoryDetail(@RequestParam("product_id") String product_id, Model model) {
		ProductVO productVO = projectService.productDetail(product_id);
		int totalReview = projectService.totalReview(product_id);
		
		
		model.addAttribute("totalReview", totalReview);
		model.addAttribute("product", productVO);
		
		
		return "inventory_detail";
	}

	@RequestMapping(value = "project/product_register", method = RequestMethod.GET)
	public String productRegister() {
		logger.info("관리자 글 작성 이동");
		return "admin_post";
	}

	// 신규 product 등록
	@RequestMapping(value = "project/product_register", method = RequestMethod.POST)
	public String productRegister(@RequestParam("files") List<MultipartFile> files, ProductVO productVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + productVO);
		
		String imagePath = "/C:\\uploads/";
		
		for (MultipartFile file : files) {
			String uuid = UUID.randomUUID().toString();
			String filename = uuid + "_" + file.getOriginalFilename();
			
			File dest = new File(imagePath + filename);
			
			file.transferTo(dest);
			
			FileVO fileVO = new FileVO();
			fileVO.setFile_name(filename);
			fileVO.setFile_path("/C:\\uploads/" + filename);
			fileVO.setFile_connection_id(productVO.getProduct_id());
			
			projectService.fileUpload(fileVO);
		}
		
		int r = projectService.productRegister(productVO);

		if (r > 0) {
			rttr.addFlashAttribute("msg", "추가에 성공하였습니다."); // 세션저장
		}
		return "redirect:inventory";
	}

	// 기존에 있던 product 수량만 추가
	@RequestMapping(value = "project/productRemainPlus", method = RequestMethod.POST)
	public String productRemainPlus(@RequestParam("product_add") int product_add,
			@RequestParam("product_id") String product_id) {
		int r = projectService.productRemainPlus(product_add, product_id);

		return "redirect:inventory";
	}

	@RequestMapping(value = "project/product_add", method = RequestMethod.POST)
	public String productAdd(@RequestParam("product_add") int product_add,
			@RequestParam("product_id") String product_id) {
		int r = projectService.productAdd(product_id, product_add);

		return "redirect:inventory_detail?product_id=" + product_id;
	}

	@RequestMapping(value = "project/product_update", method = RequestMethod.GET)
	public String productUpdate(@RequestParam("product_id") String product_id, Model model) {
		ProductVO productVO = projectService.productDetail(product_id);
		model.addAttribute("product", productVO);
		return "admin_update";
	}

	@RequestMapping(value = "project/product_update", method = RequestMethod.POST)
	public String productUpdate(ProductVO productVO, RedirectAttributes attr, HttpServletRequest request)
			throws Exception {
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

	@RequestMapping(value = "project/product_delete", method = RequestMethod.GET)
	public String productDelete(@RequestParam("product_id") String product_id, RedirectAttributes rttr) {
		int r = projectService.productDelete(product_id);

		if (r > 0) {
			rttr.addFlashAttribute("msg", "글삭제에 성공하였습니다.");
			return "redirect:inventory";
		}
		return "redirect:inventory_detail?product_id=" + product_id;
	}
	
	
	@RequestMapping(value = "project/backToList", method = RequestMethod.GET)
	public String backToList(@RequestParam("product_id") String product_id, Model model) {
		
		int no = projectService.findProductNo(product_id);
		
		int pageSize = 10;		// 해당 게시판을 호출할 때 사용한 pageSize
		int pageListSize = 10;	// 해당 게시판을 호출할 때 사용한 pageListSize
		
		int pageNUM = no/pageSize;
		if (no%pageSize != 0) {
			pageNUM++;
		}
		int pageListNUM = no/(pageSize*pageListSize);
		if (no%(pageSize*pageListSize) != 0) {
			pageListNUM++;
		}
		
		return "redirect:inventory?pageListNUM=" + pageListNUM + "&pageNUM=" + pageNUM;
	}
	

	
	

	// 주문/결제 페이지로 이동
	@RequestMapping(value = "project/pay", method = RequestMethod.GET)
	public ModelAndView pay(@RequestParam Map<String, Object> map, HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.info("pay 이동");

		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");
		
		int totalRecord = projectService.cart_totalRecord(user_id);
		
		ModelAndView mav = new ModelAndView();
		
		for (int i = 0; i < totalRecord; i++) {
			String a = "update[" + i + "].cart_productid";
			String b = "update[" + i + "].cart_amount";
			String product_id = (String)(map.get(a));
			int cart_amount = Integer.parseInt((String)map.get(b));
			int r = projectService.cartUpdate(user_id, product_id, cart_amount);
		}
		
		List<CartVO> list = projectService.listCart(user_id);
		GradeVO grade = projectService.gradeDetail(user_id);
		
		AddressVO address = projectService.findMainAddress(user_id);

		mav.addObject("address", address);
		mav.addObject("list", list);
		mav.addObject("grade", grade);
		mav.addObject("totalRecord", totalRecord);
		mav.setViewName("pay");
		return mav;
	}



	@RequestMapping(value = "project/pay", method = RequestMethod.POST)
	public String pay(@RequestParam("buy_address") String buy_address, @RequestParam("buy_receive") String buy_receive,
			HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용");

		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");

		List<CartVO> list = projectService.listCart(user_id);
		int totalRecord = projectService.cart_totalRecord(user_id);
		
//		int w = projectService.remainCheck(list);
		
		int totalPrice = 0;
		
		for (CartVO cart : list) {
	            if (cart.getCart_amount() > cart.getProduct_remain()) {
	                return "redirect:cart";
	            }
	            int price = projectService.findProductPrice(cart.getCart_productid());
	            int amount = cart.getCart_amount();
	            totalPrice += price * amount;
	        }
		
		int r = projectService.buyRegister(buy_address, buy_receive, totalRecord, user_id, totalPrice);
		int v = projectService.salesUpdate(list);
		int u = projectService.findBuyno();
		int s = projectService.buyDetailRegister(list, u);
		int t = projectService.cartDelete(user_id);

		if (r > 0) {
			rttr.addFlashAttribute("msg", "추가에 성공하였습니다."); // 세션저장
		}
		
		updateUserGrade(user_id);
		
		return "redirect:/";
	}

	
	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------
	
	// 회원가입 화면으로 이동
	@RequestMapping(value = "project/join", method = RequestMethod.GET)
	public String join() {
		logger.info("글쓰기 이동");
		return "join";
	}

	// 회원가입 정보 제출 + 등록
	@RequestMapping(value = "project/join", method = RequestMethod.POST)
	public String join(UserVO userVO, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + userVO);
		int r = projectService.join(userVO);
		int s = projectService.grade(userVO);
		
		if (r > 0) {
			rttr.addFlashAttribute("msg", "OK"); // 세션저장
		}
		return "login";
	}

	
	//중복체크 처리
	@RequestMapping(value="project/id_check", method= RequestMethod.GET)
	public String id_check(@RequestParam("user_id")String user_id, HttpServletRequest request, Model model) throws Exception {
	model.addAttribute("user_id", user_id);

		logger.info("중복체크 화면");
		return "id_check";
	}
	
	@RequestMapping(value="project/id_check", method = RequestMethod.POST)
	public String id_check(@RequestParam("user_id")String user_id, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes rttr, Model model) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("중복체크");
		int r = projectService.id_check(user_id);
		
		model.addAttribute("r",r);
		model.addAttribute("user_id", user_id);
		
		return "id_check";
	
	}	
	
	//아이디  찾기 처리
	@RequestMapping(value="project/id_search", method = RequestMethod.GET)
	public String id_search() {
		logger.info("아이디 찾기 화면");
		return "id_search";
	}	
	
	@RequestMapping(value="project/id_search", method = RequestMethod.POST)
	public String id_Search(@RequestParam Map<String,Object> map, 
		HttpServletRequest request, HttpServletResponse response,
		RedirectAttributes rttr, Model model ) throws Exception {
			request.setCharacterEncoding("UTF-8");
			logger.info("아이디 찾기");
			
			String id = projectService.id_search(map);
			
			model.addAttribute("id",id);
			
			if(id == null) {
				rttr.addFlashAttribute("msg","존재하지 않는 아이디입니다.");
			}else {
				rttr.addFlashAttribute("msg","당신의 아이디는 "+id+"입니다.");
			}
			//get으로 감
			return "redirect:/project/id_search";
			
	}	
	
	//비밀번호 찾기 처리
	@RequestMapping(value="project/pwd_search", method = RequestMethod.GET)
	public String pwd_search() {
		logger.info("비밀번호 찾기 화면");
		return "pwd_search";
	}
	
	@RequestMapping(value="project/pwd_search", method = RequestMethod.POST)
	public String pwd_search(@RequestParam("user_id")String user_id, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes rttr, Model model)throws Exception{
		request.setCharacterEncoding("UTF-8");
		logger.info("비밀번호 찾기");
		
		String id = projectService.pwd_search(user_id);
		
		 if (id == null) { // ID가 없는 경우
		        rttr.addFlashAttribute("msg", "존재하지 않는 아이디입니다."); // 메시지 전달
		        return "redirect:/project/pwd_search"; // 비밀번호 찾기 화면으로 이동
		    }

		    model.addAttribute("id", id); // ID를 JSP로 전달
		    return "pwd_search"; // 성공 시 JSP로 전달
		}	
	
	
	//비밀번호 변경 처리
	@RequestMapping(value="project/pwd_change", method = RequestMethod.POST)
	public String pwd_change(@RequestParam("user_id")String user_id, @RequestParam("user_password")String user_password, HttpServletRequest request,
		 HttpServletResponse response, RedirectAttributes rttr, Model model)throws Exception{
		request.setCharacterEncoding("UTF-8");
		logger.info("비밀번호 변경");
		
		int result = projectService.pwd_change(user_id, user_password);
		
		//model.addAttribute("r",r);
		
		if (result > 0) { // 변경 성공
	        rttr.addFlashAttribute("msg", "비밀번호가 성공적으로 변경되었습니다!");
	    } else { // 변경 실패
	        rttr.addFlashAttribute("msg", "비밀번호 변경에 실패했습니다. 다시 시도해 주세요.");
	    }

	    return "redirect:/project/pwd_search"; // 완료 후 비밀번호 찾기 화면으로 이동
	}	
	
	
	
	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	//주소지 관리 - 조회(get)
	@RequestMapping(value="project/address_manage", method=RequestMethod.GET)
	public String address_manage(HttpSession session, Model model) {
		
		//아이디 가져오기
		Map<String, Object> user = (Map) session.getAttribute("user");
		String userid = (String) user.get("user_id");
		
		//주소지 리스트 조회
		List<AddressVO> addressList = projectService.addressManageSelect1(userid);
		model.addAttribute("list", addressList);
		
		return "address_manage";
	}
	
	//주소지 관리 - 조회(post, 저장버튼)
	@RequestMapping(value="project/address_manage", method=RequestMethod.POST)
	public String address_manage() {
		return "mypage";
	}
	
	//주소지 관리 - 수정(get)
	@RequestMapping(value="project/address_manage/update", method=RequestMethod.GET)
	public String address_manage_update(@RequestParam("address_no")int address_no, HttpSession session, Model model) {
		//수정을 위한 주소지 조회
		AddressVO addressVO = projectService.addressManageUpdate1(address_no);
		model.addAttribute("address", addressVO);
			
		return "address_update";
	}
		
	//주소지 관리 - 수정(post)
	@RequestMapping(value="project/address_manage/update", method=RequestMethod.POST)
	public String address_manage_update(AddressVO addressVO, Model model) {
		//메인주소 리셋
		int address_main = addressVO.getAddress_main();
		if(address_main == 1) { //들어오는 데이터가 메인 주소라면
			int result = projectService.addressManageMainReset();
		}		
			
		//주소지 리스트 수정 update
		int result = projectService.addressManageUpdate2(addressVO);
		return "redirect:/project/address_manage";
	}
		
	//주소지 관리 - 추가(get)
	@RequestMapping(value="project/address_manage/add", method=RequestMethod.GET)
	public String address_manage_update() {
		return "address_add";
	}
		
	//주소지 관리 - 추가(post)
	@RequestMapping(value="project/address_manage/add", method=RequestMethod.POST)
	public String address_manage_update(HttpSession session, AddressVO addressVO) {
		//아이디 가져오기
		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");
		
		addressVO.setAddress_userid(user_id);
			
		//메인주소 리셋
		int address_main = addressVO.getAddress_main();
		if(address_main == 1) { //들어오는 데이터가 메인 주소라면
			int result = projectService.addressManageMainReset();
		}
			
		//주소지 리스트 추가
		int result = projectService.addressManageAdd2(addressVO);

		return "redirect:/project/address_manage";
	}
		
	//주소지 관리 - 삭제(get)
	@RequestMapping(value="project/address_manage/delete", method=RequestMethod.GET)
	public String address_manage_delete(@RequestParam("address_no")int address_no) {
		//주소지 리스트 삭제
		int result = projectService.addressManageDelete1(address_no);
		
		return "redirect:/project/address_manage";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int mypagePageSIZE = 10; // 한 페이지에 담을 게시글의 개수
	public static int mypageTotalRecord = 0;
	public static int mypageTotalPage = 1;

	public static int mypageStartPage = 1;
	public static int mypageEndPage = 10;
	public static int mypagePageListSIZE = 10;
	// public static int pageListNUM = 1;

	// 마이페이지로 이동
	@RequestMapping(value = "project/mypage1", method = RequestMethod.GET)
	public ModelAndView mypage(@RequestParam(value = "mypage_pageNUM", defaultValue = "1") int mypage_pageNUM,
			@RequestParam(value = "mypage_pageListNUM", defaultValue = "1") int mypage_pageListNUM, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");

		mypageTotalRecord = projectService.mypage_totalRecord(user_id); // del=0인 게시글의 총 개수
		mypageTotalPage = mypageTotalRecord / mypagePageSIZE;
		if (mypageTotalRecord % mypagePageSIZE != 0) {
			mypageTotalPage++;
		} // 결국 totalRecord ÷ pageSIZE 를 올림처리한 것과 동일함

		int start = (mypage_pageNUM - 1) * mypagePageSIZE;

		mypageStartPage = (mypage_pageListNUM - 1) * mypagePageListSIZE + 1;
		mypageEndPage = mypageStartPage + mypagePageListSIZE - 1;
		if (mypageEndPage > mypageTotalPage) {
			mypageEndPage = mypageTotalPage;
		}

		// int end = start + pageSIZE - 1;
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

	// 마이페이지
	@RequestMapping(value = "project/mypage", method = RequestMethod.GET)
	public String ProductMypage(Model model, HttpSession session) {
		// 아이디 가져오기
//			String userid = session.getId();
//			String userid = "yoonho";

		Map<String, Object> user = (Map) session.getAttribute("user");
		String userid = (String) user.get("user_id");

		// 결제건 데이터 가져오기
		List<BuyVO> VO = projectService.listBuy(userid);
		model.addAttribute("buy_list", VO);

		int[] buyno = new int[100];

		// 결제건 데이터에서 buy_no 뽑기
		for (int i = 0; i < VO.size(); i++) {
			BuyVO buyVO = VO.get(i);
			buyno[i] = buyVO.getBuy_no();
		}

		// 결제상세건 데이터 가져오기
		List<BuydetailVO> detailVO = projectService.listBuydetail(buyno);
		model.addAttribute("buy_detail_list", detailVO);

		String[] productno = new String[100];

		// 결제상세건 데이터에서 buydetail_productid 뽑기
		for (int i = 0; i < detailVO.size(); i++) {
			BuydetailVO buyDetailVO = detailVO.get(i);
			productno[i] = buyDetailVO.getBuydetail_productid();
		}

		// 결제상세건 데이터에서 제품 데이터 가져오기
		List<ProductVO> productVO = projectService.mypageDetailProduct(productno);
		model.addAttribute("buy_detail_product_list", productVO);

		return "mypage";
	}

	// 리뷰 작성 페이지로 이동
	@RequestMapping(value = "project/review_form", method = RequestMethod.GET)
	public String review_form(@RequestParam("product_id") String product_id, Model model, HttpSession session) {

		ProductVO productVO = projectService.productDetail(product_id);

		session.getAttribute("user");
		model.addAttribute("product", productVO);

		return "review_form";
	}

	// 리뷰 작성 후 '완료' 버튼 클릭해서 리뷰 등록
	@RequestMapping(value = "project/review_form", method = RequestMethod.POST)
	public String review(BoardsVO boardsVO, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + boardsVO);
		int r = projectService.review(boardsVO);

		if (r > 0) {
			rttr.addFlashAttribute("msg", "추가에 성공하였습니다."); // 세션저장
		}
		return "redirect:mypage";
	}
	
	
	
	
	//리뷰 구분선
	// -------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------
	
	
	//리뷰작성 처리
	@RequestMapping(value="project/review", method = RequestMethod.GET)
	public String review(@RequestParam("product_id") String product_id, Model model, HttpSession session) {
		logger.info("리뷰작성 화면");
		//session에 담겨있는 아이디 값 리뷰 데이터로 넘겨주기 위한 처리
		Map<String, Object> user = (Map) session.getAttribute("user");
		String userid = (String) user.get("user_id");
		
//		String user_id = "yoonho";
		model.addAttribute("user_id", userid);
			
		ProductVO vo = projectService.productDetail(product_id);
		model.addAttribute("product", vo);
			
		List<String> file_name = projectService.fileSelect(product_id);
			
		//첫번째 사진만 가져옴
		model.addAttribute("file_name", file_name.get(0));
			
		return "review";
	}
	
	@RequestMapping(value="project/review", method = RequestMethod.POST)
	public String review(BoardsVO boardsVO, 
			@RequestParam("files") List<MultipartFile> files,
			HttpServletRequest request,RedirectAttributes rttr, HttpSession session,
		HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("리뷰내용"+ boardsVO);
		
		//보드 테이블의 마지막 튜플 번호 조회
		int boards_no_last = projectService.boardsNoLast();
		
		int r = projectService.review(boardsVO);
		
		//다중 파일 저장
		String imagePath = "/C:\\uploads/";
		
		for (MultipartFile file : files) {
			String uuid = UUID.randomUUID().toString();
			String filename = uuid + "_" + file.getOriginalFilename();
			
			File dest = new File(imagePath + filename);
			file.transferTo(dest);
			
			FileVO fileVO = new FileVO();
			fileVO.setFile_name(filename);
			fileVO.setFile_path("/C:\\uploads/" + filename);
			fileVO.setFile_connection_id(Integer.toString(boards_no_last + 1));
			
			projectService.fileUpload(fileVO);
		}
				
		if(r>0) {
			rttr.addFlashAttribute("msg","완료");
		}
		return "home";
		
	}
	
	
	
	
	
	
	
	
	

	@RequestMapping(value = "project/address_select", method = RequestMethod.GET)
	public ModelAndView address_select(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");

		ModelAndView mav = new ModelAndView();

		int totalRecord = projectService.address_totalRecord(user_id);
		List<AddressVO> list = projectService.listAddress(user_id);
		mav.addObject("list", list);
		mav.addObject("totalRecord", totalRecord);
		mav.setViewName("address_select");
		return mav;
	}

	@RequestMapping(value = "project/cart", method = RequestMethod.GET)
	public ModelAndView cart(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");

		int totalRecord = projectService.cart_totalRecord(user_id);
		List<CartVO> list = projectService.listCart(user_id);
		
		updateUserGrade(user_id);
		GradeVO grade = projectService.gradeDetail(user_id);
		
		mav.addObject("grade", grade);
		mav.addObject("list", list);
		mav.addObject("totalRecord", totalRecord);
		mav.setViewName("cart");
		
		return mav;
	}

	@RequestMapping(value = "project/cart_register", method = RequestMethod.GET)
	public String cartRegister(@RequestParam("product_id") String product_id, @RequestParam("amount") int amount,
			HttpServletRequest request, RedirectAttributes rttr, HttpSession session, HttpServletResponse response,
			Model model) throws Exception {
		request.setCharacterEncoding("UTF-8");

		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");

		ProductVO productVO = projectService.productDetail(product_id);
		String product_name = (String) productVO.getProduct_name();

		int s = projectService.findCart(product_id);
		if (s == 0) {
			int r = projectService.cartRegister(user_id, product_id, product_name, amount);
			ScriptUtils.alertAndMovePage(response, "제품을 장바구니에 담았습니다.", "product");
		} else {
			ScriptUtils.alertAndMovePage(response, "장바구니에 이미 동일한 제품이 있습니다.", "product");
		}

		return "redirect:product";
		
	}

//	@ResponseBody
//	@RequestMapping(value = "/project/cart_update", method = RequestMethod.POST)
//	public String cartUpdate(CartVO cartVO, HttpServletRequest request, RedirectAttributes rttr, HttpSession session,
//			HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("UTF-8");
//
//		Map<String, Object> user = (Map) session.getAttribute("user");
//		String user_id = (String) user.get("user_id");
//
//		int r = projectService.cartUpdate(user_id, cartVO);
//
//		return "redirect:cart2";
//	}

	
	
	@RequestMapping(value = "project/cartRecord", method = RequestMethod.GET)
	public ResponseEntity<Integer> getCartRecord(HttpSession session) {
		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");
		
		if (user_id == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 로그인되지 않은 경우
        }

		int itemCount = projectService.cart_totalRecord(user_id);
		
        return ResponseEntity.ok(itemCount);
	}
	
	@RequestMapping(value = "project/cart_delete", method = RequestMethod.GET)
	public String cartDelete(@RequestParam("product_id") String product_id, RedirectAttributes rttr, HttpSession session,
			HttpServletResponse response)  throws Exception {
		
		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");
		
		int r = projectService.cartDelete(user_id, product_id);

		ScriptUtils.alertAndMovePage(response, "제품을 장바구니에서 삭제했습니다.", "cart");
		
		return "redirect:cart";
	}
	
	
	
	@RequestMapping(value = "project/buyCancel", method = RequestMethod.GET)
	public String buyCancel(@RequestParam("buydetail_no") int buydetail_no, RedirectAttributes rttr, HttpSession session) {
		
		Map<String, Object> user = (Map) session.getAttribute("user");
		String user_id = (String) user.get("user_id");
		
		BuydetailVO buydetailVO = projectService.buydetailDetail(buydetail_no);
		int buy_no = 0;
		buy_no = buydetailVO.getBuydetail_buyno();
		
		BuyVO buyVO = projectService.buyDetail(buy_no);
		
		int amount = buydetailVO.getBuydetail_amount();
		String product_id = buydetailVO.getBuydetail_productid();
		
		int price = projectService.findProductPrice(product_id);
		int buydetailPrice = amount * price;
		
		int s = projectService.cancelUpdateProduct(product_id, amount);
		int v = projectService.deleteBuydetail(buydetail_no);
		int r = projectService.cancelUpdateBuy(buy_no, buydetailPrice);
		
		BuyVO buyVO2 = projectService.buyDetail(buy_no);
		int buy_amount = buyVO2.getBuy_amount();
		
		if (buy_amount == 0) {
			int t = projectService.deleteBuy(buy_no);
		}
		
		return "redirect:mypage";
	}
	
	
	
	
	
	//조회된 제품의 이미지 조회
	public List<FileVO> listSelect(List<ProductVO> list) {

		//리스트에 뜬 제품 아이디 모두 조회
		String[] productno = new String[100];
		for(int i=0; i<list.size(); i++) {
			ProductVO productVO = list.get(i);
			productno[i] = productVO.getProduct_id();
		}
		
		//제품 이미지중 첫번째 이미지 조회
		List<FileVO> imageList = projectService.listFileSelect(productno);
		
		return imageList;
	}
	
	//키워드 검색
	@RequestMapping(value="product/list", method=RequestMethod.GET)
	public String ProductSearch(@RequestParam("keyword") String keyword, HttpServletRequest request, Model model) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		//검색어 가지고 리스트 검색
		List<ProductVO> list = projectService.productSearch(keyword);
		model.addAttribute("list", list);
		model.addAttribute("imageList", listSelect(list));
				
		return "product_list";
	}
	
	//카테고리 검색
	@RequestMapping(value="product/list/category", method=RequestMethod.GET)
	public String categorySearch(@RequestParam("category") int category, HttpServletRequest request, Model model) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		Map<String, Object> codeMap = new HashMap<>();
		codeMap.put("category", category);
				
		//카테고리 검색
		List<ProductVO> list = projectService.categorySearch(codeMap);
		model.addAttribute("list", list);
		model.addAttribute("imageList", listSelect(list));            
		
		return "product_list";
	}
	
	//정렬 검색
	@RequestMapping(value="product/list/order", method=RequestMethod.GET)
	public String orderSearch(@RequestParam("code") int code, HttpServletRequest request, Model model) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		Map<String, Object> codeMap = new HashMap<>();
		codeMap.put("code", code);
		
		//정렬 검색
		List<ProductVO> list = projectService.orderSearch(codeMap);
		model.addAttribute("list", list);
		model.addAttribute("imageList", listSelect(list));
		
		return "product_list";
	}
	
	
	
	
	
	
	
	
	
}
