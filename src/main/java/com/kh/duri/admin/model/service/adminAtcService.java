package com.kh.duri.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.duri.Nanummember.model.vo.PageInfo;
import com.kh.duri.admin.model.exception.ListException;
import com.kh.duri.admin.model.vo.Notice;
import com.kh.duri.admin.model.vo.RefundList;
import com.kh.duri.admin.model.vo.adminFundingList;
import com.kh.duri.admin.model.vo.adminMember;
import com.kh.duri.admin.model.vo.adminQnA;

public interface adminAtcService {

	//크라우드 금액 펀딩 승인목록
	List<adminFundingList> adminMoneyCrowdList() throws ListException;
	//크라우드 물품 펀딩 승인 목록
	List<adminFundingList> adminGoodsFundingList() throws ListException;
	//크라우드 펀딩 상세페이지 -회원정보
	adminMember CrowdMemInfoDetail(adminMember m)throws ListException;
	//크라우드 펀딩 상세페이지 -펀딩정보(금액)
	adminFundingList CrowdFundInfoDetail(adminFundingList f)throws ListException;
	//크라우드 펀딩 상세페이지 - 펀딩정보(물품)
	List<adminFundingList> CrowdFundGoodsInfo(adminFundingList f)throws ListException;
	//관리자 행복두리 Q&A 목록
	List<adminQnA> adminQnAList()throws ListException;
	//관리자 행복두리 Q&A 상세보기
	adminQnA adminQnADetail(adminQnA q)throws ListException;

	//관리자 나눔두리 Q&A 목록
	List<adminQnA> adminNanumQnAList() throws ListException;
	//관리자 나눔두리 Q&A 상세보기
	adminQnA adminNanumQnADetail(adminQnA q)throws ListException;
	//관리자 나눔두리 Q&A 댓글달기
	int insertReply(adminQnA q)throws ListException;
	

	//Q&A 답변하기 버튼
	int adminAnswer(adminQnA q);
	//크라우드 펀딩 반려 버튼
	int adminCrowdDeny(adminFundingList af);
	//크라우드 펀딩 승인 버튼
	int adminCrowdApprove(adminFundingList af);
	
	//통계 페이지 - bar chart 데이터
	List<HashMap<String, String>> getBarChartList();
	
	//관리자 환급하기 목록
	List<RefundList> adminRefundList(RefundList r,PageInfo pi)throws ListException;
	//관리자 환급목록 갯수
	int selectRefundPageCount(RefundList r);
	//환불하기 버튼 ajax
	int adminRefundButton(RefundList rfL);
	//행복두리 알림보내기 ajax
	List<Notice> adminAlarm(Notice n);
	//행복두리 알림확인완료 ajax
	int adminAlarmBtn(Notice n);
	
	//Today
	int TodayNewNanum();
	int TodayNewHappy();
	int TodayNewDirect();
	int TodayNewFund();
	int TodayNewDirectMoney();
	int TodayNewItem();
	int TodayNewFundMoney();
	int TodayNewPoint();
	
	
	
	//Total
	int TotalNanum();
	int TotalHappy();
	int TotalDirect();
	int Totalfund();
	int Totalitem();
	int TotalitemMoney();
	int TotalDirectConn();
	int TotalFundUpload();
	
	
	


}
