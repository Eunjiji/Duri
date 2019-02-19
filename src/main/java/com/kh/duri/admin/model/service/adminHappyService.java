package com.kh.duri.admin.model.service;

import java.util.List;

import com.kh.duri.admin.model.exception.ListException;
import com.kh.duri.admin.model.vo.RefundList;
import com.kh.duri.admin.model.vo.adminMember;

public interface adminHappyService {

	//행복두리 전체목록
	List<adminMember> adminHappyList() throws ListException;
	//행복두리 [기존회원(1)/신규회원(3)] 상세페이지 (공통페이지)
	adminMember HappyDetail(adminMember m)throws ListException;
	//행복두리 승인목록 -신규리스트
	List<adminMember> adminHappyNewList()throws ListException;
	//행복두리 환급목록
}
