package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.mybatis.config.MyBatisSqlSessionFactory;

public class MemberDAO implements IMemberDAO {
	private static IMemberDAO dao;

	private MemberDAO() {
	}

	public static IMemberDAO getInstance() {
		if (dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}

	@Override
	public List<MemberVO> getAllMembers() {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		List<MemberVO> list = null;
		try {
			list = session.selectList("member.getAllMembers");
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public MemberVO getOneMember(String mem_id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		MemberVO vo = null;
		try {
			vo = session.selectOne("member.getOneMember", mem_id);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return vo;
	}

	@Override
	public int insertMember(MemberVO vo) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		int count = 0;
		try {
			count = session.insert("member.insertMember", vo);
		} finally {
			session.commit();
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

	@Override
	public int updateMember(String mem_id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		int count = 0;
		try {
			count = session.update("member.updateMember", mem_id);
		} finally {
			session.commit();
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

	@Override
	public int deleteMember(String mem_id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		int count = 0;
		try {
			count = session.delete("member.deleteMember", mem_id);
		} finally {
			session.commit();
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

}
