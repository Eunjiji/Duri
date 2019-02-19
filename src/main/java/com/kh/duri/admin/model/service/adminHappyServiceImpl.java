package com.kh.duri.admin.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.duri.admin.model.dao.adminHappyDao;
import com.kh.duri.admin.model.exception.ListException;
import com.kh.duri.admin.model.vo.RefundList;
import com.kh.duri.admin.model.vo.adminMember;

@Service
public class adminHappyServiceImpl implements adminHappyService{

	@Autowired
	private SqlSessionTemplate sqlsession;
	@Autowired
	private adminHappyDao ahd;
	
	//행복두리 전체 목록
	@Override
	public List<adminMember> adminHappyList() throws ListException {
		
		List<adminMember> list = ahd.adminHappyList(sqlsession);
		
		return list;
	}

	//행복두리 [기존회원(1)/신규회원(3)] 상세페이지 (공통페이지)
	@Override
	public adminMember HappyDetail(adminMember m) throws ListException {
		
		adminMember HappyDetail = ahd.HappyDetail(sqlsession, m);
		
		return HappyDetail;
	}
	//행복두리 승인목록 -신규리스트
	@Override
	public List<adminMember> adminHappyNewList() throws ListException {
		
		List<adminMember> list = ahd.adminHappyNewList(sqlsession);
		
		return list;
	}

	//행복두리 환급목록 조회
	
}
