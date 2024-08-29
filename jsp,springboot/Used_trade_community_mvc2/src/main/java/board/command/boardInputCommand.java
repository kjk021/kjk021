package board.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.boardDAO;
import board.dto.boardDTO;

public class boardInputCommand implements boardCommand {
	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");

            // 폼 데이터 가져오기
            String writer = request.getParameter("writer");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String content = request.getParameter("content");
            String passwd = request.getParameter("passwd");
            String headline = request.getParameter("headline");

            // 답글을 다는 경우 부모글의 번호와 레벨 정보를 가져옴
            int num = Integer.parseInt(request.getParameter("num"));
            int ref = Integer.parseInt(request.getParameter("ref"));
            int re_step = Integer.parseInt(request.getParameter("re_step"));
            int re_level = Integer.parseInt(request.getParameter("re_level"));

            // DTO 객체에 저장
            boardDTO article = new boardDTO();
            article.setNum(num);
            article.setWriter(writer);
            article.setEmail(email);
            article.setSubject(subject);
            article.setContent(content);
            article.setPasswd(passwd);
            article.setRef(ref);
            article.setRe_step(re_step);
            article.setRe_level(re_level);
            article.setHeadline(headline);

            // 현재 시간을 가져와서 Timestamp 객체로 생성하여 설정
            Timestamp regDate = new Timestamp(System.currentTimeMillis());
            article.setReg_date(regDate); // 현재 시간 설정

            article.setIp(request.getRemoteAddr());

            boardDAO dao = boardDAO.getInstance();
            dao.insertArticle(article); // 데이터베이스에 삽입
            // 이후 DAO를 통해 저장 등의 작업을 진행
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 로직
        }
    }
}
