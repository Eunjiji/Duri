package com.kh.duri.payment.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.duri.Nanummember.model.vo.FundHistory;
import com.kh.duri.board.model.vo.Board;
import com.kh.duri.common.CommonUtils;
import com.kh.duri.member.model.vo.Member;
import com.kh.duri.payment.model.exception.DirectFundException;
import com.kh.duri.payment.model.exception.FundingException;
import com.kh.duri.payment.model.exception.PaymentException;
import com.kh.duri.payment.model.exception.PointHistoryException;
import com.kh.duri.payment.model.exception.ReceiptException;
import com.kh.duri.payment.model.exception.RefundException;
import com.kh.duri.payment.model.service.PaymentService;
import com.kh.duri.payment.model.vo.DirectFundHist;
import com.kh.duri.payment.model.vo.DonateReceipt;
import com.kh.duri.payment.model.vo.PageInfo;
import com.kh.duri.payment.model.vo.Pagination;
import com.kh.duri.payment.model.vo.Payment;
import com.kh.duri.payment.model.vo.Point;
import com.kh.duri.payment.model.vo.Refund;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.AgainPaymentData;
import com.siot.IamportRestClient.request.ScheduleData;
import com.siot.IamportRestClient.request.ScheduleEntry;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Schedule;

@Controller
public class PaymentController {
	@Autowired
	private PaymentService ps;

	// 나눔두리 포인트 히스토리 페이지
	@RequestMapping("pointHistory.pm")
	public String pointHistory(Model model, HttpServletRequest request, HttpServletResponse response) {
		Member m = (Member) request.getSession().getAttribute("loginUser2");
		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		try {
			int listCount = ps.selectPointListCount(m);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			
			List<Point> phList = ps.selectPointHistory(m, pi);
			
			/*for(Point i : phList) {
				System.out.println("내역 : "+ i);
			}*/
			model.addAttribute("phList", phList);
			model.addAttribute("pi", pi);
			return "payment/point_history";
		} catch (PointHistoryException e) {
			model.addAttribute("msg", e.getMessage());
			return "payment/point_history";
		}
		
	}
	// 나눔두리 포인트 히스토리 기간별 검색 조회
	@RequestMapping("searchPointHistory.pm")
	public String searchPointHistory(HttpServletRequest request, HttpServletResponse response, Model model) {
		Member m = (Member) request.getSession().getAttribute("loginUser2");
		String sm = request.getParameter("startMonth")+"-01";
		String em = request.getParameter("endMonth");
		//System.out.println("어허 " + em.substring(5, 7));
		if(em.substring(5, 7).equals("04") ||em.substring(5, 7).equals("06")||em.substring(5, 7).equals("09")||em.substring(5, 7).equals("11")) {
			em = em + "-30";
		}else if (em.substring(5, 7).equals("02")) {
			em = em + "-28";
		}else {
			em = em + "-31";
		}
		
		//System.out.println("startMonth : " + sm + "/ endMonth : " + em);
		m.setmPhone(sm); //startMonth
		m.setmAddress(em); // endMonth
		
		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Point> phList;
		try {
			int listCount = ps.selectSearchPointListCount(m);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			System.out.println("Controller : endPage :"+ pi.getEndPage());
			phList = ps.searchPointHistory(m, pi);
			
			/*for(DonateReceipt i : dr) {
				System.out.println("내역 : "+ i);
			}*/
			model.addAttribute("phList", phList);
			model.addAttribute("pi", pi);
			return "payment/point_history";
			
		} catch (PointHistoryException e) {
			model.addAttribute("msg", e.getMessage());
			return "payment/point_history";
		}
	   
	}
		
	// 나눔두리 기부금 영수증 발급내역 페이지
	@RequestMapping("donateReceipt.pm")
	public String donateReceipt(HttpServletRequest request, HttpServletResponse response, Model model) {
		Member m = (Member) request.getSession().getAttribute("loginUser2");
		//System.out.println("세션에 저장된 회원번호 : " + m.getMno());
		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<DonateReceipt> drList;
		try {
			int listCount = ps.selectReceiptListCount(m);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			
			drList = ps.selectDonateReceiptHistory(m, pi);
			
			/*for(DonateReceipt i : dr) {
				System.out.println("내역 : "+ i);
			}*/
			model.addAttribute("drList", drList);
			model.addAttribute("pi", pi);
			return "payment/donation_receipt";
			
		} catch (ReceiptException e) {
			model.addAttribute("msg", e.getMessage());
			return "payment/donation_receipt";
		}
	   
	}
	
