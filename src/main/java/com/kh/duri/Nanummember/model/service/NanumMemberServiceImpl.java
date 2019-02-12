package com.kh.duri.Nanummember.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.duri.Nanummember.model.dao.NanumMemberDao;
import com.kh.duri.Nanummember.model.exception.NanumException;
import com.kh.duri.Nanummember.model.vo.DirectFundHistory;
import com.kh.duri.Nanummember.model.vo.Funding;
import com.kh.duri.Nanummember.model.vo.Letter;
import com.kh.duri.Nanummember.model.vo.PageInfo;
import com.kh.duri.Nanummember.model.vo.SelectDirectFund;
import com.kh.duri.member.model.vo.Member;

@Service
public class NanumMemberServiceImpl implements NanumMemberService {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private NanumMemberDao nd;

	//금액 크라우드 펀딩 개수 조회 - 진행
	@Override
	public int getMoneyCloundListCount(Member m) throws NanumException {
		int listCount = nd.getPointListCount(sqlSession ,m);	
		return listCount;
	}
	//금액 크라우드 펀딩 조회 - 진행
	@Override
	public List<Funding> selectMoneyCloud(Member m, PageInfo pi) throws NanumException {
		List<Funding> fList = nd.selectMoneyCloud(sqlSession,m,pi);
		return fList;
	}
	//금액 크라우드 펀딩 개수 조회 - 종료
	@Override
	public int getEndMoneyCloundListCount2(Member m) throws NanumException {
		int listCount = nd.getPointListCount2(sqlSession ,m);	
		return listCount;
	}
	//금액 크라우드 펀딩 조회 - 종료
	@Override
	public List<Funding> selectMoneyCloud2(Member m, PageInfo pi2) throws NanumException {
		List<Funding> fList = nd.selectMoneyCloud2(sqlSession,m,pi2);
		return fList;
	}
	//정기후원 개수 조회 - 진행
	@Override
	public int getDirectFundListCount(Member m) throws NanumException {
		int listCount = nd.getDirectFundCount(sqlSession ,m);	
		return listCount;
	}	
	//정기후원 조회 - 진행
	@Override
	public List<SelectDirectFund> selectDirectFund(Member m, PageInfo pi) throws NanumException {
		List<SelectDirectFund> dfList = nd.selectDirectFund(sqlSession,m,pi);
		return dfList;
	}
	//정기후원 개수조회 - 종료
	@Override
	public int getDirectFundListCount2(Member m) throws NanumException {
		int listCount = nd.getDirectFundCount2(sqlSession ,m);	
		return listCount;
	}
	//정기후원 조회 - 종료
	@Override
	public List<SelectDirectFund> selectDirectFund2(Member m, PageInfo pi2) throws NanumException {
		List<SelectDirectFund> dfList = nd.selectDirectFund2(sqlSession,m,pi2);
		return dfList;
	}
	//정기 감사편지 개수 조회 - 진행
	@Override
	public int getDirectLetterListCount(Member m) throws NanumException {
		int listCount = nd.getDirectLetterListCount(sqlSession ,m);	
		return listCount;
	}
	//정기 감사편지 조회 - 진행
	@Override
	public List<Letter> selectDirectLetter(Member m, PageInfo pi) throws NanumException {
		List<Letter> dlList = nd.selectDirectLetter(sqlSession,m,pi);
		return dlList;
	}
	


}
