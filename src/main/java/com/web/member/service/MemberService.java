package com.web.member.service;

import java.sql.Connection;

import com.web.common.JDBCTemplate;
import com.web.member.model.dao.MemberDao;
import com.web.member.model.dto.Member;

public class MemberService {
	private final MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}
	
	public Member selectByAccount(String id, String pw) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = memberDao.selectByAccount(conn, id, pw);
		JDBCTemplate.close(conn);
		return member;
	}

	public int enrollMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = memberDao.enrollMember(conn, member);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}