	// 나눔두리 기부금 영수증 발급내역 기간별 검색 조회
	@RequestMapping("searchDonateReceipt.pm")
	public String searchDonateReceipt(HttpServletRequest request, HttpServletResponse response, Model model) {
		Member m = (Member) request.getSession().getAttribute("loginUser2");
		String sm = request.getParameter("startMonth")+"-01";
		String em = request.getParameter("endMonth");
		System.out.println("어허 " + em.substring(5, 7));
		if(em.substring(5, 7).equals("04") ||em.substring(5, 7).equals("06")||em.substring(5, 7).equals("09")||em.substring(5, 7).equals("11")) {
			em = em + "-30";
		}else if (em.substring(5, 7).equals("02")) {
			em = em + "-28";
		}else {
			em = em + "-31";
		}
		
		//System.out.println("startMonth : " + sm + "/ endMonth : " + em);
		m.setmPhone(sm); //startMonth
		m.setmAddress(em); // endMonth
		
		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<DonateReceipt> drList;
		try {
			int listCount = ps.selectSearchReceiptListCount(m);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			
			drList = ps.searchDonateReceipt(m, pi);
			
			/*for(DonateReceipt i : dr) {
				System.out.println("내역 : "+ i);
			}*/
			model.addAttribute("drList", drList);
			model.addAttribute("pi", pi);
			return "payment/donation_receipt";
			
		} catch (ReceiptException e) {
			model.addAttribute("msg", e.getMessage());
			return "payment/donation_receipt";
		}
	   
	}
	
	// 행복두리 포인트 환급목록 페이지
	@RequestMapping("pointReturnListHappy.pm")
	public String pointReturnHappyList(HttpServletRequest request, HttpServletResponse response, Model model) {
		Member m = (Member) request.getSession().getAttribute("loginUser");
		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		try {
			int listCount = ps.selectListCount(m);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			
			List<Refund> rfList	= ps.selectRefundList(m, pi);
			
			/*for(Refund i : rfList) {
				System.out.println("내역 : "+ i);
			}*/

			model.addAttribute("rfList", rfList);
			model.addAttribute("pi", pi);
			return "payment/point_returnList_happy";
		} catch (RefundException e) {
			model.addAttribute("msg", e.getMessage());
			return "payment/point_returnList_happy";
		}
		
	}
	
	// 행복두리 포인트 환급페이지
	@RequestMapping("pointReturnHappy.pm")
	public String pointReturnHappy() {
	    return "payment/point_return_happy";
	}
	
	// 나눔,행복두리 - 포인트 환급 요청하기
	@RequestMapping("execPointReturnHappy.pm")
	public String execPointReturnHappy(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		Member m = null;
		String memberType = request.getParameter("memberType");
		System.out.println("memberType :" + memberType);
		if(memberType.equals("n")) {
			m = (Member) request.getSession().getAttribute("loginUser2");
		}else if(memberType.equals("h")){
			m  = (Member) request.getSession().getAttribute("loginUser");
		}else {
			return "redirect:pointReturnListHappy.pm";
		}
		System.out.println("m :" + m);
		String rValue = request.getParameter("returnValue");
		String rBank = request.getParameter("Bank");
		String[] rAccountArr = request.getParameterValues("returnAccount");
		String rName = request.getParameter("accountName");
		String rAccount = "";
		for(int i = 0; i < rAccountArr.length; i++) {
			
			if(i == 0) {
				rAccount += rAccountArr[i];
			}else {
				rAccount += "-"+rAccountArr[i];
			}
		}
		
		Refund r = new Refund();
		r.setrValue(rValue);
		r.setR_mNo(m.getMno());
		r.setrBank(rBank);
		r.setrAccount(rAccount);
		r.setrName(rName);
		
		System.out.println("controller : "+ r);
		try {
			Member loginUser = ps.insertRefund(r, m);
			
			// 포인트 변경된 회원 정보 세션에 업데이트 
			if(memberType.equals("n")) { // 나눔두리 
				session.setAttribute("loginUser2", loginUser);
				return "redirect:pointReturnList.pm";
			}else { // 행복두리
				session.setAttribute("loginUser", loginUser);
				return "redirect:pointReturnListHappy.pm";
			}
		} catch (RefundException e) {
			model.addAttribute("msg", e.getMessage());
			return "redirect:pointReturnListHappy.pm";
		}
		
	}
	
