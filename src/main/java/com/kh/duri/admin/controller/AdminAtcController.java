package com.kh.duri.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.duri.Nanummember.model.vo.PageInfo;
import com.kh.duri.admin.model.exception.ListException;
import com.kh.duri.admin.model.service.adminAtcService;
import com.kh.duri.admin.model.vo.Pagination;
import com.kh.duri.admin.model.vo.RefundList;
import com.kh.duri.admin.model.vo.adminFundingList;
import com.kh.duri.admin.model.vo.adminMember;
import com.kh.duri.admin.model.vo.adminQnA;
import com.kh.duri.happymember.model.exception.MypageException;
import com.kh.duri.happymember.model.vo.Funding;
import com.kh.duri.member.model.vo.Member;



@Controller
public class AdminAtcController {
	@Autowired
	private adminAtcService aas;




	//크라우드펀딩 승인목록
	@RequestMapping("adminCrowd.ad")
	public String adminCrowdList(Model model1, Model model2) {
		
		try {
			List<adminFundingList>adminMoneyFundingList = aas.adminMoneyCrowdList();
			List<adminFundingList>adminGoodsFundingList = aas.adminGoodsFundingList();
			
			model1.addAttribute("adminMoneyFundingList", adminMoneyFundingList);
			model2.addAttribute("adminGoodsFundingList", adminGoodsFundingList);
			
			return "admin/adminCrowd";
			
			}catch(ListException e) {
				model1.addAttribute("msg", e.getMessage());
				model2.addAttribute("msg", e.getMessage());
				
				return "admin/adminCrowd";
			}
		
	}
	//크라우드 금액 승인글 상세페이지
	@RequestMapping("adminCrowdDetailMoney.ad")
	public String adminCrowdDetailMoney(HttpServletRequest request, HttpServletResponse response,Model model1,Model model2,adminMember m, adminFundingList f) {
		
		int fundingnum = Integer.parseInt(request.getParameter("fundingnum"));
		int membernum = Integer.parseInt(request.getParameter("membernum"));
		
		
		
		m.setMno(membernum);
		f.setfNo(fundingnum);
		
		adminMember CrowdMemInfo;
		adminFundingList CrowdFundInfo;
		
		try {
			CrowdMemInfo = aas.CrowdMemInfoDetail(m);
			CrowdFundInfo = aas.CrowdFundInfoDetail(f);
			
			model1.addAttribute("CrowdMemInfo", CrowdMemInfo);
			model2.addAttribute("CrowdFundInfo", CrowdFundInfo);
			
			return "admin/adminCrowdDetailMoney";
			
		} catch (ListException e) {

			model1.addAttribute("msg", e.getMessage());
			model2.addAttribute("msg", e.getMessage());
			
			return "admin/adminCrowdDetailMoney";
		}
		
		
	}
	//크라우드 물품 승인글 상세페이지
	@RequestMapping("adminCrowdDetailGoods.ad")
	public String adminCrowdDetailGoods(HttpServletRequest request, HttpServletResponse response,Model model1,Model model2,adminMember m, adminFundingList f) {
		
		int fundingnum = Integer.parseInt(request.getParameter("fundingnum"));
		int membernum = Integer.parseInt(request.getParameter("membernum"));
		
		
		
		m.setMno(membernum);
		f.setfNo(fundingnum);
		
		adminMember CrowdMemGoodsInfo;
		List<adminFundingList> CrowdFundGoodsInfo;
		
		try {
			CrowdMemGoodsInfo = aas.CrowdMemInfoDetail(m);
			CrowdFundGoodsInfo = aas.CrowdFundGoodsInfo(f);
			
			model1.addAttribute("CrowdMemGoodsInfo", CrowdMemGoodsInfo);
			model2.addAttribute("CrowdFundGoodsInfo", CrowdFundGoodsInfo);
			
			return "admin/adminCrowdDetailGoods";
			
		} catch (ListException e) {
			
			model1.addAttribute("msg", e.getMessage());
			model2.addAttribute("msg", e.getMessage());
			
			return "admin/adminCrowdDetailGoods";
		}
		
		
	}
	
	
	//크라우드 펀딩 반려 버튼
	@RequestMapping("adminCrowdDeny.ad")
	public String adminCrowdDeny(HttpServletRequest request, HttpServletResponse response,adminFundingList af) {
		
		int funum = Integer.parseInt(request.getParameter("funum"));
		String term = request.getParameter("term");
		af.setfNo(funum);
		af.setfLeftDay(term);
		
			int result = aas.adminCrowdDeny(af);
			return "redirect:adminCrowd.ad";
			
		
			
		
		
	}
	//크라우드 펀딩 승인 버튼
	@RequestMapping("adminCrowdApprove.ad")
	public String adminCrowdApprove(HttpServletRequest request, HttpServletResponse response,adminFundingList af) {
		int funum = Integer.parseInt(request.getParameter("funum"));
		String term = request.getParameter("term");
		
		af.setfNo(funum);
		af.setfLeftDay(term);
		
		
			int result = aas.adminCrowdApprove(af);
			return "redirect:adminCrowd.ad";
			
			
		
	}
	
