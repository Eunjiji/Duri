package com.kh.duri.happymember.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.duri.happymember.model.vo.DirectFundHistory;
import com.kh.duri.Nanummember.model.vo.FundHistory;
import com.kh.duri.Nanummember.model.vo.Letter;
import com.kh.duri.happymember.model.exception.MypageException;
import com.kh.duri.happymember.model.vo.Attachment;
import com.kh.duri.happymember.model.vo.Delivery;
import com.kh.duri.happymember.model.vo.DeliveryDetail;
import com.kh.duri.happymember.model.vo.FundItemList;
import com.kh.duri.happymember.model.vo.Funding;
import com.kh.duri.happymember.model.vo.MyDonateItems;
import com.kh.duri.happymember.model.vo.Qna;
import com.kh.duri.member.model.exception.LoginException;
import com.kh.duri.member.model.vo.Member;
import com.kh.duri.payment.model.vo.PageInfo;

public interface HappymemberService {

	//마이페이지 - 보유물품 목록 불러오기 ajax
	//보유물품 리스트
	ArrayList<MyDonateItems> donateItemList(MyDonateItems mdi) throws MypageException;

	//후원물품 리스트
	ArrayList<FundItemList> selectfundItemList() throws MypageException;

	//배송받을 물품 선택 후 수량 변경하기
	int getDelivery(String[] itemNumArray, String[] itemAmountArray, String mno, String address) throws MypageException;
	
	//정기후원 목록 개수 조회
	int selectLongDonateCount(Member m) throws MypageException;

	//정기후원 목록 조회
	List<DirectFundHistory> selectDirectFundList(Member m, PageInfo pi) throws MypageException;

	//배송현황 목록 개수 조회
	int selectDeliveryListCount(Member m) throws MypageException;

	//배송현황 목록 조회
	List<DeliveryDetail> selectDeliveryList(Member m, PageInfo pi) throws MypageException;

	//자기소개 수정 전 현재 자기소개 내용 불러오기(세션으로 가져오면 안되서..ㅠㅠ)
	Member searchMprMprNew(int mno) throws MypageException;
	
	//자기소개 수정
	int updateIntroduce(Member oldLoginUser) throws MypageException;

	//물품후원 목록 개수 조회
	int selectItemDonateCount(Member m) throws MypageException;

	//물품후원 목록 조회
	List<Funding> selectItemDonateList(int mno, PageInfo pi) throws MypageException;

	//금액후원 목록 개수 조회
	int selectMoneyDonateCount(Member m) throws MypageException;

	//금액후원 목록 조회
	List<Funding> selectMoneyDonateList(Member m, PageInfo pi) throws MypageException;

	//Q&A 목록 개수 조회
	int selectQnaListCount(Member m) throws MypageException;

	//Q&A 목록 조회
	List<Qna> selectQnaList(Member m, PageInfo pi) throws MypageException;

	//Q&A 작성
	int insertQna(Qna q) throws MypageException;
	
	//Q&A 상세보기
	List<Qna> selectQnaDetail(Qna q) throws MypageException;
	
	///감사편지 보낼 정기후원자 닉네임 뽑기
	List<Member> selectNanumNick(Member m) throws MypageException;

	//감사편지 보내기
	int insertThankyouLetter(Letter l) throws MypageException;

	//증빙서류 승인일 조회
	Attachment selectAdate(Member member) throws LoginException;

	//증빙서류 현황 조회
	Attachment selectProofDocument(Member m) throws MypageException;

	//증빙서류 업로드(파일 이름 변경)
	int updateProofDocumentUpload3(Member m);
	int updateProofDocumentUpload24(Member m);

	//크라운드 참여한 나눔두리 찾아오기
	List<FundHistory> selectNanumMno(FundHistory fs) throws MypageException;

	//펀딩 구분 찾아오기
	Funding selectFtype(Funding f) throws MypageException;

	//크라우드 펀딩 단체 감사편지 보내기
	int insertCrowdfundingLetter(Letter l) throws MypageException;

	//단체 감사편지 중복체크
	int letterCheck(Letter l) throws MypageException;

	

	
	

	


	




	


	


}
