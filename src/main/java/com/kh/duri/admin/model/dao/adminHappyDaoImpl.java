package com.kh.duri.admin.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.duri.admin.model.exception.ListException;
import com.kh.duri.admin.model.vo.Notice;
import com.kh.duri.admin.model.vo.RefundList;
import com.kh.duri.admin.model.vo.adminDirectList;
import com.kh.duri.admin.model.vo.adminFundingHistoryList;
import com.kh.duri.admin.model.vo.adminMember;

@Repository
public class adminHappyDaoImpl implements adminHappyDao{

	//행복두리 전체조회
	@Override
	public List<adminMember> adminHappyList(SqlSessionTemplate sqlsession) throws ListException{
		
		return sqlsession.selectList("Admin.adminHappyList");
	}
	//행복두리 [기존회원(1)/신규회원(3)] 상세페이지 (공통페이지)
	@Override
	public adminMember HappyDetail(SqlSessionTemplate sqlsession, adminMember m) throws ListException {

		
		return sqlsession.selectOne("Admin.adminHappyDetail",m);
	}
	//행복두리 승인 상세 페이지[자개소개 갱신/증빙성 갱신/증빙서 비갱신]
	@Override
	public List<adminMember> HappyAccDetail(SqlSessionTemplate sqlsession, adminMember m) throws ListException {
		
		return sqlsession.selectList("Admin.adminHappyAccDetail",m);
	}
		
		
	//행복두리 상세보기 - 정기후원 내역
	@Override
	public List<adminDirectList> HappydirectList(SqlSessionTemplate sqlsession, adminDirectList ad)throws ListException {
		
		return sqlsession.selectList("Admin.adminHappydirectList",ad);
	}
	//행복두리 상세보기 - 크라우드 금액 펀딩 내역
	@Override
	public List<adminFundingHistoryList> HappyfundingMoneyList(SqlSessionTemplate sqlsession,adminFundingHistoryList ahf) throws ListException {
		
		return sqlsession.selectList("Admin.adminHappyfundingMoneyList",ahf);
	}
	//행복두리 상세보기 - 크라우드 물품 펀딩 내역
	@Override
	public List<adminFundingHistoryList> HappyfundingGoodsList(SqlSessionTemplate sqlsession,adminFundingHistoryList ahf) throws ListException {
		
		return sqlsession.selectList("Admin.adminHappyfundingGoodsList",ahf);
	}
	//행복두리 승인목록 -신규리스트
	@Override
	public List<adminMember> adminHappyNewList(SqlSessionTemplate sqlsession) throws ListException {
		
		return sqlsession.selectList("Admin.adminHappyNewList");
	}
	//행복두리 승인목록 -자기소개 갱신 목록
	@Override
	public List<adminMember> adminHappyMprList(SqlSessionTemplate sqlsession) throws ListException {

		return sqlsession.selectList("Admin.adminHappyMprList");
	}
	//행복두리 승인목록 -증빙서류 갱신 목록
	@Override
	public List<adminMember> adminHappyAttachList(SqlSessionTemplate sqlsession) throws ListException {

		return sqlsession.selectList("Admin.adminHappyAttachList");
	}
	//행복두리 승인목록 -증빙서류 비갱신 목록
	@Override
	public List<adminMember> adminHappyNotAttachList(SqlSessionTemplate sqlsession) throws ListException {

		return sqlsession.selectList("Admin.adminHappyNotAttachList");
	}
	
	//신규행복두리 승인버튼
	@Override
	public int adminNewHappyMemberAgree(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminNewHappyMemberAgree",m);
		return result;
	}
	@Override
	public int adminNewHappyAttachAgree(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminNewHappyAttachAgree",m);
		return result;
	}
	//자기소개 갱신 행복두리 승인버튼
	@Override
	public int adminMprHappyAgree(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminMprHappyAgree",m);
		return result;
	}
	//증빙서류 갱신 회원 승인 버튼
	@Override
	public int adminAttachHappyMemberAgree(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminAttachHappyMemberAgree",m);
		return result;
	}
	@Override
	public int adminAttachHappyAttach1Agree(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminAttachHappyAttach1Agree",m);
		return result;
	}
	@Override
	public int adminAttachHappyAttach2Agree(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminAttachHappyAttach2Agree",m);
		return result;
	}
	//신규행복두리 반려버튼
	@Override
	public int adminNewHappyMemberRefuse(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminNewHappyMemberRefuse",m);
		return result;
	}
	@Override
	public int adminNewHappyAttachRefuse(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminNewHappyAttachRefuse",m);
		return result;
	}
	
	//자기소개 갱신 행복두리 반려버튼
	@Override
	public int adminMprHappyRefuse(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminMprHappyRefuse",m);
		return result;
	}
	//증빙서류 갱신 회원 반려 버튼
	@Override
	public int adminAttachRefuse(SqlSessionTemplate sqlsession, adminMember m) {
		int result = sqlsession.update("Admin.adminAttachRefuse",m);
		return result;
	}
	

	
	//반려사유(신규+증빙)
	@Override
	public int adminHappyNoticeRefuse(SqlSessionTemplate sqlsession, Notice n) {
		int result = sqlsession.insert("Admin.adminHappyNoticeRefuse",n);
		return result;
	}
	//반려사유(자기소개)
	@Override
	public int adminMprHappyRefuseMsg(SqlSessionTemplate sqlsession, Notice n) {
		int result = sqlsession.insert("Admin.adminHappyNoticeRefuse",n);
		return result;
	}
}
