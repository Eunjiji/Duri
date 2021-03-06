package com.kh.duri.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.duri.board.model.exception.BoardException;
import com.kh.duri.board.model.exception.DonateListException;
import com.kh.duri.board.model.vo.Board;
import com.kh.duri.board.model.vo.Board2;
import com.kh.duri.board.model.vo.BoardItem;
import com.kh.duri.member.model.exception.LoginException;
import com.kh.duri.member.model.vo.Member;
import com.kh.duri.board.model.vo.PageInfo;
import com.kh.duri.happymember.model.vo.Funding;

@Repository
public class boardDaoImpl implements boardDao {

	// 정기후원 리스트 갯수 조회
	@Override
	public int getDonateListCount(SqlSessionTemplate sqlSession) throws DonateListException {
		int listCount = sqlSession.selectOne("Member.selectDonateListCount");

		System.out.println("모든 회원 명수 : " + listCount);

		if (listCount < 0) {
			throw new DonateListException("모든 회원 명수 조회 실패");
		}

		return listCount;
	}

	// 정기후원 목록회원 리스트 조회
	@Override
	public List<Member> selectDonateList(SqlSessionTemplate sqlSession, PageInfo pi) throws DonateListException {
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());

		List<Member> bh = sqlSession.selectList("Member.selectDonateList", null,rowBounds);

		if (bh == null) {
			throw new DonateListException("정기후원 명단을 불러올수 없습니다.");
		}

