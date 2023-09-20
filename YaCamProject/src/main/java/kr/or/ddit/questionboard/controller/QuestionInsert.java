package kr.or.ddit.questionboard.controller;

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
import kr.or.ddit.questionboard.service.IQuestionService;
import kr.or.ddit.questionboard.service.QuestionServiceImpl;
import kr.or.ddit.questionboard.vo.QuestionVO;

@WebServlet("/questionInsert.do")
@MultipartConfig
public class QuestionInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        // 사용자가 업로드한 파일이 저장될 서버쪽의 폴더 경로 설정
        String uploadPath = "d:/d_other/community/uploadFiles";

        // 저장될 폴더가 없으면 폴더를 만들어 준다.
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 세션에서 로그인된 사용자의 정보를 가져온다
        MemberVO loginUser = (MemberVO) request.getSession().getAttribute("userinfo");

        // 전송시 데이터 받기
        String queType = request.getParameter("type");
        String memId = request.getParameter("id");
        String queTtl = request.getParameter("ttl");
        String queDtl = request.getParameter("dtl");

        // vo에 저장
        QuestionVO vo = new QuestionVO();
        vo.setQue_typ(queType);
        vo.setMem_id(loginUser.getMem_id());
        vo.setQue_ttl(queTtl);
        vo.setQue_dtl(queDtl);

        // form의 file입력요소의 name값은 Part객체의 이름역할을 한다.
        // 이 이름을 이용하여 Part객체를 구한다.
        Part part = request.getPart("file");

        if (part != null) {
            String fileName = extractFilename(part);

            if (!"".equals(fileName)) {
                try {
                    part.write(uploadPath + File.separator + fileName);

                    vo.setQue_file(fileName); // DB에 저장할 파일명을 VO객체에 저장
                } catch (Exception e) {
                    vo.setQue_file(null); // 프로필 사진이 오류가 생기면...
                }
            }
        }

        // service 객체 얻기
        IQuestionService service = QuestionServiceImpl.getInstance();

        // service 메소드 호출하기 - 결과값 받기
        int res = service.insertQeustion(vo);
        // 결과값을 request에 저장하기
        request.setAttribute("insert", res);

        response.sendRedirect(request.getContextPath() + "/LHH/question/question.jsp");
    }

    // Part구조 안에서 파일명을 찾는 메서드
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
