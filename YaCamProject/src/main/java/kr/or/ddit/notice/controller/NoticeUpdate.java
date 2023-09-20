package kr.or.ddit.notice.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.community.service.CommunityServiceImpl;
import kr.or.ddit.community.service.ICommunityService;
import kr.or.ddit.community.vo.CommunityVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeServiceImpl;
import kr.or.ddit.notice.vo.NoticeVO;

@WebServlet("/noticeUpdate.do")
@MultipartConfig
public class NoticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

        try {
            int notiNo = Integer.parseInt(request.getParameter("notiNo"));

            INoticeService service = NoticeServiceImpl.getInstance();
            NoticeVO vo = service.selectNo(notiNo);

            request.setAttribute("NotiVo", vo);
            request.getRequestDispatcher("/LHH/notice/NoticeUpdate.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // NotiNo가 올바르지 않은 형식이면 적절한 예외 처리를 수행한다.
            // 예를 들어, 에러 페이지로 이동하거나 다른 처리를 수행할 수 있다.
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid notiNo parameter");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        // POST방식으로 요청할 때는 수정한 회원 정보를 받아서 프로필 사진을 저장하고
        // DB의 회원 정보를 수정한다.

        // 사용자가 업로드한 파일이 저장될 서버쪽의 폴더 경로 설정
        String uploadPath = "d:/d_other/community/uploadFiles";

        // 저장될 폴더가 없으면 폴더를 만들어 준다.
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 세션에서 로그인된 사용자의 정보를 가져온다
        MemberVO loginUser = (MemberVO) request.getSession().getAttribute("userinfo");

        request.setCharacterEncoding("utf-8");

        // 회원 정보를 받는다.
        String memId = request.getParameter("id");
        String notiTtl = request.getParameter("ttl");
        String notiDtl = request.getParameter("dtl");
        int notiNo = Integer.parseInt(request.getParameter("noti_no"));

        // 받아온 회원 정보를 VO에 저장한다.
        NoticeVO vo = new NoticeVO();
        vo.setMem_id(loginUser.getMem_id());
        vo.setNoti_ttl(notiTtl);
        vo.setNoti_dtl(notiDtl);
        vo.setNoti_no(notiNo);
        
        // form의 file입력요소의 name값은 Part객체의 이름역할을 한다.
        // 이 이름을 이용하여 Part객체를 구한다.
        Part part = request.getPart("file");

        if (part != null) {
            String fileName = extractFilename(part);

            if (!"".equals(fileName)) {
                try {
                    part.write(uploadPath + File.separator + fileName);

                    vo.setNoti_file(fileName);  // DB에 저장할 파일명을 VO객체에 저장
                } catch (Exception e) {
                    vo.setNoti_file(null);  // 프로필 사진 저장 오류 처리
                }
            }
        }

        // 회원 정보를 DB에 update한다.
        INoticeService service = NoticeServiceImpl.getInstance();
        service.updateNotice(vo);

        // 작업이 완료되면 List 페이지로 이동
//        response.sendRedirect(request.getContextPath() + "/LHH/board.jsp");
        response.sendRedirect(request.getContextPath() + "/noticeInfo.do?num=" + vo.getNoti_no());
    }

    // Part 구조 안에서 파일명을 찾는 메서드
    private String extractFilename(Part part) {
        String fileName = "";

        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                // filename="test1.txt"
                fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }

        return fileName;
    }
}