	//관리자 메인페이지
	@SuppressWarnings("rawtypes")
	@RequestMapping("adminMain.ad")
	public String adminMain(Model model) {
		
		List<HashMap<String,String>> barChartList;
		barChartList = aas.getBarChartList();
		
		model.addAttribute("barChartList", barChartList);
		
		
		
		
		List<adminFundingList> CrowdFundGoodsInfo;
		return "admin/adminMain";
	}
	
	//관리자 행복두리 Q&A 목록
	@RequestMapping("adminQnA.ad")
	public String adminQnAList(Model model) {  
		
		try {
			List<adminQnA>adminQnAList = aas.adminQnAList();			
			model.addAttribute("adminQnAList", adminQnAList);		
			return "admin/adminQnA";			
			}catch(ListException e) {
				model.addAttribute("msg", e.getMessage());				
				return "admin/adminQnA";
			}		
	}
	//관리자 행복두리 Q&A상세보기
	@RequestMapping("adminQnADetail.ad")
	public String adminQnADetail(HttpServletRequest request, HttpServletResponse response,Model model,adminQnA q) {		
		int Mnonum = Integer.parseInt(request.getParameter("Mnonum"));
		int Qnanum = Integer.parseInt(request.getParameter("Qnanum"));
		q.setQ_Mno(Mnonum);
		q.setqNo(Qnanum);		
		adminQnA adminQnADetail;		
		try {
			adminQnADetail = aas.adminQnADetail(q);			
			model.addAttribute("adminQnADetail", adminQnADetail);
			return "admin/adminQnADetail";			
		} catch (ListException e) {			
			model.addAttribute("msg", e.getMessage());
			return "admin/adminQnADetail";
		}
	}	
	//관리자 나눔두리 Q&A 목록
		@RequestMapping("adminNanumQnA.ad")
		public String adminNanumQnAList(Model model) {  			
			try {
				List<adminQnA>adminNanumQnAList = aas.adminNanumQnAList();			
				model.addAttribute("adminNanumQnAList", adminNanumQnAList);		
				return "admin/adminNanumQnA";			
				}catch(ListException e) {
					model.addAttribute("msg", e.getMessage());				
					return "admin/adminNanumQnA";
				}		
		}	
	//관리자 나눔두리 Q&A상세보기
	@RequestMapping("adminNanumQnADetail.ad")
	public String adminNanumQnADetail(HttpServletRequest request, HttpServletResponse response,Model model,adminQnA q) {		
		int Mnonum = Integer.parseInt(request.getParameter("Mnonum"));
		int Qnanum = Integer.parseInt(request.getParameter("Qnanum"));
		q.setQ_Mno(Mnonum);
		q.setqNo(Qnanum);		
		adminQnA adminNanumQnADetail;		
		try {
			adminNanumQnADetail = aas.adminNanumQnADetail(q);			
			model.addAttribute("adminNanumQnADetail", adminNanumQnADetail);
			return "admin/adminNanumQnADetail";			
		} catch (ListException e) {			
			model.addAttribute("msg", e.getMessage());
		return "admin/adminNanumQnADetail";
		}
	}
	
	//관리자 QnA 답변 달기 -> ajax를 이용하자! 
 	@RequestMapping("adminNanumReply.ad")
 	public @ResponseBody String adminNanumReply(@RequestParam int qNo, @RequestParam String qAnswer, HttpServletRequest request, HttpServletResponse response,Model model) {		
		adminQnA q = new adminQnA();
		q.setqAnswer(qAnswer);
		q.setqNo(qNo);		
		try {
			int result = aas.insertReply(q);
			return qAnswer;
		} catch (ListException e) {
			return "";
		}		
 	}
 	


	//Q&A 답변하기 버튼 
	@RequestMapping("adminAnswer.ad")
	public String adminAnswer(HttpServletRequest request, HttpServletResponse response,adminQnA q) {
		
		String answer = request.getParameter("answer");
		int num = Integer.parseInt(request.getParameter("num"));
		
		q.setqNo(num);
		q.setqAnswer(answer);
		
		
		
			int result = aas.adminAnswer(q);
			return "redirect:adminQnA.ad";
			
			
		
	}
	
	
	//관리자 환급페이지
	@RequestMapping("adminRefund.ad")
	public String adminRefundList(Model model) {
		
		try {
			
			return "admin/adminRefund";
			
			}catch(Exception e) {
				model.addAttribute("msg", e.getMessage());
				return "admin/adminRefund";
				
			}
		
	}
	//환불 목록 ajax
	@RequestMapping("adminRefund_ajax.ad")
	public @ResponseBody HashMap<String, Object> adminRefundList(@RequestParam int currentPage, RefundList r, Model model, HttpServletRequest request, HttpServletResponse response){
		
		String rstatus = request.getParameter("rstatus");
		r.setrStatus(rstatus);
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		try {
			
			int listCount = aas.selectRefundPageCount(r);
			
			PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
			
			List<RefundList> adminRefundList = aas.adminRefundList(r,pi);
			map.put("adminRefundList", adminRefundList);
			map.put("pi", pi);
		
			return map;
		} catch (Exception e){
			map.put("msg", e.getMessage());
			return map;
		}
			
		
	}
		
	
	
	

	
}
