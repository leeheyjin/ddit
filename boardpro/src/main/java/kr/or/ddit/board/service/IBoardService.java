package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService extends IBoardDao{
	//	페이지별 리스트 가져오기
	public List<BoardVO> listPerPage(Map<String, Object> map);
	
	//	조건에 따라 전체 글 개수 구하기
	public int totalCount(Map<String, Object> map);
	
	//	조회수 증가하기
	public int updateHit(int num);
	
	//	글쓰기 -> 저장하기
	public int insertBoard(BoardVO vo);
	
	//	글 수정하기
	public int updateBoard(BoardVO vo);
	
	//	글 삭제하기
	public int deleteBoard(int num);
	
	//	페이지 정보 얻기 -> 시작번호, 끝번호, 시작페이지번호, 끝페이지 번호, 총페이지갯수
	public PageVO pageInfo(int page, String stype, String sword);
	
	//	댓글 쓰기(저장하기): 글 번호 기준
	public int insertReply(ReplyVO vo);
	
	//	댓글 수정: 댓글 번호 기준
	public int updateReply(ReplyVO vo);
	
	//	댓글 삭제: 댓글 번호 기준
	public int deleteReply(int renum);
	
	//	댓글리스트: 글 번호 기준
	public List<ReplyVO> listReply(int bonum);
}