	// 나눔두리 포인트 환급목록 페이지
	@RequestMapping("pointReturnList.pm")
	public String pointReturnList(HttpServletRequest request, HttpServletResponse response, Model model) {
		Member m = (Member) request.getSession().getAttribute("loginUser2");
		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		try {
			int listCount = ps.selectListCount(m);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			
			List<Refund> rfList	= ps.selectRefundList(m, pi);
			
			/*for(Refund i : rfList) {
				System.out.println("내역 : "+ i);
			}*/

			model.addAttribute("rfList", rfList);
			model.addAttribute("pi", pi);
			return "payment/point_returnList";
		} catch (RefundException e) {
			model.addAttribute("msg", e.getMessage());
			return "payment/point_returnList";
		}
	    
	}
	
	// 나눔두리 포인트 환급페이지
	@RequestMapping("pointReturn.pm")
	public String pointReturn() {
	    return "payment/point_return";
	}
	
	// 나눔두리 포인트 충전페이지
	@RequestMapping("pointCharge.pm")
	public String pointCharge() {
	    return "payment/point_charge"; 
	}

	// 나눔두리 - 포인트 충전 결제하기
	@RequestMapping("pointChargePayment.pm")
	public String pointChargePayment(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		String pyPrice = request.getParameter("price");
		String pyCardNum = request.getParameter("apply");
		String py_mNo = request.getParameter("mno");
		String value = request.getParameter("value");
		
		System.out.println("pyPrice : " + pyPrice);
		System.out.println("pyCardNum : " + pyCardNum);
		System.out.println("py_mNo : " + py_mNo);
		System.out.println("value : " + value);
		
		if(pyCardNum.equals("")) {
			pyCardNum = "kakaopay";
		}
		
		Payment py = new Payment();
		py.setPy_mNo(Integer.parseInt(py_mNo));
		py.setPyPrice(value);
		py.setPyCardNum(pyCardNum);
		
		Member m = (Member) request.getSession().getAttribute("loginUser2");
		
		Member loginUser;
		try {
			loginUser = ps.insertPayment(m, py);
			
			session.setAttribute("loginUser2", loginUser);
			model.addAttribute("msg", "포인트 충전이 완료되었습니다.");
		    return "payment/pay_success"; 
		    
		} catch (PaymentException e) {
			model.addAttribute("msg", e.getMessage());
			return "payment/pay_success"; 
		}
		
		
	}
	
	// 정기후원 결제페이지
	@RequestMapping("directFund.pm")
	public String directFund(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		Member loginUser = (Member)session.getAttribute("loginUser2");
		Member takeMember = new Member();
		String mno_take = request.getParameter("mno_take");
		String mnickname_take = request.getParameter("mnickname_take");
		String maddress_take = request.getParameter("maddress_take");
		String valueType = request.getParameter("valueType");
		
		takeMember.setMno(Integer.parseInt(mno_take));
		takeMember.setmNickName(mnickname_take);
		takeMember.setmAddress(maddress_take);
		
		System.out.println("후원대상자 : "+ takeMember);
		System.out.println("후원자 번호 : "+ loginUser.getMno());
		System.out.println("후원타입: "+ valueType);
		
		model.addAttribute("valueType", valueType);
		model.addAttribute("takeMember", takeMember);
	    return "payment/pay_directFund";
	}
	
