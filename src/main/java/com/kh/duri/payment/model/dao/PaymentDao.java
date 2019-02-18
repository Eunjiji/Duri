package com.kh.duri.payment.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.duri.Nanummember.model.vo.FundHistory;
import com.kh.duri.board.model.vo.Board;
import com.kh.duri.member.model.vo.Member;
import com.kh.duri.payment.model.exception.DirectFundException;
import com.kh.duri.payment.model.exception.FundingException;
import com.kh.duri.payment.model.exception.PaymentException;
import com.kh.duri.payment.model.exception.PointHistoryException;
import com.kh.duri.payment.model.exception.ReceiptException;
import com.kh.duri.payment.model.exception.RefundException;
import com.kh.duri.payment.model.vo.DirectFundHist;
import com.kh.duri.payment.model.vo.DonateReceipt;
import com.kh.duri.payment.model.vo.PageInfo;
import com.kh.duri.payment.model.vo.Payment;
import com.kh.duri.payment.model.vo.Point;
import com.kh.duri.payment.model.vo.Refund;

public interface PaymentDao {


	// 나눔두리 - 포인트 히스토리 갯수 세기
	int selectPointListCount(SqlSessionTemplate sqlSession, Member m) throws PointHistoryException;
	int selectSearchPointListCount(SqlSessionTemplate sqlSession, Member m) throws PointHistoryException;

	// 나눔두리 - 포인트 히스토리 가져오기 
	List<Point> selectPointHistory(SqlSessionTemplate sqlSession, Member m, PageInfo pi) throws PointHistoryException;

	// 나눔두리 - 기간별 포인트 히스토리 검색 
	List<Point> searchPointHistory(SqlSessionTemplate sqlSession, Member m, PageInfo pi) throws PointHistoryException;
	
	// 나눔두리 - 기부금영수증 발급내역 갯수 세기
	int selectReceiptListCount(SqlSessionTemplate sqlSession, Member m) throws ReceiptException;
	int selectSearchReceiptListCount(SqlSessionTemplate sqlSession, Member m) throws ReceiptException;
	
	// 나눔두리 - 기부금영수증 발급내역 보기
	List<DonateReceipt> selectDonateReceiptHistory(SqlSessionTemplate sqlSession, Member m, PageInfo pi) throws ReceiptException;
	
	// 나눔두리 - 기간별 기부금영수증 발급내역 검색
	List<DonateReceipt> searchDonateReceipt(SqlSessionTemplate sqlSession, Member m, PageInfo pi) throws ReceiptException;

	// 나눔,행복두리 - 환급목록 갯수 세기
	int selectListCount(SqlSessionTemplate sqlSession, Member m) throws RefundException;

	// 나눔,행복두리 - 환급목록 가져오기
	List<Refund> selectRefundList(SqlSessionTemplate sqlSession, Member m, PageInfo pi) throws RefundException;

	// 나눔,행복두리 - 포인트 환급 요청하기
	Member insertRefund(SqlSessionTemplate sqlSession, Refund r, Member m) throws RefundException;
	
	// 나눔두리 - 후원포인트 충전하기
	Member insertPayment(SqlSessionTemplate sqlSession, Member m, Payment py) throws PaymentException;
	
	// 나눔두리 - 정기후원 정보 입력하기 
	int insertDirectFundHist(SqlSessionTemplate sqlSession, DirectFundHist dh) throws DirectFundException;

	// DirectFundHistory seq번호 알아내기
	int selectDirectFundCurVal(SqlSessionTemplate sqlSession) throws DirectFundException;
	
	// 나눔두리 - 정기후원 상세내역 입력하기 (1차)
	int insertDirectFundDetail(SqlSessionTemplate sqlSession, DirectFundHist dh) throws DirectFundException;
	
	// DirectFundHistoryDetail seq번호 알아내기
	int selectDirectFundDetailCurVal(SqlSessionTemplate sqlSession) throws DirectFundException;
	
	// 나눔두리 - Point이력에 정기후원 정보 입력하기
	int insertPointDirect(SqlSessionTemplate sqlSession, DirectFundHist dh) throws DirectFundException;
	
	// 행복두리 - Point 업뎃하기
	int updateDirecthPoint(SqlSessionTemplate sqlSession, DirectFundHist dh) throws DirectFundException;

	// 나눔두리 - 다음 정기결제를 위한 merchant_id 값 가져오기
	DirectFundHist selectDirectFundId(SqlSessionTemplate sqlSession, DirectFundHist dh) throws DirectFundException;
	
	// 금액 후원 결제페이지 - 펀딩정보 select
	Board selectFundMoney(SqlSessionTemplate sqlSession, Board b) throws FundingException;
	
	// 금액 후원 진행 - 펀딩내역 insert
	// 펀딩내역 insert
	int insertFundMoneyHistory(SqlSessionTemplate sqlSession, FundHistory fh) throws FundingException;
	int insertDonateReceipt(SqlSessionTemplate sqlSession, FundHistory fh) throws FundingException;
	int updateMoneyhPoint(SqlSessionTemplate sqlSession, Member m) throws FundingException;
	int updateMoneynPoint(SqlSessionTemplate sqlSession, Member m) throws FundingException;
	Member selectLoginnMember(SqlSessionTemplate sqlSession, Member m) throws FundingException;
	int insertFundMoneynPoint(SqlSessionTemplate sqlSession, FundHistory fh) throws FundingException;
	int insertFundMoneyhPoint(SqlSessionTemplate sqlSession, FundHistory fh) throws FundingException;
	int selectFundHistCurVal(SqlSessionTemplate sqlSession) throws FundingException;
		
	
	


}
