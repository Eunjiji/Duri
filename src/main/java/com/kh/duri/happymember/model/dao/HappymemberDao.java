package com.kh.duri.happymember.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.duri.happymember.model.exception.MypageException;
import com.kh.duri.happymember.model.vo.FundItemList;
import com.kh.duri.happymember.model.vo.MyDonateItems;
import com.kh.duri.member.model.vo.Member;

public interface HappymemberDao {

	//마이페이지 - 보유물품 목록 불러오기 ajax
	//보유물품 리스트
	ArrayList<MyDonateItems> donateItemList(SqlSessionTemplate sqlSession, MyDonateItems mdi) throws MypageException;

	//후원물품 리스트
	ArrayList<FundItemList> fundItemList(SqlSessionTemplate sqlSession) throws MypageException;

	//배송받을 물품 선택 후 수량 변경하기
	int getDelivery(SqlSessionTemplate sqlSession, String[] itemNumArray, String[] itemAmountArray, String mno) throws MypageException;

	//배송현황 추가하기
	int insertDelivery(SqlSessionTemplate sqlSession, String address, String mno) throws MypageException;

	/*//배송현황 목록 조회(페이징)
	int getDeliveryListCount(SqlSessionTemplate sqlSession, Member m) throws MypageException;*/

	
	
}