	@RequestMapping("directFundGetToken.pm")
	public @ResponseBody String directFundGetToken(@RequestParam String customer_uid, @RequestParam String imp_uid, 
			@RequestParam String merchant_uid, @RequestParam BigDecimal amount, HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("customer_uid : "+ customer_uid);
		//System.out.println("imp_uid : "+ imp_uid);
		
		IamportClient iam = new IamportClient("2128480452188810", "auDtdoRqy5eWYjryBzpvoByL60yEzqGJUjc8I3yg9Nd76EFIe5dCGMoNNXsmn85hsipamYqvLDDSijAw");
		try {
			AccessToken at = iam.getAuth().getResponse();
			System.out.println("token? : " + at.getToken());
			System.out.println("================= 정기 후원 결제 ==================");
			// 결제 요청하기 
			AgainPaymentData again_data = new AgainPaymentData(customer_uid, merchant_uid, amount);
			again_data.setName("정기후원 정기결제");

			IamportResponse<com.siot.IamportRestClient.response.Payment> payment_response = iam.againPayment(again_data);
			System.out.println("payment_response message: " + payment_response.getMessage());
			System.out.println("payment_response code: " + payment_response.getCode());
			
			
			if(payment_response.getCode() == 0) {
				if(payment_response.getResponse().getStatus().equals("paid")) {
					return "최초결제 완료되었습니다.";
				}else {
					return payment_response.getResponse().getFailReason();
				}
				//payment_response.getResponse().getFailReason()
			}else {
				return "카드사 요청에 실패하였습니다.";
			}
			
		} catch (IamportResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
		
		
	}
	
	@RequestMapping("subscribeDirectFund.pm")
	public @ResponseBody String subscribeDirectFund(@RequestParam String customer_uid, @RequestParam String imp_uid, 
			@RequestParam String merchant_uid, @RequestParam BigDecimal amount, @RequestParam String price, @RequestParam String giveMember, @RequestParam String takeMember, 
			@RequestParam String type, HttpServletRequest request, HttpServletResponse response) {
			
		System.out.println("merchant_uid : "+ merchant_uid);
		IamportClient iam = new IamportClient("2128480452188810", "auDtdoRqy5eWYjryBzpvoByL60yEzqGJUjc8I3yg9Nd76EFIe5dCGMoNNXsmn85hsipamYqvLDDSijAw");
		
		try {
			AccessToken at = iam.getAuth().getResponse();
			System.out.println("token? : " + at.getToken());
			
			// 결제 예약하기
			System.out.println("================= 정기 결제 예약 ==================");
			ScheduleData schedule_data = new ScheduleData(customer_uid);
			
			Date today = new Date();
			Date schedule_at = CommonUtils.getNextMonth(today);
			System.out.println("today : " + today);
			System.out.println("schedule_at : " + schedule_at);
			
			ScheduleEntry entry = new ScheduleEntry(merchant_uid, schedule_at, amount);
			schedule_data.addSchedule(entry);
			
			IamportResponse<List<Schedule>> list = iam.subscribeSchedule(schedule_data);
			//System.out.println("list message : " + list.getMessage());
			System.out.println("list code : " + list.getCode());
			
			// DirectFundHist 객체 생성
			DirectFundHist dh = new DirectFundHist();
			dh.setDh_mNo_give(Integer.parseInt(giveMember));
			dh.setDh_mNo_take(Integer.parseInt(takeMember));
			dh.setDhBilling(customer_uid);
			dh.setDhImpUid(imp_uid);
			dh.setDhValue(price);
			dh.setDhType(type);
			
			if(list.getCode() == 0) {
				//DB에 저장 
				int result = ps.insertDirectFundHist(dh);
				
				if(result > 0) {
					request.setAttribute("msg", "정기후원이 완료되었습니다.");
				    return "redirect:payment/pay_success"; 
				}else {
					return "정기후원 정보 DB저장 실패 ";
				}
				
			}else {
				return "카드사 요청에 실패하였습니다.";
			}
			
		} catch (IamportResponseException e) {
			e.printStackTrace();
			return "정기결제 예약 실패";
		} catch (IOException e) {
			e.printStackTrace();
			return "정기결제 예약 실패";
		}catch (DirectFundException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	// webHook - 예약된 결제가 완료되었을 때 요청되는 서버 (다음 결제 예약을 처리한다)
	@RequestMapping("directFundCallback.pm")
	public String directFundCallback(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		System.out.println("webhook 이얏호 ");
		String imp_uid = request.getParameter("imp_uid");
		String merchant_uid = request.getParameter("merchant_uid");
		String status = request.getParameter("status");
		
		System.out.println("imp_uid : "+ imp_uid);
		System.out.println("merchant_uid : "+ merchant_uid);
		System.out.println("status : "+ status);
		
		
		DirectFundHist dh = new DirectFundHist();
		dh.setDhImpUid(imp_uid);
		
		try {
			DirectFundHist resultDirectFund = new DirectFundHist();
			resultDirectFund = ps.selectDirectFundId(dh);
			
			if(resultDirectFund != null) {
				// 다음 결제 예약하기
				IamportClient iam = new IamportClient("2128480452188810", "auDtdoRqy5eWYjryBzpvoByL60yEzqGJUjc8I3yg9Nd76EFIe5dCGMoNNXsmn85hsipamYqvLDDSijAw");
				
				System.out.println("================= 정기 결제 예약 ==================");

				String customer_uid = resultDirectFund.getDhBilling();
				ScheduleData schedule_data = new ScheduleData(customer_uid);
				
				Date today = new Date();
				Date schedule_at = CommonUtils.getNextMonth(today);
				System.out.println("today : " + today);
				System.out.println("schedule_at : " + schedule_at);
				
				BigDecimal amount = new BigDecimal("100");
				String merchant_uid_new = "merchant_" + new Date().getTime()+"_sub";
				
				ScheduleEntry entry = new ScheduleEntry(merchant_uid_new, schedule_at, amount);
				schedule_data.addSchedule(entry);
				
				IamportResponse<List<Schedule>> list = iam.subscribeSchedule(schedule_data);
				System.out.println("list message : " + list.getMessage());
				System.out.println("list code : " + list.getCode());				
				
				resultDirectFund.setDhImpUid(imp_uid);
				
				if(list.getCode() == 0) {
					// 다음 회차 정기후원 내역 DB 저장하기 
					
					
				}
				
			}
		} catch (DirectFundException e) {
			e.printStackTrace();
		} catch (IamportResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	// 금액 후원 결제페이지 
	@RequestMapping("fundMoney.pm")
	public String fundMoney(@RequestParam String fno, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("fno : " + fno);
		
		Board f = new Board();
		f.setFno(Integer.parseInt(fno));
		
		Board resultBoard;
		try {
			resultBoard = ps.selectFundMoney(f);
			
			model.addAttribute("f" ,resultBoard);
			System.out.println("controlle page뿌리기 f : "+ f);
			return "payment/pay_fundMoney";
			
		} catch (FundingException e) {
			model.addAttribute("msg" ,e.getMessage());
			return "";
		}
	}
	
	// 금액 후원 진행 
	@RequestMapping("execfundMoney.pm")
	public String execfundMoney(@RequestParam String fno , HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		Member m = (Member)session.getAttribute("loginUser2");
		String check = request.getParameter("checkDonate");
		String fValue = request.getParameter("point");
		String fWriter = request.getParameter("fWriter");
		String ipin1;
		String ipin2;
		
		System.out.println("fno : " + fno);
		System.out.println("m : " + m);
		System.out.println("fValue : " + fValue);
		System.out.println("fWriter : " + fWriter);
		if(check.equals("1")) {
			ipin1 = request.getParameter("ipin1");
			ipin2 = request.getParameter("ipin2");
			System.out.println("ipin1 : " + ipin1);
			System.out.println("ipin2 : " + ipin2);
			
		}

		FundHistory fh = new FundHistory();
		fh.setFhFno(Integer.parseInt(fno));
		fh.setFhValue(Integer.parseInt(fValue));
		fh.setFhMnoGive(m.getMno());

		try {
			Member resultM = ps.insertFundMoneyHistory(fh, check, fWriter);
			
			session.setAttribute("loginUser2", resultM);
			model.addAttribute("msg", "펀딩이 완료되었습니다.");
		    return "payment/pay_success";
			
		} catch (FundingException e) {
			model.addAttribute("msg", e.getMessage());
		    return "payment/pay_success";
		}
		
	}
	
	// 물품 후원 결제페이지
	@RequestMapping("fundItem.pm")
	public String fundItem() {
	    return "payment/pay_fundItem";
	}


}