		/*System.out.println("Dao Point 객체 : " + bh.size());
		System.out.println("Dao Point 총객체 : " + bh);*/
		return bh;
	}

	// 정기후원 상세정보 조회
	@Override
	public Member longDonateDetail(SqlSessionTemplate sqlSession, Member m) {
		Member longDetail = sqlSession.selectOne("Member.selectLongList", m); // 받아온 m을 이용해 mapper에서 sql문 실행해서 받아온 값 저장

		/*System.out.println("Dao Member : " + longDetail);*/

		/*
		 * if(longDetail ==null) { throw new LoginException("로그인정보가 존재하지 않습니다."); //예외처리
		 * }
		 */

		return longDetail;
	}

	// 금액후원 게시글 갯수 조회
	@Override
	public int getMoneyListCount(SqlSessionTemplate sqlSession) {
		int listCount = sqlSession.selectOne("Boards.selectMoneyListCount");

		System.out.println("모든 게시글 개수 : " + listCount);
		/*
		 * if(listCount < 0) { throw new DonateListException("모든 회원 명수 조회 실패"); }
		 */

		return listCount;
	}

	// 금액후원 게시글 리스트 조회
	@Override
	public List<Board> selectMoneyList(SqlSessionTemplate sqlSession, PageInfo pi) throws DonateListException {
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());

		List<Board> bh = sqlSession.selectList("Boards.selectMoneyList", null,rowBounds);
		/*List<Board> bc= sqlSession.selectList("Boards.selectFundMoneyCulValue2",null,rowBounds);*/


		if (bh == null) {
			throw new DonateListException("금액후원 명단을 불러올수 없습니다.");
		}

		/*System.out.println("Dao Point 객체 : " + bh.size());
		System.out.println("Dao Point 총객체 : " + bh);
		System.out.println("Dao Point 총객체 : " + bc);*/

		return bh;
	}

	// 금액후원 상세정보 조회
	@Override
	public Board moneyDetail(SqlSessionTemplate sqlSession, Board b) throws LoginException {
		Board moneyDetail = sqlSession.selectOne("Boards.selectMoneyDetail", b); // 받아온 m을 이용해 mapper에서 sql문 실행해서 받아온 값
																					// 저장

		System.out.println("Dao Member : " + moneyDetail);

		
		 if(moneyDetail ==null) { throw new LoginException("로그인정보가 존재하지 않습니다."); //예외처리
		  }
		 

		return moneyDetail;
	}

	// 물품후원 갯수 조회
	@Override
	public int getThingListCount(SqlSessionTemplate sqlSession) throws DonateListException {
		int listCount = sqlSession.selectOne("Boards.selectThingListCount");

		System.out.println("모든 물품게시글 개수 : " + listCount);
		if (listCount < 0) {
			throw new DonateListException("모든 물품후원게시글 갯수 조회 실패");
		}

		return listCount;
	}

	// 물품후원 리스트 조회
	@Override
	public List<BoardItem> selectThingList(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());

		List<BoardItem> th = sqlSession.selectList("Boards.selectThingList", null,rowBounds);

		/*
		 * if(th == null){ throw new DonateListException("금액후원 명단을 불러올수 없습니다."); }
		 */

		/*System.out.println("Dao Point 객체 : " + th.size());
		System.out.println("Dao Point 총객체 : " + th);*/
		return th;
	}

	@Override
	public List<BoardItem> selectThingList2(SqlSessionTemplate sqlSession, PageInfo pi) {
		//int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		//RowBounds rowBounds = new RowBounds(offset, pi.getLimit());

		//List<BoardItem> th2 = sqlSession.selectList("Boards.selectThingList2", null,rowBounds);
		List<BoardItem> th2 = sqlSession.selectList("Boards.selectThingList2");
		
		/*
		 * if(th == null){ throw new DonateListException("금액후원 명단을 불러올수 없습니다."); }
		 */

		/*System.out.println("Dao Point 객체 : " + th2.size());
		System.out.println("Dao Point 총객체 : " + th2);*/
		return th2;
	}

	@Override
	public BoardItem thingDetailOne(SqlSessionTemplate sqlSession, BoardItem bi) {
		BoardItem thingDetail = sqlSession.selectOne("Boards.selectThingDetail", bi); // 받아온 m을 이용해 mapper에서 sql문 실행해서
																						// 받아온 값 저장

		/*System.out.println("Dao thingDetail : " + thingDetail);*/

		/*
		 * if(longDetail ==null) { throw new LoginException("로그인정보가 존재하지 않습니다."); //예외처리
		 * }
		 */

		return thingDetail;
	}

	@Override
	public List<BoardItem> thingDetailOne2(SqlSessionTemplate sqlSession, BoardItem bi) {
		List<BoardItem> thingDetail2 = sqlSession.selectList("Boards.selectThingDetail2", bi); // 받아온 m을 이용해 mapper에서
																								// sql문 실행해서 받아온 값 저장

		/*System.out.println("Dao thingDetail2 : " + thingDetail2);*/

		/*
		 * if(longDetail ==null) { throw new LoginException("로그인정보가 존재하지 않습니다."); //예외처리
		 * }
		 */

		return thingDetail2;
	}

	// 크라우드펀딩 작성 (행복두리)
	@Override
	public int insertCloud(SqlSessionTemplate sqlSession, Board b) throws BoardException {
		int result = sqlSession.insert("Boards.insertCloud", b);

		System.out.println("크라우드펀딩 작성 성공여부 : " + result);
		
		
		 if(result == 0) { 
			 throw new BoardException("작성실패!"); //예외처리
		 
		 }
		 

		return result;
	}
	
	
	//크라우드 펀딩 물품 작성
	@Override
	public int insertCloud2(SqlSessionTemplate sqlSession, Board b) throws BoardException {
		int result = 0;	//전체 삽입 결과 확인
		int insertCloud = sqlSession.insert("Boards.insertCloud2", b);
		int fd_fno = sqlSession.selectOne("Boards.selectFnoCurrval");
		int insertItem1 = 0;
		int insertItem2 = 0;
		int insertItem3 = 0;
		
		System.out.println(fd_fno);
		b.setFd_fno(fd_fno);
		if(b.getFdValue() != 0 && b.getFd_ino() != 0 && b.getFdValue2() != 0 && b.getFd_ino2() != 0 && b.getFdValue3() != 0 &&b.getFd_ino3() != 0) {
			insertItem1 = sqlSession.insert("Boards.insertItem1", b);	//첫번째 물품 목록 삽입
			insertItem2 = sqlSession.insert("Boards.insertItem2", b);	//두번째 물품 목록 삽입
			insertItem3 = sqlSession.insert("Boards.insertItem3", b);	//세번째 물품 목록 삽입
			
			if(insertItem1 > 0 && insertItem2 > 0 && insertItem3 > 0) {
				result = 1;
			}
			
		}else if(b.getFdValue() != 0 && b.getFd_ino() != 0 && b.getFdValue2() != 0 && b.getFd_ino2() != 0 && b.getFdValue3() == 0 &&b.getFd_ino3() == 0) {
			insertItem1 = sqlSession.insert("Boards.insertItem1", b);	//첫번째 물품 목록 삽입
			insertItem2 = sqlSession.insert("Boards.insertItem2", b);	//두번째 물품 목록 삽입
			
			if(insertItem1 > 0 && insertItem2 > 0) {
				result = 1;
			}
			
		}else if(b.getFdValue() != 0 && b.getFd_ino() != 0 && b.getFdValue2() == 0 && b.getFd_ino2() == 0 && b.getFdValue3() != 0 &&b.getFd_ino3() != 0) {
			insertItem1 = sqlSession.insert("Boards.insertItem1", b);
			insertItem3 = sqlSession.insert("Boards.insertItem3", b);	//세번째 물품 목록 삽입
		
			if(insertItem1 > 0 && insertItem3 > 0) {
				result = 1;
			}
		
		}else if(b.getFdValue() == 0 && b.getFd_ino() == 0 && b.getFdValue2() != 0 && b.getFd_ino2() != 0 && b.getFdValue3() != 0 &&b.getFd_ino3() != 0) {
			insertItem2 = sqlSession.insert("Boards.insertItem2", b);	//두번째 물품 목록 삽입
			insertItem3 = sqlSession.insert("Boards.insertItem3", b);	//세번째 물품 목록 삽입
		
			if(insertItem2 > 0 && insertItem3 > 0) {
				result = 1;
			}
		
		}else if(b.getFdValue() == 0 && b.getFd_ino() == 0 && b.getFdValue2() == 0 && b.getFd_ino2() == 0 && b.getFdValue3() != 0 &&b.getFd_ino3() != 0) {
	
			insertItem3 = sqlSession.insert("Boards.insertItem3", b);	//세번째 물품 목록 삽입
		
			if(insertItem3 > 0) {
				result = 1;
			}
		
		}else if(b.getFdValue() != 0 && b.getFd_ino() != 0 && b.getFdValue2() == 0 && b.getFd_ino2() == 0 && b.getFdValue3() == 0 &&b.getFd_ino3() == 0) {
	
			insertItem1 = sqlSession.insert("Boards.insertItem1", b);	//첫번째 물품 목록 삽입
		
			if(insertItem1 > 0) {
				result = 1;
			}
			
			
		}else if(b.getFdValue() == 0 && b.getFd_ino() == 0 && b.getFdValue2() != 0 && b.getFd_ino2() != 0 && b.getFdValue3() == 0 &&b.getFd_ino3() == 0) {
	
			insertItem2 = sqlSession.insert("Boards.insertItem2", b);	//두번째 물품 목록 삽입
		
			if(insertItem2 > 0) {
				result = 1;
			}
		}
		
		


		
		return result;
	}

	//금액후원 작성
	@Override
	public int moneyCountDetail(SqlSessionTemplate sqlSession, Board moneyDetail) {

		System.out.println("모든 moneyDetail 개수 : " + moneyDetail);
		Integer listCount = sqlSession.selectOne("Point.selectFundMoneyCulValue",moneyDetail);
		
		if(listCount == null) {
			listCount = 0;
		}

		System.out.println("모든 후원금액 개수 : " + listCount);

		
		return listCount;
	}

	
	//모든 후원금액 조회
	@Override
	public int selectTotalMoney(SqlSessionTemplate sqlSession, Member m) {
		System.out.println(m);
		int listCount = sqlSession.selectOne("Boards.selectTotalMoney",m);

		System.out.println("모든 후원금액 개수 : " + listCount);
	

		return listCount;
	}

	
	//몇명이 후원하는가
	@Override
	public int selectTotalCount(SqlSessionTemplate sqlSession, Member m) {
		int listCount = sqlSession.selectOne("Boards.selectTotalCount",m);
	

		System.out.println("모든 후원금액 개수 : " + listCount);
		

		return listCount;
	}

	@Override
	public int moneyCountTwo(SqlSessionTemplate sqlSession, Board moneyDetail) {
		int listCount = sqlSession.selectOne("Boards.selectMoneyCount",moneyDetail);
		

		System.out.println("모든 후원금액 개수 : " + listCount);
		

		return listCount;
	}

	@Override
	public int selectMno(SqlSessionTemplate sqlSession, String nick) {
		int listCount = sqlSession.selectOne("Boards.selectMno",nick);
		

		System.out.println("모든 후원금액 개수 : " + listCount);
		

		return listCount;
	}

	@Override
	public int insertWish(SqlSessionTemplate sqlSession, Board2 b) throws BoardException {
		int result = sqlSession.insert("Boards.insertWish", b);

		System.out.println("찜하기 작성 성공여부 : " + result);
		
		
		 if(result == 0) { 
			 throw new BoardException("작성실패!"); //예외처리
		 
		 }
		 

		return result;
	}

	@Override
	public int insertWish2(SqlSessionTemplate sqlSession, Board2 b) throws BoardException {
		int result = sqlSession.insert("Boards.insertWish2", b);

		System.out.println("찜하기 작성2 성공여부 : " + result);
		
		
		 if(result == 0) { 
			 throw new BoardException("작성실패!"); //예외처리
		 
		 }
		 

		return result;
	}

	@Override
	public int insertWish3(SqlSessionTemplate sqlSession, Board2 b) throws BoardException {
		int result = sqlSession.insert("Boards.insertWish3", b);

		System.out.println("찜하기 작성3 성공여부 : " + result);
		
		
		 if(result == 0) { 
			 throw new BoardException("작성실패!"); //예외처리
		 
		 }
		 

		return result;
	}

	@Override
	public Board2 thingDetail3(SqlSessionTemplate sqlSession, BoardItem bi) throws BoardException {
		Board2 result = sqlSession.selectOne("Boards.selectThing", bi);
		
		
		System.out.println("찜하기 작성3 성공여부 : " + result);
		
		
		 if(result == null) {
		
			 /*throw new BoardException("작성실패!"); //예외처리
*/		 
		 }
		 

		return result;
	}

	@Override
	public List<Board2> selectPercent(SqlSessionTemplate sqlSession, PageInfo pi) {
		List<Board2> thingDetail2 = sqlSession.selectList("Boards.selectPercent"); // 받아온 m을 이용해 mapper에서
		// sql문 실행해서 받아온 값 저장

		/*System.out.println("Dao thingDetail2 : " + thingDetail2);*/
		
		/*
		* if(longDetail ==null) { throw new LoginException("로그인정보가 존재하지 않습니다."); //예외처리
		* }
		*/

		return thingDetail2;
	}

	@Override
	public List<Board2> selectNowList(SqlSessionTemplate sqlSession) {
		List<Board2> thingDetail2 = sqlSession.selectList("Boards.selectNow"); // 받아온 m을 이용해 mapper에서
		// sql문 실행해서 받아온 값 저장

		/*System.out.println("Dao thingDetail2 : " + thingDetail2);*/
		
		/*
		* if(longDetail ==null) { throw new LoginException("로그인정보가 존재하지 않습니다."); //예외처리
		* }
		*/

		return thingDetail2;
	}

	@Override
	public List<Board2> moneyCountThr(SqlSessionTemplate sqlSession, Board moneyDetail) {
		List<Board2> thingDetail2 = sqlSession.selectList("Boards.moneyCountThr",moneyDetail); // 받아온 m을 이용해 mapper에서
		// sql문 실행해서 받아온 값 저장

		System.out.println("Dao thingDetail2 : " + thingDetail2);
		
	

		return thingDetail2;
	}

	@Override
	public List<Board2> moneyCountThr(SqlSessionTemplate sqlSession, BoardItem thingDetail) {
		List<Board2> thingDetail2 = sqlSession.selectList("Boards.moneyCountThr",thingDetail); // 받아온 m을 이용해 mapper에서
		// sql문 실행해서 받아온 값 저장

		System.out.println("Dao thingDetail2 : " + thingDetail2);
		
	

		return thingDetail2;
	}

	@Override
	public List<Board2> moneyCountThr(SqlSessionTemplate sqlSession, Member longDetail) {
		List<Board2> thingDetail2 = sqlSession.selectList("Boards.moneyCountThr2",longDetail); // 받아온 m을 이용해 mapper에서
		// sql문 실행해서 받아온 값 저장

		System.out.println("Dao thingDetail2 : " + thingDetail2);
		
	

		return thingDetail2;
	}

	@Override
	public List<Funding> selectItemDonateList(SqlSessionTemplate sqlSession) {
		List<Funding> thingDetail2 = sqlSession.selectList("HappyMember.selectItemDonateListBoard"); // 받아온 m을 이용해 mapper에서
		// sql문 실행해서 받아온 값 저장

		System.out.println("Dao thingDetail2 : " + thingDetail2);
		
	

		return thingDetail2;
	}




}
