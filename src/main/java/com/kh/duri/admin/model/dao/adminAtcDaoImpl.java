package com.kh.duri.admin.model.dao;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.duri.admin.model.exception.ListException;
import com.kh.duri.admin.model.vo.adminFundingList;
import com.kh.duri.admin.model.vo.adminMember;
import com.kh.duri.admin.model.vo.adminQnA;

@Repository
public class adminAtcDaoImpl implements adminAtcDao{

	//크라우드 금액 펀딩 승인목록
	@Override
	public List<adminFundingList> adminMoneyCrowdList(SqlSessionTemplate sqlsession) throws ListException {
		return sqlsession.selectList("Admin.adminMoneyCrowdList");
	}
	//크라우드 물품 펀딩 승인목록
	@Override
	public List<adminFundingList> adminGoodsFundingList(SqlSessionTemplate sqlsession) throws ListException {
		return sqlsession.selectList("Admin.adminGoodsFundingList");
	}
	//크라우드 펀딩 상세페이지 -회원정보
	@Override
	public adminMember CrowdMemInfoDetail(SqlSessionTemplate sqlsession, adminMember m) throws ListException {
		return sqlsession.selectOne("Admin.CrowdMemInfoDetail",m);
	}
	//크라우드 펀딩 상세페이지 -펀딩정보(금액)
	@Override
	public adminFundingList CrowdFundInfoDetail(SqlSessionTemplate sqlsession, adminFundingList f) throws ListException {
		return sqlsession.selectOne("Admin.CrowdFundInfoDetail",f);
	}
	//크라우드 펀딩 상세페이지 -펀딩정보(물품)
	@Override
	public List<adminFundingList> CrowdFundGoodsInfo(SqlSessionTemplate sqlsession, adminFundingList f) throws ListException {
		return sqlsession.selectList("Admin.CrowdFundGoodsInfo",f);
	}
	//관리자 행복두리 Q&A 목록
	@Override
	public List<adminQnA> adminQnAList(SqlSessionTemplate sqlsession) throws ListException {
		return sqlsession.selectList("Admin.adminQnAList");
	}
	//관리자 행복두리 Q&A 상세보기
	@Override
	public adminQnA adminQnADetail(SqlSessionTemplate sqlsession, adminQnA q) throws ListException {
		return sqlsession.selectOne("Admin.adminQnADetail",q);
	}

	//관리자 나눔두리 Q&A 목록
	@Override
	public List<adminQnA> adminNanumQnAList(SqlSessionTemplate sqlsession) throws ListException {
		return sqlsession.selectList("Admin.adminNanumQnAList");
	}
	//관리자 나눔두리 Q&A 상세보기
	@Override
	public adminQnA adminNanumQnADetail(SqlSessionTemplate sqlsession, adminQnA q) throws ListException {
		return sqlsession.selectOne("Admin.adminNanumQnADetail",q);
	}
	//관리자 나눔두리 Q&A 댓글달기
	@Override
	public int insertReply(SqlSessionTemplate sqlsession, adminQnA q) throws ListException {
		return sqlsession.insert("Admin.adminNanumReply", q);
	}

	//Q&A 답변하기 버튼
	@Override
	public int adminAnswer(SqlSessionTemplate sqlsession, adminQnA q)  {
		int result = sqlsession.update("Admin.adminAnswer",q);
		return result;
	}
	//크라우드 펀딩 반려 버튼
	@Override
	public int adminCrowdDeny(SqlSessionTemplate sqlsession, adminFundingList af) {
		int result = sqlsession.update("Admin.adminCrowdDeny",af);
		return result;
		
	}
	//크라우드 펀딩 승인 버튼
	@Override
	public int adminCrowdApprove(SqlSessionTemplate sqlsession, adminFundingList af) {
		int result = sqlsession.update("Admin.adminCrowdApprove",af);
		return result;

	}
	
	
}
