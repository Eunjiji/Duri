package com.kh.duri.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.kh.duri.board.model.dao.boardDao;
import com.kh.duri.board.model.exception.BoardException;
import com.kh.duri.board.model.exception.DonateListException;
import com.kh.duri.board.model.vo.Board;
import com.kh.duri.board.model.vo.Board2;
import com.kh.duri.board.model.vo.BoardItem;
import com.kh.duri.member.model.exception.LoginException;
import com.kh.duri.member.model.vo.Member;
import com.kh.duri.board.model.vo.PageInfo;
import com.kh.duri.happymember.model.vo.Funding;

@Service
public class boardServiceImpl implements boardService {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private boardDao bd;


	//정기후원 대상자 명수 조회
	@Override
	public int getDonateListCount() throws DonateListException {
		int listCount = bd.getDonateListCount(sqlSession);
		
		return listCount;
	}

	//정기후원자 목록 조회
	@Override
	public List<Member> selectDonateList(PageInfo pi) throws DonateListException {
		List<Member> doList = bd.selectDonateList(sqlSession, pi);	//DAO로 Member 정보와 sqlSession 전송
		
		return doList;
	}

	//정기후원 상세페이지 조회
	@Override
	public Member longDanateDetail(Member m) {

		Member longDetail = bd.longDonateDetail(sqlSession, m);	//DAO로 Member 정보와 sqlSession 전송
		
		return longDetail;


	}

	//크라우드 펀딩 금액후원 갯수
	@Override
	public int getMoneyListCount() {
		int listCount = bd.getMoneyListCount(sqlSession);
		
		return listCount;
	}

	
	//크라우드 펀딩 금액후원 목록 조회
	@Override
	public List<Board> selectMoneyList(PageInfo pi) throws DonateListException {
		List<Board> moList = bd.selectMoneyList(sqlSession, pi);	//DAO로 Member 정보와 sqlSession 전송
		
		return moList;
	}

	@Override
	public Board moneyDetailOne(Board b) throws LoginException {
		Board moneyDetail = bd.moneyDetail(sqlSession, b);	//DAO로 Member 정보와 sqlSession 전송
		
		return moneyDetail ;
	}

	@Override
	public int getThingListCount() throws DonateListException {
		int listCount = bd.getThingListCount(sqlSession);
		
		return listCount;
	}

	@Override
	public List<BoardItem> selectThingList(PageInfo pi) {
		List<BoardItem> thList = bd.selectThingList(sqlSession, pi);	//DAO로 Member 정보와 sqlSession 전송
		
		return thList;
	}

	@Override
	public List<BoardItem> selectThingList2(PageInfo pi) {
		List<BoardItem> thList2 = bd.selectThingList2(sqlSession, pi);	//DAO로 Member 정보와 sqlSession 전송
		
		return thList2;
	}

	@Override
	public BoardItem thingDetailOne(BoardItem bi) {
		BoardItem thingDetail = bd.thingDetailOne(sqlSession, bi);	//DAO로 Member 정보와 sqlSession 전송
		
		return thingDetail ;
	}

	@Override
	public List<BoardItem> thingDetailOne2(BoardItem bi) {
		List<BoardItem> thingDetail2 = bd.thingDetailOne2(sqlSession, bi);	//DAO로 Member 정보와 sqlSession 전송
		
		return thingDetail2 ;
	}

	@Override
	public int insertCloud(Board b) throws BoardException {
		
		int result = bd.insertCloud(sqlSession,b);
		
		return result;	
	}

	@Override
	public int insertItem(Board b) throws BoardException {
		int result = bd.insertCloud2(sqlSession,b);
		
		return result;	
	}

	@Override
	public int moneyCountOne(Board moneyDetail) {
		int moneyDetail2 = bd.moneyCountDetail(sqlSession, moneyDetail);	//DAO로 Member 정보와 sqlSession 전송
		
		return moneyDetail2 ;
	}

	@Override
	public int selectTotalMoney(Member m) {
		int listCount = bd.selectTotalMoney(sqlSession,m);
		
		return listCount;
	}

	@Override
	public int selectTotalCount(Member m) {
		int listCount = bd.selectTotalCount(sqlSession,m);
		
		return listCount;
	}

	
	@Override
	public int moneyCountTwo(Board moneyDetail) {
		int listCount = bd.moneyCountTwo(sqlSession,moneyDetail);
		
		return listCount;
	}

	
	//회원번호 받아오기
	@Override
	public int selectMno(String nick) {
		int listCount = bd.selectMno(sqlSession,nick);
		
		return listCount;
	}

	@Override
	public int insertWish(Board2 b) throws BoardException {
		int result = bd.insertWish(sqlSession,b);
		
		return result;	
	}

	@Override
	public int insertWish2(Board2 b) throws BoardException {
		int result = bd.insertWish2(sqlSession,b);
		
		return result;	
	}

	@Override
	public int insertWish3(Board2 b) throws BoardException {
		int result = bd.insertWish3(sqlSession,b);
		
		return result;
	}

	@Override
	public Board2 thingDetailOne3(BoardItem bi) throws BoardException {
		Board2 thingDetail3 = bd.thingDetail3(sqlSession, bi);	//DAO로 Member 정보와 sqlSession 전송
		
		return thingDetail3;
	}

	@Override
	public List<Board2> selectPercent(PageInfo pi) throws BoardException {
		List<Board2> list = bd.selectPercent(sqlSession,pi);
		
		return list;
	}

	@Override
	public List<Board2> selectNowList() {
		List<Board2> list = bd.selectNowList(sqlSession);
		
		return list;
	}

	@Override
	public List<Board2> moneyCountThr(Board moneyDetail) {
		List<Board2> list = bd.moneyCountThr(sqlSession,moneyDetail);
		
		return list;
	}

	@Override
	public List<Board2> moneyCountThr(BoardItem thingDetail) {
		List<Board2> list = bd.moneyCountThr(sqlSession,thingDetail);
		
		return list;
	}

	@Override
	public List<Board2> moneyCountThr(Member longDetail) {
		List<Board2> list = bd.moneyCountThr(sqlSession,longDetail);
		
		return list;
	}

	@Override
	public List<Funding> selectItemDonateList() {
		List<Funding> list = bd.selectItemDonateList(sqlSession);
		
		return list;
	}












}
